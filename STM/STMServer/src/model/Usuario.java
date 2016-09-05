/*
Autor: Matheus Galv�o Correia
Componente Curricular: MI - Programa��o
Conclu�do em: 25/08/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum trecho de c�digo de outro colega
ou de outro autor, tais como provindos de livros, apostilas e p�ginas ou documentos eletr�nicos da Internet. Qualquer
trecho de c�digo de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte do c�digo, e
estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
*/

package model;

import util.Fila;
import util.IFila;

public class Usuario {

	private String nick;
	private IFila mensagens;
	
	public Usuario(String nick) {
		this.nick = nick;
		mensagens = new Fila();
	}
	
	public String getNick() {
		return nick;
	}
	
	public boolean temMensagens() {
		return mensagens.obterTamanho() != 0;
	}
	
	public IFila getMensagens() {
		return mensagens;
	}
	
	public void recebeMensagem(String mensagem) {
		mensagens.inserirFinal(mensagem);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario user = (Usuario) obj;
			if(user.getNick().equals(nick))
				return true;
		}
		
		return false;
	}
	
	
}
