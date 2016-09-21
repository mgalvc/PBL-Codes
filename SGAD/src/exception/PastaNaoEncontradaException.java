package exception;

/**
 * Classe do tipo Exception que � lan�ada quando uma determinada pasta n�o � encontrada.
 * @author Matheus Galv�o Correia
 */

public class PastaNaoEncontradaException extends Exception {
	public PastaNaoEncontradaException(String message) {
		super(message);
	}
}
