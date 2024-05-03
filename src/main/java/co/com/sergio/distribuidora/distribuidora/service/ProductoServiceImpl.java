package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import co.com.sergio.distribuidora.distribuidora.repository.ProductoRepository;
import co.com.sergio.distribuidora.distribuidora.utils.Convertir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    ProductoRepository productoRepository;

    Convertir convertir;

    /**
     * Método encargado de obtener la lista de productos
     *
     * @return Lista de Productos
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProdutos() {

        List<Producto> result = productoRepository.findAll();

        if (!result.isEmpty()) {

            return result.stream()
                    .map(convertir::ProductoAProductoDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * método encargado de filtrar los productos por parametros de busqueda
     *
     * @param prodcuto_id,   id del producto a filtrar
     * @param nombre,        Nombre del producto a filtrar
     * @param clasificacion, clasificacion del producto a filtrar
     * @param codigo,        codigo del producto a filtrar
     * @param pageable,      información para crear la paginación
     * @return Page de poducto
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductoDTO> filtrarProducto(String prodcuto_id, String nombre, String clasificacion, String codigo, Pageable pageable) {

        if (prodcuto_id != null) {
            return productoRepository.productoPorId(Integer.parseInt(prodcuto_id), pageable);
        } else if (nombre != null) {
            return productoRepository.productoPorNombre(nombre, pageable);
        } else if (clasificacion != null) {
            return productoRepository.productoPorclasificacion(clasificacion, pageable);
        } else if (codigo != null) {
            return productoRepository.productoPorCodigo(codigo, pageable);
        } else {
            Page<Producto> result = productoRepository.findAll(pageable);
            if (!result.isEmpty()) {
                return result.map(convertir::ProductoAProductoDTO);
            } else {
                return new PageImpl<>(Collections.emptyList(), pageable, 0);
            }
        }
    }

    /**
     * Método encargado de crear un nuevo producto
     *
     * @param productoDTO, producto a guardar en la base de datos
     * @return producto almacenado
     */
    @Override
    @Transactional
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {


        return null;
    }
}
