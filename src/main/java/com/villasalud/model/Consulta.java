package com.villasalud.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.villasalud.util.CustomLocalDateTimeDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idConsulta")
@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_consulta")
	private Integer idConsulta;

	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_paciente"))
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_medico"))
	private Medico medico;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_especialidad"))
	private Especialidad especialidad;

	//@JsonFormat(pattern = "yyyy-MM-dd")

	//@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@JsonSerialize(using =ToStringSerializer.class)
	private LocalDateTime fecha;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", targetEntity = DetalleConsulta.class, fetch = FetchType.LAZY ,orphanRemoval = true)
//	@JsonBackReference(value = "consulta_detalleConsulta")
	@JsonManagedReference

	private List<DetalleConsulta> detalleConsulta;
	//@JsonManagedReference
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "consulta_examen", joinColumns = @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta"),
			inverseJoinColumns = @JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
	)
	private  List<Examen> examenes;
	/*@JsonIgnore
	@ManyToMany(mappedBy = "consulta")
	private  List<Examen> examenes;*/
	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public List<DetalleConsulta> getDetalleConsulta() {
		return detalleConsulta;
	}

	public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
		this.detalleConsulta = detalleConsulta;
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}

	@Override
	public String toString() {
		return "Consulta{" +
				"idConsulta=" + idConsulta +
				", paciente=" + paciente +
				", medico=" + medico +
				", especialidad=" + especialidad +
				", fecha=" + fecha +
				", detalleConsulta=" + detalleConsulta +
				", examenes=" + examenes +
				'}';
	}

	public void addExamen(Examen examen) {
		if(this.examenes == null) {
			this.examenes = new ArrayList<>();
		}
		this.examenes.add(examen);
		examen.getConsultas().add(this);
	}
}
