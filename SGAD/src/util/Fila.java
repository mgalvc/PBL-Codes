package util;

public class Fila implements IFila {
	private int size;
	private Node head;
	private Node tail;
	
	private class Node {
		private Node next;
		private Object data;
		
		public Node(Object data) {
			this.data = data;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		public Object getData() {
			return data;
		}	
		
	}
	
	public boolean estaVazia() {
		return size == 0;
	}

	@Override
	public int obterTamanho() {
		return size;
	}

	@Override
	public void inserirFinal(Object o) {
		if(o == null)
			return;
		
		Node newNode = new Node(o);
		
		if(estaVazia())
			head = newNode;
		else
			tail.setNext(newNode);
		
		tail = newNode;
		tail.setNext(null);
		
		size++;
		
	}

	@Override
	public Object removerInicio() {
		if(estaVazia())
			return null;
		
		Node removed = head;
		head = head.getNext();
		
		size--;
		return removed.getData();
	}

	@Override
	public Object recuperarInicio() {
		if(estaVazia())
			return null;
		return head.getData();
		
	}

}
