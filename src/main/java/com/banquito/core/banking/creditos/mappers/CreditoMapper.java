package com.banquito.core.banking.creditos.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.dto.CreditoDTO;

@Mapper(componentModel = "spring")
public interface CreditoMapper {

    CreditoMapper INSTANCE = Mappers.getMapper( CreditoMapper.class );

    CreditoDTO DTOToEntity(Credito entity);
    @Mappings({
        @Mapping(target = "numeroOperacion", ignore = true),
        @Mapping(target = "fechaUltimoCambio", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    Credito entityToDTO(CreditoDTO dto);
}