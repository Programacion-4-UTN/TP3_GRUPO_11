package main;

import java.util.ArrayList;
import dao.DaoCategoria;
import dao.DaoProducto;
import entidad.Categoria;
import entidad.Producto;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DaoCategoria daoCat = new DaoCategoria();
        DaoProducto daoProd = new DaoProducto();
        
     // Limpieza de tablas para reiniciar el estado de prueba
        try {
            java.sql.Connection cn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/bdInventario", "root", "root");
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate("DELETE FROM Productos");
            st.executeUpdate("DELETE FROM Categorias");
            st.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        System.out.println("===============================================");
        System.out.println("  1. PRUEBA ABML DE CATEGORIAS");
        System.out.println("===============================================");

        // --- Alta ---
        daoCat.agregarCategoria(new Categoria(0, "Lácteos"));
        daoCat.agregarCategoria(new Categoria(0, "Bebidas"));
        daoCat.agregarCategoria(new Categoria(0, "Golosinas"));
        daoCat.agregarCategoria(new Categoria(0, "Temporal"));

        // --- Listado Inicial ---
        System.out.println("\nListado inicial de categorías:");
        ArrayList<Categoria> listaCat = daoCat.listarCategorias();
        for (Categoria c : listaCat) {
            System.out.println(c);
        }

        // --- Modificación ---
        if (!listaCat.isEmpty()) {
            Categoria catMod = listaCat.get(0);
            catMod.setNombre(catMod.getNombre() + " (Modificada)");
            daoCat.modificarCategoria(catMod);
            System.out.println("\nCategoría ID " + catMod.getIdCategoria() + " modificada.");
        }

        // --- Baja ---
        if (listaCat.size() >= 4) {
            int idEliminar = listaCat.get(listaCat.size() - 1).getIdCategoria();
            daoCat.eliminarCategoria(idEliminar);
            System.out.println("Categoría ID " + idEliminar + " eliminada.");
        }

        // --- Listado Actualizado ---
        System.out.println("\nCategorías luego de modificación y baja:");
        listaCat = daoCat.listarCategorias();
        for (Categoria c : listaCat) {
            System.out.println(c);
        }

        // Obtenemos dos IDs válidos generados en la base de datos
        int idCat1 = listaCat.get(0).getIdCategoria();
        int idCat2 = (listaCat.size() > 1) ? listaCat.get(1).getIdCategoria() : idCat1;
        
        System.out.println("\n===============================================");
        System.out.println("  2. CARGA DE 10 PRODUCTOS (CON STORED PROCEDURE)");
        System.out.println("===============================================");

        Producto[] loteProductos = new Producto[] {
            new Producto("P001", "Leche Entera 1L", 1200.50, 50, idCat1),
            new Producto("P002", "Yogur Frutilla 1L", 950.00, 30, idCat1),
            new Producto("P003", "Queso Cremoso 1kg", 4500.00, 15, idCat1),
            new Producto("P004", "Manteca 200g", 1800.00, 20, idCat1),
            new Producto("P005", "Crema de Leche 200g", 1600.00, 25, idCat1),
            new Producto("P006", "Agua Mineral 1.5L", 800.00, 100, idCat2),
            new Producto("P007", "Gaseosa Cola 2L", 2200.00, 40, idCat2),
            new Producto("P008", "Jugo de Naranja 1L", 1500.00, 35, idCat2),
            new Producto("P009", "Cerveza Rubia 1L", 2800.00, 60, idCat2),
            new Producto("P010", "Vino Tinto 750ml", 3500.00, 18, idCat2)
        };

        for (Producto prod : loteProductos) {
            // Alta con Stored Procedure
            daoProd.agregarProductoSP(prod);
        }
        System.out.println("Se ejecutó el alta de 10 productos mediante 'sp_AgregarProducto'.");

        
        
        System.out.println("ABML de Productos (Alta ya testeada con la carga de 10 productos)");
        ArrayList<Producto> listaProd = daoProd.listarProductos();        
        for(Producto p : listaProd) //recorre todos los p
        { System.out.println(p); // usa el to string
        	}
        System.out.println("Prueba de modificacion");
        if(!listaProd.isEmpty()) {
        	Producto prodMod = listaProd.get(0);
            prodMod.setNombre(prodMod.getNombre() + " (Modificada)");
            daoProd.modificarProducto(prodMod);
            System.out.println("\nProducto Codigo" + prodMod.getCodigo() + " modificado."); // en el nuevo listado muestro el nombre mod.
        }
        
        
        System.out.println("Prueba de Baja");
       // Producto prodModificado = listaProd.get(listaProd.size() -1 ).getIdCategoria();
        if (listaProd.size() >= 2) {
            String codigoEliminar = listaProd.get(listaProd.size() - 1).getCodigo();
            daoProd.eliminarProducto(codigoEliminar);
            System.out.println("Producto Codigo " + codigoEliminar + " eliminado.");
        } 
        System.out.println("Prueba de Listado");
        System.out.println("\n Listado Productos luego de modificación y baja:");
        listaProd = daoProd.listarProductos();
        for (Producto p : listaProd) {
            System.out.println(p);
	        }

}}
