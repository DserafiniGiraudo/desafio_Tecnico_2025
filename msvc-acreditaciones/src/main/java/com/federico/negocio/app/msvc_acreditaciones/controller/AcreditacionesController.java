package com.federico.negocio.app.msvc_acreditaciones.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.federico.negocio.app.msvc_acreditaciones.domain.dto.AcreditacionRequest;
import com.federico.negocio.app.msvc_acreditaciones.domain.dto.AcreditacionResponse;
import com.federico.negocio.app.msvc_acreditaciones.services.AcreditacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/acreditaciones")
@RequiredArgsConstructor
public class AcreditacionesController {

    private final AcreditacionService service;


    @PostMapping
    public ResponseEntity<?> guardarAcreditacion(@RequestBody AcreditacionRequest acreditacionRequest) {
        try{
            Optional<AcreditacionResponse> acreditacionOptional = service.guardarAcreditacion(acreditacionRequest);
    
            if(acreditacionOptional.isPresent()){
                return ResponseEntity.ok(acreditacionOptional.get());
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAcreditacionById(@PathVariable long id){
        try {
            Optional<AcreditacionResponse> acreditacionOptional = service.getAcreditacionById(id);
            
            if(acreditacionOptional.isPresent()){
                return ResponseEntity.ok(acreditacionOptional.get());
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
