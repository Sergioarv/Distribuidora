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
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int producto_id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String clasificación;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int cantidadStock;

    @ManyToMany(mappedBy = "productos")
    private Set<Pedido> pedidos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaProducto categoria;

    /**********************/
    /**  Getter Y Setter **/
    /**********************/

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
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

    public String getClasificación() {
        return clasificación;
    }

    public void setClasificación(String clasificación) {
        this.clasificación = clasificación;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }
}
