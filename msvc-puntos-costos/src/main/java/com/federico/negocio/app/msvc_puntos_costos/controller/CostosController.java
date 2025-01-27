package com.federico.negocio.app.msvc_puntos_costos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.federico.negocio.app.msvc_puntos_costos.domain.Camino;
import com.federico.negocio.app.msvc_puntos_costos.services.CostosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/costos")
@RequiredArgsConstructor
public class CostosController {

    private final CostosService service;

    @GetMapping
    public ResponseEntity<?> consultarCostos() {
        return ResponseEntity.ok(service.consultarCaminos());
    }


    @PostMapping
    public ResponseEntity<?> cargarCosto(@RequestBody Camino camino) {
        try {
            service.cargarCosto(camino.getCaminoPK(), camino.getCosto());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id1}/{id2}")
    public ResponseEntity<?> removerCosto(@PathVariable Long id1,@PathVariable Long id2) {
        try {
            service.removerCosto(id1, id2);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/puntosVenta/{id}")
    public ResponseEntity<?> consultarPuntosVenta(@PathVariable Long id) {
        
        try {
            return ResponseEntity.ok(service.consultarPuntoventa(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/costoMinimo/{id1}/{id2}")
    public ResponseEntity<?> consultarCostoMinimo(@PathVariable Long id1,@PathVariable Long id2) {
        try {
            return ResponseEntity.ok(service.consultarCostoMinimo(id1, id2));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
