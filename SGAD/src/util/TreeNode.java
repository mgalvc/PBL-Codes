package util;

/**
 * Classe que representa um n� da �rvore.
 * @author Matheus Galv�o Correia
 */

public class TreeNode {
	private TreeNode parent;
	private Object data;
	private int profundidade;
	private ILista children;	//lista de Nodes filhos do Node atual
	
	/**
	 * Apenas inicializa a lista de filhos do n�.
	 */
	
	public TreeNode() {
		children = new Lista();
	}
	
	/**
	 * Inicializa a lista de filhos e define o dado que ser� armazenado no n�.
	 * @param data Dado a ser armazenado no n�.
	 */
	
	public TreeNode(Object data) {
		children = new Lista();
		this.data = data;
	}
	
	/**
	 * Inicializa a lista de filhos, define o pai e o dado que ser� armazenado no n�.
	 * @param parent Pai do novo n�.
	 * @param data Dado a ser armazenado no n�.
	 */
	
	public TreeNode(TreeNode parent, Object data) {
		children = new Lista();
		this.parent = parent;
		this.data = data;
	}
	
	/**
	 * Adiciona um filho ao n�.
	 * @param node Novo n� a ser adicionado na lista de filhos.
	 */
	
	public void addChildren(TreeNode node) {
		children.inserirFinal(node);
	}
	
	/**
	 * Retorna o pai do n�.
	 * @return TreeNode
	 */
	
	public TreeNode getParent() {
		return parent;
	}
	
	/**
	 * Retorna a profundidade do n�.
	 * @return int
	 */
	
	public int getProfundidade() {
		return profundidade;
	}
	
	/**
	 * Define o pai de um n�.
	 * @param parent N� que ser� definido como pai.
	 */
	
	public void setParent(TreeNode parent) {
		this.parent = parent;
		profundidade = parent.getProfundidade() + 1;
	}
	
	/**
	 * Retorna o dado armazenado no n�.
	 * @return Object
	 */
	
	public Object getData() {
		return data;
	}
	
	/**
	 * Define o dado a ser armazenado no n�.
	 * @param data Dado a ser armazenado.
	 */
	
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * Retorna a lista de filhos do n�.
	 * @return ILista
	 */
	
	public ILista getChildren() {
		return children;
	}
	
	/**
	 * Verifica se um n� � a raiz da �rvore
	 * @return boolean
	 */
	
	public boolean isRoot() {
		return parent == null;
	}
	
	/**
	 * Verifica se um n� tem filhos.
	 * @return boolean
	 */
	
	public boolean hasChildren() {
		return !children.estaVazia();
	}
	
}
