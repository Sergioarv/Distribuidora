package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

public interface ProductoService {

    /**
     * Método encargado de obtener la lista de productos
     * @return Lista de Productos
     */
    List<ProductoDTO> obtenerProdutos();

    /**
     * método encargado de filtrar los productos por parametros de busqueda
     * @param prodcuto_id, id del producto a filtrar
     * @param nombre, Nombre del producto a filtrar
     * @param clasificacion, clasificacion del producto a filtrar
     * @param codigo, codigo del producto a filtrar
     * @param pageable, información para crear la paginación
     * @return Page de poducto
     */
    Page<ProductoDTO> filtrarProducto(String prodcuto_id, String nombre, String clasificacion, String codigo, Pageable pageable);

    /**
     * Método encargado de crear un nuevo producto
     * @param productoDTO, producto a guardar en la base de datos
     * @return producto almacenado
     */
    ProductoDTO crearProducto(ProductoDTO productoDTO);
}
