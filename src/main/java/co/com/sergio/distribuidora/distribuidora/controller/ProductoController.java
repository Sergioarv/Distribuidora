package co.com.sergio.distribuidora.distribuidora.controller;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.service.ProductoService;
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
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Método encargado de obtener la lista de productos
     *
     * @return Lista de Productos
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<ProductoDTO>>> obtenerProductos() {

        GeneralResponse<List<ProductoDTO>> response = new GeneralResponse<>();
        List<ProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        data = productoService.obtenerProdutos();

        if (data != null) {
            if (!data.isEmpty()) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Completado con exito");
            } else {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("No hay productos");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Error al obtener los productos");
        }
        return new ResponseEntity<>(response, status);
    }

    /**
     * método encargado de filtrar los productos por parametros de busqueda
     *
     * @param prodcuto_id,   id del producto a filtrar
     * @param nombre,        Nombre del producto a filtrar
     * @param clasificacion, clasificacion del producto a filtrar
     * @param codigo,        codigo del producto a filtrar
     * @param pagina,        numero de la pagina filtrada
     * @param catnPagina,    cantidad de productos filtrados por pagina
     * @return Page de poducto
     */
    @GetMapping("/filtrar")
    public ResponseEntity<GeneralResponse<Page<ProductoDTO>>> filtrarProducto(
            @RequestParam(value = "producto_id", required = false) String prodcuto_id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "clasificacion", required = false) String clasificacion,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int catnPagina
    ) {
        GeneralResponse<Page<ProductoDTO>> response = new GeneralResponse<>();
        Page<ProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        Pageable pageable = PageRequest.of(pagina, catnPagina);

        data = productoService.filtrarProducto(prodcuto_id, nombre, clasificacion, codigo, pageable);

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if (data.getContent().size() > 1) {
                response.setMessage("Lista de productos obtenida con exito");
            } else if (data.getContent().size() == 1) {
                response.setMessage("Producto obtenido con exito");
            } else {
                response.setSuccess(false);
                response.setMessage("No se encontraron productos");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Hubo un error al obtener el producto");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de crear un nuevo producto
     *
     * @param productoDTO, producto a guardar en la base de datos
     * @return producto almacenado
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<ProductoDTO>> crearProducto(@RequestBody ProductoDTO productoDTO) {

        GeneralResponse<ProductoDTO> response = new GeneralResponse<>();
        ProductoDTO data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = productoService.crearProducto(productoDTO);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se ha creado el producto con exito");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al crear el producto");
            }
        } catch (Exception e) {
            response.setData(null);
            response.setSuccess(false);

            if (e instanceof org.hibernate.PropertyValueException) {
                response.setMessage("Falta un valor para una propiedad requerida.");
            } else {
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
                    response.setMessage("Detalle de la excepción: " + detail  + e.getMessage());
                } else {
                    response.setMessage("No se encontró detalle de la excepción");
                }
            }
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de modificar un producto
     *
     * @param productoDTO, producto a modificar en la base de datos
     * @return producto modificado
     */
    @PutMapping
    public ResponseEntity<GeneralResponse<ProductoDTO>> modificarProducto(
            @RequestBody ProductoDTO productoDTO
    ) {

        GeneralResponse<ProductoDTO> response = new GeneralResponse<>();
        ProductoDTO data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = productoService.modificarProducto(productoDTO);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se ha modificado el producto con exito");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al modificar el producto");
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
     * Método encargado de eliminar un producto
     *
     * @param productoDTO, producto a eliminar en la base de datos
     * @return booleano si elimina el producto
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> eliminarProducto(
            @RequestBody ProductoDTO productoDTO
    ) {
        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = productoService.eliminarProducto(productoDTO);

            if (data) {
                response.setData(true);
                response.setSuccess(true);
                response.setMessage("Se ha eliminado el producto con exito");
            } else {
                response.setData(false);
                response.setSuccess(false);
                response.setMessage("Hubo un error al eliminar el producto");
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
