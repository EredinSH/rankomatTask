package com.rankomat.task.service;

import com.rankomat.task.domain.Client;
import com.rankomat.task.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DbService {

    private final ClientRepository repository;

    public Client saveClient(final Client client) {
        Timestamp ts = Timestamp.from((Instant.now()));
        client.setTimestamp(ts);
        repository.save(client);
        return client;
    }

    public Client update(Client client) {
        List<Client> list = repository.findAll();
        List<Client> listWithConditions = new ArrayList<>();
        List<Client> finalList = new ArrayList<>();

        for (int t=0; t < list.size(); t++) {
            if (list.get(t).isUpdated() == false) {
                for (int i = 0; i < list.size(); i++) {
                    if (client.getPhoneNumber() == list.get(i).getPhoneNumber()) {
                        listWithConditions.add(list.get(i));
                        for (int n = 0; n < listWithConditions.size(); n++) {
                            if (listWithConditions.get(n).getTimestamp().toLocalDateTime().isBefore(LocalDateTime.now().minusDays(3))) {
                                finalList.add(listWithConditions.get(n));
                            }
                        }
                    }

                }
            }
        }
        Client maxAmount = finalList.stream().max(Comparator.comparing((c -> c.getAmount()))).get();
        Client updatedClient = maxAmount;
        updatedClient.setName(client.getName());
        updatedClient.setPhoneNumber(client.getPhoneNumber());
        updatedClient.setAmount(client.getAmount());
        updatedClient.setUpdated(true);

        return updatedClient;

    }

    public List<Client> generateReport(String scope) {

        List<Client> list = repository.findAll();
        List<Client> updatedList = repository.findByUpdated(true);
        Map<Integer, Map<String,Double>> result = new HashMap<>();

        if(scope == "all") {
            Map<Integer, Map<String,Double>> filterByNumber = list.stream()
                    .collect(Collectors.groupingBy(Client::getPhoneNumber,
                            Collectors.groupingBy(Client::getName,
                                    Collectors.summingDouble(Client::getAmount))));

            result = filterByNumber;

        } else if (scope == "updated") {
            Map<Integer, Map<String,Double>> filterByNumber = updatedList.stream()
                    .collect(Collectors.groupingBy(Client::getPhoneNumber,
                            Collectors.groupingBy(Client::getName,
                                    Collectors.summingDouble(Client::getAmount))));
            result = filterByNumber;
        } else {
            System.out.println("Choose right value: \"all\" or \"updated\"");
        }

        return (List<Client>) result;

    }


}
