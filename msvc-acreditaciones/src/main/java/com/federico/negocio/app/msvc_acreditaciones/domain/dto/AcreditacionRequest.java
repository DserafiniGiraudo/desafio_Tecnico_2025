package com.federico.negocio.app.msvc_acreditaciones.domain.dto;

import lombok.Data;

@Data
public class AcreditacionRequest {

    private double importe;
    private long identificadorPuntoventa;
}
