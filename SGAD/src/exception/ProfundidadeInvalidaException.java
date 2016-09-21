package exception;

/**
 * Classe do tipo Exception que é lançada quando uma profundidade inválida é passada como argumento para os métodos de busca.
 * @author Matheus Galvão Correia
 */

public class ProfundidadeInvalidaException extends Exception {
	public ProfundidadeInvalidaException(String msg) {
		super(msg);
	}
}
