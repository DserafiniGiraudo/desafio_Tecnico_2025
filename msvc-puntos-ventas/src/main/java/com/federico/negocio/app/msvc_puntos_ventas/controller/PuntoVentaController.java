package com.federico.negocio.app.msvc_puntos_ventas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.federico.negocio.app.msvc_puntos_ventas.domain.PuntoVenta;
import com.federico.negocio.app.msvc_puntos_ventas.services.PuntoVentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/puntosVentas")
@RequiredArgsConstructor
public class PuntoVentaController {

    private final PuntoVentaService service;

    @GetMapping
    public List<PuntoVenta> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try{
            Optional<PuntoVenta> puntoventaOptional = service.findById(id);
            if(puntoventaOptional.isPresent()){
                return ResponseEntity.ok().body(puntoventaOptional.get());
            }else{
                return ResponseEntity.internalServerError().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<PuntoVenta> save(@RequestBody PuntoVenta puntoVenta) throws JsonMappingException, JsonProcessingException {
        return ResponseEntity.ok(service.save(puntoVenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoVenta> update(@RequestBody PuntoVenta puntoVenta, @PathVariable Long id) {
        Optional<PuntoVenta> puntoVentaOptional = service.update(puntoVenta, id);
        
        return puntoVentaOptional
        .map(puntoVentaDB -> ResponseEntity.ok(puntoVentaDB))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
