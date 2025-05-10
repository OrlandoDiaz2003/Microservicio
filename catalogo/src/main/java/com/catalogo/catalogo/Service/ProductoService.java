package com.catalogo.catalogo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.catalogo.Repository.ProductoRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import com.catalogo.catalogo.Model.*;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> buscarPorId(int id){
        if(id < 0){
            throw new IllegalArgumentException("Ingrese un ID valido para realizar la busqueda");
        }
        return productoRepository.findByProductoId(id);
    }

    public List<Producto> buscarProductoPorNombre(String nombre){
        if(nombre.isBlank()){
            throw new IllegalArgumentException("Debes ingresar un nombre para poder realizar la busqueda");
        }
        return productoRepository.findBynombre(nombre);
    }

    public List<Producto> buscarRangoPrecio(double minValue, double maxValue){
        if(minValue > maxValue){
            throw new IllegalArgumentException("El valor minimo no puede ser mayor al valor maximo");
        }

        if(minValue < 0 || maxValue < 0){
            throw new IllegalArgumentException("Los valores de los precios no pueden ser negativos");
        }

        return productoRepository.findByRangoPrecio(minValue, maxValue);
    }

    public List<Producto> mostrarProductos(){
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }


}
