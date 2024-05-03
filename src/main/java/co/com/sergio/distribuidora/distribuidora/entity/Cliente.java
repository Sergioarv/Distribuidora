package co.com.sergio.distribuidora.distribuidora.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Entity
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Cliente extends Usuario{

    private String telefono;

    private String direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Pedido> pedidos = new HashSet<>();

    /**********************/
    /**  Getter Y Setter **/
    /**********************/

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
