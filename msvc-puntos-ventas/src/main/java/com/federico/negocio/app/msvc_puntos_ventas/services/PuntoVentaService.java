package com.federico.negocio.app.msvc_puntos_ventas.services;

import java.util.List;
import java.util.Optional;

import com.federico.negocio.app.msvc_puntos_ventas.domain.PuntoVenta;


public interface PuntoVentaService {

    public Optional<PuntoVenta> findById(Long id);
    public List<PuntoVenta> findAll();
    public PuntoVenta save(PuntoVenta puntoVenta);
    public Optional<PuntoVenta> update(PuntoVenta puntoVenta,Long id);
    public void delete(Long id);
}
