package com.rankomat.task.controller;

import com.rankomat.task.domain.Client;
import com.rankomat.task.domain.ClientDto;
import com.rankomat.task.mapper.ClientMapper;
import com.rankomat.task.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private final DbService service;
    private final ClientMapper mapper;

    @Autowired
    public ClientController(DbService service, ClientMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createClient(@RequestBody ClientDto clientDto) {
        Client client = mapper.mapToClient(clientDto);
        service.saveClient(client);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {
        Client client = service.update(mapper.mapToClient(clientDto));
        Client savedClient = service.saveClient(client);
        return ResponseEntity.ok(mapper.mapToClientDto(savedClient));
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getReport(String scope) {
        List<Client> reportList = service.generateReport(scope);
        return ResponseEntity.ok(mapper.mapToTaskDtoList(reportList));
    }

}
