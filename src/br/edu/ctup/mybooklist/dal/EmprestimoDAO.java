package br.edu.ctup.mybooklist.dal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import br.edu.ctup.mybooklist.model.Emprestimo;

public class EmprestimoDAO {

	private static ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

	public static void realizarEmprestimo(Emprestimo e) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	public static List<Emprestimo> retornarEmprestimos() {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT e FROM Emprestimo e");
		List<Emprestimo> lista = q.getResultList();
		em.close();
		return lista;
	}

	public static void devolverLivro(Emprestimo emp) {
		try {
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			emp = em.getReference(Emprestimo.class, emp.getId());
			em.remove(emp);
			em.getTransaction().commit();
			em.close();
		} catch (RollbackException e) {
			System.out.println(e.toString());
		}
	}
}
