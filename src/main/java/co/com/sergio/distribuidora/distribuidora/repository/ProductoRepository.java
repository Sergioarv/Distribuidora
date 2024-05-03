package co.com.sergio.distribuidora.distribuidora.repository;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    /**
     * Query para consultar en la base de datos los productos por su id
     * @param producto_id, id del producto a filtrar
     * @param pageable, información para crear la paginación
     * @return page de producto por id
     */
    @Query(value = "select * from producto as p where producto_id = :producto_id", nativeQuery = true)
    Page<ProductoDTO> productoPorId(int producto_id, Pageable pageable);

    /**
     * Query para consultar en la base de datos los productos por su nombre
     * @param nombre, nombre del producto a filtrar
     * @param pageable, información para crear la paginación
     * @return page de producto por nombre
     */
    @Query(value = "select * from producto as p where lower(p.nombre) like lower(concat('%', :nombre, '%'))", nativeQuery = true)
    Page<ProductoDTO> productoPorNombre(String nombre, Pageable pageable);


    /**
     * Query para consultar en la base de datos los productos por su clasificación
     * @param clasificacion, clasificacion del producto a filtrar
     * @param pageable, información para crear la paginación
     * @return page de producto por clasificación
     */
    @Query(value = "select * from producto as p where lower(p.clasificacion) like lower(concat('%', :clasificacion, '%'))", nativeQuery = true)
    Page<ProductoDTO> productoPorclasificacion(String clasificacion, Pageable pageable);

    /**
     * Query para consultar en la base de datos los productos por su codigo
     * @param codigo, codigo del producto a filtrar
     * @param pageable, información para crear la paginación
     * @return page de producto por codigo
     */
    @Query(value = "select * from producto as p where lower(p.codigo) like lower(concat('%', :codigo, '%'))", nativeQuery = true)
    Page<ProductoDTO> productoPorCodigo(String codigo, Pageable pageable);
}
