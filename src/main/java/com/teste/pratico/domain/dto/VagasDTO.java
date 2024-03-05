package com.teste.pratico.domain.dto;

import java.time.LocalDate;

import com.teste.pratico.domain.entity.Vagas;

public class VagasDTO {

	private Long id;
	
	private LocalDate inicio;
	
	private LocalDate fim;
	
	private Integer quantidade;
	
	public VagasDTO() {
		
	}
	
	public VagasDTO(Long id, LocalDate inicio, LocalDate fim, Integer quantidade) {
		
		this.id = id;
		this.inicio = inicio;
		this.fim = fim;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Vagas montaVaga() {
		Vagas vaga = new Vagas();
		
		vaga.setInicio(inicio);
		vaga.setFim(fim);
		vaga.setQuantidade(quantidade);
		
		return vaga;
	}
	
	public static VagasDTO montaDTO(Vagas vaga) {
		return new VagasDTO(vaga.getId(), vaga.getInicio(), vaga.getFim(), vaga.getQuantidade());
	}
}
