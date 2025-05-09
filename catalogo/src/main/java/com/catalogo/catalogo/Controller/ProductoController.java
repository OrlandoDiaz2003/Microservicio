package com.catalogo.catalogo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.catalogo.Model.Producto;
import com.catalogo.catalogo.Service.ProductoService;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

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

}
