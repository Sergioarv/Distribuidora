package co.com.sergio.distribuidora.distribuidora.repository;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.dto.EmpleadoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.Empleado;
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
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {


    /**
     * Query para consultar en la base de datos los empleados por su id
     *
     * @param id,       id del empleado
     * @param pageable, información para crear la paginación
     * @return page de empleado por id
     */
    @Query(value = "select * from empleado as e where e.usuario_id = :id", nativeQuery = true)
    Page<EmpleadoDTO> empleadoPorId(int id, Pageable pageable);

    /**
     * Query para consultar en la base de datos los empleados por su nombre
     *
     * @param nombre,       nombre del empleado
     * @param pageable, información para crear la paginación
     * @return page de empleado por nombre
     */
    @Query(value = "select * from empleado as e where lower(e.nombre) like lower(concat('%', :nombre, '%'))", nativeQuery = true)
    Page<EmpleadoDTO> empleadoPorNombre(String nombre, Pageable pageable);

    /**
     * Query para consultar en la base de datos los empleados por su numeroempleado
     *
     * @param numeroempleado,       numero del empleado
     * @param pageable, información para crear la paginación
     * @return page de empleado por numero de empleado
     */
    @Query(value = "select * from empleado as e where lower(e.numeroempleado) like lower(concat('%', :numeroempleado, '%'))", nativeQuery = true)
    Page<EmpleadoDTO> empleadoPorNumero(String numeroempleado, Pageable pageable);

    /**
     * Query para consultar en la base de datos los empleados por su estado de actividad
     *
     * @param activo,       estado de actividad del empleado
     * @param pageable, información para crear la paginación
     * @return page de empleado por su estado de actividad
     */
    @Query(value = "select * from empleado as e where e.activo = :activo", nativeQuery = true)
    Page<EmpleadoDTO> empleadoPorEstado(String activo, Pageable pageable);
}
