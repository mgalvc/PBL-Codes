/*
Autor: Matheus Galv�o Correia
Componente Curricular: MI - Programa��o
Conclu�do em: 30/07/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum trecho de c�digo de outro colega
ou de outro autor, tais como provindos de livros, apostilas e p�ginas ou documentos eletr�nicos da Internet. Qualquer
trecho de c�digo de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte do c�digo, e
estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
*/

package util;

public class MeuIterador implements Iterador {
	
	public MeuIterador(Celula c) {
		celula = c;
	}
	
	private Celula celula;
	
	//verifica se h� uma pr�xima Celula a percorrer
	public boolean temProximo() {
		if(celula == null) 
			return false;
		return true;
	}
	
	//passa para a pr�xima Celula
	public Object obterProximo() {
		celula = celula.getProxima();
		return celula;
	}
	
	//retorna a Celula atual
	public Object obterAtual() {
		return celula.getItem();
	}
	
}
