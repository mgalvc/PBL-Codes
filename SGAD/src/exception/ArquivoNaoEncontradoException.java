package exception;

/**
 * Classe do tipo Exception que � lan�ada quando um determinado arquivo n�o � encontrado na �rvore.
 * @author Matheus Galv�o Correia
 */

public class ArquivoNaoEncontradoException extends Exception {
	public ArquivoNaoEncontradoException(String message) {
		super(message);
	}
}
