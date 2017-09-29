/**
 * 
 */
package fr.pizzeria.exception;

/**
 * Classe d'exception a utiliser quand il y a un problème dans la méthode deletePizza de PizzaDao.
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class DeletePizzaException extends StockageException {
	/**
	 * Constructeur par defaut.
	 */
	public DeletePizzaException(){
		super();
	}

	/**
	 * Constructeur avec un message servant à décrire la source de l'exception en parametre.
	 * @param msg message d'information sur la source de l'exception
	 */
	public DeletePizzaException(String msg){
		super(msg);
	}
}
