package co.com.sergio.distribuidora.distribuidora.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.time.LocalDate;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Entity
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Empleado extends Usuario{

    @Column(nullable = false)
    private String numeroEmpleado;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    private boolean activo;

    /**********************/
    /**  Getter Y Setter **/
    /**********************/

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
