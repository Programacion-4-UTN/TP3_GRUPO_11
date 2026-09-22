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

	}

}
