package com.nttd.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.nttd.dto.AuditDto;
import com.nttd.dto.ResponseDto;
import com.nttd.entity.AuditEntity;
import com.nttd.repository.AuditRepository;
import com.nttd.service.AuditService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuditServiceImpl implements AuditService {

    @Inject
    AuditRepository auditRepository;

    @ConfigProperty(name = "mensaje.general")
    String messagegeneric;

    @ConfigProperty(name = "message.001")
    String message001;

    @ConfigProperty(name = "error.generic")
    String errorgeneric;

    public Uni<ResponseDto> add(AuditDto auditDto) {
        try {
            return auditRepository.add(toAudit(auditDto))
                    .map(audit -> new ResponseDto(201, message001, toAuditDto(audit)))
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric, error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    public Uni<ResponseDto> listAll() {
        try {
            return auditRepository.listAll()
                    .map(audits -> {
                        List<AuditDto> lista = new ArrayList<>();

                        for (AuditEntity audit : audits) {
                            AuditDto auditDto = new AuditDto();

                            auditDto.setIdAuditoria(audit.id.toString());
                            auditDto.setAplicacion(audit.getApplication());
                            auditDto.setUsuarioAplicacion(audit.getApplicationUser());
                            auditDto.setUsuarioSesion(audit.getSessionUser());
                            auditDto.setCodigoTransaccion(audit.getTransactionCode());
                            auditDto.setFechaTransaccion(audit.getTransactionDate());
                            auditDto.setMensaje(audit.getMessage());
                            auditDto.setRequest(audit.getRequest());
                            auditDto.setResponse(audit.getResponse());

                            lista.add(auditDto);
                        }

                        return lista;
                    })
                    .map(auditDto -> new ResponseDto(200, messagegeneric, auditDto))
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric,
                            error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    AuditEntity toAudit(AuditDto auditDto) {
        AuditEntity audit = new AuditEntity();

        audit.setApplication(auditDto.getAplicacion());
        audit.setApplicationUser(auditDto.getUsuarioAplicacion());
        audit.setSessionUser(auditDto.getUsuarioSesion());
        audit.setTransactionCode(String.valueOf(System.currentTimeMillis()));
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss z");
        audit.setTransactionDate(dateFormat.format(new Date()));
        audit.setMessage(auditDto.getMensaje());
        audit.setRequest(auditDto.getRequest());
        audit.setResponse(auditDto.getResponse());

        return audit;
    }

    AuditDto toAuditDto(AuditEntity audit) {
        AuditDto auditDto = new AuditDto();

        auditDto.setIdAuditoria(audit.id.toString());
        auditDto.setAplicacion(audit.getApplication());
        auditDto.setUsuarioAplicacion(audit.getApplicationUser());
        auditDto.setUsuarioSesion(audit.getSessionUser());
        auditDto.setCodigoTransaccion(audit.getTransactionCode());
        auditDto.setFechaTransaccion(audit.getTransactionDate());
        auditDto.setMensaje(audit.getMessage());
        auditDto.setRequest(audit.getRequest());
        auditDto.setResponse(audit.getResponse());

        return auditDto;
    }

}
