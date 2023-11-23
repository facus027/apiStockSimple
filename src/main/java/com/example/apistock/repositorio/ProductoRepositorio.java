/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apistock.repositorio;

import com.example.apistock.entidades.Producto;
import com.example.apistock.enumeraciones.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,String> {
    
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre%")
    public List<Producto> buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.categoria LIKE :categoria")
    public List<Producto> searchCategoria(@Param("categoria") Categoria categoria);
    
    @Query("SELECT p FROM Producto p WHERE p.proveedor.nombre LIKE %:nombre%")
    public List<Producto> searchProveedorName(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :minimo AND :maximo")
    public List<Producto> filterPrecio(Float minimo,Float maximo);
    
    @Query("SELECT p FROM Producto p WHERE p.stock <= :stock")
    public List<Producto> stockFilter(@Param("stock") Integer stock);
    
}
