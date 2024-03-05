package com.teste.pratico.domain.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Vagas extends EntidadeBasica {

	@NotNull
	private LocalDate inicio;
	
	@NotNull
	private LocalDate fim;
	
	@NotNull
	private Integer quantidade;

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
