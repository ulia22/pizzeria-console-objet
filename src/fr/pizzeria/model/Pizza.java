package fr.pizzeria.model;

/** Classe représentant une pizza par instance. */
public class Pizza {

	/**
	 * ID global des pizzas. Contient un ID unique libre à associer à la
	 * prochaine instance de Pizza, lequel est incrémenté de 1 appres chaque
	 * utilisation afin de rester unique.
	 */
	private static int currentGlobalId;
	/** Id unique de l'instance courrante de Pizza. */
	private int id;
	/**
	 * Code litteral d'identification de la pizza représenté par l'instance
	 * courrante de Pizza.
	 */
	private String code;
	/** Nom de la pizza représenté par l'instance courrante de Pizza. */
	private String nom;
	/** Prix de la pizza représenté par l'instance courrante de Pizza. */
	private double prix;

	/**
	 * @param code
	 *            Code litteral d'identification de la pizza représenté par
	 *            l'instance courrante de Pizza.
	 * @param nom
	 *            Nom de la pizza représenté par l'instance courrante de Pizza.
	 * @param prix
	 *            Prix de la pizza représenté par l'instance courrante de Pizza.
	 */
	public Pizza(String code, String nom, double prix) {
		this.id = Pizza.currentGlobalId;
		Pizza.currentGlobalId++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return this.getCode() + " -> " + this.getNom() + "(" + this.getPrix() + "\u20AC)";
	}

	/**
	 * Getter.
	 * 
	 * @return the currentGlobalId
	 */
	public static int getCurrentGlobalId() {
		return currentGlobalId;
	}

	/**
	 * Setter.
	 * 
	 * @param currentGlobalId
	 *            the currentGlobalId to set
	 */
	public static void setCurrentGlobalId(int currentGlobalId) {
		Pizza.currentGlobalId = currentGlobalId;
	}

	/**
	 * Getter.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter.
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter.
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter.
	 * 
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Setter.
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

}
