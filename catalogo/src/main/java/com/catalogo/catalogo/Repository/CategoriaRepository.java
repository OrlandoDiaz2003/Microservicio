package com.catalogo.catalogo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.catalogo.catalogo.Model.*;

import java.util.List;
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
    //Mostrar productos buscando la categoria
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.productos WHERE LOWER(c.descripcion) = LOWER(:descripcion)")
    List<Categoria> findBydescripcion(@Param("descripcion") String descripcion);

}
