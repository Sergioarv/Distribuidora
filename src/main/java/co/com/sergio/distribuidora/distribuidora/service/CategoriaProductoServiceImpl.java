package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.CategoriaProducto;
import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import co.com.sergio.distribuidora.distribuidora.repository.CategoriaProductoRepository;
import co.com.sergio.distribuidora.distribuidora.utils.Convertir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 03/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    Convertir convertir;

    /**
     * Método encargado de obtener la lista de categorias
     *
     * @return Lista de categorias
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProductoDTO> obtenerCategoria() {

        List<CategoriaProducto> result = categoriaProductoRepository.findAll();

        if (!result.isEmpty()) {

            return result.stream()
                    .map(convertir::CategoriaProductoACategoriaProductoDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Método encargado de filtrar las categorias por parametros de busqueda
     *
     * @param id,          id de la categoria
     * @param nombre,      nombre de la categoria
     * @param descripcion, descripcion de categoria
     * @param pageable,    información para crear la paginación
     * @return Page de Categoria
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaProductoDTO> filtrarCategoria(String id, String nombre, String descripcion, Pageable pageable) {

        if (id != null) {
            return categoriaProductoRepository.categoriaPorId(Integer.parseInt(id), pageable);
        } else if (nombre != null) {
            return categoriaProductoRepository.categoriaPorNombre(nombre, pageable);
        } else if (descripcion != null) {
            return categoriaProductoRepository.categoriaPorDescripcion(descripcion, pageable);
        } else {
            Page<CategoriaProducto> result = categoriaProductoRepository.findAll(pageable);
            if (!result.isEmpty()) {
                return result.map(convertir::CategoriaProductoACategoriaProductoDTO);
            } else {
                return new PageImpl<>(Collections.emptyList(), pageable, 0);
            }
        }
    }

    /**
     * Método encargado de crear una nueva categoria
     *
     * @param categoriaProductoDTO, categoria a guardar en la base de datos
     * @return categoria almacenada
     */
    @Override
    @Transactional
    public CategoriaProductoDTO crearCategoria(CategoriaProductoDTO categoriaProductoDTO) {

        CategoriaProducto categoriaProducto = convertir.CategoriaProductoDTOACategoriaProducto(categoriaProductoDTO);
        CategoriaProducto categoriaProductoGuardado = categoriaProductoRepository.save(categoriaProducto);

        return convertir.CategoriaProductoACategoriaProductoDTO(categoriaProductoGuardado);
    }

    /**
     * Método encargado de modificar una categoria
     *
     * @param categoriaProductoDTO, categoria a modificar en la base de datos
     * @return categoria modificada
     */
    @Override
    @Transactional
    public CategoriaProductoDTO modificarCategoria(CategoriaProductoDTO categoriaProductoDTO) {

        CategoriaProducto categoriaProducto = convertir.CategoriaProductoDTOACategoriaProducto(categoriaProductoDTO);
        CategoriaProducto categoriaProductoGuardado = categoriaProductoRepository.save(categoriaProducto);

        return convertir.CategoriaProductoACategoriaProductoDTO(categoriaProductoGuardado);

    }
}
