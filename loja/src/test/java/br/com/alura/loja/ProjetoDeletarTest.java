package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ProjetoDeletarTest {
	
	private HttpServer server;
	
	@Before
	public void iniciar() {
		this.server = Servidor.inicialiarServidor();
	}
	
	@After
	public void parar() {
		this.server.stop();
	}
	
	@Test
	public void test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		
		target.path("/projetos/1").request().get(String.class);
		
		Response responseDetele = target.path("/projetos/1").request().delete();
		
		Assert.assertEquals(200, responseDetele.getStatus());
	}
}
