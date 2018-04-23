package br.edu.ctup.mybooklist.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import br.edu.ctup.mybooklist.model.Livro;

public class LivroDAO {
	
	
		private static ArrayList<Livro> livros = new ArrayList<Livro>();
	
		public static void addLivro(Livro l){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.persist(l);
			em.getTransaction().commit();
			em.close();
			
			
		}
		
		public static void removerLivro(Livro l){
			try{
				EntityManager em = Conexao.getEntityManager();
				em.getTransaction().begin();
				l = em.getReference(Livro.class, l.getId());
				em.remove(l);
				em.getTransaction().commit();
				em.close();
			}catch(RollbackException e){
				System.out.println(e.toString());
			}
		}
			
		public static void alterarLivro(Livro l){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			Livro livro = em.find(Livro.class, l.getId());
			livro.setNome(l.getNome());
			livro.setGenero(l.getGenero());
			livro.setAutor(l.getAutor());
			livro.setDataLan(l.getDataLan());
			livro.setQtdePagL(l.getQtdePagL());
			em.merge(livro);
			em.getTransaction().commit();
			em.close();
		}
		
		public static List<Livro> retornaLivros(){
			EntityManager em = Conexao.getEntityManager();
			Query q = em.createQuery("SELECT l FROM Livro l");
			List<Livro> lista = q.getResultList();
			em.close();
			return lista;
		}
		
		public static Livro buscarLivroPorId(int idL){
			EntityManager em = Conexao.getEntityManager();
			return em.find(Livro.class, idL);
		}
		
		public static Livro buscarLivroPorcod(int cod){
			try {
				EntityManager em = Conexao.getEntityManager();
				Livro livro = (Livro) em.createQuery("SELECT l FROM Livro l WHERE l.cod = :cod").setParameter("cod", cod).getSingleResult();
				return livro;
			} catch (NoResultException e) {
				return null;
			}
		}
		
		
		public static Livro buscarLivroPorNome(String nome){
			try {
				EntityManager em = Conexao.getEntityManager();
				Livro livro = (Livro) em.createQuery("SELECT l FROM Livro l WHERE l.nome = :nome").setParameter("nome", nome).getSingleResult();
				return livro;
			} catch (NoResultException e) {
				return null;
			}
		}
		
		
		

}
