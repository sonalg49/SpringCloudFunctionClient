package com.example.ItemOrderClientApplication.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ItemOrderClientApplication.domain.Item;
import com.example.ItemOrderClientApplication.domain.Order;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableDiscoveryClient
@RestController
public class ItemOrderServiceClient {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value ="/test/getitems", method = RequestMethod.GET)
	public JsonNode getItemsList() throws IOException  {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/getitems"))
						.get();
		
		String jsonString =  restTemplate.getForObject(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
	}
	
	@RequestMapping(value = "/test/getItemById/{id}", method = RequestMethod.GET)
	public JsonNode getItemById(@PathVariable String id) throws IOException  {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/getItemUsingId"))
						.get();
		
		String jsonString =  restTemplate.getForObject(uri+"/{id}", String.class,id);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
	} 
	
	@RequestMapping(value ="/test/additem", method = RequestMethod.POST)
	public String addItem(@RequestBody Item item) {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/additem"))
						.get();
		
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Item> element = new HttpEntity<Item>(item,headers);
	      
	      String response = restTemplate.exchange(
	         uri, HttpMethod.POST, element, String.class).getBody();
	      return response;
	}
	
	@RequestMapping(value ="/test/deleteItem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable String id) {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/deleteItem"))
						.get();
		
		String response =  restTemplate.getForObject(uri + "/{id}", String.class,id);
		return response;
	}
	
	@RequestMapping(value ="/test/getorders", method = RequestMethod.GET)
	public JsonNode getOrdersList() throws IOException  {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/getorders"))
						.get();
		
		String jsonString =  restTemplate.getForObject(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
	}
	
	@RequestMapping(value = "/test/getOrderById/{id}", method = RequestMethod.GET)
	public JsonNode getOrderById(@PathVariable String id) throws IOException  {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/getOrderById"))
						.get();
		
		String jsonString =  restTemplate.getForObject(uri+"/{id}", String.class,id);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
	} 
	
	@RequestMapping(value ="/test/addorder", method = RequestMethod.POST)
	public String addOrder(@RequestBody Order order) {
		URI uri = client.getInstances("ItemOrder-service")
						.stream()
						.map(s -> s.getUri())
						.findFirst()
						.map(func -> func.resolve("/addorder"))
						.get();
		
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Order> element = new HttpEntity<Order>(order,headers);
	      
	      String response = restTemplate.exchange(
	         uri, HttpMethod.POST, element, String.class).getBody();
	      return response;
	}

}
