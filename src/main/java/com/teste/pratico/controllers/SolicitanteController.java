package com.teste.pratico.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.services.SolicitanteService;

@Component
@ViewScoped
public class SolicitanteController {
	
	@Autowired
	private SolicitanteService service;

	private SolicitanteDTO solicitante;
	
	private List<SolicitanteDTO> solicitantes;
	
	private boolean cadastrando;
	
	@PostConstruct
	private void inicializar() {
		carregarSolicitatntes();
	}

	private void carregarSolicitatntes() {
		solicitantes = service.findAll();
	}
	
	public void novoSolicitante() {
		solicitante = new SolicitanteDTO();
		cadastrando = true;
	}
	
	public void salvarCadastro() {
		if (solicitante.getNome() == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Todos os campos são obrigatórios.", "");
		} else {
			service.save(solicitante);
			cadastrando = false;
			carregarSolicitatntes();			
			addMessage(FacesMessage.SEVERITY_INFO, "Solicitante cadastrado com sucesso.", "");
		}
	}
	
	public void cancelarNovoSolicitante() {
		cadastrando = false;
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public SolicitanteDTO getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(SolicitanteDTO solicitante) {
		this.solicitante = solicitante;
	}

	public List<SolicitanteDTO> getSolicitantes() {
		return solicitantes;
	}

	public void setSolicitantes(List<SolicitanteDTO> solicitantes) {
		this.solicitantes = solicitantes;
	}

	public boolean isCadastrando() {
		return cadastrando;
	}

	public void setCadastrando(boolean cadastrando) {
		this.cadastrando = cadastrando;
	}
}
