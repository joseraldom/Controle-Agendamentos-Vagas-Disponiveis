package com.teste.pratico.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.repositories.VagasRepository;

@Component
public class ValidaPeriodoSalvarVagasValidation implements SalvaVagasValidations {

	@Autowired
	private VagasRepository vagasRepository;
	
	@Override
	public void validar(VagasDTO dto) {
		if (dto.getInicio() == null || dto.getFim() == null) {
			throw new RuntimeException("Escolha um Periodo para cadastrar a vaga.");			
		}
		
		if (dto.getFim().isBefore(dto.getInicio())) {
			throw new RuntimeException("A Data Inicial não pode ser após a Data Final.");						
		}
		
		if (vagasRepository.countVagasNoPeriodo(dto.getInicio(), dto.getFim()) > 0) {
			throw new RuntimeException("Já existe vagas cadastradas para esse periodo.");
		}
	}
}
