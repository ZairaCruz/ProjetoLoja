package negocio;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import beans.Cidade;
import beans.Pessoa;
import beans.Produto;
import beans.Venda;
import persistencia.PessoaDAO;
import persistencia.ProdutoDAO;
import persistencia.VendaDAO;

public class VendaTest {

	Produto produto = new Produto();
	Produto produto2 = new Produto();
	Pessoa pessoa = new Pessoa();
	Venda venda = new Venda();

	@Before
	public void setUp(){
	
	}
	
	@Ignore
	@Test
	public void salvarProduto(){
		produto = new Produto();
		produto.setNome("Torrada");
		produto.setPreco(new BigDecimal("10"));
		produto.setFabricante("Do Sitio");
		produto.setDescricao("Novo");
		produto.setDisponivel("true");
		ProdutoDAO.inserir(produto);
		
	}
	
	@Ignore
	@Test
	public void salvarProduto2(){
		produto2 = new Produto();
		produto2.setNome("Açucar");
		produto2.setPreco(new BigDecimal("10"));
		produto2.setFabricante("Cristal");
		produto2.setDescricao("Novo");
		produto2.setDisponivel("false");
		ProdutoDAO.inserir(produto2);
		
	}
	
	@Ignore
	@Test
	public void salvarPessoa(){
		pessoa = new Pessoa();
		pessoa.setNome("Jane Fernandes");
		pessoa.setCpf("12345678912");
		pessoa.setRg("123");
		pessoa.setBairro("Centro");
		pessoa.setCidade("Goiania");
		pessoa.setUf("GO");
		pessoa.setCep("74775012");
		pessoa.setData_nasc(new Date());
		pessoa.setEmail("maria@gmail.com");
		pessoa.setRua("Avenida 2");
		pessoa.setTipo("Cliente");
		pessoa.setSenha("123");
		PessoaDAO.inserir(pessoa);		
	}
	
	@Ignore
	@Test
	public void salvarVenda(){

		PessoaDAO pessoaDao = new PessoaDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		VendaDAO vendaDao = new VendaDAO();
		
		Pessoa pessoa = pessoaDao.buscarPorCodigo(3L);
		Produto produto = produtoDAO.buscarPorCodigo(2L);
		
		Venda venda = new Venda();
		venda.setId_pessoa(pessoa.getId());
		venda.setId_produto(produto.getId());
		venda.setData(new Date());
		venda.setValor(new BigDecimal(50D));
		vendaDao.inserir(venda);	
	}

	@SuppressWarnings("static-access")
	@Test
	public void buscar(){
		PessoaDAO pessoaDao = new PessoaDAO();
		Pessoa pessoa = pessoaDao.buscarPorCodigo(3L);
	
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(2L);
		
		assertEquals(3, pessoa.getId());
		assertEquals(2, produto.getId());
	}
	
	@Test
	public void listarPessoas_Todos(){
		PessoaDAO pessoaDao = new PessoaDAO();
		String filtro = "";
		List<Pessoa> pessoas = pessoaDao.listagem(filtro);
		
		assertNotNull(pessoas);
		System.out.println("tamanho da lista: " + pessoas.size());
	}
	
	@Test
	public void listarPessoas_ComFiltro(){
		PessoaDAO pessoaDao = new PessoaDAO();
		String filtro = "M";
		List<Pessoa> pessoas = pessoaDao.listagem(filtro);
		
		assertNotNull(pessoas);
		System.out.println("tamanho da lista: " + pessoas.size());
	}
	
	@Test
	public void listarCidades(){
		PessoaDAO pessoaDao = new PessoaDAO();
		List<Cidade> cidades = pessoaDao.listagemCidades();
		
		assertNotNull(cidades);
	}
	
}
