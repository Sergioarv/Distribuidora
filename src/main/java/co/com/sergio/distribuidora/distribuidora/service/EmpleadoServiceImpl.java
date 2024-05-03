package co.com.sergio.distribuidora.distribuidora.service;

import co.com.sergio.distribuidora.distribuidora.dto.EmpleadoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.CategoriaProducto;
import co.com.sergio.distribuidora.distribuidora.entity.Empleado;
import co.com.sergio.distribuidora.distribuidora.repository.EmpleadoRepository;
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
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    Convertir convertir;

    /**
     * Método encargado de obtener la lista de empleados
     *
     * @return Lista de empleados
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> obtenerEmpleado() {

        List<Empleado> result = empleadoRepository.findAll();

        if (!result.isEmpty()) {

            return result.stream()
                    .map(convertir::empleadoAEmpleadoDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

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
    @Override
    @Transactional(readOnly = true)
    public Page<EmpleadoDTO> filtrarEmpleado(String id, String nombre, String numeroempleado, String activo, Pageable pageable) {

        if (id != null) {
            return empleadoRepository.empleadoPorId(Integer.parseInt(id), pageable);
        } else if (nombre != null) {
            return empleadoRepository.empleadoPorNombre(nombre, pageable);
        } else if (numeroempleado != null) {
            return empleadoRepository.empleadoPorNumero(numeroempleado, pageable);
        }  else if (activo != null) {
            return empleadoRepository.empleadoPorEstado(activo, pageable);
        } else {
            Page<Empleado> result = empleadoRepository.findAll(pageable);
            if (!result.isEmpty()) {
                return result.map(convertir::empleadoAEmpleadoDTO);
            } else {
                return new PageImpl<>(Collections.emptyList(), pageable, 0);
            }
        }
    }

    /**
     * Método encargado de crear un nuevo empleado
     *
     * @param empleadoDTO, empleado a guardar en la base de datos
     * @return empleado almacenado
     */
    @Override
    @Transactional
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {


        Empleado empleado = convertir.empleadoDTOAEmpleado(empleadoDTO);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        return convertir.empleadoAEmpleadoDTO(empleadoGuardado);
    }

    /**
     * Método encargado de modificar un empleado
     *
     * @param empleadoDTO, empleado a modificar en la base de datos
     * @return empleado modificado
     */
    @Override
    @Transactional
    public EmpleadoDTO modificarEmpleado(EmpleadoDTO empleadoDTO) {


        Empleado result = empleadoRepository.findById(empleadoDTO.getEmpleado_id()).orElse(null);

        if (result != null) {
            Empleado empleado = convertir.empleadoDTOAEmpleado(empleadoDTO);
            Empleado empleadoGuardado = empleadoRepository.save(empleado);

            return convertir.empleadoAEmpleadoDTO(empleadoGuardado);
        }

        return null;
    }

    /**
     * Método encargado de eliminar un empleado
     *
     * @param empleadoDTO, empleado a eliminar en la base de datos
     * @return booleano si se elimina el empleado
     */
    @Override
    @Transactional
    public Boolean eliminarEmpleado(EmpleadoDTO empleadoDTO) {

        Empleado empleadoEliminar = convertir.empleadoDTOAEmpleado(empleadoDTO);

        Empleado result = empleadoRepository.findById(empleadoEliminar.getUsuario_id()).orElse(null);
        if (result != null){
            empleadoRepository.delete(result);
            return true;
        }

        return false;
    }
}
