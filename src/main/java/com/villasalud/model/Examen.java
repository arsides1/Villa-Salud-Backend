package com.villasalud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idExamen")
@Entity
@Table(name = "examen")
public class Examen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_examen")
	private Integer idExamen;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "descripcion", nullable = true, length = 150)
	private String descripcion;
	//@JsonBackReference
	@JsonIgnore
	@ManyToMany(mappedBy = "examenes")
	private List<Consulta> consultas;



	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "consulta_examen", joinColumns = @JoinColumn(name = "id_examen", referencedColumnName = "id_examen"),
			inverseJoinColumns = @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	)
	private List<Consulta> consulta = new ArrayList<>();*/

	public Examen() {
	}

	public Integer getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public String toString() {
		return "Examen{" +
				"idExamen=" + idExamen +
				", nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				", consultas=" + consultas +
				'}';
	}

	public void addConsulta(Consulta consulta) {
		if(this.consultas == null) {
			this.consultas = new ArrayList<>();
		}
		this.consultas.add(consulta);
		consulta.getExamenes().add(this);
	}
}
