package com.spring.petflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.petflow.DTO.ClienteRquestDTO;
import com.spring.petflow.entity.Cliente;
import com.spring.petflow.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criarCliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid ClienteRquestDTO clienteRquestDTO) {
        Cliente cliente = clienteService.criarCliente(clienteRquestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

}
