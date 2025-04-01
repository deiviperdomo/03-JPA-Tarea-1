package es.eviden.club.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.eviden.club.entities.Patron;
import es.eviden.club.modelo.rpository.PatronRepository;

public class PatronServiceImpl implements PatronService {

	@Autowired
	PatronRepository patronRepo;
	
	@Override
	public Patron alta(Patron entidad) {
		if (patronRepo.existsById(entidad.getId())) {
			return null;
		} else {
			return patronRepo.save(entidad);
		}
	}

	@Override
	public Patron modificar(Patron entidad) {
		try {
			if (patronRepo.existsById(entidad.getId())) {
				return patronRepo.save(entidad);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int eliminar(Patron entidad) {
		return eliminarPorId(entidad.getId());
	}

	@Override
	public int eliminarPorId(Long id) {
		try {
			if (patronRepo.existsById(id)) {
				patronRepo.deleteById(id);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Patron buscarPorId(Long id) {
		return patronRepo.findById(id).orElse(null);
	}

	@Override
	public List<Patron> buscarTodos() {
		return patronRepo.findAll();
	}

}
