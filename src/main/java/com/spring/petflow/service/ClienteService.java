package com.spring.petflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.petflow.entity.Cliente;
import com.spring.petflow.entity.StatusAtendimento;
import com.spring.petflow.repository.AtendimentoRepository;
import com.spring.petflow.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final AtendimentoRepository AtendimentoRepository;

    public ClienteService(ClienteRepository clienteRepository, AtendimentoRepository atendimentoRepository) {
        this.clienteRepository = clienteRepository;
        this.AtendimentoRepository = atendimentoRepository;
    }

    public String criarCliente(Cliente cliente) {
        if (!cliente.getNome().isEmpty() && !cliente.getTelefone().isEmpty()) {
            clienteRepository.save(cliente);
            return "Cliente criado com sucesso!";    
        } else {
            return "Nome e telefone são obrigatórios para criar um cliente.";
        }
    }

    public String deletarCliente(Long id) {
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
            clienteRepository.setAtivoFalseById(id);
            return "Cliente deletado com sucesso!";
        }
    }
}
