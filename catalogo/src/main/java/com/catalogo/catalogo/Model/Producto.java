package com.catalogo.catalogo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "producto_seq")
    @TableGenerator(
        name = "producto_seq",
        table = "sequence_table",
        pkColumnName = "sequence_name",
        valueColumnName = "next_val",
        allocationSize = 1000
    )
    
    @Column(name = "Producto_id")
    private int productoId;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
