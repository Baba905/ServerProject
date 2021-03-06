package com.example.Server.service.implementation;

import com.example.Server.enumeration.Status;
import com.example.Server.model.Server;
import com.example.Server.repository.ServerRepo;
import com.example.Server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Logger;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private  ServerRepo serverRepo;
    Logger log
            = Logger.getLogger(
            Server.class.getName());
    @Override
    public Server create(Server server) {
        String message= "Saving new server: "+server.getName();
        log.info(message);
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        String message= "Pinging server IP: "+ ipAddress;
        log.info(message);
        Server server =serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP: Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        String message= "Fetching all server";
        log.info(message);
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        String message= "Fetching by ID: "+ id;
        log.info(message);
        return serverRepo.findById(id).get();
    }


    @Override
    public Server update(Server server) {
        String message= "Updating server : "+ server.getName();
        log.info(message);
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        String message= "Deleting server ID: "+ id;
        log.info(message);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames ={"server1.png","server2.png","server3.png","server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image"+imageNames[new Random().nextInt(4)]).toString();
    }
}


