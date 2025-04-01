package es.eviden.club.modelo.rpository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eviden.club.entities.Patron;

public interface PatronRepository extends JpaRepository<Patron, Long> {

}
