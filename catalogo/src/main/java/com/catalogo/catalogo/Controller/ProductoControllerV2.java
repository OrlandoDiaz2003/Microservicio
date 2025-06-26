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
    public EntityModel<Producto> getById(@PathVariable int id){
            Producto producto = productoService.buscarPorId(id);

            EntityModel<Producto> productoModel = assembler.toModel(producto);

            return productoModel;
    }

    @GetMapping(value = "/buscarProductoNombre/{nombre}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getByName(@PathVariable String nombre){
        List<EntityModel<Producto>> productos = productoService.buscarProductoPorNombre(nombre).stream()
        .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(productos,linkTo(methodOn(ProductoControllerV2.class).getByName(nombre)).withSelfRel());
    }

    @GetMapping(value = "/buscarPorCategoria/{categoria}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getByCategory(@PathVariable String categoria){
        List<EntityModel<Producto>> productos = productoService.buscarPorCategoria(categoria).stream()
        .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(productos,linkTo(methodOn(ProductoControllerV2.class).getByCategory(categoria)).withSelfRel());
    }
}
