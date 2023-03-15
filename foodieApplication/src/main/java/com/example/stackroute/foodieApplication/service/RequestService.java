package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.model.OwnerRequest;

import java.util.List;

public interface RequestService {
    public abstract OwnerRequest requestRepo(OwnerRequest request);
    public abstract List<OwnerRequest> allRequests();
    public abstract boolean removeReq(String emailID);
}
