package com.bartender.api.services;

import com.bartender.api.dto.BartenderRequestDto;
import com.bartender.api.infrastructure.entity.ArraysEntity;
import com.bartender.api.infrastructure.repository.ArrayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class BartenderServiceTest {

    @Mock
    private ArrayRepository arraysRepository;

    String expected;

    @InjectMocks
    private BartenderService bartenderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        expected = "7,10,15,11,9";
    }

    @Test
    void calculateAssertNotNull() {
        ArraysEntity array = new ArraysEntity();
        array.setId(1);
        array.setInputArray(expected);
        when(arraysRepository.findById(anyInt())).thenReturn(Optional.of(array));
        BartenderRequestDto body = new BartenderRequestDto();
        body.setNIteration(2);
        body.setPilaId(1);
        assertNotNull(bartenderService.calculate(body));
    }

    @Test
    void calculateAssertExact() {
        ArraysEntity array = new ArraysEntity();
        array.setId(1);
        array.setInputArray(expected);
        when(arraysRepository.findById(anyInt())).thenReturn(Optional.of(array));
        BartenderRequestDto body = new BartenderRequestDto();
        body.setNIteration(3);
        body.setPilaId(5);
        Map<String, Object> expect = new HashMap<>();
        List<Integer> result = new ArrayList<>(Arrays.asList(10,15,9,11,7));
        expect.put("result", result);
        assertEquals(bartenderService.calculate(body), expect);
    }

}