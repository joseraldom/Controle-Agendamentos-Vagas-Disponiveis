package com.teste.pratico.domain.dto;

import java.time.LocalDate;

public class PeriodoDTO {
	
	private LocalDate data;

	private LocalDate inicio;
	
	private LocalDate fim;
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

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
}
