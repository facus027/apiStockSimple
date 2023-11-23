/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apistock.controladores;

import com.example.apistock.entidades.Proveedor;
import com.example.apistock.exception.MiException;
import com.example.apistock.servicios.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("proveedor") //  http://localhost:8080/proveedor
public class ProveedorControlador {
    
    @Autowired
    private ProveedorServicio prose;
    
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Proveedor proveedor){
        
        try {
            return ResponseEntity.status(200).body(prose.crear(proveedor));
        } catch (MiException ex) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ex.getMessage());
        }
    }
    
    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        try {
            return ResponseEntity.status(200).body(prose.getList());
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supr(@PathVariable String id){
        try {
            prose.delete(id);
            return ResponseEntity.status(200).body("Deleted");
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,@RequestBody Proveedor proveedor){
        try {
            Proveedor pro=prose.modificar(id, proveedor);
            return ResponseEntity.status(200).body(pro);
        } catch ( MiException ex) {
            return ResponseEntity.status(200).body(ex.getMessage());
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<Object> seachName(@RequestParam String name){
        try {
            return ResponseEntity.status(200).body(prose.getName(name));
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    
}
