package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.EmpleadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 03/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

public interface EmpleadoService {

    /**
     * Método encargado de obtener la lista de empleados
     *
     * @return Lista de empleados
     */
    List<EmpleadoDTO> obtenerEmpleado();

    /**
     * Método encargado de filtrar los empleados por parametros de busqueda
     *
     * @param id,             id de la categoria a filtrar
     * @param nombre,         Nombre de la categoria a filtrar
     * @param numeroempleado, descripcion de la categoria a filtrar
     * @param activo,         descripcion de la categoria a filtrar
     * @param pageable,       información para crear la paginaci+on
     * @return Page de empleado
     */
    Page<EmpleadoDTO> filtrarEmpleado(String id, String nombre, String numeroempleado, String activo, Pageable pageable);

    /**
     * Método encargado de crear un nuevo empleado
     *
     * @param empleadoDTO, empleado a guardar en la base de datos
     * @return empleado almacenado
     */
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);


    /**
     * Método encargado de modificar un empleado
     *
     * @param empleadoDTO, empleado a modificar en la base de datos
     * @return empleado modificado
     */
    EmpleadoDTO modificarEmpleado(EmpleadoDTO empleadoDTO);

    /**
     * Método encargado de eliminar un empleado
     *
     * @param empleadoDTO, empleado a eliminar en la base de datos
     * @return booleano si se elimina el empleado
     */
    Boolean eliminarEmpleado(EmpleadoDTO empleadoDTO);
}
