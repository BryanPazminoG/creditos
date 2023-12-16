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

import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.service.CreditoService;

@RestController
@RequestMapping("/credito")
public class CreditoController {
    @Autowired
    private CreditoService creditoService;

    // @GetMapping("/getall")
    // public ResponseEntity<List<Credito>> GetAll() {
    // return new ResponseEntity<>(creditoService.GetAll(), HttpStatus.OK);
    // }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Credito> GetById(@PathVariable("id") Integer id) {
        return creditoService.getById(id)
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Credito> Save(@RequestBody Credito credito) {
        return new ResponseEntity<>(creditoService.create(credito), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> Delete(@PathVariable Integer id) {
        creditoService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Credito> Update(@RequestBody Credito credito) {
        return new ResponseEntity<>(creditoService.update(credito), HttpStatus.OK);
    }
    // @GetMapping("/tipocredito/{tipocredito}")
    // public ResponseEntity<List<Credito>> TipoCredito(@PathVariable("tipocredito")
    // Integer tipocredito) {
    // return new ResponseEntity<>(creditoService.TipoCredito(tipocredito),
    // HttpStatus.OK);
    // }

    // @GetMapping("/cliente/{cliente}")
    // public ResponseEntity<List<Credito>> Cliente(@PathVariable("cliente") Integer
    // cliente) {
    // return creditoService.Cliente(cliente).map(register -> {
    // return new ResponseEntity<>(register, HttpStatus.OK);
    // }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }

    // @GetMapping("/estadocliente/{estado}/{cliente}")
    // public ResponseEntity<List<Credito>> EstadoCliente(@PathVariable("estado")
    // String estado,
    // @PathVariable("cliente") Integer cliente) {
    // return creditoService.EstadoCliente(estado, cliente).map(register -> {
    // return new ResponseEntity<>(register, HttpStatus.OK);
    // }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }

}
