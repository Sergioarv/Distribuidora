package co.com.sergio.distribuidora.distribuidora.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario  implements Serializable {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String documento;

    /**********************/
    /**  Getter Y Setter **/
    /**********************/

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
