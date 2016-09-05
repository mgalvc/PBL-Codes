/*
Autor: Matheus Galv�o Correia
Componente Curricular: MI - Programa��o
Conclu�do em: 25/08/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum trecho de c�digo de outro colega
ou de outro autor, tais como provindos de livros, apostilas e p�ginas ou documentos eletr�nicos da Internet. Qualquer
trecho de c�digo de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte do c�digo, e
estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
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
		assertEquals("Usu�rio logado com sucesso",cliente.login("Thiago"));
		cliente.logoff();
	}
	
	@Test
	public void testLoginNickJaLogado() throws IOException, InterruptedException, ClassNotFoundException{
		cliente.login("Thiago");
		assertEquals("J� existe usu�rio com este nick", cliente2.login("Thiago"));
		cliente.logoff();
	}
	
	@Test
	public void testEnviaMensagemUsuarioDesconectado() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		assertEquals("Usu�rio desconectado. Mensagem n�o enviada", cliente.enviaMensagem("Ol�", "somebody"));
		cliente.logoff();
	}
	
	@Test
	public void testEnviaMensagemUsuarioConectado() throws ClassNotFoundException, IOException, InterruptedException {
		cliente.login("Matheus");
		cliente2.login("Felipe");
		assertEquals("Mensagem enviada com sucesso", cliente.enviaMensagem("Ol�", "Felipe"));
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
		
		cliente.enviaMensagem("Ol�", "Felipe");
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
		cliente.enviaMensagem("Ol�", "Matheus");
		
		String[] mensagens = cliente.recebeMensagens();
		
		assertEquals(1, mensagens.length);
		cliente.logoff();
	}
}
