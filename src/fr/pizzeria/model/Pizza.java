package fr.pizzeria.model;

public class Pizza {
	
private static int currentGlobalId;
private int id;
private String code;
private String nom;
private double prix;

/**
 * @param code the code to set
 * @param nom the nom to set
 * @param prix the prix to set
 */
public Pizza(String code, String nom, double prix) {
	this.id = Pizza.currentGlobalId;
	Pizza.currentGlobalId++;
	this.code = code;
	this.nom = nom;
	this.prix = prix;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return this.getCode()+" -> "+this.getNom()+"("+this.getPrix()+"\u20AC)";
}
/**
 * @return the currentGlobalId
 */
public static int getCurrentGlobalId() {
	return currentGlobalId;
}
/**
 * @param currentGlobalId the currentGlobalId to set
 */
public static void setCurrentGlobalId(int currentGlobalId) {
	Pizza.currentGlobalId = currentGlobalId;
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the code
 */
public String getCode() {
	return code;
}
/**
 * @param code the code to set
 */
public void setCode(String code) {
	this.code = code;
}
/**
 * @return the nom
 */
public String getNom() {
	return nom;
}
/**
 * @param nom the nom to set
 */
public void setNom(String nom) {
	this.nom = nom;
}
/**
 * @return the prix
 */
public double getPrix() {
	return prix;
}
/**
 * @param prix the prix to set
 */
public void setPrix(double prix) {
	this.prix = prix;
}

}
