package com.catalogo.catalogo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.catalogo.catalogo.Model.*;
import com.catalogo.catalogo.Repository.CategoriaRepository;
import com.catalogo.catalogo.Service.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/buscarProductoId/{id}")
    public List<Producto> buscarPorId(@PathVariable Integer id){
        return productoService.buscarPorId(id);
    }
    @GetMapping("/buscarProductoNombre/{nombre}")
    public List<Producto> buscarPorNombre(@PathVariable String nombre){
        return productoService.buscarProductoPorNombre(nombre);
    }
    @GetMapping("/buscarProductoPrecio/{minValue}/{maxValue}")
    public List<Producto> buscarPorPrecio(@PathVariable double minValue, @PathVariable double maxValue){
        return productoService.buscarRangoPrecio(minValue, maxValue);
    }
    @GetMapping("/mostrarProductos")
    public List<Producto> mostraProductos(){
        return productoService.mostrarProductos();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto){
        Producto productoGuardado = productoService.guardarProducto(producto);
        productoGuardado.getCategoria().setDescripcion(categoriaRepository.getById(producto.getCategoria().getCategoriaId()).getDescripcion());
        return ResponseEntity.ok(productoGuardado);
    }

    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id){
        if(id < 0){
            return ResponseEntity.badRequest().body("Ingrese un id valido");
        }
        productoService.eliminarProducto(id);
        return ResponseEntity.ok().body("Producto Eliminado");
    }

    @GetMapping("/masCaro")
    public ResponseEntity<?> buscarPrecioDesc() {
        return ResponseEntity.ok(productoService.buscarPrecioDesc());
    }
    
    @GetMapping("/masBarato")
    public ResponseEntity<?> buscarPrecioAsc() {
        return ResponseEntity.ok(productoService.buscarPrecioAsc());
    }
}
