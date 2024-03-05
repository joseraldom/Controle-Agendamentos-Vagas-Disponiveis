package com.teste.pratico.domain.dto;

import java.time.LocalDate;

import com.teste.pratico.domain.entity.Agendamento;
import com.teste.pratico.domain.entity.Solicitante;
import com.teste.pratico.repositories.SolicitanteRepository;

public class AgendamentoDTO {

	private Long id;
	
	private LocalDate data;
	
	private String numero;
	
	private String motivo;
	
	private Long solicitanteId;

	private String solicitanteNome;

	public AgendamentoDTO() {
		
	}
	
	public AgendamentoDTO(Long id, LocalDate data, String numero, String motivo, Long solicitanteId, String solicitanteNome) {
		this.id = id;
		this.data = data;
		this.numero = numero;
		this.motivo = motivo;
		this.solicitanteId = solicitanteId;
		this.solicitanteNome = solicitanteNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Long getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(Long solicitanteId) {
		this.solicitanteId = solicitanteId;
	}
	

	public String getSolicitanteNome() {
		return solicitanteNome;
	}

	public void setSolicitanteNome(String solicitanteNome) {
		this.solicitanteNome = solicitanteNome;
	}

	public Agendamento montaAgendamento(SolicitanteRepository solicitanteRepository) {
		Agendamento agendamento = new Agendamento();
		Solicitante solicitante = solicitanteRepository.findById(solicitanteId).get();
		
		agendamento.setData(data);
		agendamento.setNumero(numero);
		agendamento.setMotivo(motivo);
		agendamento.setSolicitante(solicitante);
		
		return agendamento;
	}
	
	public static AgendamentoDTO montaDTO(Agendamento agendamento, SolicitanteRepository solicitanteRepository) {
		return new AgendamentoDTO(
				agendamento.getId(),
				agendamento.getData(),
				agendamento.getNumero(),
				agendamento.getMotivo(),
				agendamento.getSolicitante().getId(),
				solicitanteRepository.findById(agendamento.getSolicitante().getId()).get().getNome()
		);
	}
}
