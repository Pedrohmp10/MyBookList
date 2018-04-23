package br.edu.ctup.mybooklist.dal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import br.edu.ctup.mybooklist.model.Livro;
import br.edu.ctup.mybooklist.model.UsuComum;

public class UsuComumDAO {
	
	private static ArrayList<UsuComum> UsersC = new ArrayList<UsuComum>();
	
		public static void addUsuComum(UsuComum u){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			em.close();
		}
		
		public static void removerUsuComum(UsuComum u){
			try{
				EntityManager em = Conexao.getEntityManager();
				em.getTransaction().begin();
				u = em.getReference(UsuComum.class, u.getId());
				em.remove(u);
				em.getTransaction().commit();
				em.close();
			}catch(RollbackException e){
				System.out.println(e.toString());
			}
		}
		
		public static void alterarUsuComum(UsuComum u){
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			UsuComum usu = em.find(UsuComum.class, u.getId());
			usu.setSenha(u.getSenha());
			usu.setCpf(u.getCpf());
			usu.setNome(u.getNome());
			usu.setSobre(u.getSobre());
			usu.setEmail(u.getEmail());
			usu.setCel(u.getCel());
			em.merge(usu);
			em.getTransaction().commit();
			em.close();
		}
			
		public static List<UsuComum> retornaUsersC(){
			EntityManager em = Conexao.getEntityManager();
			Query q = em.createQuery("SELECT u FROM UsuComum u");
			List<UsuComum> lista = q.getResultList();
			em.close();
			return lista;
		}
		
		public static UsuComum buscarUsuComumPorSenha(String senha){
			try{
				EntityManager em = Conexao.getEntityManager();
				UsuComum usuComum = (UsuComum) em.createQuery("SELECT u FROM UsuComum u WHERE u.senha = :senha").setParameter("senha", senha).getSingleResult();
				return usuComum;
			}catch(NoResultException e){
				return null;
			}
		}
		
		public static UsuComum buscarUsuComumPorId(int id){
			EntityManager em = Conexao.getEntityManager();
			return em.find(UsuComum.class, id);
		}
		
		

		public UsuComum getUsuComum(String user, String senha){
			EntityManager em = Conexao.getEntityManager();
			try{
				UsuComum u = (UsuComum) em.createQuery("SELECT u FROM UsuComum u WHERE u.user = :name AND u.senha = :senha").setParameter("name", user).setParameter("senha", senha).getSingleResult();
				return u;
				
			} catch (NoResultException e){
				return null;
			}
		}
		
		public UsuComum buscarcpf(String cpf){
			EntityManager em = Conexao.getEntityManager();
			try{
				UsuComum u = (UsuComum) em.createQuery("SELECT u FROM UsuComum u WHERE u.cpf = :cpf").setParameter("cpf", cpf).getSingleResult();
				return u;
				
			} catch (NoResultException e){
				return null;
			}
		}
		
		public UsuComum buscaremail(String email){
			EntityManager em = Conexao.getEntityManager();
			try{
				UsuComum u = (UsuComum) em.createQuery("SELECT u FROM UsuComum u WHERE u.email = :email").setParameter("email", email).getSingleResult();
				return u;
				
			} catch (NoResultException e){
				return null;
			}
		}
		
		
		public UsuComum buscaruser(String user){
			EntityManager em = Conexao.getEntityManager();
			try{
				UsuComum u = (UsuComum) em.createQuery("SELECT u FROM UsuComum u WHERE u.user = :user").setParameter("user", user).getSingleResult();
				return u;
				
			} catch (NoResultException e){
				return null;
			}
		}
		
		
		
		
		
		
}
