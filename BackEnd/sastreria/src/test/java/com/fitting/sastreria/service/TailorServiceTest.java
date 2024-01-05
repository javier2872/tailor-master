package com.fitting.sastreria.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fitting.sastreria.data.TailorRepository;
import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.utils.Specialties;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TailorServiceTest {

    @Mock
    private TailorRepository tailorRepository;

    @InjectMocks
    private TailorServiceImpl talorService;
    
    @Test
    void shouldSavedTailorSuccess() {
    	List<Specialties> specialties_1 = Arrays.asList(new Specialties ("pantalon", "2.0")); 
    	List<String> availability_1 = Arrays.asList("12-4-3124"); 
  
        Tailor tailor = new Tailor(1L, "Pepe", "hago pantalones",specialties_1,availability_1);

        talorService.createTailor(new CreateTailorRequest(tailor.getName(),
        																	    tailor.getDescription(),
        																	    tailor.getSpecialties(),
        																	    tailor.getAvailability()));
        verify(tailorRepository, times(1)).save(any(Tailor.class));

    }

}
