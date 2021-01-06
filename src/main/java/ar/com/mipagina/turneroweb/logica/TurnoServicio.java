package ar.com.mipagina.turneroweb.logica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ar.com.mipagina.turneroweb.modelo.Categoria;
import ar.com.mipagina.turneroweb.modelo.Turno;
import ar.com.mipagina.turneroweb.persistencia.CategoriaRepositorio;
import ar.com.mipagina.turneroweb.persistencia.TurnoRepositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicio {
	
	@Autowired
	private TurnoRepositorio repositorio;
	@Autowired
	private CategoriaRepositorio repositorioCategoria;

	public Page<Turno> listarTodos(Pageable pagina) {		
		return repositorio.findAll(pagina);
	}
	
	public Optional<Turno> getTurno(Integer id) {
		//return repositorio.getOne(id); // getOne() no anda con el postgres por cosas del lazy fetch.
		return repositorio.findById(id);
	}
	
	public Page<Turno> getTurnosPorFecha(Date fecha, Pageable pagina) {
		return repositorio.findByFecha(fecha, pagina);
	}

	public Page<Turno> getTurnosPorNombreApellido(String nombre, Pageable pagina) {
		return repositorio.findByNombreApellidoContainingIgnoreCase(nombre, pagina);
	}
	
	public Page<Turno> getTurnosPorNombreApellidoYFecha(String nombre, Date fecha, Pageable pagina) {
		return repositorio.findByNombreApellidoContainingIgnoreCaseAndFecha(nombre, fecha, pagina);
	}
	
	public Turno guardar(Turno t) {
		return repositorio.save(t);
	}

	public Turno actualizar(Turno t, Integer id) {		
		if (t.getId() == null) {
			throw new RuntimeException("Error: El objeto no tiene id.");
		} else if (t.getId() != id) {
			throw new RuntimeException("Error: El id del objeto no coincide con el id proporcionado.");
		} else if (repositorio.getOne(id).getYaTranscurrio()) {
			throw new RuntimeException("Error: No se puede modificar un turno cuya fecha/hora ya ha transcurrrido.");
		} else if (t.getYaTranscurrio()) {
			throw new RuntimeException("Error: La fecha/hora indicada ya ha transcurrrido.");
		}
		
		return repositorio.save(t);
	}

	public void eliminar(Integer id) {
		if (repositorio.getOne(id).getYaTranscurrio()) {
			throw new RuntimeException("Error: No se puede eliminar un turno cuya fecha/hora ya ha transcurrrido.");
		}
		
		repositorio.deleteById(id);
	}

	public Page<Turno> getTurnosPorCategoriaId(Integer categoriaId, Pageable pagina) {
		Categoria categoria = repositorioCategoria.getOne(categoriaId);
		
		return this.repositorio.findByCategoria(categoria, pagina);
	}

	public Page<Turno> getTurnosPorNombreApellidoYCategoriaId(String nombreApellido, Integer categoriaId,
			Pageable pagina) {
		Categoria categoria = repositorioCategoria.getOne(categoriaId);
		
		return this.repositorio.findByNombreApellidoContainingIgnoreCaseAndCategoria(nombreApellido, categoria, pagina);
	}

	public Page<Turno> getTurnosPorCategoriaIdYFecha(Integer categoriaId, Date fecha, Pageable pagina) {
		Categoria categoria = repositorioCategoria.getOne(categoriaId);
		
		return this.repositorio.findByCategoriaAndFecha(categoria, fecha, pagina);
	}

	public Page<Turno> getTurnosPorNombreApellidoYCategoriaIdYFecha(String nombreApellido, Integer categoriaId,
			Date fecha, Pageable pagina) {
		Categoria categoria = repositorioCategoria.getOne(categoriaId);
		
		return this.repositorio.findByNombreApellidoContainingIgnoreCaseAndCategoriaAndFecha(nombreApellido, categoria, fecha, pagina);
	}
}
