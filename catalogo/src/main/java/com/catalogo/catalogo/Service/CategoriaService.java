package com.catalogo.catalogo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.catalogo.catalogo.Model.*;
import com.catalogo.catalogo.Repository.CategoriaRepository;
import java.util.List;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
   
    public List<Categoria> guardarCategoria(List<Categoria> categoria){
        categoriaRepository.saveAll(categoria);
        return categoria;
    }
}
