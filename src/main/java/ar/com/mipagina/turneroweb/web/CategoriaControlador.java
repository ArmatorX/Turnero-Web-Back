package ar.com.mipagina.turneroweb.web;

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

import ar.com.mipagina.turneroweb.logica.CategoriaServicio;
import ar.com.mipagina.turneroweb.modelo.Categoria;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaControlador {
	 @Autowired
	 private CategoriaServicio servicio;
	 
	 @GetMapping
	 public List<Categoria> listarTodos() {
		 return servicio.listarTodos();
	 }
	 
	 @GetMapping(value = "/y", params = {"nombre", "descripcion"})
	 public Page<Categoria> listarTodosFiltradoPorNombreYDescripcion(String nombre, String descripcion, Pageable pagina) {
		 return servicio.listarTodosFiltradoPorNombreYDescripcion(nombre, descripcion, pagina);
	 }
	 
	 @GetMapping(value = "/o", params = {"nombre", "descripcion"})
	 public Page<Categoria> listarTodosFiltradoPorNombreODescripcion(String nombre, String descripcion, Pageable pagina) {
		 return servicio.listarTodosFiltradoPorNombreODescripcion(nombre, descripcion, pagina);
	 }
	 
	 @GetMapping(params = {"nombre"})
	 public Page<Categoria> listarTodosFiltradosPorNombre(String nombre, Pageable pagina) {
		 return servicio.listarTodosFiltradosPorNombre(nombre, pagina);
	 }

	 @GetMapping(params = {"descripcion"})
	 public Page<Categoria> listarTodosFiltradosPorDescripcion(String descripcion, Pageable pagina) {
		 return servicio.listarTodosFiltradosPorDescripcion(descripcion, pagina);
	 }
	 
	 @RequestMapping(value = "/{id}")
	 public Optional<Categoria> getCategoria(@PathVariable(name = "id") Integer id) {
		 return servicio.getCategoria(id);
	 }
	 
	 @PostMapping
	 public Categoria guardar(@RequestBody Categoria c) {
		 return servicio.guardar(c);
	 }
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	 public Categoria actualizar(@RequestBody Categoria c, @PathVariable(name = "id") Integer id) {
		 return servicio.actualizar(c, id); 
	 }
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public void eliminar(@PathVariable(name = "id") Integer id) {
	 	servicio.eliminar(id);
	 }
}
