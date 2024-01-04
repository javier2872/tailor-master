package com.fitting.sastreria.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.service.TailorService;
import com.fitting.sastreria.utils.Specialties;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TailorController.class)
public class TailorControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
    
	private List<Tailor> tailorList;
	
	@MockBean
	private TailorService tailorService;
	
    @BeforeEach
    void setUp() {
    	List<Specialties> specialties_1 = Arrays.asList(new Specialties ("pantalon", "2.0")); 
    	List<Specialties> specialties_2 = Arrays.asList(new Specialties ("camiseta", "4.0")); 
    	List<String> availability_1 = Arrays.asList("12-4-3124"); 
    	List<String> availability_2 = Arrays.asList("23-09-2346");
  
        this.tailorList = new ArrayList<>();
        this.tailorList.add(new Tailor(1L, "Pepe", "hago pantalones",specialties_1,availability_1));
        this.tailorList.add(new Tailor(2L,  "Juan", "hago camisetas",specialties_2,availability_2));

    }
    
    @Test
    void shouldCreateNewTailor() throws Exception {
    	
    	given(tailorService.createTailor(any(CreateTailorRequest.class))).willAnswer((invocation) -> invocation.getArgument(0));
    	Tailor tailor = this.tailorList.get(0);
        this.mockMvc.perform(post("/tailor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tailor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(tailor.getName())))
                .andExpect(jsonPath("$.description", is(tailor.getDescription())))
                .andExpect(jsonPath("$.specialties[0].name", is(tailor.getSpecialties().get(0).getName())))
                .andExpect(jsonPath("$.specialties[0].price", is(tailor.getSpecialties().get(0).getPrice())))
                .andExpect(jsonPath("$.availability[0]", is(tailor.getAvailability().get(0))));
    }
    
    @Test    
    void shouldReturn400WhenTailorIsNull() throws Exception {
    	Tailor tailor = new Tailor(null, null, null, null, null);

        this.mockMvc.perform(post("/tailor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tailor)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void shouldUpdateTailor() throws Exception {
        String tailorId = "1";
        Tailor tailorUpdated = new Tailor(1L,"Jose", "Una actualizacion",  this.tailorList.get(0).getSpecialties(), this.tailorList.get(0).getAvailability());	     
        
        given(tailorService.getATailor(tailorId)).willReturn(this.tailorList.get(0));
        when(tailorService.updateATailor(any(Tailor.class),any(CreateTailorRequest.class))).thenReturn(tailorUpdated);
        
        this.mockMvc.perform(put("/tailor/{tailorId}", tailorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tailorUpdated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(Integer.parseInt(tailorId))))
                .andExpect(jsonPath("$.name", is(tailorUpdated.getName())))
                .andExpect(jsonPath("$.description", is(tailorUpdated.getDescription())))
                .andExpect(jsonPath("$.specialties[0].name", is(tailorUpdated.getSpecialties().get(0).getName())))
                .andExpect(jsonPath("$.specialties[0].price", is(tailorUpdated.getSpecialties().get(0).getPrice())))
                .andExpect(jsonPath("$.availability[0]", is(tailorUpdated.getAvailability().get(0))));

    }
    
    @Test
    void shouldReturn404WhenUpdatingNonExistingTailor() throws Exception {
        String tailorId = "1";
        given(tailorService.getATailor(tailorId)).willReturn(null);
        Tailor tailorUpdated = new Tailor(1L,"Jose", "Una actualizacion",  this.tailorList.get(0).getSpecialties(), this.tailorList.get(0).getAvailability());	     

        this.mockMvc.perform(put("/tailor/{tailorId}", tailorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tailorUpdated)))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldDeleteTailor() throws Exception {
    	Long tailorId = 1L;
    	given(tailorService.getATailor(String.valueOf(tailorId))).willReturn(this.tailorList.get(0));
    	

        this.mockMvc.perform(delete("/tailor/{tailorId}", tailorId))
                .andExpect(status().isNoContent());

    }
    
    @Test
    void shouldReturn404WhenDeletingNonExistingTailor() throws Exception {
    	Long tailorId = 1L;  
    	given(tailorService.getATailor(String.valueOf(tailorId))).willReturn(null);

        this.mockMvc.perform(delete("/tailor/{tailorId}", tailorId))
                .andExpect(status().isNotFound());

    }

}
