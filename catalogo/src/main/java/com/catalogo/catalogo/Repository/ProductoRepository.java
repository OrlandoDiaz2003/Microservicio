package com.catalogo.catalogo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.catalogo.catalogo.Model.*;
import java.util.List;

public interface ProductoRepository extends JpaRepository <Producto, Integer>{
    //Buscar producto por id
    Producto findByProductoId(int productoId);

    //Buscar producto nombre
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Producto> findBynombre(String nombre);

    //buscar por categoria
    @Query("SElECT p FROM Producto p WHERE LOWER(p.categoria.descripcion) LIKE LOWER(CONCAT('%',:descripcion,'%'))")
    List<Producto> findByCategoria(String descripcion);
    



}
