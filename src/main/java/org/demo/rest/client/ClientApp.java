package org.demo.rest.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Simple JAX-RS client
 * @author l-gu
 *
 */
public class ClientApp {

	public static void main(String[] args) {
		Client client = Client.create();
		
		WebResource webResource = getWebResource(client, "/hello" );
		//ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		webResource.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		ClientResponse response = webResource.get(ClientResponse.class);
		print(response);

		print(webResource.type(MediaType.TEXT_XML).get(ClientResponse.class));
	}
	
	private static WebResource getWebResource(Client client, String resource) {  
		return client.resource("http://localhost:8080/jax-rs/rest" + resource );  
	}  

	private static void print(ClientResponse response) {  
		System.out.println("RESPONSE : ");
		System.out.println(" . toString : " + response.toString() );
		System.out.println(" . status   : " + response.getStatus() );
		System.out.println(" . statusInfo : " + response.getStatusInfo() );
		System.out.println(" . type     : " + response.getType() );
		System.out.println(" . length    : " + response.getLength() );
		System.out.println(" . content     : " + content(response.getEntityInputStream()));
	}  
	
	private static String content(InputStream is) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int c ;
		try {
			while ( ( c = is.read() ) != -1 ) {
				buf.write(c);
			}
		} catch (IOException e) {
			throw new RuntimeException("Cannot read response ", e);
		}
		return buf.toString();
	}
}
