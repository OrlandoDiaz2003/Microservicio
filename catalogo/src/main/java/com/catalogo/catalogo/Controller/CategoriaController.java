package com.catalogo.catalogo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.catalogo.catalogo.Service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import com.catalogo.catalogo.Model.Categoria;
import com.catalogo.catalogo.Repository.CategoriaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/api/v1/categoria")
@Tag(name = "Categoria", description = "Operaciones relacionadas con categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    @Operation(summary = "Lista de métodos disponibles", description = "Obtiene una lista de los métodos disponibles en categorías")
    @ApiResponse(responseCode = "200", description = "Lista de métodos obtenida correctamente")
    public ResponseEntity<?> metodos() {
        List<String> metodos = List.of(
                "POST /api/v1/categoria/guardar",
                "GET api/v1/categoria/mostrarCategoria");

        return ResponseEntity.ok(metodos);
    }

    @GetMapping("/mostrarCategoria")
    @Operation(summary = "Mostrar todas las categorías", description = "Devuelve una lista de todas las categorías disponibles en el catálogo")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron categorías en el catálogo")
    public ResponseEntity<?> mostrarCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categorias);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar categorías", description = "Guarda una lista de categorías en el catálogo")
    @ApiResponse(responseCode = "200", description = "Categorías guardadas correctamente")
    public ResponseEntity<?> guardarCategoria(@RequestBody List<Categoria> categoria) {
        return ResponseEntity.ok(categoriaService.guardarCategoria(categoria));

    }

}
