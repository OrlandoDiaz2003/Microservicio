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
@Entity
@Table(name = "Categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoriaId;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

}
