/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Option du menu permettant d'ajouter une pizza au menu.
 * @author ETY9
 *
 */
public class AjouterPizzaOptionMenu implements OptionMenu {

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
	public AjouterPizzaOptionMenu(Scanner sc, IPizzaDao iPizza) {
		super();
		this.sc = sc;
		this.iPizza = iPizza;
	}


	/** Permet d'executer l'algorithme pour ajouter une pizza au menu.*/
	public void execute(){
		LOG.info("Ajout d’une nouvelle pizza");
		String code;
		String nom;
		double prix;
		int categorie;

		// Créer la nouvelle pizza.
		try{
			LOG.info("Veuillez saisir le code");
			code = sc.nextLine();
			LOG.info("Veuillez saisir le nom (sans espace)");
			nom = sc.nextLine();
			LOG.info("Veuillez saisir le prix");
			prix = Double.parseDouble(sc.nextLine());
			LOG.info("Veuillez saisir la categorie (1.Viande 2.Sans-viande 3.Poisson");
			categorie = Integer.parseInt(sc.nextLine());
			Pizza p;
			switch (categorie){
			case 1 :
				p = new Pizza(code, nom, prix, CategoriePizza.VIANDE);
				break;
			case 2 :
				p = new Pizza(code, nom, prix, CategoriePizza.SANS_VIANDE);
				break;
			case 3 :
				p = new Pizza(code, nom, prix, CategoriePizza.POISSON);
				break;
			default:
				LOG.info("La catégorie n'est pas valide.");
				return;
			}


			iPizza.saveNexPizza(p);
		}catch(StockageException e){
			LOG.info((e.getMessage()));
		}
		catch (NumberFormatException n){
			LOG.info(n.getMessage());
		}
		catch(Exception x){
			LOG.info(x.getMessage());
		}
	}

}
