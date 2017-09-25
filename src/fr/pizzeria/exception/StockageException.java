/**
 * 
 */
package fr.pizzeria.exception;

/**
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class StockageException extends Exception {

	public StockageException(){
		super();
	}
	
	public StockageException(String msg){
		super(msg);
	}
}
