package com.catalogo.catalogo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.catalogo.catalogo.Model.*;
import com.catalogo.catalogo.Repository.CategoriaRepository;;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
   
    public Categoria guardarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
        return categoria;
    }
}
