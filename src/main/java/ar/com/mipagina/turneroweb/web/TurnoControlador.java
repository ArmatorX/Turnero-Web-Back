package ar.com.mipagina.turneroweb.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import ar.com.mipagina.turneroweb.logica.TurnoServicio;
import ar.com.mipagina.turneroweb.modelo.Turno;

@RestController 
@RequestMapping("/turno")
@CrossOrigin(origins = "*")	
public class TurnoControlador {
	
	@Autowired
	private TurnoServicio servicio;
	
	@GetMapping
	public Page<Turno> listarTodos(Pageable pagina) {
		return servicio.listarTodos(pagina);
	}

	@RequestMapping(value = "/{id}")
	public Optional<Turno> getTurno(@PathVariable(name = "id") Integer id) {
		return servicio.getTurno(id);
	}
	
	@GetMapping(params = {"fecha"})
	public Page<Turno> getTurnosPorFecha(Date fecha, Pageable pagina) {
		return servicio.getTurnosPorFecha(fecha, pagina);
	}
	
	@GetMapping(params = {"nombreApellido"})
	public Page<Turno> getTurnosPorNombreApellido(String nombreApellido, Pageable pagina) {
		return servicio.getTurnosPorNombreApellido(nombreApellido, pagina);
	}
	
	@GetMapping(params = {"categoriaId"})
	public Page<Turno> getTurnosPorCategoriaId(Integer categoriaId, Pageable pagina) {
		return servicio.getTurnosPorCategoriaId(categoriaId, pagina);
	}
	
	@GetMapping(params = {"nombreApellido", "fecha"})
	public Page<Turno> getTurnosPorNombreApellidoYFecha(String nombreApellido, Date fecha, Pageable pagina) {
		return servicio.getTurnosPorNombreApellidoYFecha(nombreApellido, fecha, pagina);
	}
	
	@GetMapping(params = {"nombreApellido", "categoriaId"})
	public Page<Turno> getTurnosPorNombreApellidoYCategoriaId(String nombreApellido, Integer categoriaId, Pageable pagina) {
		return servicio.getTurnosPorNombreApellidoYCategoriaId(nombreApellido, categoriaId, pagina);
	}
	
	@GetMapping(params = {"categoriaId", "fecha"})
	public Page<Turno> getTurnosPorCategoriaIdYFecha(Integer categoriaId, Date fecha, Pageable pagina) {
		return servicio.getTurnosPorCategoriaIdYFecha(categoriaId, fecha, pagina);
	}
	
	@GetMapping(params = {"nombreApellido", "categoriaId", "fecha"})
	public Page<Turno> getTurnosPorNombreApellidoYCategoriaIdYFecha(String nombreApellido, Integer categoriaId, Date fecha, Pageable pagina) {
		return servicio.getTurnosPorNombreApellidoYCategoriaIdYFecha(nombreApellido, categoriaId, fecha, pagina);
	}

	@PostMapping
	public Turno guardar(@RequestBody Turno t) {
		return servicio.guardar(t);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Turno actualizar(@RequestBody Turno t, @PathVariable(name = "id") Integer id) {
		return servicio.actualizar(t, id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable(name = "id") Integer id) {
		servicio.eliminar(id);
	}
}