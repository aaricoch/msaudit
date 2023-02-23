package com.nttd.service;


import com.nttd.dto.AuditDto;
import com.nttd.dto.ResponseDto;

import io.smallrye.mutiny.Uni;

public interface AuditService {

    Uni<ResponseDto> add(AuditDto audit);

    Uni<ResponseDto> listAll();

}
