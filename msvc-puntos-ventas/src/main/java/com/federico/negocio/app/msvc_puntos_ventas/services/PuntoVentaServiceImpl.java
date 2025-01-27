package com.federico.negocio.app.msvc_puntos_ventas.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.federico.negocio.app.msvc_puntos_ventas.domain.PuntoVenta;

import jakarta.annotation.PostConstruct;


@Service
public class PuntoVentaServiceImpl implements PuntoVentaService {

    HashMap<Long,String> puntosVenta = new HashMap<Long,String>();

    @PostConstruct
    private void poblarPuntosVenta(){
        puntosVenta.put(1L, "CABA");
        puntosVenta.put(2L, "GBA_1");
        puntosVenta.put(3L, "GBA_2");
        puntosVenta.put(4L, "Santa Fe");
        puntosVenta.put(5L, "Cordoba");
        puntosVenta.put(6L, "Misiones");
        puntosVenta.put(7L, "Salta");
        puntosVenta.put(8L, "Chubut");
        puntosVenta.put(9L, "Santa Cruz");
        puntosVenta.put(10L, "Catamarca");
    }

    @Override
    public Optional<PuntoVenta> findById(Long id){
        return puntosVenta.entrySet().stream()
            .filter(pv -> pv.getKey().equals(id))
            .findFirst()
            .map(pv -> new PuntoVenta(pv.getKey(),pv.getValue()));
        }

    @Override
    public List<PuntoVenta> findAll() {

        return puntosVenta.entrySet().stream()
        .map(entry -> new PuntoVenta(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
    }

    @Override
    public PuntoVenta save(PuntoVenta puntoVenta) {

        if(puntosVenta.containsValue(puntoVenta.getPuntoVenta())) {
            throw new IllegalArgumentException("El punto de venta ya existe para la ciudad " + puntoVenta.getPuntoVenta());
        }else{
            Long id = puntosVenta.size() + 1L;
            puntosVenta.put(id, puntoVenta.getPuntoVenta());
            puntoVenta.setId(id);
            return puntoVenta;
        }
    }

    @Override
    public Optional<PuntoVenta> update(PuntoVenta puntoVenta, Long id) {

        return puntosVenta.entrySet().stream()
            .filter(pv -> pv.getKey().equals(id))
            .findFirst()
            .map(pv ->{
                pv.setValue(puntoVenta.getPuntoVenta());
                return new PuntoVenta (id,pv.getValue());
            });
    }
  

    @Override
    public void delete(Long id) {
        puntosVenta.remove(id);
    }
}
