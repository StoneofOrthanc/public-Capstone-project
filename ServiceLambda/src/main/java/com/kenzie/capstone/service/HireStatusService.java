package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.HireStatusDao;
import com.kenzie.capstone.service.exceptions.InvalidDataException;
import com.kenzie.capstone.service.model.HireRequest;
import com.kenzie.capstone.service.model.HireResponse;
import com.kenzie.capstone.service.model.HireStatus;
import com.kenzie.capstone.service.model.HireStatusRecord;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/** This file created by another team member **/

public class HireStatusService {
    private HireStatusDao hireStatusDao;

    @Inject
    public HireStatusService(HireStatusDao hireStatusDao) {
        this.hireStatusDao = hireStatusDao;
    }

    public HireStatus getHireStatus(String freelancerId) {
        List<HireStatusRecord> records = hireStatusDao.getHireStatus(freelancerId);
        if (records.size() > 0) {
            return new HireStatus(records.get(0).getId(), records.get(0).getStatus());
        }

        return null;
    }

    public HireResponse setHireStatus(HireRequest hireRequest) {
        if (hireRequest == null) {
            throw new InvalidDataException("Request must not be null");
        }
        HireStatusRecord record = new HireStatusRecord();
        record.setId(hireRequest.getId());
        record.setStatus(hireRequest.getStatus());
        hireStatusDao.setHireStatus(record);

        return new HireResponse(record.getId(), record.getStatus());
    }
}
