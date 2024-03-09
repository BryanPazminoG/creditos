package com.banquito.core.banking.creditos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;

@Mapper(componentModel = "spring")
public interface TasaInteresMapper {

    TasaInteresDTO DTOToEntity(TasaInteres entity);
    @Mappings({
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    TasaInteres entityToDTO(TasaInteresDTO dto);
}