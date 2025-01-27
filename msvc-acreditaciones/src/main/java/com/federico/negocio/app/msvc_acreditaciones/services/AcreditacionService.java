package com.federico.negocio.app.msvc_acreditaciones.services;

import java.util.Optional;

import com.federico.negocio.app.msvc_acreditaciones.domain.dto.AcreditacionRequest;
import com.federico.negocio.app.msvc_acreditaciones.domain.dto.AcreditacionResponse;

public interface AcreditacionService {
    
    Optional<AcreditacionResponse> getAcreditacionById(Long id);
    public Optional<AcreditacionResponse> guardarAcreditacion(AcreditacionRequest acreditacionRequest);
}
