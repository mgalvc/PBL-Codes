package util;

/**
 * Classe que representa um nó da árvore.
 * @author Matheus Galvão Correia
 */

public class TreeNode {
	private TreeNode parent;
	private Object data;
	private int profundidade;
	private ILista children;	//lista de Nodes filhos do Node atual
	
	/**
	 * Apenas inicializa a lista de filhos do nó.
	 */
	
	public TreeNode() {
		children = new Lista();
	}
	
	/**
	 * Inicializa a lista de filhos e define o dado que será armazenado no nó.
	 * @param data Dado a ser armazenado no nó.
	 */
	
	public TreeNode(Object data) {
		children = new Lista();
		this.data = data;
	}
	
	/**
	 * Inicializa a lista de filhos, define o pai e o dado que será armazenado no nó.
	 * @param parent Pai do novo nó.
	 * @param data Dado a ser armazenado no nó.
	 */
	
	public TreeNode(TreeNode parent, Object data) {
		children = new Lista();
		this.parent = parent;
		this.data = data;
	}
	
	/**
	 * Adiciona um filho ao nó.
	 * @param node Novo nó a ser adicionado na lista de filhos.
	 */
	
	public void addChildren(TreeNode node) {
		children.inserirFinal(node);
	}
	
	/**
	 * Retorna o pai do nó.
	 * @return TreeNode
	 */
	
	public TreeNode getParent() {
		return parent;
	}
	
	/**
	 * Retorna a profundidade do nó.
	 * @return int
	 */
	
	public int getProfundidade() {
		return profundidade;
	}
	
	/**
	 * Define o pai de um nó.
	 * @param parent Nó que será definido como pai.
	 */
	
	public void setParent(TreeNode parent) {
		this.parent = parent;
		profundidade = parent.getProfundidade() + 1;
	}
	
	/**
	 * Retorna o dado armazenado no nó.
	 * @return Object
	 */
	
	public Object getData() {
		return data;
	}
	
	/**
	 * Define o dado a ser armazenado no nó.
	 * @param data Dado a ser armazenado.
	 */
	
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * Retorna a lista de filhos do nó.
	 * @return ILista
	 */
	
	public ILista getChildren() {
		return children;
	}
	
	/**
	 * Verifica se um nó é a raiz da árvore
	 * @return boolean
	 */
	
	public boolean isRoot() {
		return parent == null;
	}
	
	/**
	 * Verifica se um nó tem filhos.
	 * @return boolean
	 */
	
	public boolean hasChildren() {
		return !children.estaVazia();
	}
	
}
