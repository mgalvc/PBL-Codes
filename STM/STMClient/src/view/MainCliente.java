/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 25/08/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package view;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.*;
import util.Console;

public class MainCliente {
	private static Cliente cliente;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		cliente = new Cliente();
		boolean continua = true;
		
		while(continua) {	
			System.out.print("Login: ");
			String nick = Console.readString();
			
			String resultado = cliente.login(nick);
			System.out.println(resultado);
			
			if(resultado.equals("Usuário logado com sucesso")) {
				System.out.println("1 - Atualizar usuários"
								+ "\n2 - Enviar mensagem"
								+ "\n3 - Receber mensagens"
								+ "\n4 - Logoff"
								+ "\n5 - Sair");
				
				boolean logado = true;
	
				while(logado) {
					int menu = Console.readInt();
					
					switch(menu) {
						case 1:
							System.out.println("Usuários online:");
							String[] users = cliente.atualizaUsuarios();
							for(String user : users) {
								System.out.println(user);
							}
							
							break;
						case 2:
							System.out.print("Mensagem: ");
							String msg = Console.readString();
							System.out.print("To: ");
							String to = Console.readString();
							System.out.println(cliente.enviaMensagem(msg, to));
							break;
						case 3:
							String[] mensagens = cliente.recebeMensagens();
							
							if(mensagens.length == 0) {
								System.out.println("Todas as suas mensagens já foram lidas");
								break;
							}
							
							for(String mensagem : mensagens)
								System.out.println(mensagem);
							break;
						case 4:
							String[] msgs = cliente.recebeMensagens();
							
							for(String mensagem : msgs) {
								System.out.println(mensagem);
							}
							
							/*if(msgs != null) {
								for(String mensagem : msgs)
									System.out.println(mensagem);
							}*/
							cliente.logoff();
							logado = false;
							break;
						case 5:
							String[] recebidas = cliente.recebeMensagens();
							
							for(String mensagem : recebidas) {
								System.out.println(mensagem);
							}
							
							cliente.logoff();
							System.exit(0);
						default:
							System.out.println("Opção inválida");
							break;
								
					}
				}
			}
		}
			
	}

}
