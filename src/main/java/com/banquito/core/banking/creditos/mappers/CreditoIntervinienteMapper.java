package com.banquito.core.banking.creditos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;

@Mapper(componentModel = "spring")
public interface CreditoIntervinienteMapper {
    CreditoIntervinienteMapper INSTANCE = Mappers.getMapper( CreditoIntervinienteMapper.class );

    CreditoIntervinienteDTO DTOToEntity(CreditoInterviniente entity);
    @Mappings({
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    CreditoInterviniente entityToDTO(CreditoIntervinienteDTO dto);
}