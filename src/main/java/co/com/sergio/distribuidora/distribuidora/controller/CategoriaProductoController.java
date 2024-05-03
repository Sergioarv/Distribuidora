package co.com.sergio.distribuidora.distribuidora.controller;

import co.com.sergio.distribuidora.distribuidora.dto.CategoriaProductoDTO;
import co.com.sergio.distribuidora.distribuidora.service.CategoriaProductoService;
import co.com.sergio.distribuidora.distribuidora.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 03/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@RestController
@RequestMapping("/categoriaProducto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoriaProductoController {

    @Autowired
    CategoriaProductoService categoriaProductoService;

    /**
     * Método encargado de obtener la lista de categoriaProducto
     *
     * @return Lista de CategoriaProducto
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<CategoriaProductoDTO>>> obtenerCategoriaProducto() {

        GeneralResponse<List<CategoriaProductoDTO>> response = new GeneralResponse<>();
        List<CategoriaProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        data = categoriaProductoService.obtenerCategoria();

        if (data != null) {
            if (!data.isEmpty()) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Completado con exito");
            } else {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("No hay categorias");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Error al obtener las categorias");
        }
        return new ResponseEntity<>(response, status);

    }

    /**
     * método encargado de filtrar las categorias por parametros de busqueda
     *
     * @param id,          id de la categoria a filtrar
     * @param nombre,      Nombre de la categoria a filtrar
     * @param descripcion, descripcion de la categoria a filtrar
     * @param pagina,      numero de la pagina filtrada
     * @param catnPagina,  cantidad de categorias filtradas por pagina
     * @return Page de categoria
     */
    @GetMapping("/filtrar")
    public ResponseEntity<GeneralResponse<Page<CategoriaProductoDTO>>> filtrarCategoria(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int catnPagina
    ) {
        GeneralResponse<Page<CategoriaProductoDTO>> response = new GeneralResponse<>();
        Page<CategoriaProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        Pageable pageable = PageRequest.of(pagina, catnPagina);

        data = categoriaProductoService.filtrarCategoria(id, nombre, descripcion, pageable);

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if (data.getContent().size() > 1) {
                response.setMessage("Lista de categorias obtenida con exito");
            } else if (data.getContent().size() == 1) {
                response.setMessage("Categoria obtenida con exito");
            } else {
                response.setSuccess(false);
                response.setMessage("No se encontraron Categorias");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Hubo un error al obtener la categoria");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de crear una nueva categoria
     *
     * @param categoriaProductoDTO, categoria a guardar en la base de datos
     * @return categoria almacenada
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<CategoriaProductoDTO>> crearCategoria(
            @RequestBody CategoriaProductoDTO categoriaProductoDTO) {

        GeneralResponse<CategoriaProductoDTO> response = new GeneralResponse<>();
        CategoriaProductoDTO data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = categoriaProductoService.crearCategoria(categoriaProductoDTO);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se ha creado la categoria con exito");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al crear la categoria");
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
     * Método encargado de modificar una categoria
     *
     * @param categoriaProductoDTO, categoria a modificar en la base de datos
     * @return categoria modificada
     */
    @PutMapping
    public ResponseEntity<GeneralResponse<CategoriaProductoDTO>> modificarCategoria(
            @RequestBody CategoriaProductoDTO categoriaProductoDTO
    ) {

        GeneralResponse<CategoriaProductoDTO> response = new GeneralResponse<>();
        CategoriaProductoDTO data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = categoriaProductoService.modificarCategoria(categoriaProductoDTO);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se ha modificado la categoria con exito");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al modificar la categoria");
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
     * Método encargado de eliminar una categoria
     *
     * @param categoriaProductoDTO, categoria a eliminar en la base de datos
     * @return booleano si elimina la categoria
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> eliminarCategoria(
            @RequestBody CategoriaProductoDTO categoriaProductoDTO
    ) {
        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = categoriaProductoService.eliminarCategoria(categoriaProductoDTO);

            if (data) {
                response.setData(true);
                response.setSuccess(true);
                response.setMessage("Se ha eliminado la categoria con exito");
            } else {
                response.setData(false);
                response.setSuccess(false);
                response.setMessage("Hubo un error al eliminar la categoria");
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
