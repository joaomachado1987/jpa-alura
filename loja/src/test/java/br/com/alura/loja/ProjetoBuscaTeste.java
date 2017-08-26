package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjetoBuscaTeste {
	
	private HttpServer servidor;
	
	@Before
	public void conect() {
		this.servidor = Servidor.inicialiarServidor();
	}
	
	@After
	public void disconect() {
		this.servidor.stop();
	}
	
	@Test
	public void busca() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String json = target.path("/projetos/1/").request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(json);
//		Gson gson = Gson();
	}
	
}
