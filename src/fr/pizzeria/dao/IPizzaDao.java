/**
 * 
 */
package fr.pizzeria.dao;

import java.util.List;

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
	 * @return true si l'ajout s'est bien passé, sinon false.
	 */
	boolean saveNexPizza(Pizza pizza);
	/**
	 * Permet de modifier une pizza parmis l'ensemble des pizzas.
	 * @param codePizza Le code de la pizza à modifier.
	 * @param pizza L'objet pizza contenant les nouvelles valeurs pour la pizza avec le code codePizza.
	 * @return true s'il n'y a pas eu d'erreur, sinon false.
	 */
	boolean updatePizza(String codePizza, Pizza pizza);
	/**
	 * Permet de supprimer une instance pizza de l'ensemble des pizzas.
	 * @param codePizza Le code de la pizza à supprimer.
	 * @return s'il n'y a pas eu d'erreur, sinon false.
	 */
	boolean deletePizza(String codePizza);
}