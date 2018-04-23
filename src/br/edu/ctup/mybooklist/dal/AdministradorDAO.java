package br.edu.ctup.mybooklist.dal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import br.edu.ctup.mybooklist.model.Administrador;

public class AdministradorDAO {

	private static ArrayList<Administrador> administradores = new ArrayList<Administrador>();

	public static void AddAdministrador(Administrador a) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
	}

	public static void removerAdministrador(Administrador a) {
		try {
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			a = em.getReference(Administrador.class, a.getId());
			em.remove(a);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			System.out.println(e.toString());
		}
	}

	public static void alterarAdministrador(Administrador a) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		Administrador adm = em.find(Administrador.class, a.getId());
		adm.setSenha(a.getSenha());
		adm.setNome(a.getNome());
		adm.setSobre(a.getSobre());
		adm.setCpf(a.getCpf());
		adm.setEmail(a.getEmail());
		adm.setCel(a.getCel());
		em.merge(adm);
		em.getTransaction().commit();
		em.close();
	}

	public static List<Administrador> retornarAdministradores() {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT a FROM Administrador a");
		List<Administrador> lista = q.getResultList();
		em.close();
		return lista;
	}

	public static Administrador buscarAdmPorMatricula(String matricula) {
		try {
			EntityManager em = Conexao.getEntityManager();
			Administrador adm = (Administrador) em
					.createQuery("SELECT a FROM Administrador a WHERE a.matricula = :matricula")
					.setParameter("matricula", matricula).getSingleResult();
			return adm;
		} catch (NoResultException e) {
			return null;
		}
	}

	// método para efetuar login
	public Administrador getAdministrador(String matricula, String senha) {
		EntityManager em = Conexao.getEntityManager();
		try {
			Administrador a = (Administrador) em
					.createQuery("SELECT a FROM Administrador a WHERE a.matricula = :matricula AND a.senha = :senha ")
					.setParameter("matricula", matricula).setParameter("senha", senha).getSingleResult();
			return a;
		} catch (NoResultException e) {
			return null;
		}
	}

}
