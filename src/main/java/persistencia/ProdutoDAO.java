package persistencia;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static void inserir(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try{
			t = sessao.beginTransaction();	
			sessao.save(produto);
			t.commit();
		}catch(RuntimeException ex){
			if(t != null){
				t.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public static void alterar(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(produto);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(Produto produto){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(produto);
		t.commit();
		sessao.close();
	}
	
	public static List<Produto> listagem(String filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery(" from Produto order by nome ");
		}
		else{
			consulta = sessao.createQuery(" from Produto "
					+ " where nome like :parametro order by nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Produto buscarPorCodigo(long valor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery(" from Produto where id = :parametro");
		consulta.setLong("parametro", valor);
		Produto uniqueResult = (Produto)consulta.uniqueResult();
		sessao.close();
		return uniqueResult;
	}
}
