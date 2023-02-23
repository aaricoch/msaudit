package com.nttd.repository;

import java.util.List;

import com.nttd.entity.AuditEntity;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditRepository {

    public Uni<AuditEntity> add(AuditEntity audit) {
        return audit.persist();
    }

    public Uni<List<AuditEntity>> listAll() {
        return AuditEntity.listAll();
    }

}
