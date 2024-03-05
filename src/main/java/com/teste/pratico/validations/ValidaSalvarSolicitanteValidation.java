package com.teste.pratico.validations;

import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.SolicitanteDTO;

@Component
public class ValidaSalvarSolicitanteValidation implements SalvaSolicitanteValidations{

	@Override
	public void validar(SolicitanteDTO dto) {
		if (dto == null) {
			throw new RuntimeException("Escolha Solicitante para o agendamento.");
		}
	}

}
