package com.fitting.trabajo.model.request;

import java.util.List;

import com.fitting.trabajo.utils.Item;
import com.fitting.trabajo.utils.Client;
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
	private List<Item> item;
	private List<Client> client;
	
}
