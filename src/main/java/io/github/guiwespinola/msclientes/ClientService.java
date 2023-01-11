package io.github.guiwespinola.msclientes;

import io.github.guiwespinola.msclientes.domain.Client;
import io.github.guiwespinola.msclientes.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor // Cria um construtor (injeção de dependência do repositório) em tempo de compilação
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByCPF(String cpf) {
        return clientRepository.findByCpf(cpf);
    }
}
