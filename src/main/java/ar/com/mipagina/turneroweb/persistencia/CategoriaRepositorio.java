package ar.com.mipagina.turneroweb.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.com.mipagina.turneroweb.modelo.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer>{
	Page<Categoria> findByNombreContainingIgnoreCaseAndDescripcionContainingIgnoreCase(String nombre, String descripcion, Pageable pagina);
	
	Page<Categoria> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion, Pageable pagina);
	
	Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable pagina);
	
	Page<Categoria> findByDescripcionContainingIgnoreCase(String descipcion, Pageable pagina);
}
