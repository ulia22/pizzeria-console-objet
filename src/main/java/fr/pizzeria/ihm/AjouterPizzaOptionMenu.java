/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Option du menu permettant d'ajouter une pizza au menu.
 * @author ETY9
 *
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	private Scanner sc;
	
	/**Reference vers le singleton IPizzaDao qui accede à l'ensembles des pizza et fournis des méthodes pour le manipuler.*/
	private IPizzaDao iPizza;
	
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
		System.out.println("Ajout d’une nouvelle pizza");
		String code;
		String nom;
		double prix;
		int categorie;

		// Créer la nouvelle pizza.
		try{
			System.out.println("Veuillez saisir le code");
			code = sc.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace)");
			nom = sc.nextLine();
			System.out.println("Veuillez saisir le prix");
			prix = Double.parseDouble(sc.nextLine());
			System.out.println("Veuillez saisir la categorie (1.Viande 2.Sans-viande 3.Poisson");
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
				System.out.println("La catégorie n'est pas valide.");
				return;
			}


			iPizza.saveNexPizza(p);
		}catch(StockageException e){
			System.out.println((e.getMessage()));
		}
		catch (NumberFormatException n){
			System.out.println(n.getMessage());
		}
		catch(Exception x){
			System.out.println(x.getMessage());
		}
	}

}
