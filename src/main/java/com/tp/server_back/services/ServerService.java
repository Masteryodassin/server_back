package com.tp.server_back.services;

import com.tp.server_back.entities.Server;
import com.tp.server_back.repository.ServerRepository;
import com.tp.server_back.services.interfaces.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ServerService extends AbstractService<Server, ServerRepository>{


}
