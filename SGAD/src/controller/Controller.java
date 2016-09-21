package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import exception.ArquivoNaoEncontradoException;
import exception.NaoEhPastaException;
import exception.PastaNaoEncontradaException;
import exception.ProfundidadeInvalidaException;
import exception.TipoNaoEncontradoException;
import util.IFila;
import util.Iterador;
import util.Tree;
import util.TreeNode;
import util.Fila;

/**
 * Classe respons�vel por intermediar o <i>view</i> e a estrutura de dados. Permite as opera��es
 *  de constru��o da �rvore, busca e gera��o de um arquivo de texto com os caminhos de todos os arquivos da estrutura.
 *  
 *  @author Matheus Galv�o Correia
 */

public class Controller {
	
	private Tree t = new Tree();
	
	/**
	 * M�todo que constr�i a hierarquia dos diret�rios a partir da raiz passada como par�metro.
	 * @param caminho Caminho da raiz do diret�rio a ser acessado.
	 * @throws NaoEhPastaException
	 * @throws PastaNaoEncontradaException
	 */
	
	public void constroiArvore(String caminho) throws NaoEhPastaException, PastaNaoEncontradaException {
		File diretorio = new File(caminho);
		
		if(diretorio.isFile())
			throw new NaoEhPastaException("O caminho escolhido n�o indica um diret�rio.");
		if(!diretorio.exists())
			throw new PastaNaoEncontradaException("O caminho escolhido n�o existe.");
		
		File[] arqs = diretorio.listFiles();
		
		//adiciona o diret�rio raiz na raiz da �rvore
		TreeNode root = t.getRoot();
		root.setData(caminho);
		
		constroiArvore(root, arqs);
	}
	
	private void constroiArvore(TreeNode parent, File[] arquivos) {
		for(File arquivo : arquivos) {
			TreeNode child = new TreeNode(parent, arquivo.getAbsolutePath());
			t.insert(parent, child);
			if(arquivo.isDirectory())
				constroiArvore(child, arquivo.listFiles());
		}
	}
	
	/**
	 * M�todo que percorre a �rvore dada uma determinada profundidade.
	 * @param profundidade N�vel m�ximo at� onde a busca deve ser realizada.
	 * @return String[]
	 * @throws ProfundidadeInvalidaException
	 */
	
	public String[] percorreArvore(int profundidade) throws ProfundidadeInvalidaException {
		IFila fila = new Fila();
		Iterador it = null;
		
		if(profundidade < 0)
			throw new ProfundidadeInvalidaException("Profundidade inv�lida");
		
		it = t.iterador(profundidade);
		
		//percorre a �rvore e guarda todos os seus n�s em uma fila
		while(it.hasNext()) {
			TreeNode aux = (TreeNode) it.next();
			fila.inserirFinal(aux.getData());
		}
		
		String[] resultado = new String[fila.obterTamanho()];
		
		for(int i = 0; !fila.estaVazia(); i++)
			resultado[i] = (String) fila.removerInicio();
		
		return resultado;
	}
	
	/**
	 * M�todo que gera um arquivo de texto com os caminhos completos de todos os arquivos presentes na estrutura.
	 * @param profundidade N�vel m�ximo at� onde a busca deve ser realizada.
	 * @param caminho Caminho completo onde o arquivo de texto deve ser salvo.
	 * @throws IOException
	 * @throws ProfundidadeInvalidaException
	 * @throws PastaNaoEncontradaException 
	 * @throws NaoEhPastaException 
	 */
	
	public void geraArquivo(int profundidade, String caminho, String nomeArquivo) throws IOException, ProfundidadeInvalidaException, PastaNaoEncontradaException, NaoEhPastaException {
		if(!new File(caminho).exists())
			throw new PastaNaoEncontradaException("O caminho escolhido n�o existe.");
		if(new File(caminho).isFile())
			throw new NaoEhPastaException("O caminho escolhido n�o indica um diret�rio.");
		
		BufferedWriter bfw = new BufferedWriter(new FileWriter(caminho + "\\" + nomeArquivo + ".txt"));
		
		for(String nome : percorreArvore(profundidade)) {
			bfw.write(nome);
			bfw.newLine();
		}
		
		bfw.close();
	}
	
	/**
	 * Busca e retorna todos os arquivos de uma dada extens�o.
	 * @param extensao Extens�o (tipo) dos arquivos a serem buscados.
	 * @param profundidade N�vel m�ximo at� onde a busca deve ser realizada.
	 * @return String
	 * @throws ProfundidadeInvalidaException
	 * @throws TipoNaoEncontradoException
	 */

	public String buscaExtensao(String extensao, int profundidade) throws ProfundidadeInvalidaException, TipoNaoEncontradoException {
		String resultado = new String();
		
		for(String arquivo : percorreArvore(profundidade))
			if(arquivo.endsWith(extensao))
				resultado += arquivo + "\n";
		
		if(resultado.isEmpty())
			throw new TipoNaoEncontradoException("Arquivo com extens�o " + extensao + " n�o encontrado.");
			
		return resultado;
	}
	
	/**
	 * Busca e retorna todos os arquivos que cont�m um dado nome.
	 * @param nome Nome do arquivo a ser buscado.
	 * @param profundidade N�vel m�ximo at� onde a busca deve ser realizada.
	 * @return String
	 * @throws ProfundidadeInvalidaException
	 * @throws ArquivoNaoEncontradoException
	 */

	public String buscaArquivo(String nome, int profundidade) throws ProfundidadeInvalidaException, ArquivoNaoEncontradoException {
		String resultado = new String();
		
		for(String arquivo : percorreArvore(profundidade))
			if(new File(arquivo).isFile() && arquivo.contains(nome))
				resultado += arquivo + "\n";
		
		if(resultado.isEmpty())
			throw new ArquivoNaoEncontradoException("Nenhuma correspond�ncia de arquivo com o nome " + nome);
		
		return resultado;
	}
	
	/**
	 * Busca e retorna todos os diret�rios que cont�m um dado nome.
	 * @param nome Nome do diret�rio a ser buscado.
	 * @param profundidade N�vel m�ximo at� onde a busca deve ser realizada.
	 * @return String
	 * @throws ProfundidadeInvalidaException
	 * @throws PastaNaoEncontradaException 
	 */
	
	public String buscaPasta(String nome, int profundidade) throws ProfundidadeInvalidaException, PastaNaoEncontradaException {
		String resultado = new String();
		
		for(String arquivo : percorreArvore(profundidade))
			if(new File(arquivo).isDirectory() && arquivo.contains(nome))
				resultado += arquivo + "\n";
		
		if(resultado.isEmpty())
			throw new PastaNaoEncontradaException("Nenhuma correspond�ncia de diret�rio com o nome " + nome);
		
		return resultado;
	}

}