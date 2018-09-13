package com.ps.services.impl;

import com.ps.ents.Response;
import com.ps.repos.ResponseRepo;
import com.ps.services.ResponseService;
import org.springframework.stereotype.Service;

@Service
public class SimpleResponseService extends SimpleAbstractService<Response> implements ResponseService {

    private ResponseRepo repo;
    
    //                setters & getters
    public void setRepo(ResponseRepo responseRepo) {
        this.repo = responseRepo;
    }

    @Override
    public ResponseRepo getRepo() {
        return repo;
    }
}