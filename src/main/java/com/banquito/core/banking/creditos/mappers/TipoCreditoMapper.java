package com.banquito.core.banking.creditos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;

@Mapper(componentModel = "spring")
public interface TipoCreditoMapper {

    TipoCreditoMapper INSTANCE = Mappers.getMapper(TipoCreditoMapper.class);

    TipoCreditoDTO DTOToEntity(TipoCredito entity);

    @Mappings({
            @Mapping(target = "fechaCreacion", ignore = true),
            @Mapping(target = "fechaUltimoCambio", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    TipoCredito entityToDTO(TipoCreditoDTO dto);
}