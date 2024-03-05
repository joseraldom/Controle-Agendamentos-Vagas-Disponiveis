package com.teste.pratico.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.PeriodoDTO;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.domain.entity.Agendamento;
import com.teste.pratico.repositories.AgendamentoRepository;
import com.teste.pratico.repositories.SolicitanteRepository;
import com.teste.pratico.validations.SalvaAgendamentoValidations;
import com.teste.pratico.validations.SalvaSolicitanteValidations;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository repository;
	
	@Autowired
	private SolicitanteRepository solicitanteRepository;
	
	@Autowired
	private List<SalvaAgendamentoValidations> salvaAgendamentoValidations;
	
	@Autowired
	private List<SalvaSolicitanteValidations> salvaSolicitanteValidations;
	
	@Transactional
	public AgendamentoDTO save(AgendamentoDTO dto) {
		salvaAgendamentoValidations.forEach(v -> v.validar(dto));
		
		Agendamento agendamento = dto.montaAgendamento(solicitanteRepository);
		
		return AgendamentoDTO.montaDTO(repository.save(agendamento), solicitanteRepository);
	}
	
	@Transactional
	public List<AgendamentoDTO> findByPeriodo(PeriodoDTO dto) {
		List<Agendamento> agendamentos = repository.findByPeriodo(dto.getInicio(), dto.getFim());
		
		return agendamentos.stream().map(a -> AgendamentoDTO.montaDTO(a, solicitanteRepository)).collect(Collectors.toList());
	}
	
	@Transactional
	public List<AgendamentoDTO> findAll() {
		List<Agendamento> agendamentos = repository.findAll();
		
		return agendamentos.stream().map(a -> AgendamentoDTO.montaDTO(a, solicitanteRepository)).collect(Collectors.toList());
	}

	public Long setaSolicitante(SolicitanteDTO dto) {
		salvaSolicitanteValidations.forEach(v -> v.validar(dto));
		return dto.getId();
	}
	
	@Transactional
	public List<AgendamentoDTO> buscar(PeriodoDTO dto, String numero, SolicitanteDTO solicitanteDTO) {
		String numeroParam = numero == null || numero.trim().isEmpty() ? null : numero;
		Long idSolicitante = solicitanteDTO == null ? null : solicitanteDTO.getId();
		List<Agendamento> agendamentos = null;
		
		if (dto == null || dto.getInicio() == null || dto.getFim() == null) {
			agendamentos = repository.buscar(numeroParam, idSolicitante);
		} else {
			agendamentos = repository.buscar(dto.getInicio(), dto.getFim(), numeroParam, idSolicitante);
		}
		
		return agendamentos.stream().map(a -> AgendamentoDTO.montaDTO(a, solicitanteRepository)).collect(Collectors.toList());
	}
}
