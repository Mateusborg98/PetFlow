package com.spring.petflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.petflow.DTO.ClienteRquestDTO;
import com.spring.petflow.entity.Cliente;
import com.spring.petflow.entity.StatusAtendimento;
import com.spring.petflow.repository.AtendimentoRepository;
import com.spring.petflow.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final AtendimentoRepository AtendimentoRepository;

    public ClienteService(ClienteRepository clienteRepository, AtendimentoRepository atendimentoRepository) {
        this.clienteRepository = clienteRepository;
        this.AtendimentoRepository = atendimentoRepository;
    }

    public void desativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente criarCliente(ClienteRquestDTO clienteDto) {
        if (!clienteDto.getNome().isEmpty() && !clienteDto.getTelefone().isEmpty()) {
            Cliente clienteEntity = new Cliente();
            clienteEntity.setNome(clienteDto.getNome());
            clienteEntity.setCpf(clienteDto.getCpf());
            clienteEntity.setTelefone(clienteDto.getTelefone());
            clienteEntity.setEmail(clienteDto.getEmail());
            clienteEntity.setEndereco(clienteDto.getEndereco());
            return clienteRepository.save(clienteEntity);
        } else {
            throw new IllegalStateException(
                    "Nome e telefone são obrigatórios para criar um cliente.");
        }
    }

    @Transactional
    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + id));
        boolean existeAberto = AtendimentoRepository.existsByPetClienteIdAndStatusIn(
                cliente.getId(),
                List.of(
                        StatusAtendimento.ABERTO,
                        StatusAtendimento.EM_EXECUCAO));

        if (existeAberto) {
            throw new IllegalStateException(
                    "Não é possível deletar o cliente. Existem atendimentos em aberto ou em andamento.");
        } else {
            this.desativarCliente(id);
        }
    }
}
