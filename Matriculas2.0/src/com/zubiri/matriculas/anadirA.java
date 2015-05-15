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


import com.zubiri.matriculas.Alumno;

/**
 * Servlet implementation class anadirA
 */
public class anadirA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public anadirA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Alumno alumnojar = new Alumno();
				
		alumnojar.setDni(request.getParameter("dniA"));
		alumnojar.setNombre(request.getParameter("nombreA"));
		alumnojar.setApellido(request.getParameter("apellidoA"));
		alumnojar.setAñoInscripcion(Integer.parseInt(request.getParameter("anoA")));
		alumnojar.setCiclo(request.getParameter("cicloA"));
				//request.getParameter("dniA"),
				//request.getParameter("nombreA"),
				//request.getParameter("apellidoA"),
				//Integer.parseInt(request.getParameter("anoA")),
				//request.getParameter("cicloA")
				
		
		
		/*funcuiona
		String dni = request.getParameter("dniA");
		String nombre = request.getParameter("nombreA");
		String apellido = request.getParameter("apellidoA");
		int año = Integer.parseInt(request.getParameter("anoA"));
		String ciclo = request.getParameter("apellidoA");
		*/
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			/*sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS alumno"
					+ "(dni varchar (15) primary key,"
					+"nombre varchar(15),"
					+"apellido varchar(15),"
					+"añoInscripcion int(4),"
					+"ciclo varchar (15));");
			*/
		/*	sentencia.executeUpdate("INSERT INTO alumno (dni, nombre, apellido, añoInscripcion, ciclo) VALUES ('"
					+request.getParameter("dniA")+"','"
					+request.getParameter("nombreA")+"',"
					+request.getParameter("apellidoA")+"','"
					+Integer.parseInt(request.getParameter("anoA"))+"','"
					+request.getParameter("cicloA")+")");
		*/	
			//funciona
			//sentencia.executeUpdate("INSERT INTO alumno (dni, nombre, apellido, añoInscripcion, ciclo)"
			//		+ "VALUES ('"+dni+"','"+nombre+"','"+apellido+"','"+año+"','"+ciclo+"')");
			
			sentencia.executeUpdate("INSERT INTO alumno (dni, nombre, apellido, añoInscripcion, ciclo)"
						+ "VALUES ('"+alumnojar.getDni()+"','"+alumnojar.getNombre()+"','"+alumnojar.getApellido()+"','"+alumnojar.getAñoInscripcion()+"','"+alumnojar.getCiclo()+"')");
					
			
			ResultSet alumno = sentencia.executeQuery("SELECT * FROM alumno WHERE dni = '"+request.getParameter("dniA")+"';"); 
			alumno.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			//out.println("<p>el alumno con el dni : " + alumno.getString("dni")  + " ha sido añadido a la base de datos</p>");
			out.println("<p>el alumno con el dni : " + alumnojar.formattedAlumno()
					+ " ha sido añadido a la base de datos</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
