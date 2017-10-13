/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * Option du menu permettant de supprimer une pizza du menu.
 * @author ETY9
 *
 */
public class SupprimerPizzaOptionMenu implements OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	Scanner sc;

	/**Reference vers le singleton IPizzaDao qui accede à l'ensembles des pizza et fournis des méthodes pour le manipuler.*/
	private IPizzaDao iPizza;
	
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	
	
	/**
	 * Constructeur, résupérant le scanner pour lire les inputs de la console.
	 * @param sc Scanner vers le System.in.
	 */
	public SupprimerPizzaOptionMenu(Scanner sc, IPizzaDao iPizza) {
		super();
		this.sc = sc;
		this.iPizza = iPizza;
	}

	/** Permet d'executer l'algorithme pour supprimer une pizza du menu.*/
	public void execute() {
		String code;
		// Afficher les pizzas.
		iPizza.displayPizzaMenu();
		LOG.info("Veuillez choisir la pizza à supprimer.");
		LOG.info("(99 pour abandonner).");

		code = sc.nextLine();
		try{
			if (!"99".equals(code)) {
				iPizza.deletePizza(code);
			}
		}catch(StockageException e){
			String msg = e.getMessage();
			LOG.info(msg);
		}
		catch(Exception n){
			String msg = n.getMessage();
			LOG.info(msg);
		}
	}

}
