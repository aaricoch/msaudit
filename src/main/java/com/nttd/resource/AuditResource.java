package com.nttd.resource;


import org.eclipse.microprofile.openapi.annotations.Operation;

import com.nttd.dto.AuditDto;
import com.nttd.dto.ResponseDto;
import com.nttd.service.AuditService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/audit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditResource {

    @Inject
    AuditService auditService;

    @POST
    @Operation(summary = "Registrar la auditoria",description = "Registrar la auditoria")
    public Uni<ResponseDto> add(AuditDto auditDto) {
        return auditService.add(auditDto);
    }

    @GET
    @Operation(summary = "Obtener la auditoria total",description = "Obtienes la auditoria total")
    public Uni<ResponseDto> listAll() {
        return auditService.listAll();
    }

}
