package ar.com.mipagina.turneroweb.persistencia;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.mipagina.turneroweb.modelo.Categoria;
import ar.com.mipagina.turneroweb.modelo.Turno;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, Integer>{

	Page<Turno> findByFecha(Date fecha, Pageable pagina);
	Page<Turno> findByNombreApellidoContainingIgnoreCase(String nombre, Pageable pagina);
	Page<Turno> findByNombreApellidoContainingIgnoreCaseAndFecha(String nombre, Date fecha, Pageable pagina);
	Page<Turno> findByCategoria(Categoria categoria, Pageable pagina);
	Page<Turno> findByNombreApellidoContainingIgnoreCaseAndCategoria(String nombre, Categoria categoria, Pageable pagina);
	Page<Turno> findByCategoriaAndFecha(Categoria categoria, Date fecha, Pageable pagina);
	Page<Turno> findByNombreApellidoContainingIgnoreCaseAndCategoriaAndFecha(String nombre, Categoria categoria, Date fecha, Pageable pagina);
}
