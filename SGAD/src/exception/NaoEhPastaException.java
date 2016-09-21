package exception;

/**
 * Classe do tipo Exception que é lançada quando o usuário passa o caminho de um arquivo para o construtor da árvore.
 * @author Matheus Galvão Correia
 */

public class NaoEhPastaException extends Exception {
	public NaoEhPastaException(String message) {
		super(message);
	}
}
