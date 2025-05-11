package com.catalogo.catalogo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.catalogo.catalogo.Model.*;
import java.util.List;

public interface ProductoRepository extends JpaRepository <Producto, Integer>{
    //Buscar producto por id
    List<Producto> findByProductoId(int productoId);

    //Buscar producto por rango de precio
    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :minValue AND :maxValue")
    List<Producto> findByRangoPrecio(@Param("minValue") double minValue, @Param("maxValue") double maxValue);

    //Buscar producto nombre
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Producto> findBynombre(String nombre);

    //Ordenar de mas caro a mas barato
    @Query("SELECT p FROM Producto p ORDER BY p.precio DESC")
    List<Producto> buscarPrecioDesc();

    //Ordenar de mas barato a mas caro
    @Query("SELECT p FROM Producto p ORDER BY p.precio ASC")
    List<Producto> buscarPrecioAsc();



}
