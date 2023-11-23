/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apistock.servicios;

import com.example.apistock.entidades.Producto;
import com.example.apistock.enumeraciones.Categoria;
import com.example.apistock.exception.MiException;
import com.example.apistock.repositorio.ProductoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio proRepo;
    
    public Producto crear (Producto producto) throws MiException{
        validar(producto);
        
       return proRepo.save(producto);
    }
    
    @Transactional
    public Producto modiicar(String id,Producto producto) throws MiException{
        
        Optional<Producto> resPro=proRepo.findById(id);
        
        if(resPro.isPresent()){
            validar(producto);
            Producto pro=resPro.get();
            pro.setNombre(producto.getNombre());
            pro.setDetalle(producto.getDetalle());
            pro.setPrecio(producto.getPrecio());
            pro.setStock(producto.getStock());
            pro.setProveedor(producto.getProveedor());
            pro.setCategoria(producto.getCategoria());
            
           return proRepo.save(pro);
        }
        throw new MiException("Imposible modificar el producto");
        
    }
    
    
    @Transactional
    public void delete(String id) throws MiException{
        
        Optional<Producto>resPro=proRepo.findById(id);
        
        if(resPro.isPresent()){
            proRepo.deleteById(id);
        }
        else{
            throw new MiException("El producto no puede ser eliminado");
        }
    }
    
     public Producto getId(String id) throws MiException{
         
         Optional<Producto>resPro=proRepo.findById(id);
         if(resPro.isPresent()){
             return resPro.get();
         }
         throw new MiException("El producto no existe");
     }
    
    @Transactional(readOnly = true)
    public List<Producto> getList() throws MiException{
        
                
        List<Producto> productos=new ArrayList();
        productos=proRepo.findAll();
        if(productos.isEmpty()){
            throw new MiException("No hay productos en base de datos");
        }
        return productos;
    }
    
    public List<Producto> buscarPorNombre(String nombre){
 
        List<Producto>productos=proRepo.buscarPorNombre(nombre);
        return productos;
    }
    
     public List<Producto> searchCategoria(Categoria categoria) throws MiException{
        List<Producto>productos=proRepo.searchCategoria(categoria);
         if(productos.isEmpty()){
            throw new MiException("No hay productos con esa categotria");
        }
        return productos;
     }
     
    
     public List<Producto> filterPrecio(Float min,Float max) throws MiException{
         List<Producto>productos=productos=proRepo.filterPrecio(min, max);
        if(productos.isEmpty()){
            throw new MiException("No hay productos en ese rango de precio");
        }
        return productos;
     }
     
     public List<Producto> stockFilter(Integer stock){
        List<Producto>productos=proRepo.stockFilter(stock);
        return productos;
     }
   
    
    public void validar(Producto producto) throws MiException{
        
        if(producto==null){
            throw new MiException("El producto no puede ser nulo");
        }
        if(producto.getNombre()==null || producto.getNombre().isEmpty()){
            throw new MiException("El nombre no puede ser nulo");
        }
         if(producto.getDetalle()==null || producto.getDetalle().isEmpty()){
            throw new MiException("El detalle no puede ser nulo");
        }
         if(producto.getCategoria()==null){
            throw new MiException("La categoria no puede ser nulo");
        }
        if(producto.getPrecio()==null || producto.getPrecio() <=0) {
            throw new MiException("el precio no puede ser nulo o menor a 0");
        }
        if(producto.getStock()==null || producto.getStock() <=0) {
            throw new MiException("el stock no puede ser nulo o menor a 0");
        }
        if(producto.getProveedor()==null){
            throw new MiException("el proveedor no puede estar vacio");
        }
    }
    
    
    
}
