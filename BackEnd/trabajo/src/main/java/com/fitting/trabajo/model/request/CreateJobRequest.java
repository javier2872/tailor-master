package com.fitting.trabajo.model.request;

import java.util.List;

import com.fitting.trabajo.utils.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defining a request when job is created
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequest {
	
	private String idTailor;
	private String date;
	private List<Order> order;
}
