/**
 * 
 */
package fr.pizzeria.exception;

/**
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class DeletePizzaException extends StockageException {
	public DeletePizzaException(){
		super();
	}
	
	public DeletePizzaException(String msg){
		super(msg);
	}
}
