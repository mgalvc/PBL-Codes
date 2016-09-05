/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 25/08/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package view;

import java.io.IOException;
import controller.*;

public class MainServer {
	private static Servidor servidor;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		servidor = new Servidor();
		servidor.iniciaServidor();
	}

}
