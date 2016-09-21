package util;

/**
 * �rvore generalizada.
 * @author Matheus Galv�o Correia
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
	 * Insere um n� na �rvore.
	 * @param parent N� pai do novo n�.
	 * @param data Dado a ser armazenado no novo n�.
	 */
	
	public void insert(TreeNode parent, Object data) {
		TreeNode node = new TreeNode(data);
		node.setParent(parent);
		parent.addChildren(node);
	}
	
	/**
	 * Insere um n� na �rvore.
	 * @param parent N� pai do novo n�.
	 * @param child Novo n�.
	 */
	
	public void insert(TreeNode parent, TreeNode child) {
		child.setParent(parent);
		parent.addChildren(child);
	}
	
	/**
	 * Retorna o pai de um determinado n�.
	 * @param node N� cujo pai ser� retornado
	 * @return TreeNode
	 */

	public TreeNode parent(TreeNode node) {
		return node.getParent();
	}
	
	/**
	 * Retorna a raiz da �rvore.
	 * @return TreeNode
	 */

	public TreeNode getRoot() {
		return root;
	}
	
	/**
	 * Retorna a lista de filhos de um determinado n�.
	 * @param node N� cuja lista de filhos ser� retornada.
	 * @return ILista
	 */

	public ILista children(TreeNode node) {
		return node.getChildren();
	}
	
	/**
	 * Retorna um iterador que permite a busca por profundidade.
	 * @param profundidade Profundidade m�xima da busca.
	 * @return Iterador
	 */
	
	public Iterador iterador(int profundidade) {
		return new MyIterator(profundidade);
	}

	
		
}
