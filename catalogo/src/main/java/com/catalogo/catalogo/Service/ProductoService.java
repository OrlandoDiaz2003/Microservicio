package com.catalogo.catalogo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.catalogo.catalogo.Repository.CategoriaRepository;
import com.catalogo.catalogo.Repository.ProductoRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.catalogo.catalogo.Model.*;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Actualizar producto
    public Producto actualizarProducto(int id, Map<String, Object> datos) {
        Optional<Producto> productoOp = productoRepository.findById(id);
        Producto producto = productoOp.orElseThrow(() -> new IllegalArgumentException("producto no encontrado"));

        if (datos.containsKey("nombre")) {
            producto.setNombre((String) datos.get("nombre"));
        }

        productoRepository.save(producto);
        return producto;
    }

    // Buscar por id
    public Producto buscarPorId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Ingrese un ID valido para realizar la busqueda");
        }
        return productoRepository.findByProductoId(id);
    }
    //buscar productos por id
    public List<Producto> buscaProductos(List<Integer> ids){
        List<Producto> productos = productoRepository.findAllById(ids);
        return productos;
    }

    // buscar por nombre
    public List<Producto> buscarProductoPorNombre(String nombre) {
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("Debes ingresar un nombre para poder realizar la busqueda");
        }
        return productoRepository.findBynombre(nombre);
    }

    // mostrar todos los productos
    public List<Producto> mostrarProductos() {
        return productoRepository.findAll();
    }

    // Guardar productos
    public List<Producto> guardarProducto(List<Producto> productos) {

        List<String> nombresBD = productoRepository.findAll().stream().map(producto -> producto.getNombre()).toList();

        List<Producto> productoValido = new ArrayList<>();

        List<Integer> categoriaIds = productos.stream().map(producto -> producto.getCategoria().getCategoriaId())
                .distinct().toList();

        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);

        if (categorias.size() != categoriaIds.size()) {
            throw new IllegalArgumentException("Algunas categorias no se encuentran en la base de datos");
        }

        Map<Integer, Categoria> categoriaMap = categorias.stream()
                .collect(Collectors.toMap(Categoria::getCategoriaId, categoria -> categoria));

        for (Producto pro : productos) {

            if (pro.getProductoId() != 0) {
                continue;
            }

            boolean nombreExiste = nombresBD.stream().anyMatch(nombreBD -> nombreBD.equalsIgnoreCase(pro.getNombre()));
            if (nombreExiste) {
                continue;
            }

            if (pro.getCategoria() == null) {
                continue;
            }
            Categoria categoria = categoriaMap.get(pro.getCategoria().getCategoriaId());

            pro.getCategoria().setDescripcion(categoria.getDescripcion());

            productoValido.add(pro);

        }

        return productoRepository.saveAll(productoValido);
    }

    // Eliminar productos
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    // buscar por categoria
    public List<Producto> buscarPorCategoria(String descripcion) {
        return productoRepository.findByCategoria(descripcion);
    }

    //metodo para devolver una lista de todos id de los productos
    public ResponseEntity<List<Integer>> productoDisponible(){
        List<Integer> ids = productoRepository.findAll().stream().map(producto -> producto.getProductoId()).toList();

        if(ids.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ids);
    }
}
