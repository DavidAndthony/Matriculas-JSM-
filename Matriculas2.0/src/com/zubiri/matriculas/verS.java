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

/**
 * Servlet implementation class verS
 */
public class verS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verS() {
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
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
		
			
			ResultSet asignatura = sentencia.executeQuery("SELECT * FROM asignatura;"); 
			//alumno.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			
			//out.println("<p>el alumno con el dni : " + alumno.getString("dni")  + " ha sido añadido a la base de datos</p>");
			while (asignatura.next()){
				out.println("<p>--------------asignaturas---------------</p>");
				out.println("<p>nombre: "+asignatura.getString("nombre")+ "</p>");
				out.println("<p>creditos: "+asignatura.getInt("creditos")+ "</p>");
				out.println("<p>DNI del profsor: "+asignatura.getString("dni")+ "</p>");
				out.println("<p>nombre del profsor: "+asignatura.getString("nombreP")+ "</p>");
				out.println("<p>apellido del profsor: "+asignatura.getString("apellido")+ "</p>");
				out.println("<p>titulacion del profsor: "+asignatura.getString("titulacion")+ "</p>");
				out.println("<p>departamento: "+asignatura.getString("departamento")+ "</p>");
				out.println("<p>año de matriculacion: "+asignatura.getInt("añoMatriculacion")+ "</p>");
				out.println("<p>precio: "+asignatura.getDouble("precio")+ "</p>");
			}
			
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
