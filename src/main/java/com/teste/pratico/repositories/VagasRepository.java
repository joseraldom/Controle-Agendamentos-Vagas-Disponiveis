package com.teste.pratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.entity.Vagas;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

	@Query(value = "SELECT v FROM Vagas v WHERE ?1 BETWEEN v.inicio AND v.fim")
	Vagas findByPeriodo(LocalDate data);
	
	@Query(value = "SELECT COUNT(v) FROM Vagas v WHERE ((?1 BETWEEN v.inicio AND v.fim) OR (?2 BETWEEN v.inicio AND v.fim))")
    Long countVagasNoPeriodo(LocalDate inicio, LocalDate fim);
}
