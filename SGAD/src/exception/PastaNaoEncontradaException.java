package exception;

/**
 * Classe do tipo Exception que é lançada quando uma determinada pasta não é encontrada.
 * @author Matheus Galvão Correia
 */

public class PastaNaoEncontradaException extends Exception {
	public PastaNaoEncontradaException(String message) {
		super(message);
	}
}
