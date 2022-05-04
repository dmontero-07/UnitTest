package com.fca.calidad.dobles;

public interface IAlumnoDAO {
	public boolean addAlumno(Alumno a);
	public boolean deleteAlumno(Alumno a);
	public boolean updateEmail(Alumno a);
	public Alumno consultarAlumno(String id);
}
