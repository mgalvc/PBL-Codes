/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 25/08/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
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
