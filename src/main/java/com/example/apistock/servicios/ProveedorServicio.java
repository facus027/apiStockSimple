/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apistock.servicios;

import com.example.apistock.entidades.Proveedor;
import com.example.apistock.exception.MiException;
import com.example.apistock.repositorio.ProveedorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorServicio {
    
    @Autowired
    private ProveedorRepositorio proveeRepo;
    
    
    
    @Transactional
    public Proveedor crear(Proveedor proveedor) throws MiException{
        
        validar(proveedor);
        return proveeRepo.save(proveedor);
        
    }
    
    @Transactional
    public Proveedor modificar(String id,Proveedor proveedor) throws MiException{
        
        Optional<Proveedor>resProvee=proveeRepo.findById(id);
        
        if(resProvee.isPresent()){
            validar(proveedor);
            Proveedor prove=resProvee.get();
            prove.setNombre(proveedor.getNombre());
            prove.setSitioWeb(proveedor.getSitioWeb());
            prove.setTel(proveedor.getTel());
            
            return proveeRepo.save(prove);
        }
        throw new MiException("Imposible modificar el proveedor");
        
    }
    
    @Transactional
    public void delete(String id) throws MiException{
        
        Optional<Proveedor>resPro=proveeRepo.findById(id);
        
        if(resPro.isPresent()){
            proveeRepo.deleteById(id);
        }
        else{
            throw new MiException("El producto no puede ser eliminado");
        }
        
    }
    
    
    
     public List<Proveedor> getList() throws MiException{
         
       List<Proveedor>proveedores=new ArrayList();
       proveedores=proveeRepo.findAll();
       
       if(proveedores.isEmpty()){
           throw new MiException("No hay proveedores en base de dato");
       }
       return proveedores;
    }
    
    public Proveedor getName(String nombre) throws MiException{
        Proveedor pro=proveeRepo.seachName(nombre);
        if(pro==null){
            throw new MiException("No existe el proveedor");
        }
        return pro;
    }
    
    public Proveedor getId(String id){
        return proveeRepo.getOne(id);
    }
    
    public void validar(Proveedor prov) throws MiException{
        
        if(prov==null){
            throw new MiException("El proveedor no puede estar vacio");
        }
        if(prov.getNombre()==null||prov.getNombre().isEmpty()){
            throw new MiException("El nombre no puede estar vacio");
        }
        if(prov.getSitioWeb()==null||prov.getSitioWeb().isEmpty()){
            throw new MiException("El sitio web no puede estar vacio");
        }
        if(prov.getTel()==null||prov.getTel().isEmpty()){
            throw new MiException("El telefono no puede estar vacio");
            
        }
    }
    
}
