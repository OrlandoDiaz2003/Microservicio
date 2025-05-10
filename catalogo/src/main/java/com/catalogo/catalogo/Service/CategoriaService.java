package com.catalogo.catalogo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.catalogo.catalogo.Model.*;
import com.catalogo.catalogo.Repository.CategoriaRepository;;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> buscarCategoria(String descripcion){
        if(descripcion.isBlank()){
            throw new IllegalArgumentException("Debes ingresar una categoria para poder buscar");
        }
        return categoriaRepository.findBydescripcion(descripcion);
    }
}
