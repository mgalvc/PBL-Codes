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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Mensagem;

public class Cliente {
	private Socket socket;
	private String nick;
	
	private ObjectOutputStream oo;
	private ObjectInputStream oi;
	
	public Cliente() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 3000);
		oo = new ObjectOutputStream(socket.getOutputStream());
		oi = new ObjectInputStream(socket.getInputStream());
	}
	
	public String login(String nick) throws IOException, InterruptedException, ClassNotFoundException{
		//mensagem a ser passada para o servidor
		Mensagem msg = new Mensagem();
		msg.setNick(nick);
		msg.setAction("LOGIN");
		
		this.nick = nick;
		
		//escreve a mensagem para o servidor
		oo.writeObject(msg);
		
		//recebe uma mensagem do servidor
		String resultado = (String) oi.readObject();
		
		return resultado;
	}
	
	public String enviaMensagem(String msg, String to) throws IOException, ClassNotFoundException{
		//mensagem a ser passada para o servidor
		Mensagem mensagem = new Mensagem();
		mensagem.setNick(nick);
		mensagem.setAction("SEND");
		mensagem.setText(msg);
		mensagem.setTo(to);
		
		//escreve a mensagem para o servidor
		oo.writeObject(mensagem);
		
		//recebe uma mensagem do servidor
		String resultado = (String) oi.readObject();
		
		return resultado;
	}
	
	public String[] recebeMensagens() throws IOException, ClassNotFoundException{
		//mensagem a ser passada para o servidor
		Mensagem msg = new Mensagem();
		msg.setAction("RECEIVE");
		msg.setNick(nick);
		
		//escreve a mensagem para o servidor
		oo.writeObject(msg);
		
		//recebe uma mensagem do servidor - vetor com as mensagens recebidas
		String[] mensagens = (String[]) oi.readObject();
		
		return mensagens;
	}
	
	public String[] atualizaUsuarios() throws IOException, InterruptedException, ClassNotFoundException{
		//mensagem a ser passada para o servidor
		Mensagem msg = new Mensagem();
		msg.setNick(nick);
		msg.setAction("REFRESH");
		
		//escreve a mensagem para o servidor
		oo.writeObject(msg);
		
		//recebe uma mensagem do servidor - vetor com os usuarios logados no sistema
		String usuarios[] = (String[]) oi.readObject();
		
		return usuarios;
	}
	
	public void logoff() throws IOException, ClassNotFoundException {
		//mensagem a ser passada para o servidor
		Mensagem msg = new Mensagem();
		msg.setNick(nick);
		msg.setAction("LOGOFF");
		
		//escreve a mensagem para o servidor
		oo.writeObject(msg);
	}
}
