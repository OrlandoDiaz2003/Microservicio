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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/producto")
@Tag(name = "Producto", description = "Operaciones relacionadas con productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Lista de métodos disponibles", description = "Obtiene una lista de los métodos disponibles en productos")
    @ApiResponse(responseCode = "200", description = "Lista de métodos obtenida correctamente")
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
    @Operation(summary = "Buscar producto por ID", description = "Busca un producto por su ID y devuelve los detalles del producto")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "400", description = "ID inválido proporcionado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
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
    @Operation(summary = "Buscar producto por nombre", description = "Busca productos por su nombre y devuelve una lista de productos que coinciden")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    @ApiResponse(responseCode = "400", description = "Nombre inválido proporcionado")
    @ApiResponse(responseCode = "404", description = "No se encontraron productos con el nombre proporcionado")
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
    @Operation(summary = "Modificar producto", description = "Modifica un producto por su ID existente con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Producto modificado correctamente")
    @ApiResponse(responseCode = "400", description = "ID inválido proporcionado o error al modificar el producto")
    public ResponseEntity<?> modificarProducto(@PathVariable Integer id, @RequestBody Map<String, Object> datos) {

        Producto producto = productoService.actualizarProducto(id, datos);

        if (producto == null) {
            return ResponseEntity.badRequest().body("Error no se pudo modificar el producto");
        }

        return ResponseEntity.ok(producto);

    }

    @GetMapping("/buscarPorCategoria/{categoria}")
    @Operation(summary = "Buscar productos por categoría", description = "Busca productos por su categoría y devuelve una lista de productos que coinciden")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    @ApiResponse(responseCode = "404", description = "No se encontraron productos en la categoría proporcionada")
    public ResponseEntity<?> buscarPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.buscarPorCategoria(categoria);

        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/mostrarProductos")
    @Operation(summary = "Mostrar todos los productos", description = "Devuelve una lista de todos los productos disponibles en el catálogo")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    public List<Producto> mostraProductos() {
        return productoService.mostrarProductos();
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar productos", description = "Guarda una lista de productos en el catálogo. ")
    @ApiResponse(responseCode = "200", description = "Productos guardados correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar los productos, asegúrese de no ingresar un ID y que el nombre no esté duplicado")
    public ResponseEntity<?> guardarProducto(@RequestBody List<Producto> productos) {
        List<Producto> productoRecibidos = productoService.guardarProducto(productos);

        if (productoRecibidos.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Guardar fallo, para evitar fallos no ingrese el id del producto y no use un nombre ya existente");
        }
        return ResponseEntity.ok(productoRecibidos);
    }

    @DeleteMapping("/eliminarPorId/{id}")
    @Operation(summary = "Eliminar producto por ID", description = "Elimina un producto del catálogo por su ID")
    @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente")
    @ApiResponse(responseCode = "400", description = "ID inválido proporcionado")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.badRequest().body("Ingrese un id valido");
        }
        productoService.eliminarProducto(id);
        return ResponseEntity.ok().body("Producto Eliminado");
    }

    @PostMapping("buscarProductos")
    @Operation(summary = "Buscar productos por lista de IDs", description = "Busca productos por una lista de IDs y devuelve los detalles de los productos encontrados")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    public ResponseEntity<?> buscarProductos(@RequestBody List<Integer> ids){
        List<Producto> productos = productoService.buscaProductos(ids);

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/productoDisponible")
    @Operation(summary = "Obtener productos disponibles", description = "Devuelve una lista de IDs de productos que están disponibles en el catálogo")
    @ApiResponse(responseCode = "200", description = "Lista de IDs de productos disponibles obtenida correctamente")
    public ResponseEntity<List<Integer>> productoDisponible(){
        return productoService.productoDisponible();
    }

}

