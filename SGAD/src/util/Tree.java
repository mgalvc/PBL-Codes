package util;

/**
 * Árvore generalizada.
 * @author Matheus Galvão Correia
 */

public class Tree {
	private TreeNode root;
	
	public Tree() {
		root = new TreeNode();
	}
	
	private class MyIterator implements Iterador {
		private IFila fila;
		private int profundidade;
		
		public MyIterator(int profundidade) {
			fila = new Fila();
			fila.inserirFinal(root);
			this.profundidade = profundidade;
		}
		
		public boolean hasNext() {
			return !fila.estaVazia();
		}

		public Object next() {
			TreeNode atual = (TreeNode) fila.removerInicio();
			if(atual.hasChildren()) {
				Iterador it = atual.getChildren().iterador();
				
				if(atual.getProfundidade() < profundidade || profundidade == 0)
					while(it.hasNext())
						fila.inserirFinal(it.next());
			}
			
			return atual;
		}
		
	}
	
	/**
	 * Insere um nó na árvore.
	 * @param parent Nó pai do novo nó.
	 * @param data Dado a ser armazenado no novo nó.
	 */
	
	public void insert(TreeNode parent, Object data) {
		TreeNode node = new TreeNode(data);
		node.setParent(parent);
		parent.addChildren(node);
	}
	
	/**
	 * Insere um nó na árvore.
	 * @param parent Nó pai do novo nó.
	 * @param child Novo nó.
	 */
	
	public void insert(TreeNode parent, TreeNode child) {
		child.setParent(parent);
		parent.addChildren(child);
	}
	
	/**
	 * Retorna o pai de um determinado nó.
	 * @param node Nó cujo pai será retornado
	 * @return TreeNode
	 */

	public TreeNode parent(TreeNode node) {
		return node.getParent();
	}
	
	/**
	 * Retorna a raiz da árvore.
	 * @return TreeNode
	 */

	public TreeNode getRoot() {
		return root;
	}
	
	/**
	 * Retorna a lista de filhos de um determinado nó.
	 * @param node Nó cuja lista de filhos será retornada.
	 * @return ILista
	 */

	public ILista children(TreeNode node) {
		return node.getChildren();
	}
	
	/**
	 * Retorna um iterador que permite a busca por profundidade.
	 * @param profundidade Profundidade máxima da busca.
	 * @return Iterador
	 */
	
	public Iterador iterador(int profundidade) {
		return new MyIterator(profundidade);
	}

	
		
}
