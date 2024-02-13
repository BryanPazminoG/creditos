package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.TipoCreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TipoCreditoService {
    private final TipoCreditoRepository tipoCreditoRepository;

    public TipoCreditoService(TipoCreditoRepository tipoCreditoRepository) {
        this.tipoCreditoRepository = tipoCreditoRepository;
    }

    public TipoCreditoDTO obtenerPorId(Integer id) {
        Optional<TipoCredito> tipoCredito = this.tipoCreditoRepository.findById(id);
        if (tipoCredito.isPresent()) {
            log.info("El tipo credito con id {} se ha obtenido", id);
            return TipoCreditoBuilder.toDTO(tipoCredito.get());
        } else {
            throw new RuntimeException("El Tipo Credito con id" + id + " no existe");
        }
    }

    public List<TipoCreditoDTO> listar() {
        List<TipoCreditoDTO> listDTO = new ArrayList<>();
        for (TipoCredito tipoCredito : this.tipoCreditoRepository.findAll()) {
            if(tipoCredito.getEstado().equals("ACT") && tipoCredito.getTipoCliente().equals("NAT")){
                listDTO.add(TipoCreditoBuilder.toDTO(tipoCredito));
            }
        }
        return listDTO;
    }

    public TipoCreditoDTO crear(TipoCreditoDTO dto) {
        try {
            TipoCredito tipoCredito = TipoCreditoBuilder.toTipoCredito(dto);
            this.tipoCreditoRepository.save(tipoCredito);
            log.info("El tipo credito se ha almacenado correctamente: {}", dto);
            return dto;
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Tipo Credito: " + dto.toString(), e);
        }
    }

    public void eliminar(Integer id) {
        try {
            Optional<TipoCredito> tipoCredito = this.tipoCreditoRepository.findById(id);
            if (tipoCredito.isPresent()) {
                this.tipoCreditoRepository.delete(tipoCredito.get());
                log.info("El tipo credito con el id {} se ha eliminado exitosamente", id);
            } else {
                throw new RuntimeException("El Tipo Credito con id" + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Tipo Credito, error: " + e.getMessage(), e);
        }
    }

    public TipoCreditoDTO actualizar(TipoCreditoDTO dto) {
        try {
            TipoCreditoDTO tipoCredito = obtenerPorId(dto.getCodTipoCredito());
            if (tipoCredito != null) {
                return crear(tipoCredito);
            } else {
                throw new RuntimeException(
                        "El Tipo Credito con id" + dto.getCodTipoCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la Tipo Credito, error: " + e.getMessage(), e);
        }
    }
}
