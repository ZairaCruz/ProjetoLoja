package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Pessoa;
import beans.Venda;

public class VendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static void inserir(Venda venda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(venda);
		t.commit();
		sessao.close();
	}
	
	public static void alterar(Venda venda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(venda);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(Venda venda){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(venda);
		t.commit();
		sessao.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Venda> listagem(String filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from Venda order by id ");
		}
		else{
			consulta = sessao.createQuery("from Venda "
					+ "where id like :parametro order by id");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		@SuppressWarnings("rawtypes")
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Venda buscarPorCodigo(long valor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Venda where id = :parametro");
		consulta.setLong("parametro", valor);
		sessao.close();
		return (Venda)consulta.uniqueResult();
	}
}

