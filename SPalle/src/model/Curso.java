/*
Autor: Matheus Galv�o Correia
Componente Curricular: MI - Programa��o
Conclu�do em: 30/07/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum trecho de c�digo de outro colega
ou de outro autor, tais como provindos de livros, apostilas e p�ginas ou documentos eletr�nicos da Internet. Qualquer
trecho de c�digo de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte do c�digo, e
estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
*/

package model;
import util.ILista;
import util.Iterador;
import util.Lista;

public class Curso {
	
	public Curso(String nome, int qtdVagas) {
		this.nome = nome;
		this.qtdVagas = qtdVagas;
		
		alunos = new Lista();
		
		totalCursos++;
		id = totalCursos;
	}

	private int id;
	private String nome;
	private int qtdVagas;
	private ILista alunos;
	
	//contador est�tico para gerar os IDs do curso
	private static int totalCursos = 0;
	
	public void adicionaAluno (Object o) {
		alunos.inserirFinal(o);
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setQtdVagas(int qtd) {
		this.qtdVagas = qtd;
	}

	public int getQtdVagas() {
		return qtdVagas;
	}

	public int getTotalCursos() {
		return totalCursos;
	}
	
	//retorna uma String com os dados do curso
	public String toString() {
		return "Nome: " + getNome() + "\nVagas: " + getQtdVagas();
	}
	
	//retorna o iterador para a lista de alunos
	public Iterador getAlunos() {
		return alunos.iterador();
	}
	
	//equals sobrescrito para comparar os cursos pelo nome
	public boolean equals(Object o) {
		if (o instanceof Curso) {
			Curso c = (Curso) o;
			if (this.nome.equals(c))
				return true;
		}
		return false;
	}


}
