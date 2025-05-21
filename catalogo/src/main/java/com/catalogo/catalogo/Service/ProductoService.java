package com.catalogo.catalogo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.catalogo.Repository.CategoriaRepository;
import com.catalogo.catalogo.Repository.ProductoRepository;

import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.catalogo.catalogo.Model.*;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> buscarPorId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Ingrese un ID valido para realizar la busqueda");
        }
        return productoRepository.findByProductoId(id);
    }

    public List<Producto> buscarProductoPorNombre(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("Debes ingresar un nombre para poder realizar la busqueda");
        }
        return productoRepository.findBynombre(nombre);
    }

    public List<Producto> buscarRangoPrecio(double minValue, double maxValue) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("El valor minimo no puede ser mayor al valor maximo");
        }

        if (minValue < 0 || maxValue < 0) {
            throw new IllegalArgumentException("Los valores de los precios no pueden ser negativos");
        }

        return productoRepository.findByRangoPrecio(minValue, maxValue);
    }

    public List<Producto> mostrarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> guardarProducto(List<Producto> productos) {

        boolean hayId = productos.stream().anyMatch(producto -> producto.getProductoId() != 0);

        if(hayId){
            return Collections.emptyList();
        }
        // Se crea una lista de id de categorias haciendo uso de un flujo(stream)
        List<Integer> categoriaIds = productos.stream().map(producto -> producto.getCategoria().getCategoriaId())
                .distinct().toList();

        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);

        if (categorias.size() != categoriaIds.size()) {
            throw new IllegalArgumentException("Algunas categorias no se encuentran en la base de datos");
        }

        Map<Integer, Categoria> categoriaMap = categorias.stream()
                .collect(Collectors.toMap(Categoria::getCategoriaId, categoria -> categoria));

        for (Producto pro : productos) {
            // se crea objeto de categoria para obtener la descripcion y asignarsela al
            // producto
            Categoria categoria = categoriaMap.get(pro.getCategoria().getCategoriaId());

            pro.getCategoria().setDescripcion(categoria.getDescripcion());
        }

        return productoRepository.saveAll(productos);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPrecioDesc() {
        return productoRepository.buscarPrecioDesc();
    }

    public List<Producto> buscarPrecioAsc() {
        return productoRepository.buscarPrecioAsc();
    }

    public List<Producto> buscarPorCategoria(String descripcion) {
        return productoRepository.findByCategoria(descripcion);
    }

}
