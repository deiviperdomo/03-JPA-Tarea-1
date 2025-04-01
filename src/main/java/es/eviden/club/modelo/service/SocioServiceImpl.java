package es.eviden.club.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eviden.club.entities.Socio;
import es.eviden.club.modelo.rpository.SocioRepository;

@Service
public class SocioServiceImpl implements SocioService {

	@Autowired
	SocioRepository socioRepo;
	
	@Override
	public Socio alta(Socio entidad) {
		if (socioRepo.existsById(entidad.getId())) {
			return null;
		} else {
			return socioRepo.save(entidad);
		}
	}

	@Override
	public Socio modificar(Socio entidad) {
		try {
			if (socioRepo.existsById(entidad.getId())) {
				return socioRepo.save(entidad);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int eliminar(Socio entidad) {
		return eliminarPorId(entidad.getId());
	}

	@Override
	public int eliminarPorId(Long id) {
		try {
			if (socioRepo.existsById(id)) {
				socioRepo.deleteById(id);
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
	public Socio buscarPorId(Long id) {
		return socioRepo.findById(id).orElse(null);
	}

	@Override
	public List<Socio> buscarTodos() {
		return socioRepo.findAll();
	}

}
