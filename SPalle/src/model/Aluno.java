/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 30/07/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package model;

public class Aluno {

	public Aluno(String nome, String nacionalidade, String pais, String cidade, String estado, String bairro,
            String rua, int numero, int cep, String telefone, String opcao1, String opcao2) {
		
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.pais = pais;
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.telefone = telefone;
		this.opcao1 = opcao1;
		this.opcao2 = opcao2;
		ativo = true;
		sorteado = false;
		selecionado = false;
		
		totalAlunos++;
		id = totalAlunos;
	}
	
	//contador estático para gerar os IDs do aluno
	private static int totalAlunos = 0;
	
	private Integer id;
	private String nome;
	private String nacionalidade;
	private String pais;
	private String estado;
	private int cep;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private String telefone;
	private String opcao1;
	private String opcao2;
	
	//ativo é false se o aluno desistir
	private boolean ativo;

	//garantir que um aluno não seja sorteado ou selecionado mais de uma vez
	private boolean sorteado;
	private boolean selecionado;
	
	public void setSelecionado() {
		selecionado = true;
	}
	
	public boolean isSelecionado() {
		return selecionado;
	}

	public Integer getId() {
		return id;
	}

	public String getOpcao1() {
		return opcao1;
	}

	public String getOpcao2() {
		return opcao2;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setInativo() {
		ativo = false;
	}
	
	public void setSorteado() {
		sorteado = true;
	}
	
	public boolean isSorteado() {
		return sorteado;
	}
	
	//retorna String com os dados do aluno
	public String toString() {
		return "Nome: " + nome + 
				"\nNacionalidade: " + nacionalidade + 
				"\nEndereço: " + rua + ", " + numero + ", " + bairro + ", " + cidade + ", " + estado + ", " + pais + 
				"\nCEP: " + cep + 
				"\nTelefone: " + telefone + 
				"\nOpção 1: " + opcao1 + 
				"\nOpção 2: " + opcao2 +
				"\nAtivo: " + ativo +
				"\nSorteado: " + sorteado +
				"\nSelecionado: " + selecionado +
				"\n";
	}
	
}
