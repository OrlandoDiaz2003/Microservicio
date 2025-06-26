package com.catalogo.catalogo.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.catalogo.Assemblers.ProductoModelAssembler;
import com.catalogo.catalogo.Model.Producto;
import com.catalogo.catalogo.Repository.ProductoRepository;
import com.catalogo.catalogo.Service.ProductoService;

@RestController
@RequestMapping("api/v2/producto")
public class ProductoControllerV2 {

    @Autowired
    ProductoService productoService;

    @Autowired
    ProductoModelAssembler assembler;

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAll(){
        List<EntityModel<Producto>> productos = productoRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(productos,
        linkTo(methodOn(ProductoControllerV2.class).getAll()).withSelfRel());
    } 
   
    @GetMapping(value = "/buscarPorid/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getById(@PathVariable int id){
        List<EntityModel<Producto>> producto = productoService.buscarPorId(id).stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(producto, linkTo(methodOn(ProductoControllerV2.class).getById(id)).withSelfRel());
    }


}
