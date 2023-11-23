
package com.example.apistock.controladores;

import com.example.apistock.entidades.Producto;
import com.example.apistock.enumeraciones.Categoria;
import com.example.apistock.exception.MiException;
import com.example.apistock.servicios.ProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*") //Que direcion tiene permitido consumir esta api
@RequestMapping("producto")  // http://localhost:8080/producto
public class ProductoControlador {
    
    @Autowired
    private ProductoServicio ps;
    
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Producto producto){
        
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ps.crear(producto));
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }  
    }
        
    @GetMapping("")
    public ResponseEntity<Object> getAll(){
        
        try {
            return ResponseEntity.status(200).body(ps.getList());
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,@RequestBody Producto producto){
        
        try {
            Producto pro=ps.modiicar(id, producto);
            return ResponseEntity.status(201).body(pro);
        } catch (MiException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> suprimir(@PathVariable String id){
        
        try {
            ps.delete(id);
            return ResponseEntity.status(200).body("Deleted");
        } catch (MiException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
    
    @GetMapping("/searchName")
    public ResponseEntity<Object> searchName(@RequestParam String nombre){
        try {
            return ResponseEntity.status(200).body(ps.buscarPorNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    @GetMapping("/{categoria}")
    public ResponseEntity<Object> searchCate(@PathVariable Categoria categoria){
        try {
            return ResponseEntity.status(200).body(ps.searchCategoria(categoria));
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> searchId(@PathVariable String id){
        try {
            return ResponseEntity.status(200).body(ps.getId(id));
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<Object> filtPrecio(@RequestParam Float min,@RequestParam Float max){
        try {
            List<Producto>productos=ps.filterPrecio(min, max);
            return ResponseEntity.status(200).body(productos);
        } catch (MiException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    
}
