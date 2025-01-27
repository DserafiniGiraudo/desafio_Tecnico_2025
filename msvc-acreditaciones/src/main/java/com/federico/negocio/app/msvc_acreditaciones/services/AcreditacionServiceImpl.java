package com.federico.negocio.app.msvc_acreditaciones.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.federico.negocio.app.msvc_acreditaciones.domain.Acreditacion;
import com.federico.negocio.app.msvc_acreditaciones.domain.dto.AcreditacionRequest;
import com.federico.negocio.app.msvc_acreditaciones.domain.dto.AcreditacionResponse;
import com.federico.negocio.app.msvc_acreditaciones.mapper.AcreditacionesMapper;
import com.federico.negocio.app.msvc_acreditaciones.repositories.AcreditacionRepository;
import com.federico.negocio.libs.commons.libs_msvc_commons.domain.PuntoVenta;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcreditacionServiceImpl implements AcreditacionService {

    private final AcreditacionRepository repo;
    private final WebClient.Builder client;
    private final AcreditacionesMapper mapper;

    @Override
    @Transactional
    public Optional<AcreditacionResponse> guardarAcreditacion(AcreditacionRequest acreditacionRequest) {
        
        Optional<String> nombrePuntoventa = getNombrePuntoventa(acreditacionRequest.getIdentificadorPuntoventa());
        if(nombrePuntoventa.isEmpty()){
            return Optional.empty();
        }else{
            Acreditacion acreditacion = mapper.toAcreditacion(acreditacionRequest);
            acreditacion.setFechaPedido(LocalDate.now());
            acreditacion.setNombrePuntoventa(nombrePuntoventa.get());
            return Optional.of(mapper.toAcreditacionResponse(repo.save(acreditacion)));
        }
    }

    private Optional<String> getNombrePuntoventa(long identificadorPuntoventa) {

        Optional<PuntoVenta> nombreOptional = client.build()
            .get()
            .uri("http://msvc-puntos-ventas/puntosVentas/{id}",identificadorPuntoventa)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(PuntoVenta.class)
            .map(ResponseEntity::getBody)
            .blockOptional();

        if(nombreOptional.isPresent()) {
            return Optional.of(nombreOptional.get().getPuntoVenta());
        } else {
           return Optional.empty();
        }
    }

    @Override
    public Optional<AcreditacionResponse> getAcreditacionById(Long id) {
        return repo.findById(id).map(mapper::toAcreditacionResponse);
    }
}
