/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apistock.repositorio;

import com.example.apistock.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
    @Query("SELECT p FROM Proveedor p WHERE p.nombre LIKE %:nombre%")
    public Proveedor seachName(@Param("nombre") String nombre);
       
    
}
