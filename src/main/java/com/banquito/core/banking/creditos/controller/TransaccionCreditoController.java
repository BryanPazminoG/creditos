package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200", "http://34.145.220.97:4204", "http://34.145.219.32:4200", "http://34.86.49.111:4200", "http://34.162.115.216:4200"})
@RestController
@RequestMapping("api/v1/transaccionCredito")
public class TransaccionCreditoController {
    
    @Autowired
    private TransaccionCreditoService transaccionCreditoService;

    @GetMapping("/{codTransaccionCredito}")
    public ResponseEntity<TransaccionCreditoDTO> ObtenerPorId(
            @PathVariable("codTransaccionCredito") Integer codTransaccionCredito) {
        try {
            log.info("Obteniendo la transaccion credito por el ID: {}", codTransaccionCredito);
            TransaccionCreditoDTO transaccionCreditoDTO = transaccionCreditoService.ObtenerPorId(codTransaccionCredito);
            return ResponseEntity.ok(transaccionCreditoDTO);
        } catch (Exception e) {
            log.error("Error al obtener la transaccion credito por el ID {}", codTransaccionCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}")
    public ResponseEntity<List<TransaccionCreditoDTO>> ObtenerPorCredito(
            @PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo la transaccion credito por el ID credito: {}", codCredito);
            List<TransaccionCreditoDTO> listTransaccionCreditoDTO = transaccionCreditoService
                    .ListarPorCredito(codCredito);
            return ResponseEntity.ok(listTransaccionCreditoDTO);
        } catch (Exception e) {
            log.error("Error al obtener la transaccion credito por el ID credito {}", codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}/{numeroCuota}")
    public ResponseEntity<TransaccionCreditoDTO> ObtenerPorCreditoCuota(@PathVariable("codCredito") Integer codCredito,
            @PathVariable("numeroCuota") Integer numeroCuota) {
        try {
            log.info("Obteniendo la transaccion credito por el ID credito  {} y cuota: {}", codCredito, numeroCuota);
            TransaccionCreditoDTO transaccionCreditoDTO = transaccionCreditoService.ListarPorCreditoCuota(codCredito,
                    numeroCuota);
            return ResponseEntity.ok(transaccionCreditoDTO);
        } catch (Exception e) {
            log.error("Error al obtener la transaccion credito por el ID credito  {} y cuota: {}", codCredito,
                    numeroCuota);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransaccionCreditoDTO> Crear(@RequestBody TransaccionCreditoDTO transaccionCreditoDTO) {
        try {
            log.info("Creando la transaccion credito: {}", transaccionCreditoDTO);
            return ResponseEntity.ok(transaccionCreditoService.Crear(transaccionCreditoDTO));
        } catch (RuntimeException rte) {
            log.error("Error al crear la transaccion credito: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
