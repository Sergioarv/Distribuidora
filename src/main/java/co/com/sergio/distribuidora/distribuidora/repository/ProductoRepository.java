package co.com.sergio.distribuidora.distribuidora.repository;

import co.com.sergio.distribuidora.distribuidora.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 02/05/2024
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
