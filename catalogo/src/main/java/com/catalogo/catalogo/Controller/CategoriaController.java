package com.catalogo.catalogo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.catalogo.catalogo.Service.CategoriaService;

import oracle.jdbc.proxy.annotation.Post;

import java.util.List;
import com.catalogo.catalogo.Model.Categoria;
import com.catalogo.catalogo.Repository.CategoriaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<?> metodos(){
        List<String> metodos = List.of(
            "POST /api/v1/categoria/guardar/{categoria}",
            "GET api/v1/categoria/mostrarCategoria"
        );

        return ResponseEntity.ok(metodos);
    }

    @GetMapping("/mostrarCategoria")
    public ResponseEntity<?> mostrarCategoria(){
        List<Categoria> categorias = categoriaRepository.findAll();    
        
        if(categorias.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categorias);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?>  guardarCategoria(@RequestBody Categoria categoria) {
    
        return ResponseEntity.ok(categoriaService.guardarCategoria(categoria));
    }
    


}
