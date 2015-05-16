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
 * Servlet implementation class anadirS
 */
public class anadirS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public anadirS() {
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
		
		String nombreAS = request.getParameter("nombreAS");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		String dni = request.getParameter("dniP");
		String nombreP = request.getParameter("nombreP");
		String apellidoP = request.getParameter("apellidoP");
		String titulacionP = request.getParameter("titulacionP");
		String departamentoP = request.getParameter("departamentoP");
		int añoM = Integer.parseInt(request.getParameter("añoM"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
		
			sentencia.executeUpdate("INSERT INTO asignatura (nombre, creditos, dni, nombreP, apellido, titulacion, departamento, añoMatriculacion, precio) "
					+ "VALUES ('"+nombreAS+"','"+creditos+"','"+dni+"','"+nombreP+"','"+apellidoP+"','"+titulacionP+"','"+departamentoP+"','"+añoM+"','"+precio+"')");
				
		
		ResultSet asignatura = sentencia.executeQuery("SELECT * FROM asignatura WHERE nombre = '"+request.getParameter("nombreAS")+"';"); 
		asignatura.next();
		out.println("<html>");
		out.println("<head><title>Respuesta</title>");
		out.println("<body>");
		out.println("<h1>alumnos</h1>");
		out.println("<p>la asignatura: " +asignatura+ " ha sido añadido a la base de datos</p>");
		out.println("</body></html>");

			conexion.close();	
			
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}
		
}


