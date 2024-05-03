package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

public interface ProductoService {

    List<ProductoDTO> obtenerProdutos();
}
