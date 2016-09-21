package exception;

/**
 * Classe do tipo Exception que � lan�ada quando um determinado tipo de arquivo n�o � encontrado na �rvore.
 * @author Matheus Galv�o Correia
 */

public class TipoNaoEncontradoException extends Exception {
	public TipoNaoEncontradoException(String msg) {
		super(msg);
	}
}
