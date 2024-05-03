package co.com.sergio.distribuidora.distribuidora.controller;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.dto.EmpleadoDTO;
import co.com.sergio.distribuidora.distribuidora.entity.Empleado;
import co.com.sergio.distribuidora.distribuidora.service.EmpleadoService;
import co.com.sergio.distribuidora.distribuidora.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 03/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@RestController
@RequestMapping("/empleado")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    /**
     * Método encargado de obtener la lista de empleados
     *
     * @return Lista de empleados
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<EmpleadoDTO>>> obtenerEmpleado() {

        GeneralResponse<List<EmpleadoDTO>> response = new GeneralResponse<>();
        List<EmpleadoDTO> data;
        HttpStatus status = HttpStatus.OK;

        data = empleadoService.obtenerEmpleado();

        if (data != null) {
            if (!data.isEmpty()) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Completado con exito");
            } else {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("No hay empleados");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Error al obtener los empleados");
        }
        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de filtrar los empleados por parametros de busqueda
     *
     * @param id,             id de la categoria a filtrar
     * @param nombre,         Nombre de la categoria a filtrar
     * @param numeroempleado, descripcion de la categoria a filtrar
     * @param activo,         descripcion de la categoria a filtrar
     * @param pagina,         numero de la pagina filtrada
     * @param catnPagina,     cantidad de categorias filtradas por pagina
     * @return Page de empleado
     */

    @GetMapping("/filtrar")
    public ResponseEntity<GeneralResponse<Page<EmpleadoDTO>>> filtrarEmpleado(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "numeroempleado", required = false) String numeroempleado,
            @RequestParam(value = "activo", required = false) String activo,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int catnPagina
    ) {

        GeneralResponse<Page<EmpleadoDTO>> response = new GeneralResponse<>();
        Page<EmpleadoDTO> data;
        HttpStatus status = HttpStatus.OK;

        Pageable pageable = PageRequest.of(pagina, catnPagina);

        data = empleadoService.filtrarEmpleado(id, nombre, numeroempleado, activo, pageable);

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if (data.getContent().size() > 1) {
                response.setMessage("Lista de empleados obtenida con exito");
            } else if (data.getContent().size() == 1) {
                response.setMessage("Empleado obtenido con exito");
            } else {
                response.setSuccess(false);
                response.setMessage("No se encontraron empleados");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Hubo un error al obtener los empleados");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de crear un nuevo empleado
     *
     * @param empleadoDTO, empleado a guardar en la base de datos
     * @return empleado almacenado
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<EmpleadoDTO>> crearEmpleado(
            @RequestBody EmpleadoDTO empleadoDTO) {

        GeneralResponse<EmpleadoDTO> response = new GeneralResponse<>();
        EmpleadoDTO data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = empleadoService.crearEmpleado(empleadoDTO);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se ha creado el empleado con exito");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al crear al empleado");
            }
        } catch (Exception e) {
            response.setData(null);
            response.setSuccess(false);

            String errorMessage = e.getMessage();
            // Buscar la posición de "Detail:"
            int detailIndex = errorMessage.indexOf("Detail:");
            if (detailIndex != -1) {
                // Obtener el detalle de la excepción
                String detail = errorMessage.substring(detailIndex + "Detail:".length()).trim();
                int endDetail = detail.indexOf(".]");
                if (endDetail != -1) {
                    // Si se encuentra el final del detalle, extraer el detalle
                    detail = detail.substring(0, endDetail);
                }
                response.setMessage("Detalle de la excepción: " + detail);
            } else {
                response.setMessage("No se encontró detalle de la excepción");
            }
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de modificar un empleado
     *
     * @param empleadoDTO, empleado a modificar en la base de datos
     * @return empleado modificado
     */
@PutMapping
    public ResponseEntity<GeneralResponse<EmpleadoDTO>> modificarEmpleado(EmpleadoDTO empleadoDTO) {

    GeneralResponse<EmpleadoDTO> response = new GeneralResponse<>();
    EmpleadoDTO data;
    HttpStatus status = HttpStatus.OK;

    try {
        data = empleadoService.modificarEmpleado(empleadoDTO);

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);
            response.setMessage("Se ha modificafo el empleado con exito");
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Hubo un error al modificar al empleado");
        }
    } catch (Exception e) {
        response.setData(null);
        response.setSuccess(false);

        String errorMessage = e.getMessage();
        // Buscar la posición de "Detail:"
        int detailIndex = errorMessage.indexOf("Detail:");
        if (detailIndex != -1) {
            // Obtener el detalle de la excepción
            String detail = errorMessage.substring(detailIndex + "Detail:".length()).trim();
            int endDetail = detail.indexOf(".]");
            if (endDetail != -1) {
                // Si se encuentra el final del detalle, extraer el detalle
                detail = detail.substring(0, endDetail);
            }
            response.setMessage("Detalle de la excepción: " + detail);
        } else {
            response.setMessage("No se encontró detalle de la excepción");
        }
    }

    return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de eliminar un empleado
     *
     * @param empleadoDTO, empleado a eliminar en la base de datos
     * @return booleano si se elimina el empleado
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> eliminarEmpleado(
            @RequestBody EmpleadoDTO empleadoDTO) {

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = empleadoService.eliminarEmpleado(empleadoDTO);

            if (data) {
                response.setData(true);
                response.setSuccess(true);
                response.setMessage("Se ha eliminado el empleado con exito");
            } else {
                response.setData(false);
                response.setSuccess(false);
                response.setMessage("Hubo un error al eliminar el emeplado");
            }
        } catch (Exception e) {
            response.setData(null);
            response.setSuccess(false);

            String errorMessage = e.getMessage();

            // Buscar la posición de "Detail:"
            int detailIndex = errorMessage.indexOf("Detail:");
            if (detailIndex != -1) {
                // Obtener el detalle de la excepción
                String detail = errorMessage.substring(detailIndex + "Detail:".length()).trim();
                int endDetail = detail.indexOf(".]");
                if (endDetail != -1) {
                    // Si se encuentra el final del detalle, extraer el detalle
                    detail = detail.substring(0, endDetail);
                }
                response.setMessage("Detalle de la excepción: " + detail);
            } else {
                response.setMessage("No se encontró detalle de la excepción");
            }
        }

        return new ResponseEntity<>(response, status);
    }
}
