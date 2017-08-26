package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoClientTest {
	
	HttpServer httpServer;
	
	@Before
	public void inicializaServidor() {
		this.httpServer = Servidor.inicialiarServidor();
	}
	
	@After
	public void paraServidor() {
		this.httpServer.stop();
	}
	
	@Test
	public void testaQueBuscarUmProjetoTrazOProjetoEsperado() {	
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(new LoggingFilter());
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget target = client.target("http://localhost:8080");
		
		Entity<String> entity = Entity.entity(createNewProjeto(), MediaType.APPLICATION_XML);
		
		Response response = target.path("/projetos").request().post(entity);
		
		Assert.assertEquals(201, response.getStatus());
	}	
	
	private String createNewProjeto() {
		Projeto projeto = new Projeto();
		
		projeto.setAnoDeInicio(1997);
		projeto.setId(5);
		projeto.setNome("CDB");
		
		return projeto.toXml();
	}
}
