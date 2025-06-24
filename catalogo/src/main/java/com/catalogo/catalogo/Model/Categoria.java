package com.catalogo.catalogo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "Categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private int categoriaId;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore //Esta notacion evita un bucle al usar metodos get
    private List<Producto> productos;

}
