package com.teste.pratico.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.entity.Solicitante;
import com.teste.pratico.repositories.SolicitanteRepository;

@Service
public class SolicitanteService {
	
	@Autowired
	private SolicitanteRepository repository;
	
	@Transactional
	public SolicitanteDTO save(SolicitanteDTO dto) {
		if (dto.getId() == null) {
			Solicitante solicitante = dto.montaSolicitante();
			
			return SolicitanteDTO.montaDTO(repository.save(solicitante));
		} else {
			return null;
		}
	}
	
	@Transactional
	public List<SolicitanteDTO> findAll() {
		return repository.findAll().stream().map(SolicitanteDTO::new).collect(Collectors.toList());
	}
	
	@Transactional
	public List<SolicitanteDTO> findListByNome(String value) {
		return repository.findListByNome(value);
	}

	public SolicitanteDTO findById(Long solicitanteId) {
		return SolicitanteDTO.montaDTO(repository.findById(solicitanteId).get());
	}
}
