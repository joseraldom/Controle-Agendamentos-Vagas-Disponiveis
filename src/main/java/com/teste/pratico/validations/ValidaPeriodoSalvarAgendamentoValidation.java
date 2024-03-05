package com.teste.pratico.validations;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.repositories.AgendamentoRepository;
import com.teste.pratico.repositories.VagasRepository;

@Component
public class ValidaPeriodoSalvarAgendamentoValidation implements SalvaAgendamentoValidations {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private VagasRepository vagasRepository;
	
	@Override
	public void validar(AgendamentoDTO agendamentoDTO) {
		VagasDTO vaga = VagasDTO.montaDTO(vagasRepository.findByPeriodo(agendamentoDTO.getData()));
		
		validaDatasPreenchidas(agendamentoDTO);
		
		validaDatasValidas(agendamentoDTO);
			
		validarVagasCadastradas(vaga);
		
		validarVagasDisponiveisData(vaga);
	}

	private void validaDatasPreenchidas(AgendamentoDTO agendamentoDTO) {
		if (agendamentoDTO.getData() == null) {
			throw new RuntimeException("Escolha uma Data para o agendamento.");
		}
	}

	private void validaDatasValidas(AgendamentoDTO agendamentoDTO) {
		if (agendamentoDTO.getData().isBefore(LocalDate.now())) {
			throw new RuntimeException("Não é permitido agendamento retroativo.");
		}
	}

	private void validarVagasCadastradas(VagasDTO vaga) {
		if (vaga == null) {
			throw new RuntimeException("Não existe Vagas para essa data.");
		}
	}

	private void validarVagasDisponiveisData(VagasDTO vaga) {
		if (agendamentoRepository.findByPeriodo(vaga.getInicio(), vaga.getFim()).size() >= vaga.getQuantidade()) {
			throw new RuntimeException("Vagas esgotadas para a Data selecionada.");
		}
	}
}
