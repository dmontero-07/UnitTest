package com.fca.calidad.dobles;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class FakeDAOAlumnoTest {
	
	private FakeMySQLAlumnoDAO dao;
	private Alumno alumno;
	
	public HashMap<Integer, Alumno> alumnos_db;

	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(FakeMySQLAlumnoDAO.class);
		alumnos_db = new HashMap<Integer, Alumno>();
		alumnos_db.put(2, new Alumno(2, "nombre2", 15, "correo"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createAlumnoTest() {
		//int id, String nombre, int edad, String email
		alumno = new Alumno(1, "nombre", 15, "email");
		
		//Vamos a probar el método createAlumno
		//tenemos que programar el comportamiento
		
		when(dao.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			Alumno arg = (Alumno) invocation.getArguments()[0];
			 
			alumnos_db.put(arg.getId(), arg);
			return true;
			}
		});
		
		//Ejercitar al código
		int sizeAntes = alumnos_db.size();
		dao.addAlumno(alumno);
		
		int sizeDesp = alumnos_db.size();
		
		//Verificar
		assertThat(sizeDesp, is(sizeAntes + 1));
	}

}
