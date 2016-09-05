/*
Autor: Matheus Galv�o Correia
Componente Curricular: MI - Programa��o
Conclu�do em: 25/08/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum trecho de c�digo de outro colega
ou de outro autor, tais como provindos de livros, apostilas e p�ginas ou documentos eletr�nicos da Internet. Qualquer
trecho de c�digo de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte do c�digo, e
estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
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
			
			if(resultado.equals("Usu�rio logado com sucesso")) {
				System.out.println("1 - Atualizar usu�rios"
								+ "\n2 - Enviar mensagem"
								+ "\n3 - Receber mensagens"
								+ "\n4 - Logoff"
								+ "\n5 - Sair");
				
				boolean logado = true;
	
				while(logado) {
					int menu = Console.readInt();
					
					switch(menu) {
						case 1:
							System.out.println("Usu�rios online:");
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
								System.out.println("Todas as suas mensagens j� foram lidas");
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
							System.out.println("Op��o inv�lida");
							break;
								
					}
				}
			}
		}
			
	}

}
