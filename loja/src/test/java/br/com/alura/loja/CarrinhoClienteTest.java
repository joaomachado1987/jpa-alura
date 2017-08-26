package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class CarrinhoClienteTest {
	
	private HttpServer httpServer = null;
	
	@Before
	public void startServer() {
		this.httpServer = Servidor.inicialiarServidor();
	}
	
	@After
	public void stopServer() {
		this.httpServer.stop();
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		Entity<String> entity = Entity.entity(this.createCarrinhoXML(), MediaType.APPLICATION_XML);
		Response response = target.path("/carrinhos").request().post(entity);
		int status = response.getStatus();
		
		String location = response.getHeaderString("Location");
		String conteudo = client.target(location).request().get(String.class);
		
		System.out.println(conteudo);
		Assert.assertTrue(status == 201);
	}
	
	private String createCarrinhoXML() {
		Carrinho carrinho = new Carrinho();
		Produto produto = new Produto(10,"Microfone", 100, 10);
		carrinho.adiciona(produto);
		carrinho.setCidade("Santa Maria");
		carrinho.setId(3);
		carrinho.setRua("João Machado Soares");
		return carrinho.toXml();
	}
}
