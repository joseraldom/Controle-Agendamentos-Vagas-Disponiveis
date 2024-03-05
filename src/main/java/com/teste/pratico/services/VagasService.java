package com.teste.pratico.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.domain.entity.Vagas;
import com.teste.pratico.repositories.VagasRepository;
import com.teste.pratico.validations.SalvaVagasValidations;

@Service
public class VagasService {

	@Autowired
	private VagasRepository repository;
	
	@Autowired
	private List<SalvaVagasValidations> salvaVagasValidations;
	
	@Transactional
	public VagasDTO save(VagasDTO dto) {
		salvaVagasValidations.forEach(v -> v.validar(dto));
		
		Vagas vaga = dto.montaVaga();
		
		return VagasDTO.montaDTO(repository.save(vaga));
	}
	
	@Transactional
	public List<VagasDTO> findAll() {
		List<Vagas> vagas = repository.findAll();
		
		return vagas.stream().map(v -> VagasDTO.montaDTO(v)).collect(Collectors.toList());
	}
	
//	@Transactional
//	public VagasDTO findByData(LocalDate data) {
//		List<Vagas> vagas = repository.findAll();
//		VagasDTO vagaEncontrada = null;
//				
//		for (Vagas v : vagas) { 
//			Vagas vaga = repository.findByPeriodo(data);
//			
//			if (vaga != null) vagaEncontrada = VagasDTO.montaDTO(vaga);
//		}
//		
//		return vagaEncontrada;
//	}
	
	@Transactional
	public void delete(Long vagasId) {
		Vagas vaga = repository.findById(vagasId).get();
		
		repository.delete(vaga);
	}
}
