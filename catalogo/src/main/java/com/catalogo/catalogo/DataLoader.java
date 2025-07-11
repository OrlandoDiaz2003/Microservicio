package com.catalogo.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.catalogo.catalogo.Model.Categoria;
import com.catalogo.catalogo.Model.Producto;
import com.catalogo.catalogo.Repository.CategoriaRepository;
import com.catalogo.catalogo.Service.ProductoService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
@Profile("test")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired 
    private ProductoService productoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception{
        Faker faker = new Faker();
        Random random = new Random();    
        List<Producto> listaProducto = new ArrayList<>();

        //agregar categorias
        Categoria nuevCategoria1 = new Categoria();
        Categoria nuevCategoria2 = new Categoria();
        Categoria nuevCategoria3 = new Categoria();

        nuevCategoria1.setDescripcion("Alimento");
        
        nuevCategoria2.setDescripcion("Electronico");

        nuevCategoria3.setDescripcion("Aseo");

        categoriaRepository.save(nuevCategoria1);
        categoriaRepository.save(nuevCategoria2);
        categoriaRepository.save(nuevCategoria3);

        List<Categoria> listaCategoria = new ArrayList<>();
        listaCategoria.add(nuevCategoria1);
        listaCategoria.add(nuevCategoria2);
        listaCategoria.add(nuevCategoria3);
        //agregar productos
        for(int i = 0; i < 20; i ++){
            Producto nuevoProducto = new Producto();
            //Capturando datos

            //nombre
            String nombre = faker.commerce().productName();

            //categoria 
            Categoria categoriaRandom = listaCategoria.get(random.nextInt(listaCategoria.size()));
            
            //asignando valores
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setCategoria(categoriaRandom);

            entityManager.persist(nuevoProducto);

            if(i > 0 && i % 1000 == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        
        productoService.guardarProducto(listaProducto);
    }

}
