package exception;

/**
 * Classe do tipo Exception que � lan�ada quando uma profundidade inv�lida � passada como argumento para os m�todos de busca.
 * @author Matheus Galv�o Correia
 */

public class ProfundidadeInvalidaException extends Exception {
	public ProfundidadeInvalidaException(String msg) {
		super(msg);
	}
}
