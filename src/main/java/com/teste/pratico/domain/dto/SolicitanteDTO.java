package com.teste.pratico.domain.dto;

import com.teste.pratico.domain.entity.Solicitante;

public class SolicitanteDTO {

	private Long id;
	
	private String nome;
	
	public SolicitanteDTO() {
	}
	
	public SolicitanteDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public SolicitanteDTO(Solicitante solicitante) {
		this.id = solicitante.getId();
		this.nome = solicitante.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Solicitante montaSolicitante() {
		Solicitante solicitante = new Solicitante();
		
		solicitante.setNome(nome);
		
		return solicitante;
	}
	
	public static SolicitanteDTO montaDTO(Solicitante solicitante) {
		return new SolicitanteDTO(solicitante.getId(), solicitante.getNome());
	}
}
