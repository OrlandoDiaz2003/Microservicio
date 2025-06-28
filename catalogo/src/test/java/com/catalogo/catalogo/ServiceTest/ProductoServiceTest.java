package com.catalogo.catalogo.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.catalogo.catalogo.Model.Producto;
import com.catalogo.catalogo.Service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    ProductoService productoService;

    @Test
    void buscarPorId() {
        int id = 1;
        Producto productoTest = productoService.buscarPorId(id);
        assertFalse(productoTest == null, "EL producto no deberia ser nulo");
    }

    @Test
    void actualizarProducto(){
        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", "orlando");

        int id = 1;
        Producto producto = productoService.buscarPorId(id);
        assertFalse(producto == null, "El producto no deberia ser nulo");

        producto = productoService.actualizarProducto(id, datos);
        assertTrue(producto.getNombre().equals("orlando"), "El nombre debia cambiar a orlando pero sigue siendo" + producto.getNombre());
    }

    @Test
    void buscarPorNombre(){
        String nombre = "orlando";
        List<Producto> producto = productoService.buscarProductoPorNombre(nombre);

        assertFalse(producto == null, "El producto no deberia ser null");
    }

    @Test
    void mostrarProductos(){
        List<Producto> productos = productoService.mostrarProductos();

        assertFalse(productos.isEmpty(), "La lista de producto no deberia ser vacia");
    }

    @Test
    void buscarPorCategoria(){
        String categoriaAseo= "aseo";
        String categoriaAlimento= "alimento";
        String categoriaElectronico= "electronico";

        List<Producto> productosAseo = productoService.buscarPorCategoria(categoriaAseo);
        List<Producto> productoAlimento = productoService.buscarPorCategoria(categoriaAlimento);
        List<Producto> productoElectronico = productoService.buscarPorCategoria(categoriaElectronico);

        assertFalse(productosAseo.isEmpty(), "La lista no deberia estar vacia");
        assertFalse(productoAlimento.isEmpty(), "La lista no deberia estar vacia");
        assertFalse(productoElectronico.isEmpty(), "La lista no deberia estar vacia");
    }

}
