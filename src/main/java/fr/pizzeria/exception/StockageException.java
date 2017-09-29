/**
 * 
 */
package fr.pizzeria.exception;

/**
 * Classe d'exception a utiliser quand il y a un problème dans pizzaDao.
 * Cette classe a pour but d'être hérité par les autres classe d'exception.
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class StockageException extends Exception {

	/**
	 * Constructeur par defaut.
	 */
	public StockageException(){
		super();
	}
	
	/**
	 * Constructeur avec un message servant à décrire la source de l'exception en paramètre.
	 * @param msg message d'information sur la source de l'exception
	 */
	public StockageException(String msg){
		super(msg);
	}
}
