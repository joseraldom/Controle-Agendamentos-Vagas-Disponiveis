package com.teste.pratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.entity.Solicitante;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {

	@Query(value = "SELECT s FROM Solicitante s WHERE LOWER(s.nome) LIKE LOWER(CONCAT('%', :value, '%'))")
	List<SolicitanteDTO> findListByNome(String value);

}
