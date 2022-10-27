package com.rankomat.task.mapper;

import com.rankomat.task.domain.Client;
import com.rankomat.task.domain.ClientDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientMapper {

    public Client mapToClient(final ClientDto clientDto) {
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getPhoneNumber(), clientDto.getAmount());
    }

    public ClientDto mapToClientDto(final Client client) {
        return new ClientDto(client.getId(), client.getName(), client.getPhoneNumber(), client.getAmount());
    }

    public List<ClientDto> mapToClientDtoList(final List<Client> clientList) {
        return clientList.stream()
                .map(this::mapToClientDto)
                .collect(Collectors.toList());
    }

}
