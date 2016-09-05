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

public class Lista implements ILista{
	
	private Celula primeira = null;
	private Celula ultima = null;
	private int totalElementos = 0;
	
	public boolean estaVazia() {
		if (totalElementos == 0) {
			return true;
		}
		
		return false;
	}
	
	//verifica se um dado elemento existe
	private boolean elementoExiste(int index) {
		if (index < 1 || index > totalElementos) {
			return false;
		}
		return true;
	}
	
	//retorna o tamanho da Lista
	public int obterTamanho() {
		return totalElementos;
	}
	
	//insere uma Celula no inicio da Lista
	public void inserirInicio(Object o) {
		Celula nova = new Celula(o);
		nova.setProxima(primeira);
		primeira = nova;
		
		if (this.estaVazia()) {
			ultima = primeira;
		}
		
		this.totalElementos++;
	}
	
	//insere uma Celula no final da Lista
	public void inserirFinal(Object o) {
		if (this.estaVazia()) {
			this.inserirInicio(o);
			return;
		}
		
		Celula nova = new Celula (o);
		this.ultima.setProxima(nova);
		this.ultima = nova;
		ultima.setProxima(null);
		this.totalElementos++;
		
	}
	
	//insere uma Celula em uma dada posi��o da Lista
	public void inserir(Object o, int index) {
		if (!elementoExiste(index)) {
			throw new IllegalArgumentException("Posi��o " + index + " n�o existe!");
		} else
			if (index == 1) {
				inserirInicio(o);
			} else
				if (index == totalElementos) {
					inserirFinal(o);
				} else {
					Celula anteriorAtual = (Celula) this.recuperar(index - 1);
					Celula atual = anteriorAtual.getProxima();
					
					Celula nova = new Celula(o);
					
					nova.setProxima(atual);
					anteriorAtual.setProxima(nova);
				}
		totalElementos++;
	}
	
	//remove uma Celula do inicio da Lista
	public Object removerInicio() {
		if(estaVazia()) {
			throw new IllegalArgumentException("Lista vazia!");
		}
		totalElementos--;
		Celula removida = primeira;
		primeira = primeira.getProxima();
		return removida;
	}
	
	//remove uma Celula do final da Lista
	public Object removerFinal() {
		if(estaVazia()) {
			throw new IllegalArgumentException("Lista vazia!");
		}

		MeuIterador it = this.iterador();
		Celula aux = null;
		
		while (it.temProximo()) {
			aux = (Celula) it.obterAtual();
			it.obterProximo();
			
		}
		
		Celula removida = ultima;
		ultima = aux;
		ultima.setProxima(null);
		
		totalElementos--;
		return removida;	
	}
	
	//remove uma Celula de qualquer posi��o da Lista
	public Object remover(int index) {
		if(!elementoExiste(index)) {
			throw new IllegalArgumentException("Posi��o " + index + " n�o existe!");
		} else
			if (index == 1)
				removerInicio();
			else
				if (index == totalElementos)
					removerFinal();
				else {
					Celula aux = (Celula) recuperar(index-1);
					Celula removida = (Celula) recuperar(index);
					
					aux.setProxima(removida.getProxima());
					
					return removida;
				}
					
		return null;
	}
	
	//retorna a Celula de uma dada posi��o da Lista
	public Object recuperar(int index) {		
		if (!elementoExiste(index)) {
			throw new IllegalArgumentException("Posi��o " + index + " n�o existe!");
		} else
			if (index == 1)
				return primeira;
			else 
				if(index == totalElementos)
					return ultima;
				else {
					MeuIterador it = this.iterador();
					Celula aux = null;
					
					for (int i = 1; i != index && it.temProximo(); i++) {
						aux = (Celula) it.obterProximo();
					}
					
					return aux;
				}
	}
	
	//retorna um Iterador para a Lista
	public MeuIterador iterador() {
		MeuIterador i;
		if (estaVazia())
			i = new MeuIterador(null);
		else
			i = new MeuIterador(primeira);
		
		return i;
	}
}
