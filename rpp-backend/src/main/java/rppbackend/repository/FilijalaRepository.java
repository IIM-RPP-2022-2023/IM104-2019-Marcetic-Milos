package rppbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rppbackend.model.Filijala;

public interface FilijalaRepository extends JpaRepository<Filijala, Integer> {

	List<Filijala> findByAdresaContainingIgnoreCase(String adresa);
	
}
