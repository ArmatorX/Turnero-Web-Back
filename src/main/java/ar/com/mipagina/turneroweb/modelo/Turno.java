package ar.com.mipagina.turneroweb.modelo;

import java.util.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Turno {
	@Id
	@GeneratedValue
	private Integer id;
	private String nombreApellido;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "America/Argentina/Cordoba")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Time hora;
	@ManyToOne
	private Categoria categoria;
	private Integer orden;
	
	public boolean getYaTranscurrio() {
		Date fechaHoraActual = new Date();
		Date fechaHoraTurno = new Date(this.getFecha().getYear(),
				this.getFecha().getMonth(),
				this.getFecha().getDate(),
				this.getHora().getHours(),
				this.getHora().getMinutes()
				);
		
		return fechaHoraTurno.before(fechaHoraActual);
	}
	
	public boolean fechasSonIguales(Date fecha1, Date fecha2) {
		if (fecha1.getDate() == fecha2.getDate() &&
				fecha1.getMonth() == fecha2.getMonth() &&
				fecha1.getYear() == fecha2.getYear()) {
			return true;
		} else {
			return false;
		}
	}
	
}
