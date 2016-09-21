package exception;

/**
 * Classe do tipo Exception que é lançada quando um determinado tipo de arquivo não é encontrado na árvore.
 * @author Matheus Galvão Correia
 */

public class TipoNaoEncontradoException extends Exception {
	public TipoNaoEncontradoException(String msg) {
		super(msg);
	}
}
