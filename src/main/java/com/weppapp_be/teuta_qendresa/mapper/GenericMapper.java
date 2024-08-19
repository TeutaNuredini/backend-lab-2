package com.weppapp_be.teuta_qendresa.mapper;

public interface GenericMapper<E, D, R>{

    D toDto(E entity);

    E toEntity(R request);
}
