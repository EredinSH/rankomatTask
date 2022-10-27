package com.rankomat.task.mapper;

import com.rankomat.task.domain.Client;
import com.rankomat.task.domain.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientMapperTest {

    @Mock
    ClientMapper mapper;

    @Test
    public void mapToClientTest() {
        //GIVEN
        ClientDto clientDto = new ClientDto(1L,"Karol",111222333,21.37);
        when(mapper.mapToClient(clientDto)).thenReturn((new Client(1L,"Karol",111222333,21.37)));

        //WHEN
        Client client = mapper.mapToClient(clientDto);

        //THEN
        assertThat(client).isNotNull();
        assertEquals(1L,client.getId());
        assertEquals("Karol",client.getName());
        assertEquals(111222333,client.getPhoneNumber());
        assertEquals(21.37,client.getAmount());

    }

    @Test
    public void mapToClientDtoTest() {
        //GIVEN
        Client client = new Client(1L,"Karol",111222333,21.37);
        when(mapper.mapToClientDto(client)).thenReturn((new ClientDto(1L,"Karol",111222333,21.37)));

        //WHEN
        ClientDto clientDto = mapper.mapToClientDto(client);

        //THEN
        assertThat(clientDto).isNotNull();
        assertEquals(1L,clientDto.getId());
        assertEquals("Karol",clientDto.getName());
        assertEquals(111222333,clientDto.getPhoneNumber());
        assertEquals(21.37,clientDto.getAmount());

    }

    @Test
    public void mapToClientDtoListTest() {
        //GIVEN
        Client client1 = new Client(1L,"Karol",111222333,21.37);
        Client client2 = new Client(1L,"Jacek",123456789,70000000);
        List<Client> clientsList = new ArrayList<>();
        clientsList.add(client1);
        clientsList.add(client2);

        List<ClientDto> clientDtoList = List.of(new ClientDto(1L,"Karol",111222333,21.37),
        (new ClientDto(1L,"Jacek",123456789,70000000)));

        when(mapper.mapToClientDtoList(clientsList)).thenReturn(clientDtoList);

        //WHEN
        List<ClientDto> result = mapper.mapToClientDtoList(clientsList);

        //THEN
        assertEquals(2, result.size());
        assertEquals(21.37, result.get(0).getAmount());
        assertEquals(123456789, result.get(1).getPhoneNumber());

    }

}
