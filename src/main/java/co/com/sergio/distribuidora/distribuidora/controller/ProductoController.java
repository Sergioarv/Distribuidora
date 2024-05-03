package co.com.sergio.distribuidora.distribuidora.controller;

import co.com.sergio.distribuidora.distribuidora.dto.ProductoDTO;
import co.com.sergio.distribuidora.distribuidora.service.ProductoService;
import co.com.sergio.distribuidora.distribuidora.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<GeneralResponse<List<ProductoDTO>>> obtenerproductos(){

        GeneralResponse<List<ProductoDTO>> response = new GeneralResponse<>();
        List<ProductoDTO> data;
        HttpStatus status = HttpStatus.OK;

        data = productoService.obtenerProdutos();

        if(data != null) {
            if(data.size() > 0) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("completado con exito");
            } else {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("No hay productos");
            }
        }
        else{
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Error");
        }
        return new ResponseEntity<>(response, status);
    }
}
