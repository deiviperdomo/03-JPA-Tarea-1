package es.eviden.club.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.eviden.club.entities.Salida;
import es.eviden.club.modelo.rpository.SalidaRpository;

public class SalidaServiceImpl implements SalidaService {

	@Autowired
	SalidaRpository salidaRepo;
	
	@Override
	public Salida alta(Salida entidad) {
		if (salidaRepo.existsById(entidad.getId())) {
			return null;
		} else {
			return salidaRepo.save(entidad);
		}
	}

	@Override
	public Salida modificar(Salida entidad) {
		try {
			if (salidaRepo.existsById(entidad.getId())) {
				return salidaRepo.save(entidad);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int eliminar(Salida entidad) {
		return eliminarPorId(entidad.getId());
	}

	@Override
	public int eliminarPorId(Long id) {
		try {
			if (salidaRepo.existsById(id)) {
				salidaRepo.deleteById(id);
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
	public Salida buscarPorId(Long id) {
		return salidaRepo.findById(id).orElse(null);
	}

	@Override
	public List<Salida> buscarTodos() {
		return salidaRepo.findAll();
	}

}
