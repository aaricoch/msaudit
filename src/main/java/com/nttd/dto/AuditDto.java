package com.nttd.dto;

import lombok.Data;

@Data
public class AuditDto {
    private String idAuditoria;
    private String aplicacion;
    private String usuarioAplicacion;
    private String usuarioSesion;
    private String codigoTransaccion;
    private String fechaTransaccion;
    private String mensaje;
    private String request;
    private String response;
}
