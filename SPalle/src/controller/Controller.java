/*
Autor: Matheus Galvão Correia
Componente Curricular: MI - Programação
Concluído em: 30/07/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum trecho de código de outro colega
ou de outro autor, tais como provindos de livros, apostilas e páginas ou documentos eletrônicos da Internet. Qualquer
trecho de código de outra autoria que não a minha está destacado com uma citação para o autor e a fonte do código, e
estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

package controller;
import util.*;

import java.util.Random;

import model.*;

public class Controller {
	
	//listas de alunos e cursos
	private ILista alunos;
	private ILista cursos;
	
	public Controller() {
		alunos = new Lista();
		cursos = new Lista();
	}

	//método que cadastra um Aluno no sistema
    public Aluno cadastrarAluno(String nome, String nacionalidade, String pais, String cidade, String estado, String bairro,
            String rua, int numero, int cep, String telefone, String opcao1, String opcao2) {
    	
    	//instância de um objeto do tipo Aluno
    	Aluno aluno = new Aluno(nome, nacionalidade, pais, cidade, estado, bairro, rua, numero, cep, telefone, opcao1, opcao2);
		//adiciona aluno na lista de alunos
    	alunos.inserirFinal(aluno);

        return aluno;
    }

    //método que retorna o Aluno a ser consultado
    public Aluno consultarAluno(Integer id) {
    	//cria um iterador para a lista de alunos
        Iterador it =  alunos.iterador();
        
        //percorre a lista procurando pelo aluno do ID escolhido
        while(it.temProximo()) {
        	Aluno aluno = (Aluno) it.obterAtual();
        	
        	if(aluno.getId() == id)
        		return aluno;
        	
        	it.obterProximo();
        }
        
        return null;

    }

    //método que cadastra um Curso no sistema
    public Curso cadastrarCurso(String nome, int qtdVagas) {    
    	//instância de um objeto do tipo Curso
    	Curso curso = new Curso(nome, qtdVagas);
    	//adiciona o curso na lista de cursos
    	cursos.inserirFinal(curso);

        return curso;
    }

  //método que retorna o Curso a ser consultado
    public Curso consultarCurso(Integer id) {
    	//cria um iterador para a lista de cursos
    	Iterador it = cursos.iterador();
    	
    	//percorre a lista procurando pelo curso do ID escolhido
    	while(it.temProximo()) {
    		Curso curso = (Curso) it.obterAtual();
    		
    		if(curso.getId() == id) {
    			return curso;
    		}
    		
    		it.obterProximo();		
    	}

        return null;

    }
 
    //método que altera as vagas de um Curso
    public void alterarVagas(Object o, int qtd) {
    	Curso curso = (Curso) o;
    	curso.setQtdVagas(qtd);
    }

    //método que realiza o sorteio 
    public void sortearVagas() {
    	//instância de um objeto do tipo Random
    	Random gerador = new Random();
    	int contador = 1;
    	
    	while(contador <= alunos.obterTamanho()) {
    		//sorteia um número
    		int sorteado = gerador.nextInt(alunos.obterTamanho() + 1);
    		
    		//não existe aluno com ID zero
    		if (sorteado == 0) {
    			continue;
    		}	

    		Iterador itAlunos = alunos.iterador();
    		
    		//procura pelo aluno de ID sorteado
    		while(itAlunos.temProximo()) {
    			Aluno aluno = (Aluno) itAlunos.obterAtual();
    			
    			//verifica se o aluno atual é o sorteado e se está apto para a seleção
    			if(aluno.isAtivo() && !aluno.isSorteado() && aluno.getId() == sorteado) {
    				aluno.setSorteado();
    				
    				//cursos escolhidos pelo aluno
    				String opcao1 = aluno.getOpcao1();
    				String opcao2 = aluno.getOpcao2();
    				
    				Iterador itCursos = cursos.iterador();
    				
    				//procura pelo curso escolhido
    				while(itCursos.temProximo()) {
    					Curso curso = (Curso) itCursos.obterAtual();
    					if(curso.getQtdVagas() > 0 && curso.getNome().equals(opcao1)) {
    						curso.adicionaAluno(aluno);
    						aluno.setSelecionado();
    						curso.setQtdVagas(curso.getQtdVagas() - 1);
    						break;
    					} else
    						if(curso.getQtdVagas() > 0 && curso.getNome().equals(opcao2)) {
    							curso.adicionaAluno(aluno);
    							aluno.setSelecionado();
    							curso.setQtdVagas(curso.getQtdVagas() - 1);
    							break;
    						}
    					itCursos.obterProximo();
    				}
    				break;
    			}
    			
    			itAlunos.obterProximo();
    		}
    		
    		//verifica se os cursos estão todos cheios
    		Iterador itCursos = cursos.iterador();
    		int cursosCheios = 0;
    		
    		while (itCursos.temProximo()) {
    			Curso curso = (Curso) itCursos.obterAtual();
    			if(curso.getQtdVagas() == 0)
    				cursosCheios++;
    			itCursos.obterProximo();
    		}
    		
    		if(cursosCheios == cursos.obterTamanho())
    			return;
    		
    		contador++;  		
    	}
    	
    }

    //método auxiliar para listar os alunos selecionados por curso
    public Iterador listarSelecionados(String curso) {
    	//procura o curso
    	Iterador it = cursos.iterador();
    	Curso cursoSelecionado = null;
    	
    	while (it.temProximo()) {
    		Curso c = (Curso) it.obterAtual();
    		if (c.getNome().equals(curso)) {
    			cursoSelecionado = c;
    		}
    		it.obterProximo();
    	}
    	return listarAlunosSelecionados(cursoSelecionado);
    }
    
    //retorna o iterador para a lista de alunos selecionados em dado curso
    public Iterador listarAlunosSelecionados(Curso c) { 	
    	if (c != null) {
	    	Iterador itAlunos = c.getAlunos();
	    	ILista listaSelecionados = new Lista();
	    	
	    	while (itAlunos.temProximo()) {
	    		Aluno aluno = (Aluno) itAlunos.obterAtual();
	    		if (aluno.isSelecionado()) {
	    			listaSelecionados.inserirFinal(aluno);
	    		}
	    		itAlunos.obterProximo();
	    	}
	    	
	    	return listaSelecionados.iterador();
    	}
    	
    	return null;
    }

    
    //retorna um iterador para a lista de alunos desistentes e não selecionados
    //aluno desistente - aluno.isAtivo == false
    //aluno nao selecionado - aluno.isSelecionado == false
    public Iterador listarAlunosReserva() {
    	Iterador itAlunos = alunos.iterador();
    	ILista listaReservas = new Lista();
    	
    	while (itAlunos.temProximo()) {
    		Aluno aluno = (Aluno) itAlunos.obterAtual();
    		//verifica se o aluno desistiu ou se não foi selecionado
    		if (!aluno.isAtivo() || !aluno.isSelecionado()) {
    			listaReservas.inserirFinal(aluno);
    		}
    		itAlunos.obterProximo();
    	}
    	
    	return listaReservas.iterador();
    }
    
    //método auxiliar para permitir a desistência de um aluno
    public String desistencia(Object o) {
    	Aluno a = (Aluno) o;
    	desistir(a);
    	
    	return "Aluno " + a.getId() + " na lista de reservas"; 
    }

    //realiza a desistência do aluno
    public void desistir(Aluno a) {
    	a.setInativo();
    }

   



}
