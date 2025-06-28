package com.catalogo.catalogo.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.catalogo.catalogo.Controller.ProductoControllerV2;
import com.catalogo.catalogo.Model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

    @Override
    public EntityModel<Producto> toModel (Producto producto){
        return EntityModel.of(producto, 
        linkTo(methodOn(ProductoControllerV2.class).mostrarProductos()).withRel("productos"),
        linkTo(methodOn(ProductoControllerV2.class).buscarPorId(producto.getProductoId())).withSelfRel(),
        linkTo(methodOn(ProductoControllerV2.class).buscarPorNombre(producto.getNombre())).withSelfRel(),
        linkTo(methodOn(ProductoControllerV2.class).buscarPorCategoria(producto.getCategoria().getDescripcion())).withSelfRel());
    }

}
