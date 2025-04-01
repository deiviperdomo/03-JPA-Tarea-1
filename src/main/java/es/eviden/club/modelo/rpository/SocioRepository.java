package es.eviden.club.modelo.rpository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eviden.club.entities.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {

}
