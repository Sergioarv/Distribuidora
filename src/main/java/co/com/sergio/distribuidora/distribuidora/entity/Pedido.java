package co.com.sergio.distribuidora.distribuidora.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Entity
public class Pedido {

    @Id
    @Column(name = "pedido_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedido_id;

    private LocalDate fecha;

    @ManyToMany
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    /**********************/
    /**  Getter Y Setter **/
    /**********************/

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
