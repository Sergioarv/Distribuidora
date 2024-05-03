package co.com.sergio.distribuidora.distribuidora.utils;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

public class Convertir {

    @Autowired
    private ModelMapper modelMapper;

    public ProductoDTO ProductoAProductoDTO(Producto producto){
        return modelMapper.map(producto, ProductoDTO.class);
    }
}
