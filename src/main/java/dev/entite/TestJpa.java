package dev.entite;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestJpa {

	private static final Logger LOGGER = Logger.getLogger(TestJpa.class.getName());

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("biblio-pu");

		// On ouvre
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// LOGGER.log(Level.INFO, "Etat :" + entityManager.isOpen());

//		Livre livre1 = entityManager.find(Livre.class, 3);
//		TypedQuery<Livre> query = entityManager.createQuery("select l from Livre l where titre = 'Germinal'",
//				Livre.class);
//		List<Livre> listeLivres = query.getResultList();
//		for (Livre valeur : listeLivres) {
//			System.out.println(valeur.getAuteur());
//		}
		System.out.println("Choix client (int)");
		int choix = sc.nextInt();

		Emprunt emprunt = entityManager.find(Emprunt.class, choix);
		LOGGER.log(Level.INFO, "Emprunt id(1) : {0}", emprunt);

		TypedQuery<Emprunt> query = entityManager.createQuery(
				"SELECT emp FROM Emprunt emp, Client cli WHERE cli.id = emp.client AND cli.id =" + choix,
				Emprunt.class);

		// Liste des emprunts client
		List<Emprunt> empruntsClient = query.getResultList();

		// Affichage
		for (Emprunt valeur : empruntsClient) {
			System.out.println(valeur.getLivres());
		}

		// On ferme
		entityManager.close();

		// On ferme le factory
		entityManagerFactory.close();

	}

}
