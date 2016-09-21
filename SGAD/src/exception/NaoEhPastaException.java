package exception;

/**
 * Classe do tipo Exception que � lan�ada quando o usu�rio passa o caminho de um arquivo para o construtor da �rvore.
 * @author Matheus Galv�o Correia
 */

public class NaoEhPastaException extends Exception {
	public NaoEhPastaException(String message) {
		super(message);
	}
}
