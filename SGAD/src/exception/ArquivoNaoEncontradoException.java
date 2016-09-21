package exception;

/**
 * Classe do tipo Exception que é lançada quando um determinado arquivo não é encontrado na árvore.
 * @author Matheus Galvão Correia
 */

public class ArquivoNaoEncontradoException extends Exception {
	public ArquivoNaoEncontradoException(String message) {
		super(message);
	}
}
