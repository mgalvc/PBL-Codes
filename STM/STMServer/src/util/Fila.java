/*
Autor: Matheus Galv�o Correia
Componente Curricular: MI - Programa��o
Conclu�do em: 25/08/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum trecho de c�digo de outro colega
ou de outro autor, tais como provindos de livros, apostilas e p�ginas ou documentos eletr�nicos da Internet. Qualquer
trecho de c�digo de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte do c�digo, e
estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
*/

package util;

public class Fila implements IFila {
	private Node head;
	private Node tail;
	private int size;
	
	private class Node {
		private Object data;
		private Node next;
		
		private Node(Object data) {
			this.data = data;
		}
		
		public Object getData() {
			return data;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}	
	}
	
	private class Iterator implements Iterador {
		private Node aux = head;
		
		public boolean temProximo() {
			return aux != null;
		}

		public Object obterProximo() {
			Node atual = aux;
			aux = aux.getNext();
			return atual.getData();
		}
	}

	public boolean estaVazia() {
		return size == 0;
	}

	@Override
	public int obterTamanho() {
		return size;
	}

	public void inserirFinal(Object o) {
		Node novo = new Node(o);
		
		if(estaVazia())
			head = novo;
		else
			tail.setNext(novo);
		
		tail = novo;
		tail.setNext(null);
		size++;
		
	}

	@Override
	public Object removerInicio() {
		Node removed = head;
		head = head.getNext();
		size--;
		return removed.getData();
	}

	@Override
	public Object recuperarInicio() {
		return head;
	}

}
