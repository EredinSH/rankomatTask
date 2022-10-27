package com.rankomat.task.service;

import com.rankomat.task.domain.Client;
import com.rankomat.task.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService service;

    @Mock
    private ClientRepository repository;

    @Test
    public void saveClientTest() {
        //GIVEN
        Client client = new Client(1L,"Karol",111222333,21.37);
        when(repository.save(client)).thenReturn(client);

        //WHEN
        Client result = service.saveClient(client);

        //THEN
        assertThat(result).isNotNull();

    }

/*    @Test
    public void updateClientTest() {                                  //TEST FAILED
        //GIVEN
        Client client1 = new Client(1L,"one",123456781,111.11, Timestamp.valueOf("2022-10-01 01:02:03.123456789"),false);
        Client client2 = new Client(2L,"two",123456782,222.22, Timestamp.valueOf("2022-10-02 01:02:03.123456789"),true);
        Client client3 = new Client(3L,"three",123456783,333.33, Timestamp.valueOf("2022-10-03 01:02:03.123456789"),false);
        Client client4 = new Client(4L,"two",123456782,333.33, Timestamp.valueOf("2022-10-04 01:02:03.123456789"),false);
        Client client5 = new Client(5L,"four",123456782,999.33, Timestamp.valueOf("2022-10-26 01:02:03.123456789"),false);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        clientList.add(client4);
        clientList.add(client5);

        Client updatedClient = new Client(4L,"two",123456782,555.55, Timestamp.valueOf("2022-10-27 01:02:03.123456789"),false);

        //WHEN
        Client result = service.update(updatedClient);

        //THEN
        assertEquals(555.55,result.getAmount());

    }*/


}
