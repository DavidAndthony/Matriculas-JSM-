package com.zubiri.matriculas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.zubiri.matriculas.*;
//import com.zubiri.matriculas.Alumno;
//import com.zubiri.matriculas.Persona;
/**
 * Servlet implementation class modificarA
 */
public class modificarA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String dni = request.getParameter("mdniA");
		String nombre = request.getParameter("mnombreA");
		String apellido = request.getParameter("mapellidoA");
		int año = Integer.parseInt(request.getParameter("manoA"));
		String ciclo = request.getParameter("mapellidoA");
		
		try{
		/*
			Persona alumno = new Alumno(
					request.getParameter("mdniA"),
					request.getParameter("mnombreA"),
					request.getParameter("mapellidoA"),
					Integer.parseInt(request.getParameter("manoA")),
					request.getParameter("mapellidoA")
					);
			*/
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			//update alumno set nombre = "x" where dni = "a";
		/*	funciona */
		 sentencia.executeUpdate("UPDATE alumno set "
					+"nombre = '"+nombre+"'"+","
					+"apellido = '"+apellido+"'"+","
					+"añoInscripcion = "+año+","
					+"ciclo = '"+ciclo+"'"
					+ "where dni = '"+dni+"'"+";");
		
			/*
			sentencia.executeUpdate("UPDATE alumno set "
					+"nombre = '"+Alumno.getNombre()+"'"+","
					+"apellido = '"+Alumno.getApellido()+"'"+","
					+"añoInscripcion = "+Alumno.getAñoInscripcion()+","
					+"ciclo = '"+Alumno.getCiclo()+"'"
					+ "where dni = '"+Alumno.getDni()+"'"+";");
				*/	
			ResultSet Ralumno = sentencia.executeQuery("SELECT * FROM alumno WHERE dni = '"+dni+"';"); 
			Ralumno.next();
			
			 /* alumno = new Alumno(
					Ralumno.getString(1),
					Ralumno.getString(2),
					Ralumno.getString(3),
					Ralumno.getInt(4),
					Ralumno.getString(5)
					);
			*/
			
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			out.println("<p>el alumno: " + nombre+" "+apellido +" ha sido modificado</p>");
			//out.println("<p><a href='index.html>volver<a>'</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
