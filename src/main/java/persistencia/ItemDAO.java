package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Item;
import beans.Produto;

public class ItemDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static void inserir(Item item){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(item);
		t.commit();
		sessao.close();
	}
	
	public static void alterar(Item item){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(item);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(Item item){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(item);
		t.commit();
		sessao.close();
	}
	
	public static List<Item> listagem(String filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from Item order by id ");
		}
		else{
			consulta = sessao.createQuery("from Item "
					+ "where id like :parametro order by id");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Item buscarPorCodigo(long valor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Item where id = :parametro");
		consulta.setLong("parametro", valor);
		sessao.close();
		return (Item)consulta.uniqueResult();
	}
}
