package view;

import java.io.IOException;

import controller.Controller;
import exception.ArquivoNaoEncontradoException;
import exception.NaoEhPastaException;
import exception.PastaNaoEncontradaException;
import exception.ProfundidadeInvalidaException;
import exception.TipoNaoEncontradoException;
import util.Console;
import util.IFila;
import util.Iterador;
import util.TreeNode;
import util.Tree;

public class Main {
	static Controller c;
	
	public static void main(String[] args) throws IOException {
		Controller c = new Controller();
		boolean caminhoInvalido = true;
		
		System.out.println("------ Sistema de Gerenciamento de Arquivos e Diretórios");
		
		while(caminhoInvalido) {
			System.out.print("Indique a raiz do diretório: ");
			String caminho = Console.readString();
			
			try {
				c.constroiArvore(caminho);
				caminhoInvalido = false;
			} catch(PastaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch(NaoEhPastaException e) {
				System.out.println(e.getMessage());
			}
		}
		
		while(true) {
			System.out.println("1 - Busca de arquivos por nome");
			System.out.println("2 - Busca de arquivos por extensão");
			System.out.println("3 - Busca de diretórios");
			System.out.println("4 - Geração de arquivo");
			System.out.println("5 - Encerrar");
			System.out.print("-> ");
			int menu = Console.readInt();
			
			switch(menu) {
				case 1:
					System.out.print("Nome do arquivo: ");
					String nome = Console.readString();
					
					System.out.print("Profundidade da busca: ");
					int profu = Console.readInt();
					
					try {
						System.out.println(c.buscaArquivo(nome, profu));
					} catch(ArquivoNaoEncontradoException e) {
						System.out.println(e.getMessage());
					} catch(ProfundidadeInvalidaException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Extensão: ");
					String extensao = Console.readString();
					
					System.out.print("Profundidade da busca: ");
					int prof = Console.readInt();
					
					try {
						System.out.println(c.buscaExtensao(extensao, prof));
					} catch(TipoNaoEncontradoException e) {
						System.out.println(e.getMessage());
					} catch(ProfundidadeInvalidaException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				case 3:
					System.out.print("Nome do diretório: ");
					String pasta = Console.readString();
					
					System.out.print("Profundidade da busca: ");
					int profund = Console.readInt();
					
					try {
						System.out.println(c.buscaPasta(pasta, profund));
					} catch(PastaNaoEncontradaException e) {
						System.out.println(e.getMessage());
					} catch(ProfundidadeInvalidaException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					System.out.print("Caminho onde o arquivo deve ser gerado: ");
					String caminho = Console.readString();
					System.out.print("Nome do arquivo a ser gerado: ");
					String nomeArquivo = Console.readString();
					
					System.out.print("Profundidade da busca: ");
					int profundidade = Console.readInt();
					
					try {
						c.geraArquivo(profundidade, caminho, nomeArquivo);
					} catch(NaoEhPastaException e) {
						System.out.println(e.getMessage());
					} catch(PastaNaoEncontradaException e) {
						System.out.println(e.getMessage());
					} catch(ProfundidadeInvalidaException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5:
					System.out.println("SGAD encerrado");
					System.exit(0);
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
	}
}
