package com.example.Server.service;

import com.example.Server.model.Server;

import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress);
    Collection<Server> list(int limit);
    Server ge(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
