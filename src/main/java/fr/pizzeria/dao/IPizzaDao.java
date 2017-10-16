/**
 * 
 */
package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Interface définissant les méthodes pour manipuler les stockages de données contenant l'ensemble des pizzas.
 * @author ETY9
 *
 */
public interface IPizzaDao {


	/**
	 * Retourne une collection (arraylist) contenant l'ensemble des pizzas, sans avoir à se soucier de la manière selon laquelle elles sont stockés en mémoire.
	 * @return List<Pizza> Une collection contenant l'ensemble des pizzas.
	 */
	List<Pizza> findAllPizza();

	/**
	 * Permet d'ajouter une nouvelle pizza à l'ensemble de pizzas.
	 * @param pizza L'objet pizza à insérer.
	 * @throws SavePizzaException lance une exception si la pizza de param est nulle
	 */
	void saveNextPizza(Pizza pizza) throws SavePizzaException;

	/**
	 * Permet de modifier une pizza parmis l'ensemble des pizzas.
	 * @param codePizza Le code de la pizza à modifier.
	 * @param pizza L'objet pizza contenant les nouvelles valeurs pour la pizza avec le code codePizza.
	 * @throws UpdatePizzaException lance une exception si le code fournis ne correspond à aucune pizza
	 */
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	/**
	 * Permet de supprimer une instance pizza de l'ensemble des pizzas.
	 * @param codePizza Le code de la pizza à supprimer.
	 * @throws DeletePizzaException lance une exception si le code fournis ne correspond à aucune pizza
	 */
	void deletePizza(String codePizza) throws DeletePizzaException;

	
	/**
	 * Permet d'afficher la liste des pizza sous la forme d'un menu.
	 */
	void displayPizzaMenu(); 
}