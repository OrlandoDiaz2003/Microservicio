package com.catalogo.catalogo.ProductoControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import com.catalogo.catalogo.Controller.ProductoController;
import com.catalogo.catalogo.Repository.ProductoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProductoController productoController;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void buscarPorId() throws Exception {

        Integer id = 19;

        mockMvc.perform(get("http://local:8080/api/v1/producto/buscarProductoId/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void buscarProductoPorNombre() throws Exception {
        int id = 1;
        String nombre = productoRepository.findByProductoId(id).getNombre();
        mockMvc.perform(get("http://local:8080/api/v1/producto/buscarProductoNombre/{nombre}", nombre))
                .andExpect(status().isOk());
    }

    @Test
    void buscarProductoPorCategoria() throws Exception {
        int id = 1;
        String categoria = productoRepository.findByProductoId(id).getCategoria().getDescripcion();
        mockMvc.perform(get("http://local:8080/api/v1/producto/buscarPorCategoria/{categoria}", categoria))
                .andExpect(status().isOk());
    }

    @Test
    void modificar() throws Exception {
        Integer id = 1;

        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", "Gei");

        mockMvc.perform(put("/api/v1/producto/modificar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk());
    }
}
