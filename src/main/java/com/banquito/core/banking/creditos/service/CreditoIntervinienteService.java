package com.banquito.core.banking.creditos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;
import com.banquito.core.banking.creditos.mappers.CreditoIntervinienteMapper;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import java.time.LocalDate;
import java.sql.Date;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditoIntervinienteService {
    private final CreditoIntervinienteRepository creditoIntervinienteRepository;

    public CreditoIntervinienteService(CreditoIntervinienteRepository creditoIntervinienteRepository) {
        this.creditoIntervinienteRepository = creditoIntervinienteRepository;
    }

    public CreditoIntervinienteDTO ObtenerPorId(Integer codCredito, String codCliente) {
        CreditoIntervinientePK creditoIntervinientePK = new CreditoIntervinientePK(codCredito, codCliente);
        Optional<CreditoInterviniente> creditoInterviniente = this.creditoIntervinienteRepository
                .findById(creditoIntervinientePK);
        if (creditoInterviniente.isPresent()) {
            log.info("Credito Interviniente encotrado");
            return CreditoIntervinienteMapper.INSTANCE.DTOToEntity(creditoInterviniente.get());
        } else {
            throw new RuntimeException(
                    "No se han encontrado el interviniente con el codigo" + codCliente + " en el credito " + codCredito);
        }
    }
    public List<CreditoIntervinienteDTO> ListarIntervinienteCredito(Integer codCredito) {
        List<CreditoIntervinienteDTO> listDTO = new ArrayList<>();
        for (CreditoInterviniente creditoInterviniente : this.creditoIntervinienteRepository.findByPKCodCredito(codCredito)) {
            listDTO.add(CreditoIntervinienteMapper.INSTANCE.DTOToEntity(creditoInterviniente));
        }
        return listDTO;
    }

    @Transactional
    public CreditoIntervinienteDTO Crear(CreditoIntervinienteDTO dto) {
        try {
            CreditoInterviniente creditoInterviniente = CreditoIntervinienteMapper.INSTANCE.entityToDTO(dto);
            LocalDate fechaActualDate = LocalDate.now();
            creditoInterviniente.setFechaCreacion(Date.valueOf(fechaActualDate));
            log.info("El credito Interviniente : {} esta en poceso de creacion ", dto);
            return CreditoIntervinienteMapper.INSTANCE.DTOToEntity(this.creditoIntervinienteRepository.save(creditoInterviniente));
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al crear el Credito Interviniente: " + dto.toString(), e);
        }
    }

    @Transactional
    public void Eliminar(Integer codCredito, String codCliente) {
        try {
            CreditoIntervinientePK PK = new CreditoIntervinientePK(codCredito, codCliente);
            Optional<CreditoInterviniente> creditoInterviniente = this.creditoIntervinienteRepository.findById(PK);
            if (creditoInterviniente.isPresent()) {
                log.info("Se encontro el credito Interviniente");
                this.creditoIntervinienteRepository.delete(creditoInterviniente.get());
                log.info("El credito Interviniente se ha eliminado ");
            } else {
                throw new RuntimeException(
                        "El Credito Interviniente con id " + codCredito + " - " + codCliente + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Interviniente, error: " + e.getMessage(),
                    e);
        }
    }
}
