package com.banquito.core.banking.creditos.controller;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
// import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

@RestController
@RequestMapping("/creditointerviniente")
public class CreditoIntervinienteController {
    @Autowired
    private CreditoIntervinienteService creditoIntervinienteService;

    // @GetMapping("/getall")
    // public ResponseEntity<List<CreditoInterviniente>> GetAll() {
    // return new ResponseEntity<>(creditoIntervinienteService.GetAll(),
    // HttpStatus.OK);
    // }

    @GetMapping("/getbyid/{creditoid}/{clienteid}")
    public ResponseEntity<CreditoInterviniente> GetById(@PathVariable("creditoid") Integer creditoId,
            @PathVariable("clienteid") Integer clienteId) {
        return creditoIntervinienteService.getById(creditoId, clienteId)
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<CreditoInterviniente> Save(@RequestBody CreditoInterviniente creditoInterviniente) {
        return new ResponseEntity<>(creditoIntervinienteService.create(creditoInterviniente), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{creditoid}/{clienteid}")
    public ResponseEntity<Boolean> Delete(@PathVariable("creditoid") Integer creditoId,
            @PathVariable("clienteid") Integer clienteId) {
        creditoIntervinienteService.delete(creditoId, clienteId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CreditoInterviniente> Update(@RequestBody CreditoInterviniente creditoInterviniente) {
        return new ResponseEntity<>(creditoIntervinienteService.update(creditoInterviniente), HttpStatus.OK);
    }

    // @GetMapping("/bytipo/{tipo}/{creditoid}/{clienteid}")
    // public ResponseEntity<List<CreditoInterviniente>>
    // ByTipo(@PathVariable("tipo") String tipo,
    // @PathVariable("creditoid") Integer creditoId,
    // @PathVariable("clienteid") Integer clienteId) {
    // CreditoIntervinientePK creditoIntervinientePK = new
    // CreditoIntervinientePK(creditoId, clienteId);
    // return creditoIntervinienteService.ByTipo(tipo,
    // creditoIntervinientePK).map(register -> {
    // return new ResponseEntity<>(register, HttpStatus.OK);
    // }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }
}
