package co.com.sergio.distribuidora.distribuidora.dto;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

public class ProductoDTO {
    private int producto_id;
    private String nombre;
    private String descripcion;
    private String clasificacion;
    private String codigo;
    private double precioVenta;
    private int cantidadStock;
    private CategoriaProductoDTO categoria;

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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public CategoriaProductoDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProductoDTO categoria) {
        this.categoria = categoria;
    }
}

