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

public class Lista implements ILista {
	private Node head;
	private Node tail;
	private int size = 0;
	
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

	public int obterTamanho() {
		return size;
	}

	public void inserirInicio(Object o) {
		Node novo = new Node(o);
		
		if(estaVazia())
			tail = novo;
		else
			novo.setNext(head);
		
		head = novo;
		size++;
			
	}

	public void inserirFinal(Object o) {
		Node novo = new Node(o);
		
		if (estaVazia())
			head = novo;
		else
			tail.setNext(novo);
		
		tail = novo;
		tail.setNext(null);
		size++;
	}

	public void inserir(Object o, int index) {
		if(estaVazia())
			return;
		if(index == 0)
			inserirInicio(o);
		else if(index == size - 1)
			inserirFinal(o);
		else {
			Node novo = new Node(o);
			Node anteriorNovo = (Node) getNode(index - 1);
			Node proximoNovo = (Node) getNode(index + 1);
			
			anteriorNovo.setNext(novo);
			novo.setNext(proximoNovo);
			
			size++;
		}
	}

	public Object removerInicio() {
		if(estaVazia())
			return null;
		Node removed = head;
		head = head.getNext();
		size--;
		return removed.getData();
	}

	public Object removerFinal() {
		if(estaVazia())
			return null;
		//tail � size - 1
		Node antesTail = (Node) getNode(size - 2);
		Node removed = tail;
		tail = antesTail;
		tail.setNext(null);
		size--;
		return removed.getData();
	}

	public Object remover(int index) {
		if(estaVazia() || (index < 0 || index >= size))
			return null;
		if(index == 0)
			return removerInicio();
		if(index == size - 1)
			return removerFinal();
		
		Node antesRemoved = (Node) getNode(index - 1);
		Node removed = (Node) getNode(index);
		
		antesRemoved.setNext(removed.getNext());
		removed.setNext(null);
		
		size--;
		return removed.getData();
	}

	private Object getNode(int index) {
		if(index < 0 || index >= size) 
			return null;
		
		Node aux = head;
		
		for(int i = 0; i < index; i++) {
			aux = aux.getNext();
		}
		
		return aux;
	}
	
	public Object recuperar(int index) {
		if(index < 0 || index >= size)
			return null;
		return ((Node) getNode(index)).getData();
	}

	public Iterador iterador() {
		return new Iterator();
	}

}
