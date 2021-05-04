package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public BigDecimal valorTotalVendido(){
		
		String jpql ="SELECT SUM(p.valorTotal)from Pedido p" ;
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
		
	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas(){
		String jpql="select new br.com.alura.loja.vo.RelatorioDeVendasVo("
			+ "produto.nome,"
			+ "sum(item.quantidade),"
			+ "max(pedido.data))"
			+ "from Pedido pedido"
			+ "joim pedido.itens item"
			+ "joim item.quantidade desc";
		return em.createQuery(jpql,RelatorioDeVendasVo.class).getResultList();
			
	}
}
