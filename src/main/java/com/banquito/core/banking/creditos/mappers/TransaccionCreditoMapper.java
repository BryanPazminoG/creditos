package com.banquito.core.banking.creditos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.TransaccionCredito;
import com.banquito.core.banking.creditos.dto.TransaccionCreditoDTO;

@Mapper(componentModel = "spring")
public interface TransaccionCreditoMapper {
    TransaccionCreditoMapper INSTANCE = Mappers.getMapper(TransaccionCreditoMapper.class);

    TransaccionCreditoDTO DTOToEntity(TransaccionCredito entity);

    @Mappings({
            @Mapping(target = "fechaCreacion", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    TransaccionCredito entityToDTO(TransaccionCreditoDTO dto);

}