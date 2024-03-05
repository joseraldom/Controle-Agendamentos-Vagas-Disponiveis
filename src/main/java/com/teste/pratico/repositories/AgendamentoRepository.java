package com.teste.pratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.entity.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	@Query(value = "SELECT a FROM Agendamento a WHERE a.data BETWEEN ?1 AND ?2")
	List<Agendamento> findByPeriodo(LocalDate inicio, LocalDate fim);
	
	@Query(value = "SELECT a FROM Agendamento a WHERE a.data BETWEEN ?1 AND ?2 and (?3 is null or a.numero = ?3) and (?4 is null or a.solicitante.id = ?4)")
	List<Agendamento> buscar(LocalDate inicio, LocalDate fim, String numero, Long idSolicitante);
	
	@Query(value = "SELECT a FROM Agendamento a WHERE (?1 is null or a.numero = ?1) and (?2 is null or a.solicitante.id = ?2)")
	List<Agendamento> buscar(String numero, Long idSolicitante);
}
