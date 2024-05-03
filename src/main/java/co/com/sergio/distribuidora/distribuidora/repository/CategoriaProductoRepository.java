package co.com.sergio.distribuidora.distribuidora.repository;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.CategoriaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 03/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {

    /**
     * Query para consultar en la base de datos las categorias por su id
     *
     * @param id,       id de la categoria
     * @param pageable, información para crear la paginación
     * @return page de vategoria por id
     */
    @Query(value = "select * from categoriaproducto as cp where cp.id = :id", nativeQuery = true)
    Page<CategoriaProductoDTO> categoriaPorId(int id, Pageable pageable);


    /**
     * Query para consultar en la base de datos las categorias por su nombre
     *
     * @param nombre,   nombre de la categoria
     * @param pageable, información para crear la paginación
     * @return page de vategoria por nombre
     */
    @Query(value = "select * from categoriaproducto as cp where lower(cp.nombre) like lower(concat('%', :nombre, '%'))", nativeQuery = true)
    Page<CategoriaProductoDTO> categoriaPorNombre(String nombre, Pageable pageable);

    /**
     * Query para consultar en la base de datos las categorias por su descripcion
     *
     * @param descripcion, descripcion de la categoria
     * @param pageable,    información para crear la paginación
     * @return page de vategoria por descripcion
     */
    @Query(value = "select * from categoriaproducto as cp where lower(cp.descripcion) like lower(concat('%', :descripcion, '%'))", nativeQuery = true)
    Page<CategoriaProductoDTO> categoriaPorDescripcion(String descripcion, Pageable pageable);
}
