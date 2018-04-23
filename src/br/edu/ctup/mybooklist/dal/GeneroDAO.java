package br.edu.ctup.mybooklist.dal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import br.edu.ctup.mybooklist.model.Genero;

public class GeneroDAO {
	
		private static ArrayList<Genero> generos = new ArrayList<Genero>();
	
		public static void addGenero(Genero g){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.persist(g);
			em.getTransaction().commit();
			em.close();
		}
		
		public static void removerGenero(Genero g) {
			try {
				EntityManager em = Conexao.getEntityManager();
				em.getTransaction().begin();
				g = em.getReference(Genero.class, g.getId());
				em.remove(g);
				em.getTransaction().commit();
				em.close();
			} catch (RollbackException e) {
				System.out.println(e.toString());
			}
		}
			
		public static void alterarGenero(Genero g){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			Genero genero = em.find(Genero.class, g.getId());
			genero.setNome(g.getNome());
			em.merge(genero);
			em.getTransaction().commit();
			em.close();
		}
		
		public static List<Genero> retornaGeneros(){
			EntityManager em = Conexao.getEntityManager();
			Query q = em.createQuery("SELECT g FROM Genero g");
			List<Genero> lista = q.getResultList();
			em.close();
			return lista;
		}
		
		public static Genero buscarGeneroPorId(int id){
			EntityManager em = Conexao.getEntityManager();
			return em.find(Genero.class, id);
		}
		
		public static Genero buscarGeneroPorNome(String nome){
			EntityManager em = Conexao.getEntityManager();
			Query query = em.createQuery("SELECT g FROM Genero g WHERE g.nome = :nome", Genero.class);
			query.setParameter("nome", nome);
			return (Genero) query.getResultList().get(0);
		}
		
}
