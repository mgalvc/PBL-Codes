/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 30/07/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package util;

public class MeuIterador implements Iterador {
	
	public MeuIterador(Celula c) {
		celula = c;
	}
	
	private Celula celula;
	
	//verifica se há uma próxima Celula a percorrer
	public boolean temProximo() {
		if(celula == null) 
			return false;
		return true;
	}
	
	//passa para a próxima Celula
	public Object obterProximo() {
		celula = celula.getProxima();
		return celula;
	}
	
	//retorna a Celula atual
	public Object obterAtual() {
		return celula.getItem();
	}
	
}
