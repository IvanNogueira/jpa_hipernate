package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedidos {
	
	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscasPorId(1l);
		Produto produto2 = produtoDao.buscasPorId(2l);
		Produto produto3 = produtoDao.buscasPorId(3l);
		Cliente cliente = clienteDao.buscasPorId(1l);
	
	    em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicinarItem(new ItemPedido(10, pedido, produto));
		pedido.adicinarItem(new ItemPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicinarItem(new ItemPedido(2, pedido, produto3));
	
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
  
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
	    System.out.println("Valor Total: "+ totalVendido); 
	    
	    List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
	    relatorio.forEach(System.out::println);
	      
	    
		
	}//fechamento main

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto videogame = new Produto("PS5", "Playstation 5 ", new BigDecimal("3000"), celulares );
        Produto macbook = new Produto("Macbook", "Macbook pro", new BigDecimal("5000"), celulares );

		Cliente cliente = new Cliente ("Rodigo", "123456");
		
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);
        
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
	}
	
}