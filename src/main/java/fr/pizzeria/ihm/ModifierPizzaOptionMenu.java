/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Option du menu permettant de modifier une pizza du menu.
 * @author ETY9
 *
 */
public class ModifierPizzaOptionMenu implements OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	private Scanner sc;

	/**Reference vers le singleton IPizzaDao qui accede à l'ensembles des pizza et fournis des méthodes pour le manipuler.*/
	private IPizzaDao iPizza;
	
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	
	/**
	 * Constructeur, résupérant le scanner pour lire les inputs de la console.
	 * @param sc Scanner vers le System.in.
	 */
	public ModifierPizzaOptionMenu(Scanner sc, IPizzaDao iPizza) {
		super();
		this.sc = sc;
		this.iPizza = iPizza;
	}


	/**Permet d'executer l'algorithme pour modifier une pizza du menu.*/
	public void execute() {
		String code;
		int categorie; 
		Pizza p;

		// Afficher les pizzas.
		for (Pizza pi : iPizza.findAllPizza()) {
			String str = pi.toString();
			LOG.info(str);
		}
		LOG.info("Veuillez choisir la pizza à modifier.");
		LOG.info("(99 pour abandonner).");

		try{
			code = sc.nextLine();
			if (!"99".equals(code)) {
				String newCode;
				String nom;
				double prix;

				LOG.info("Veuillez saisir le code");
				newCode = sc.nextLine();

				LOG.info("Veuillez saisir le nom (sans espace)");
				nom = sc.nextLine();

				LOG.info("Veuillez saisir le prix");
				prix = Double.parseDouble(sc.nextLine());

				LOG.info("Veuillez saisir la categorie (1.Viande 2.Sans-viande 3.Poisson");
				categorie = Integer.parseInt(sc.nextLine());

				switch (categorie){
				case 1 :
					p = new Pizza(newCode, nom, prix, CategoriePizza.VIANDE);
					iPizza.updatePizza(code, p);
					break;
				case 2 :
					p = new Pizza(newCode, nom, prix, CategoriePizza.SANS_VIANDE);
					iPizza.updatePizza(code, p);
					break;
				case 3 :
					p = new Pizza(newCode, nom, prix, CategoriePizza.POISSON);
					iPizza.updatePizza(code, p);
					break;
				default:
					LOG.info("La catégorie n'est pas valide.");
					return;
				}
			}

		}catch(StockageException e){
			String msg = e.getMessage();
			LOG.info(msg);
		}
		catch(Exception n){
			String msg = n.getMessage();
			LOG.info(msg);
		}

		// Afficher la liste des pizza apres la modification.
		for (Pizza pi : PizzaDao.getInstance().getListePizza()) {
			String s = pi.toString();
			LOG.info(s);
		}
	}


}
