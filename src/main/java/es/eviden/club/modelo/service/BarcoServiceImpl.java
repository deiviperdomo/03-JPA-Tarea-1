package es.eviden.club.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eviden.club.entities.Barco;
import es.eviden.club.modelo.rpository.BarcoRepository;

@Service
public class BarcoServiceImpl implements BarcoService {

	@Autowired
	BarcoRepository barcoRepo;

	@Override
	public Barco alta(Barco entidad) {
		if (barcoRepo.existsById(entidad.getId())) {
			return null;
		} else {
			return barcoRepo.save(entidad);
		}
	}

	@Override
	public Barco modificar(Barco entidad) {
		try {
			if (barcoRepo.existsById(entidad.getId())) {
				return barcoRepo.save(entidad);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int eliminar(Barco entidad) {
		return eliminarPorId(entidad.getId());
	}

	@Override
	public int eliminarPorId(Long id) {
		try {
			if (barcoRepo.existsById(id)) {
				barcoRepo.deleteById(id);
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
	public Barco buscarPorId(Long id) {
		return barcoRepo.findById(id).orElse(null);
	}

	@Override
	public List<Barco> buscarTodos() {
		return barcoRepo.findAll();
	}
	
	
}
