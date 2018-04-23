package br.edu.ctup.mybooklist.dal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import br.edu.ctup.mybooklist.model.Autor;


public class AutorDAO {

	
		private static ArrayList<Autor> autores = new ArrayList<Autor>();
	
		public static void addAutor(Autor a){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			em.close();
		}
	
		public static void removerAutor(Autor a){
			try{
				EntityManager em = Conexao.getEntityManager();
				em.getTransaction().begin();
				a = em.getReference(Autor.class, a.getId());
				em.remove(a);
				em.getTransaction().commit();
				em.close();
			}catch(RollbackException e){
				System.out.println(e.toString());
			}
		}
	
		public static void alterarAutor(Autor a){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			Autor autor = em.find(Autor.class, a.getId());
			autor.setNome(a.getNome());
			autor.setSobrenome(a.getSobrenome());
			autor.setIdade(a.getIdade());
			em.merge(autor);
			em.getTransaction().commit();
			em.close();
		}
	
		public static List<Autor> retornaAutores(){
			EntityManager em = Conexao.getEntityManager();
			Query q = em.createQuery("SELECT a FROM Autor a");
			List<Autor> lista = q.getResultList();
			em.close();
			return lista;
		}
	
		public static Autor buscarAutorPorId(int id){
			EntityManager em = Conexao.getEntityManager();
			return em.find(Autor.class, id);
		}
		
		public static Autor buscarAutorPorNome(String nome){
			EntityManager em = Conexao.getEntityManager();
			Query query = em.createQuery("SELECT a FROM Autor a WHERE a.nome = :nome", Autor.class);
			query.setParameter("nome", nome);
			return (Autor) query.getResultList().get(0);
		}
		
}
