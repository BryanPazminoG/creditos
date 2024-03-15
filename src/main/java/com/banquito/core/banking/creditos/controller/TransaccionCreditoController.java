package com.banquito.core.banking.creditos.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.banquito.core.banking.creditos.dto.TransaccionCreditoDTO;
import com.banquito.core.banking.creditos.service.TransaccionCreditoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/transaccionCreditos")
public class TransaccionCreditoController {
    private TransaccionCreditoService transaccionCreditoService;

    public TransaccionCreditoController(TransaccionCreditoService transaccionCreditoService) {
        this.transaccionCreditoService = transaccionCreditoService;
    }

    @GetMapping("{codTransaccionCredito}")
    public ResponseEntity<TransaccionCreditoDTO> obtenerPorId(@PathVariable("codTransaccionCredito") Integer codTransaccionCredito) {
        try {
            log.info("Obteniendo la transaccion credito por el ID: {}", codTransaccionCredito);
            TransaccionCreditoDTO transaccionCreditoDTO = transaccionCreditoService.obtenerPorId(codTransaccionCredito);
            return ResponseEntity.ok(transaccionCreditoDTO);
        } catch (Exception e) {
            log.error("Error al obtener la transaccion credito por el ID {}", codTransaccionCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}")
    public ResponseEntity<List<TransaccionCreditoDTO>> obtenerPorCredito(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo la transaccion credito por el ID credito: {}", codCredito);
            List<TransaccionCreditoDTO> listTransaccionCreditoDTO = transaccionCreditoService.listarPorCredito(codCredito);
            return ResponseEntity.ok(listTransaccionCreditoDTO);
        } catch (Exception e) {
            log.error("Error al obtener la transaccion credito por el ID credito {}", codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}/{cuota}")
    public ResponseEntity<TransaccionCreditoDTO> obtenerPorCreditoCuota(@PathVariable("codCredito") Integer codCredito, @PathVariable("cuota") Integer cuota) {
        try {
            log.info("Obteniendo la transaccion credito por el ID credito  {} y cuota: {}", codCredito, cuota);
            TransaccionCreditoDTO transaccionCreditoDTO = transaccionCreditoService.listarPorCreditoCuota(codCredito, cuota);
            return ResponseEntity.ok(transaccionCreditoDTO);
        } catch (Exception e) {
            log.error("Error al obtener la transaccion credito por el ID credito  {} y cuota: {}", codCredito, cuota);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<TransaccionCreditoDTO> crear(@RequestBody TransaccionCreditoDTO transaccionCreditoDTO) {
        try {
            log.info("Creando la transaccion credito: {}", transaccionCreditoDTO);
            return ResponseEntity.ok(transaccionCreditoService.crear(transaccionCreditoDTO));
        } catch (RuntimeException rte) {
            log.error("Error al crear la transaccion credito: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
