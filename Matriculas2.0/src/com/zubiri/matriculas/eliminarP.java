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
 * Servlet implementation class eliminarA
 */
public class eliminarP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarP() {
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
		String dni = request.getParameter("eliminarP");
		
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
		
			//delete from departamentos where dnombre = "VENTAS";
			//ResultSet profesor = sentencia.executeQuery("delete FROM profesor where dni ='"+dni+"';"); 
			//alumno.next();
			String borrar ="delete FROM profesor where dni ='"+dni+"';";
			sentencia.execute(borrar);
			
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>profesor</h1>");
			out.println("<p>el profesor con el dni: "+request.getParameter("eliminarP")+ " ha sido eliminado de la base de datos</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}