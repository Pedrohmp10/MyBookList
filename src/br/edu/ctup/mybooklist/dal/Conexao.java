package br.edu.ctup.mybooklist.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	
	// final é para descrever que a variavel nunca vai mudar seu valor;
		private static final EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("MBLDB");
		
		
		public static EntityManager getEntityManager(){
			return factory.createEntityManager();
			
		}

}
