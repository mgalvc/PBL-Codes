/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 25/08/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	private Cliente cliente2;
	
	@Before
	public void setUp() throws UnknownHostException, IOException {
		cliente  = new Cliente();
		cliente2  = new Cliente();
	}
	
	@Test
	public void testLoginSucesso() throws IOException, InterruptedException, ClassNotFoundException{
		assertEquals("Usuário logado com sucesso",cliente.login("Thiago"));
		cliente.logoff();
	}
	
	@Test
	public void testLoginNickJaLogado() throws IOException, InterruptedException, ClassNotFoundException{
		cliente.login("Thiago");
		assertEquals("Já existe usuário com este nick", cliente2.login("Thiago"));
		cliente.logoff();
	}
	
	@Test
	public void testEnviaMensagemUsuarioDesconectado() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		assertEquals("Usuário desconectado. Mensagem não enviada", cliente.enviaMensagem("Olá", "somebody"));
		cliente.logoff();
	}
	
	@Test
	public void testEnviaMensagemUsuarioConectado() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		cliente2.login("Felipe");
		assertEquals("Mensagem enviada com sucesso", cliente.enviaMensagem("Olá", "Felipe"));
		cliente.logoff();
		cliente2.logoff();
		
	}
	
	@Test
	public void testAtualizaListaUsuariosSucesso() throws IOException, InterruptedException, ClassNotFoundException{
		cliente.login("Thiago");
		String[] usuarios =  cliente.atualizaUsuarios();
		assertEquals(1, usuarios.length);
		assertEquals("Thiago",usuarios[0]);
		cliente.logoff();
	}
	
	@Test
	public void testAtualizaUsuariosListaOrdenada() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		cliente2.login("Jessica");
		
		String[] usuarios = cliente.atualizaUsuarios();
		String[] retornoEsperado = {"Jessica", "Matheus"};
		
		assertEquals(retornoEsperado[0], usuarios[0]);
		
		cliente.logoff();
		cliente2.logoff();
	}
	
	@Test
	public void testAtualizaListaUsuariosVazia() throws IOException, InterruptedException, ClassNotFoundException{
		String[] usuarios =  cliente.atualizaUsuarios();
		assertEquals(0, usuarios.length);
		cliente.logoff();
	}
	
	@Test
	public void testReceberMensagemSucesso() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		cliente2.login("Felipe");
		
		cliente.enviaMensagem("Olá", "Felipe");
		cliente.enviaMensagem("Tudo bem?", "Felipe");
		
		String[] retorno = cliente2.recebeMensagens();
		
		assertEquals(2, retorno.length);
		
		cliente.logoff();
		cliente2.logoff();
	}
	
	@Test
	public void testReceberMensagensFilaVazia() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		
		String[] mensagens = cliente.recebeMensagens();
		
		assertEquals(0, mensagens.length);
		cliente.logoff();
	}
	
	@Test
	public void testLogoffSemMensagensAReceber() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		
		String[] mensagens = cliente.recebeMensagens();
		
		assertEquals(0,  mensagens.length);
		cliente.logoff();
	}
	
	@Test
	public void testLogoffComMensagensAReceber() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		cliente.enviaMensagem("Olá", "Matheus");
		
		String[] mensagens = cliente.recebeMensagens();
		
		assertEquals(1, mensagens.length);
		cliente.logoff();
	}
}
