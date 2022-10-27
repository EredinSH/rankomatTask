package com.rankomat.task.controller;

import com.google.gson.Gson;
import com.rankomat.task.domain.Client;
import com.rankomat.task.domain.ClientDto;
import com.rankomat.task.mapper.ClientMapper;
import com.rankomat.task.service.DbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private ClientMapper mapper;

    @Test
    public void shouldCreateClientTest() throws Exception {
        //GIVEN
        Client client = new Client(1L,"Karol",111222333,21.37);
        ClientDto clientDto = new ClientDto(1L,"Karol",111222333,21.37);

        when(mapper.mapToClient(clientDto)).thenReturn(client);
        when(service.saveClient(client)).thenReturn(new Client(1L,"Karol",111222333,21.37));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(clientDto);

        //WHEN && THEN
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateClient() throws Exception {
        //GIVEN
        Client client = new Client(1L,"Karol",111222333,21.37);
        ClientDto clientDto = new ClientDto(1L,"Karol",111222333,21.37);
        ClientDto updatedClientDto = new ClientDto(2L,"Karol",111222333,50.5);

        when(mapper.mapToClient(clientDto)).thenReturn(client);
        when(service.saveClient(client)).thenReturn(new Client(2L,"Karol",111222333,50.5));
        when(mapper.mapToClientDto(any())).thenReturn(updatedClientDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedClientDto);

        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Karol")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.is(111222333)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(50.5)))
                .andExpect(status().isOk());

    }


}
