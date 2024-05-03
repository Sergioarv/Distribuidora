package co.com.sergio.distribuidora.distribuidora.utils;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.CategoriaProducto;
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
    public ProductoDTO ProductoAProductoDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }

    /**
     * Método encargado de comvertir un DTO a entity producto
     *
     * @param productoDTO, DTO a convertir
     * @return DTO comvertido
     */
    public Producto ProductoDTOAProducto(ProductoDTO productoDTO) {
        return modelMapper.map(productoDTO, Producto.class);
    }

    /**
     * Método encargado de convertir una entity CategoriaProducto a DTO
     *
     * @param categoriaProducto, entity a convertir
     * @return Entity convertida a DTO
     */
    public CategoriaProductoDTO CategoriaProductoACategoriaProductoDTO(CategoriaProducto categoriaProducto) {
        return modelMapper.map(categoriaProducto, CategoriaProductoDTO.class);
    }

    /**
     * Método encargado de comvertir un DTO a entity CategoriaProducto
     *
     * @param categoriaProductoDTO, DTO a convertir
     * @return DTO comvertido
     */
    public CategoriaProducto CategoriaProductoDTOACategoriaProducto(CategoriaProductoDTO categoriaProductoDTO) {
        return modelMapper.map(categoriaProductoDTO, CategoriaProducto.class);
    }
}
