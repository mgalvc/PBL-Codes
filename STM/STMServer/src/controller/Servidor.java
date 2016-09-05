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
		
		//espera uma requisição de algum cliente
		while(true) {
			//recebe o cliente que requisitou o serviço
			Socket cliente = servidor.accept();
			
			//inicia uma thread com o cliente que requisitou o serviço
			Handler h = new Handler(cliente);
			Thread t = new Thread(h);
			t.start();
		}
	}
	
	private void logoff(String nick) throws IOException, MensagensNaoLidasException {		
		Iterador itUsers = usuarios.iterador();
		Usuario user = null;
		int index = 0;
		
		//procura pelo usuário na lista de usuários
		while(itUsers.temProximo()) {
			user = (Usuario) itUsers.obterProximo();
			if(user.getNick().equals(nick))
				break;
			index++;
		}
		
		//remove o usuário da lista de usuários do sistema
		usuarios.remover(index);
		
		System.out.println("Usuário " + nick + " offline");
	}

	private void buscaUsuarios(ObjectOutputStream oo) throws IOException {
		Iterador it = usuarios.iterador();
		
		//vetor a ser preenchido com os usuários online
		String usuariosOnline[] = new String[usuarios.obterTamanho()];
		
		//preenche o vetor com os usuários online
		for (int i = 0; it.temProximo(); i++) {
			usuariosOnline[i] = ((Usuario) it.obterProximo()).getNick();
		}
		
		//ordena o vetor em ordem alfabética
		selectionSort(usuariosOnline);
		
		//retorna para o cliente o vetor ordenado
		oo.writeObject(usuariosOnline);
	}

	private Usuario buscaUsuario(String nick){
		Iterador it = usuarios.iterador();
		
		//procura um determinado usuário
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
		
		//Fila que contém as mensagens do usuário
		IFila filaMensagens = user.getMensagens();
		//vetor a ser preenchido com as mensagens do usuário
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
		
		//exceção caso o usuário informado não exista
		if(user == null)
			throw new UsuarioDesconectadoException();
		
		//adiciona a mensagem à fila de mensagens do destinatário
		String mensagem = nickFrom + " (" + getDataHora() + ") - " + msg;
		user.recebeMensagem(mensagem);
	}

	private void logar(String nick) throws IOException, UsuarioJaExisteException{
		Usuario user = new Usuario(nick);
		Iterador it = usuarios.iterador();
		
		//verifica se já existe um usário com o nick informado
		while(it.temProximo())
			if(((Usuario) it.obterProximo()).equals(user))
				throw new UsuarioJaExisteException();
		
		//insere o usuario na lista de usuários do sistema
		usuarios.inserirFinal(user);
		
		System.out.println("Usuário " + user.getNick() + " online");
	}
	
	private void selectionSort(String[] array) {
		for(int i = 0; i < array.length; i++) {
			int indexMenor = i;
			
			//busca pelo menor valor na parte não ordenada do vetor
			for(int j = i + 1; j < array.length; j++) {
				if(array[j].compareTo(array[indexMenor]) < 0)
					indexMenor = j;
			}
			
			if(indexMenor != i) {	//troca as posições
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
				
				//enquanto o usuário estiver enviando mensagens...
				while ((msg = (Mensagem) oi.readObject()) != null) {
					if(msg.getAction().equals("LOGIN")) {
						try {
							logar(msg.getNick());
							oo.writeObject("Usuário logado com sucesso");
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
