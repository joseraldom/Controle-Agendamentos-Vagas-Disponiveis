package com.teste.pratico.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.services.VagasService;

@Component
@ViewScoped
public class VagasController {
	
	@Autowired
	private VagasService service;
	
	private boolean cadastrando;
	
	private VagasDTO vaga;
	
	private List<VagasDTO> vagas;
	
	@PostConstruct
	private void inicializar() {
		carregaVagas();
	}

	private void carregaVagas() {
		vagas = service.findAll();
	}
	
	public void novaVaga() {
		vaga = new VagasDTO(null, null, null, null);
		cadastrando = true;
	}
	
	public void cancelarNovaVaga() {
		cadastrando = false;
	}
	
	public void salvarVaga() {
		try {
			service.save(vaga);
			cadastrando = false;
			carregaVagas();
			addMessage(FacesMessage.SEVERITY_INFO, "Vaga cadastrado com sucesso.", "");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public boolean isCadastrando() {
		return cadastrando;
	}

	public void setCadastrando(boolean cadastrando) {
		this.cadastrando = cadastrando;
	}

	public VagasDTO getVaga() {
		return vaga;
	}

	public void setVaga(VagasDTO vaga) {
		this.vaga = vaga;
	}

	public List<VagasDTO> getVagas() {
		return vagas;
	}

	public void setVagas(List<VagasDTO> vagas) {
		this.vagas = vagas;
	}
}
