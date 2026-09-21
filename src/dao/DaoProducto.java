package dao;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import entidad.Producto;

public class DaoProducto {

    private String host = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "bdInventario";

    public int agregarProducto(Producto producto) {
        String query = "CALL sp_AgregarProducto('" 
                + producto.getCodigo() + "', '" 
                + producto.getNombre() + "', " 
                + producto.getPrecio() + ", " 
                + producto.getStock() + ", " 
                + producto.getIdCategoria() + ")";
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

    public int modificarProducto(Producto producto) {
        String query = "UPDATE Productos SET Nombre = '" + producto.getNombre() 
                + "', Precio = " + producto.getPrecio() 
                + ", Stock = " + producto.getStock() 
                + ", IdCategoria = " + producto.getIdCategoria() 
                + " WHERE Codigo = '" + producto.getCodigo() + "'";
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

    public int eliminarProducto(String codigo) {
        String query = "DELETE FROM Productos WHERE Codigo = '" + codigo + "'";
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

    public ArrayList<Producto> listarProductos() {
        String query = "SELECT Codigo, Nombre, Precio, Stock, IdCategoria FROM Productos";
        Connection cn = null;
        ArrayList<Producto> lista = new ArrayList<Producto>();

        try {
            cn = DriverManager.getConnection(host + dbName, user, pass);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre");
                Double precio = rs.getDouble("Precio");
                int stock = rs.getInt("Stock");
                int idCategoria = rs.getInt("IdCategoria");

                Producto prod = new Producto(codigo, nombre, precio, stock, idCategoria);
                lista.add(prod);
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}