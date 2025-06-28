package com.catalogo.catalogo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.catalogo.catalogo.Model.*;
import com.catalogo.catalogo.Service.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> listarMetodos() {
        List<String> metodos = List.of(
                "GET    /api/v1/producto/buscarProductoId/{id}",
                "GET    /api/v1/producto/buscarProductoNombre/{nombre}",
                "GET    /api/v1/producto/buscarPorCategoria/{categoria}",
                "GET    /api/v1/producto/mostrarProductos",
                "POST   /api/v1/producto/guardar",
                "PUT    /api/v1/producto/modificar/{id}",
                "DELETE /api/v1/producto/eliminarPorId/{id}");

        return ResponseEntity.ok(metodos);

    }

    @GetMapping("/buscarProductoId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        if (id < 0 || id == null) {
            return ResponseEntity.badRequest().body("Ingrese un id valido para realizar la busqueda");
        }

        Producto producto = productoService.buscarPorId(id);

        if (producto == null) {
            return ResponseEntity.status(404).body("No se encontro un producto con este id");
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/buscarProductoNombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        if (nombre.isBlank()) {
            return ResponseEntity.badRequest().body("Ingrese un nombre valido");
        }
        List<Producto> productos = productoService.buscarProductoPorNombre(nombre);

        if (productos.isEmpty()) {
            return ResponseEntity.status(404).body("No se han encontrado productos que coincidan con el nombre");
        }

        return ResponseEntity.ok(productos);

    }

@PutMapping("modificar/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Integer id, @RequestBody Map<String, Object> datos) {

        Producto producto = productoService.actualizarProducto(id, datos);

        if (producto == null) {
            return ResponseEntity.badRequest().body("Error no se pudo modificar el producto");
        }

        return ResponseEntity.ok(producto);

    }

    @GetMapping("/buscarPorCategoria/{categoria}")
    public ResponseEntity<?> buscarPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.buscarPorCategoria(categoria);

        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/mostrarProductos")
    public List<Producto> mostraProductos() {
        return productoService.mostrarProductos();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody List<Producto> productos) {
        List<Producto> productoRecibidos = productoService.guardarProducto(productos);

        if (productoRecibidos.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Guardar fallo, para evitar fallos no ingrese el id del producto y no use un nombre ya existente");
        }
        return ResponseEntity.ok(productoRecibidos);
    }

    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.badRequest().body("Ingrese un id valido");
        }
        productoService.eliminarProducto(id);
        return ResponseEntity.ok().body("Producto Eliminado");
    }

    @PostMapping("buscarProductos")
    public ResponseEntity<?> buscarProductos(@RequestBody List<Integer> ids){
        List<Producto> productos = productoService.buscaProductos(ids);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/productoDisponible")
    public ResponseEntity<List<Integer>> productoDisponible(){
        return productoService.productoDisponible();
    }

}
