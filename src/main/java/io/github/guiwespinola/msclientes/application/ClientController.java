package io.github.guiwespinola.msclientes.application;

import io.github.guiwespinola.msclientes.ClientService;
import io.github.guiwespinola.msclientes.application.representation.ClientSaveRequest;
import io.github.guiwespinola.msclientes.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientSaveRequest request) {
        var client = clientService.save(request.toModel());

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest() // cria uma URI a partir da requisição atual
                .query("cpf={cpf}") // para passar parâmetros via URL (parâmetro cpf vai ser recebido do buildAndExpand
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> ClientData(@RequestParam("cpf") String cpf) {
        var client = clientService.getByCPF(cpf);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.ok(client);
    }
}
