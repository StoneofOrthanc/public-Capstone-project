package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.HireStatusDao;
import com.kenzie.capstone.service.exceptions.InvalidDataException;
import com.kenzie.capstone.service.model.HireRequest;
import com.kenzie.capstone.service.model.HireResponse;
import com.kenzie.capstone.service.model.HireStatus;
import com.kenzie.capstone.service.model.HireStatusRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** This file created by another team member **/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HireStatusServiceTest {

    /** ------------------------------------------------------------------------
     *  expenseService.getExpenseById
     *  ------------------------------------------------------------------------ **/

    private HireStatusDao hireDao;
    private HireStatusService hireService;

    @BeforeAll
    void setup() {
        this.hireDao = mock(HireStatusDao.class);
        this.hireService = new HireStatusService(hireDao);
    }

    @Test
    void setHireStatusTest() {
        ArgumentCaptor<HireStatusRecord> hireCaptor = ArgumentCaptor.forClass(HireStatusRecord.class);

        // GIVEN
        String id = "freelancerId";
        String status = "hired";
        HireRequest request = new HireRequest();
        request.setId(id);
        request.setStatus(status);

        // WHEN
        HireResponse response = this.hireService.setHireStatus(request);

        // THEN
        verify(hireDao, times(1)).setHireStatus(hireCaptor.capture());
        HireStatusRecord record = hireCaptor.getValue();

        assertNotNull(record, "The record is valid");
        assertEquals(id, record.getId(), "The id matches");
        assertEquals(status, record.getStatus(), "The status matches");

        assertNotNull(response, "A response is returned");
        assertEquals(id, response.getId(), "The response id should match");
        assertEquals(status, response.getStatus(), "The response status should match");
    }

    @Test
    void getHireStatusTest() {
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);

        // GIVEN
        String id = "fakeid";
        String status = "hired";
        HireStatusRecord record = new HireStatusRecord();
        record.setId(id);
        record.setStatus(status);


        when(hireDao.getHireStatus(id)).thenReturn(List.of(record));

        // WHEN
        HireStatus response = this.hireService.getHireStatus(id);

        // THEN
        verify(hireDao, times(2)).getHireStatus(idCaptor.capture());

        assertEquals(id, idCaptor.getValue(), "The correct id is used");

        assertNotNull(response, "A response is returned");
        assertEquals(id, response.getId(), "The response id should match");
        assertEquals(status, response.getStatus(), "The response status should match");
    }

    @Test
    void getHireStatusTest_null() {
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);

        // GIVEN
        String id = "fakeid2";

        when(hireDao.getHireStatus(id)).thenReturn(Collections.emptyList());

        HireStatus nullResponse = this.hireService.getHireStatus(id);

        assertNull(nullResponse, "A null response is returned");
    }

    @Test
    void setHireStatusTest_exception() {
        Assertions.assertThrows(InvalidDataException.class, () -> this.hireService.setHireStatus(null));
    }
}