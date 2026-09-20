package dao;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import entidad.Categoria;

public class DaoCategoria {

   private String host = "jdbc:mysql://localhost:3306/";
   private String user = "root";
   private String pass = "root";
   private String dbName = "bdInventario";
		   
   public int agregarCategoria(Categoria categoria) {
	   String query = "INSERT INTO Categorias (Nombre) VALUES ('" + categoria.getNombre() + "')";
	    Connection cn = null;
	    int filas = 0;

	    try {
	        cn = DriverManager.getConnection(host + dbName, user, pass); 
	        Statement st = cn.createStatement();
	        filas = st.executeUpdate(query);
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return filas;
	}
   public int modificarCategoria(Categoria categoria) {
       String query = "UPDATE Categorias SET Nombre = '" + categoria.getNombre() + "' WHERE IdCategoria = " + categoria.getIdCategoria();
       Connection cn = null;
       int filas = 0;

       try {
           cn = DriverManager.getConnection(host + dbName, user, pass);
           Statement st = cn.createStatement();
           filas = st.executeUpdate(query);
           cn.close();
           
       } catch (Exception e) {
           e.printStackTrace();
       }

       return filas;
   }

   
   public int eliminarCategoria(int idCategoria) {
       String query = "DELETE FROM Categorias WHERE IdCategoria = " + idCategoria;
       Connection cn = null;
       int filas = 0;

       try {
           cn = DriverManager.getConnection(host + dbName, user, pass);
           Statement st = cn.createStatement();
           filas = st.executeUpdate(query);
           cn.close();
           
       } catch (Exception e) {
           e.printStackTrace();
       }

       return filas;
   }

   
   public ArrayList<Categoria> listarCategorias() {
       String query = "SELECT IdCategoria, Nombre FROM Categorias";
       Connection cn = null;
       ArrayList<Categoria> lista = new ArrayList<Categoria>();

       try {
           cn = DriverManager.getConnection(host + dbName, user, pass);
           Statement st = cn.createStatement();
           ResultSet rs = st.executeQuery(query);

           while (rs.next()) {
               Categoria cat = new Categoria();
               cat.setIdCategoria(rs.getInt("IdCategoria"));
               cat.setNombre(rs.getString("Nombre"));
               lista.add(cat);
           }
           cn.close();
           
       } catch (Exception e) {
           e.printStackTrace();
       }

       return lista;
   }
  }

