package co.com.sergio.distribuidora.distribuidora.controller;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.service.ProductoService;
import co.com.sergio.distribuidora.distribuidora.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
     * @return Lista de Productos
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<ProductoDTO>>> obtenerProductos(){

        GeneralResponse<List<ProductoDTO>> response = new GeneralResponse<>();
        List<ProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        data = productoService.obtenerProdutos();

        if(data != null) {
            if(!data.isEmpty()) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Completado con exito");
            } else {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("No hay productos");
            }
        }
        else{
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Error al obtener los productos");
        }
        return new ResponseEntity<>(response, status);
    }

    /**
     * método encargado de filtrar los productos por parametros de busqueda
     * @param prodcuto_id, id del producto a filtrar
     * @param nombre, Nombre del producto a filtrar
     * @param clasificacion, clasificacion del producto a filtrar
     * @param codigo, codigo del producto a filtrar
     * @param pagina, numero de la pagina filtrada
     * @param catnPagina, cantidad de productos filtrados por pagina
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
    ){
        GeneralResponse<Page<ProductoDTO>> response = new GeneralResponse<>();
        Page<ProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        Pageable pageable = PageRequest.of(pagina, catnPagina);

        data = productoService.filtrarProducto(prodcuto_id, nombre, clasificacion, codigo, pageable);

        if(data != null){
            response.setData(data);
            response.setSuccess(true);

            if(data.getContent().size() > 1){
                response.setMessage("Lista de productos obtenida con exito");
            } else if(data.getContent().size() == 1){
                response.setMessage("Producto obtenido con exito");
            } else{
                response.setSuccess(false);
                response.setMessage("No hay productos");
            }
        }else{
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("hubo un error al obtener el producto");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de crear un nuevo producto
     * @param productoDTO, producto a guardar en la base de datos
     * @return producto almacenado
     */
//    @PostMapping
//    public ResponseEntity<GeneralResponse<ProductoDTO>> crearProducto(@RequestBody ProductoDTO productoDTO){
//
//        GeneralResponse<ProductoDTO> response = new GeneralResponse<>();
//        ProductoDTO data;
//        HttpStatus status = HttpStatus.OK;
//
//        data = productoService.crearProducto(productoDTO);
//
//    }
}
