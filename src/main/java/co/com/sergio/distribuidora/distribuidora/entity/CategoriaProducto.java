package co.com.sergio.distribuidora.distribuidora.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Entity
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Producto> productos = new HashSet<>();

    /**********************/
    /**  Getter Y Setter **/
    /**********************/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
