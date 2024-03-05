package com.teste.pratico.domain.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Solicitante extends EntidadeBasica {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
