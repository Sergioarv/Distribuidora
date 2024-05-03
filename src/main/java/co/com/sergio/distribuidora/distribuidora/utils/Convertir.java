package co.com.sergio.distribuidora.distribuidora.utils;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.dto.EmpleadoDTO;
import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.CategoriaProducto;
import co.com.sergio.distribuidora.distribuidora.entity.Empleado;
import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Component
public class Convertir {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Método encargado de convertir una entity producto a DTO
     *
     * @param producto, entity a convertir
     * @return Entity convertida a DTO
     */
    public ProductoDTO productoAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
        productoDTO.setCategoria(categoriaProductoACategoriaProductoDTO(producto.getCategoria()));
        return  productoDTO;
    }

    /**
     * Método encargado de comvertir un DTO a entity producto
     *
     * @param productoDTO, DTO a convertir
     * @return DTO convertido
     */
    public Producto productoDTOAProducto(ProductoDTO productoDTO) {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        producto.setCategoria(categoriaProductoDTOACategoriaProducto(productoDTO.getCategoria()));
        return producto;
    }

    /**
     * Método encargado de convertir una entity CategoriaProducto a DTO
     *
     * @param categoriaProducto, entity a convertir
     * @return Entity convertida a DTO
     */
    public CategoriaProductoDTO categoriaProductoACategoriaProductoDTO(CategoriaProducto categoriaProducto) {
        return modelMapper.map(categoriaProducto, CategoriaProductoDTO.class);
    }

    /**
     * Método encargado de comvertir un DTO a entity CategoriaProducto
     *
     * @param categoriaProductoDTO, DTO a convertir
     * @return DTO convertido
     */
    public CategoriaProducto categoriaProductoDTOACategoriaProducto(CategoriaProductoDTO categoriaProductoDTO) {
        return modelMapper.map(categoriaProductoDTO, CategoriaProducto.class);
    }


    /**
     * Método encargado de convertir una entity Empleado a DTO
     *
     * @param empleado, entity a convertir
     * @return Entity convertida a DTO
     */
    public EmpleadoDTO empleadoAEmpleadoDTO(Empleado empleado) {
        return modelMapper.map(empleado, EmpleadoDTO.class);
    }

    /**
     * Método encargado de comvertir un DTO a entity Empleado
     *
     * @param empleadoDTO, DTO a convertir
     * @return DTO convertido
     */
    public Empleado empleadoDTOAEmpleado(EmpleadoDTO empleadoDTO) {
        return modelMapper.map(empleadoDTO, Empleado.class);
    }
}
