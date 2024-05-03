package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 03/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

public interface CategoriaProductoService {

    /**
     * Método encargado de obtener la lista de categorias
     *
     * @return Lista de categorias
     */
    List<CategoriaProductoDTO> obtenerCategoria();

    /**
     * Método encargado de filtrar las categorias por parametros de busqueda
     *
     * @param id,          id de la categoria a filtrar
     * @param nombre,      Nombre de la categoria a filtrar
     * @param descripcion, descripcion de la categoria a filtrar
     * @param pageable,    información para crear la paginaci+on
     * @return Page de categoria
     */
    Page<CategoriaProductoDTO> filtrarCategoria(String id, String nombre, String descripcion, Pageable pageable);

    /**
     * Método encargado de crear una nueva categoria
     *
     * @param categoriaProductoDTO, categoria a guardar en la base de datos
     * @return categoria almacenada
     */
    CategoriaProductoDTO crearCategoria(CategoriaProductoDTO categoriaProductoDTO);
}
