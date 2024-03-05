package com.teste.pratico.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.PeriodoDTO;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.services.AgendamentoService;
import com.teste.pratico.services.SolicitanteService;

@Component
@ViewScoped
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;
	
	@Autowired
	private SolicitanteService solicitanteService;
	
	private List<AgendamentoDTO> agendamentos;
	
	private boolean cadastrando;
	
	private AgendamentoDTO agendamento;
	
	private SolicitanteDTO solicitanteSelecionado;
	
	private List<LocalDate> range;
	
	private String numeroFiltro;
	
	private SolicitanteDTO solicitanteSelecionadoFiltro;
	
	@PostConstruct
	private void inicializar() {
		carregaAgendamentos();
	}
	
	private void carregaAgendamentos() {
		agendamentos = service.findAll();
	}
	
	public void novoAgendamento() {
		agendamento = new AgendamentoDTO();
		solicitanteSelecionado = new SolicitanteDTO();
		cadastrando = true;
	}
	
	public void salvarAgendamento() {
		try {
			agendamento.setSolicitanteId(service.setaSolicitante(solicitanteSelecionado));
			service.save(agendamento);
			cadastrando = false;
			carregaAgendamentos();
			addMessage(FacesMessage.SEVERITY_INFO, "Agendamento feito com sucesso.", "");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
	}
	
	public void cancelarNovoAgendamento() {
		cadastrando = false;
	}
	
	public List<SolicitanteDTO> completeSolicitantes(String value) {
		return solicitanteService.findListByNome(value);
	}
	
	public void consultaPorPeriodo() {
		PeriodoDTO periodoDTO = new PeriodoDTO();
		
		if (range != null && !range.isEmpty()) {
			periodoDTO.setInicio(range.get(0));
			periodoDTO.setFim(range.get(1));
		}
		
		agendamentos = service.buscar(periodoDTO, numeroFiltro, solicitanteSelecionadoFiltro);
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public List<AgendamentoDTO> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public boolean isCadastrando() {
		return cadastrando;
	}

	public void setCadastrando(boolean cadastrando) {
		this.cadastrando = cadastrando;
	}

	public AgendamentoDTO getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(AgendamentoDTO agendamento) {
		this.agendamento = agendamento;
	}

	public SolicitanteDTO getSolicitanteSelecionado() {
		return solicitanteSelecionado;
	}

	public void setSolicitanteSelecionado(SolicitanteDTO solicitanteSelecionado) {
		this.solicitanteSelecionado = solicitanteSelecionado;
	}

	public List<LocalDate> getRange() {
		return range;
	}

	public void setRange(List<LocalDate> range) {
		this.range = range;
	}

	public String getNumeroFiltro() {
		return numeroFiltro;
	}

	public void setNumeroFiltro(String numeroFiltro) {
		this.numeroFiltro = numeroFiltro;
	}

	public SolicitanteDTO getSolicitanteSelecionadoFiltro() {
		return solicitanteSelecionadoFiltro;
	}

	public void setSolicitanteSelecionadoFiltro(SolicitanteDTO solicitanteSelecionadoFiltro) {
		this.solicitanteSelecionadoFiltro = solicitanteSelecionadoFiltro;
	}
}
