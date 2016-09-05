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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Mensagem;
import model.MensagensNaoLidasException;
import model.Usuario;
import model.UsuarioDesconectadoException;
import model.UsuarioJaExisteException;
import util.IFila;
import util.ILista;
import util.Iterador;
import util.Lista;

import java.net.*;

public class Servidor {
	
	private ILista usuarios;
	
	public Servidor() {
		usuarios = new Lista();
	}

	public void iniciaServidor() throws IOException, ClassNotFoundException {
		
		//inicia o servidor
		ServerSocket servidor = new ServerSocket(3000);
		System.out.println("Porta 3000 aberta");
		
		//espera uma requisi��o de algum cliente
		while(true) {
			//recebe o cliente que requisitou o servi�o
			Socket cliente = servidor.accept();
			
			//inicia uma thread com o cliente que requisitou o servi�o
			Handler h = new Handler(cliente);
			Thread t = new Thread(h);
			t.start();
		}
	}
	
	private void logoff(String nick) throws IOException, MensagensNaoLidasException {		
		Iterador itUsers = usuarios.iterador();
		Usuario user = null;
		int index = 0;
		
		//procura pelo usu�rio na lista de usu�rios
		while(itUsers.temProximo()) {
			user = (Usuario) itUsers.obterProximo();
			if(user.getNick().equals(nick))
				break;
			index++;
		}
		
		//remove o usu�rio da lista de usu�rios do sistema
		usuarios.remover(index);
		
		System.out.println("Usu�rio " + nick + " offline");
	}

	private void buscaUsuarios(ObjectOutputStream oo) throws IOException {
		Iterador it = usuarios.iterador();
		
		//vetor a ser preenchido com os usu�rios online
		String usuariosOnline[] = new String[usuarios.obterTamanho()];
		
		//preenche o vetor com os usu�rios online
		for (int i = 0; it.temProximo(); i++) {
			usuariosOnline[i] = ((Usuario) it.obterProximo()).getNick();
		}
		
		//ordena o vetor em ordem alfab�tica
		selectionSort(usuariosOnline);
		
		//retorna para o cliente o vetor ordenado
		oo.writeObject(usuariosOnline);
	}

	private Usuario buscaUsuario(String nick){
		Iterador it = usuarios.iterador();
		
		//procura um determinado usu�rio
		while(it.temProximo()) {
			Usuario user = (Usuario) it.obterProximo();
			
			if(user.getNick().equals(nick))
				return user;
		}
		
		return null;
	}
	
	private void buscaMensagens(String nick, ObjectOutputStream oo) throws IOException {
		Usuario user = buscaUsuario(nick);
		String[] mensagens;
		
		//Fila que cont�m as mensagens do usu�rio
		IFila filaMensagens = user.getMensagens();
		//vetor a ser preenchido com as mensagens do usu�rio
		mensagens = new String[filaMensagens.obterTamanho()];
		
		//preenche o vetor
		for(int i = 0; user.temMensagens(); i++) 
			mensagens[i] = (String) filaMensagens.removerInicio();
		
		//retorna o vetor para o cliente
		oo.writeObject(mensagens); 			
	}
	
	private String getDataHora(){
		String data = "";
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		data = formata.format(new Date());							
		formata = new SimpleDateFormat("hh:mm");
		data = data + " - "+formata.format(new Date());
		return data;
	}

	private void send(String nickFrom, String nickTo, String msg) throws IOException, UsuarioDesconectadoException {
		Usuario user = buscaUsuario(nickTo);
		
		//exce��o caso o usu�rio informado n�o exista
		if(user == null)
			throw new UsuarioDesconectadoException();
		
		//adiciona a mensagem � fila de mensagens do destinat�rio
		String mensagem = nickFrom + " (" + getDataHora() + ") - " + msg;
		user.recebeMensagem(mensagem);
	}

	private void logar(String nick) throws IOException, UsuarioJaExisteException{
		Usuario user = new Usuario(nick);
		Iterador it = usuarios.iterador();
		
		//verifica se j� existe um us�rio com o nick informado
		while(it.temProximo())
			if(((Usuario) it.obterProximo()).equals(user))
				throw new UsuarioJaExisteException();
		
		//insere o usuario na lista de usu�rios do sistema
		usuarios.inserirFinal(user);
		
		System.out.println("Usu�rio " + user.getNick() + " online");
	}
	
	private void selectionSort(String[] array) {
		for(int i = 0; i < array.length; i++) {
			int indexMenor = i;
			
			//busca pelo menor valor na parte n�o ordenada do vetor
			for(int j = i + 1; j < array.length; j++) {
				if(array[j].compareTo(array[indexMenor]) < 0)
					indexMenor = j;
			}
			
			if(indexMenor != i) {	//troca as posi��es
				String aux = array[i];
				array[i] = array[indexMenor];
				array[indexMenor] = aux;
			}
		}
	}
	
	private class Handler implements Runnable {
		private Socket cliente;
		
		private ObjectOutputStream oo;
		private ObjectInputStream oi;
		
		private Handler(Socket cliente) {
			this.cliente = cliente;
		}
		
		public void run() {
			try {
				oi = new ObjectInputStream(cliente.getInputStream());
				oo = new ObjectOutputStream(cliente.getOutputStream());
				Mensagem msg;
				
				//enquanto o usu�rio estiver enviando mensagens...
				while ((msg = (Mensagem) oi.readObject()) != null) {
					if(msg.getAction().equals("LOGIN")) {
						try {
							logar(msg.getNick());
							oo.writeObject("Usu�rio logado com sucesso");
						} catch(UsuarioJaExisteException e) {
							oo.writeObject(e.getMessage());
						}
					} else
					if(msg.getAction().equals("LOGOFF"))
						logoff(msg.getNick());
					else
					if(msg.getAction().equals("REFRESH")) {
						buscaUsuarios(oo);
					} else
					if(msg.getAction().equals("SEND")) {
						try {
							send(msg.getNick(), msg.getTo(), msg.getText());
							oo.writeObject("Mensagem enviada com sucesso");
						} catch (UsuarioDesconectadoException e) {
							oo.writeObject(e.getMessage());
						}
					} else
					if(msg.getAction().equals("RECEIVE")) {
						buscaMensagens(msg.getNick(), oo);
					}
				}
				
				oi.close();
				oo.close();
			} catch (Exception e) {
				//i'm fine, thanks
			}
		}
		
	}
}
