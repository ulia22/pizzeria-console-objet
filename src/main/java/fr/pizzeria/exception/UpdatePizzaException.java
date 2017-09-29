/**
 * 
 */
package fr.pizzeria.exception;

/**
 * Classe d'exception a utiliser quand il y a un problème dans la méthode updatePizza de PizzaDao.
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class UpdatePizzaException extends StockageException {
	/**
	 * Constructeur par defaut.
	 */
	public UpdatePizzaException(){
		super();
	}
	
	/**
	 * Constructeur avec un message servant à décrire la source de l'exception en paramètre.
	 * @param msg message d'information sur la source de l'exception
	 */
	public UpdatePizzaException(String msg){
		super(msg);
	}
}
