package util;

public interface ITree {
	public void insert(TreeNode parent, Object data);
	public int size();
	public boolean isEmpty();
	public Iterador iterador(int profundidade);
	public TreeNode parent(TreeNode node);
	public TreeNode getRoot();
	public ILista children(TreeNode node);
}
