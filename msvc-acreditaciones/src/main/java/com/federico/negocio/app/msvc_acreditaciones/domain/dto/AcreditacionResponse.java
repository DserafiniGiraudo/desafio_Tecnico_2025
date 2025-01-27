package com.federico.negocio.app.msvc_acreditaciones.domain.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AcreditacionResponse {

    private long id;
    private double importe;
    private long identificadorPuntoventa;
    private LocalDate fechaPedido;
    private String nombrePuntoventa;
}
