/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 30/07/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package view;
import java.io.IOException;

import controller.*;
import util.Console;
import util.Iterador;


public class Menu {
	private static Controller controle = new Controller();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		while (true) {
			System.out.println("1 - Cadastrar aluno");
			System.out.println("2 - Consultar aluno");
			System.out.println("3 - Cadastrar curso");
			System.out.println("4 - Consultar curso");
			System.out.println("5 - Realizar sorteio");
			System.out.println("6 - Listar lista de reservas");
			System.out.println("7 - Listar alunos selecionados");
			System.out.println("8 - Encerrar programa");
			int menu = 0;
					
			menu = Console.readInt();
					
			switch (menu) {
			case 1:
				menuCadastroAluno();
				break;
			case 2:
				menuConsultaAluno();		
				break;
			case 3:
				menuCadastroCurso();
				break;
			case 4:
				menuConsultaCurso();
				break;
			case 5:
				controle.sortearVagas();
				break;
			case 6:
				//percorre o Iterador da lista de reservas e exibe os dados dos alunos
				Iterador itListaReserva = controle.listarAlunosReserva();
				
				if (itListaReserva != null) {
					while (itListaReserva.temProximo()) {
						System.out.println(itListaReserva.obterAtual());
						itListaReserva.obterProximo();
					}
				}
				
				break;
			case 7:
				//percorre o Iterador da lista de alunos de determinado Curso e exibe os dados deles
				System.out.print("Curso: ");
				String curso = Console.readString();
				
				Iterador itListaSelecionados = controle.listarSelecionados(curso);
				
				if (itListaSelecionados != null) {
					while (itListaSelecionados.temProximo()) {
						System.out.println(itListaSelecionados.obterAtual());
						itListaSelecionados.obterProximo();
					}
				}
				break;
			case 8:
				System.out.println("Programa encerrado");
				return;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}
	
	//exibe o menu para o cadastro de um Curso
	public static void menuCadastroCurso() throws IOException {
		String nome = "";
		int qtdVagas = 0;

		System.out.print("Nome: ");
		nome = Console.readString();
		
		System.out.print("Quantidade de vagas: ");
		qtdVagas = Console.readInt();
		
		controle.cadastrarCurso(nome, qtdVagas);
		System.out.println("Curso de " + nome + " cadastrado com sucesso!");		
	}
	
	//exibe o menu para a consulta de um Curso
	public static void menuConsultaCurso() throws NumberFormatException, IOException {
		int id = 0;

		System.out.print("ID: ");
		id = Console.readInt();
		
		Object resultado = controle.consultarCurso(id);
		
		if (resultado == null)
			System.out.println("Curso não encontrado!");
		else {
			System.out.println(resultado.toString());
			System.out.println("1 - Voltar");
			System.out.println("2 - Alterar quantidade de vagas");
			
			int menu = Console.readInt();
			
			if (menu == 2) {
				menuAlterarVagas(resultado);
			}
		}
		
	}
	
	//exibe o menu para alterar as vagas de um Curso
	public static void menuAlterarVagas(Object o) throws NumberFormatException, IOException {
		System.out.print("Nova quantidade de vagas: ");
		int qtd = Console.readInt();
		
		controle.alterarVagas(o, qtd);
	}
	
	//exibe o menu para o cadastro de um Aluno
	public static void menuCadastroAluno() throws IOException {
				System.out.print("Nome: ");
				String nome = Console.readString();

				System.out.print("Nacionalidade: ");
				String nacionalidade = Console.readString();

				System.out.print("País: ");
				String pais = Console.readString();

				System.out.print("Estado: ");
				String estado = Console.readString();

				System.out.print("Cidade: ");
				String cidade = Console.readString();

				System.out.print("Bairro: ");
				String bairro = Console.readString();

				System.out.print("Rua: ");
				String rua = Console.readString();

				System.out.print("Número: ");
				int numero = Console.readInt();

				System.out.print("CEP: ");
				int cep = Console.readInt();

				System.out.print("Telefone: ");
				String telefone = Console.readString();
				
				System.out.print("Opção 1: ");
				String opcao1 = Console.readString();
				
				System.out.print("Opção 2: ");
				String opcao2 = Console.readString();
				
				controle.cadastrarAluno(nome, nacionalidade, pais, cidade, estado, bairro, rua, numero, cep, telefone, opcao1, opcao2);
	}

	//exibe o menu para a consulta de um Aluno
	public static void menuConsultaAluno() throws NumberFormatException, IOException {
		System.out.print("ID: ");
		int id = Console.readInt();
		
		Object resultado = controle.consultarAluno(id);
		
		if(resultado == null) 
			System.out.println("Aluno não encontrado");
		else {
			System.out.println(resultado.toString());
			System.out.println("1 - Voltar");
			System.out.println("2 - Desistência");
			
			int menu = Console.readInt();
			
			if (menu == 2) {
				System.out.println(controle.desistencia(resultado));				
			}
		}
		
		
	}
}

