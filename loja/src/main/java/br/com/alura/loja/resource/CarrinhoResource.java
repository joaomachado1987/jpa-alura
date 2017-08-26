package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response busca(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return Response.ok(carrinho.toXml()).build();  
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI location = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(location).build();
	}
	
	@DELETE
	@Path("{id}/produtos/{idProduto}")
	public Response deleta(@PathParam("id") long id, @PathParam("idProduto") long idProduto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(idProduto);
		return Response.ok().build();
	}
	
	@PUT
	@Path("{id}/produtos/{idProduto}")
	public Response alterarQuantidadeProduto(String conteudo, @PathParam("id") long id, @PathParam("idProduto") long idProduto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
//		Produto 
//		carrinho.troca(produto);
//		new CarrinhoDAO().
		
		return Response.ok().build();
	}
	
}