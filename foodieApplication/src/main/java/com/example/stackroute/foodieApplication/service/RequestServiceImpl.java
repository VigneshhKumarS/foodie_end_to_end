package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.model.OwnerRequest;
import com.example.stackroute.foodieApplication.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RequestServiceImpl implements RequestService{

    @Autowired
    private RequestRepository requestRepository;
    @Override
    public OwnerRequest requestRepo(OwnerRequest request) {
        return requestRepository.save(request);
    }

    @Override
    public List<OwnerRequest> allRequests() {
        return requestRepository.findAll();
    }

    @Override
    public boolean removeReq(String emailID) {
        requestRepository.deleteById(emailID);
        return true;
    }
}
