package br.com.proway.cadastro;

import java.io.Serializable;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private int idade;
	private String cpf;

	public Cliente(String nome, int idade, String cpf) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}

	public Cliente(String nome) {
		this(nome, 0, "");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean equals(Object obj) {
		Cliente outro = (Cliente) obj; // type cast para transforma o obj generico em um obj Aluno
		return this.nome.equals(outro.getNome());
	}

}
