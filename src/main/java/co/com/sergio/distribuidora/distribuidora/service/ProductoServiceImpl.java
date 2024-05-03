package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import co.com.sergio.distribuidora.distribuidora.repository.ProductoRepository;
import co.com.sergio.distribuidora.distribuidora.utils.Convertir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private Convertir convertir;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProdutos() {

        List<Producto> result = productoRepository.findAll(Sort.by("producto_id").ascending());

        return result.stream()
                .map(convertir::ProductoAProductoDTO)
                .collect(Collectors.toList());
    }
}
