package com.bartender.api.services;

import com.bartender.api.dto.BartenderRequestDto;
import com.bartender.api.infrastructure.entity.ArraysEntity;
import com.bartender.api.infrastructure.repository.ArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BartenderService {

    @Autowired
    private ArrayRepository arrayRepo;

    private Optional<ArraysEntity> getArray(Integer id ) {
        return arrayRepo.findById(id);
    }

    public Object calculate(BartenderRequestDto data) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        List<Integer> array = converterArray(data.getPilaId());
        List<Integer> primes = generatePrimes(data.getNIteration());
        for (int i = 1; i <= data.getNIteration(); i++) {
            Integer prime = primes.get(i-1);
            for ( int x = array.toArray().length - 1; x >= 0; x-- ) {
                if (array.get(x) % prime == 0) { b.add(array.get(x)); } else { a.add(array.get(x)); }
            }
            r = Stream.concat(r.stream(), b.stream()).collect(Collectors.toList());
            array = new ArrayList<>(a);
            a.clear();
            b.clear();
        }
        r = Stream.concat(r.stream(), array.stream()).collect(Collectors.toList());
        HashMap<String, List<Integer>> response = new HashMap<>();
        response.put("result",r);
        return response;
    }

    private List<Integer> converterArray(Integer pilaId) {
        Optional<ArraysEntity> array = arrayRepo.findById(pilaId);
        if (array.isPresent()) {
            int[] arrays = Arrays.stream(array.get().getInputArray().split(",")).mapToInt(Integer::parseInt).toArray();
            return Arrays.stream(arrays).boxed().toList();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pila Id not exists...");
        }
    }

    private List<Integer> generatePrimes(Integer n_iteration) {
        List<Integer> result = new ArrayList<>();
        Integer count = 2;
        while (n_iteration != 0) {
            if (isPrime(count)) {
                result.add(count);
                n_iteration--;
            }
            count++;
        }
        return result;
    }

    private boolean isPrime(int value) {
        if (value == 0 || value == 1 || value == 4) return false;
        for (int x = 2; x < value / 2; x++) {
            if (value % x == 0) return false;
        }
        return true;
    }

}
