package fr.pizzeria.console;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	private static String menu = new String(
			"***** Pizzeria Administration *****\n"
			+ "1. Lister les pizzas\n"
			+ "2. Ajouter une nouvelle pizza\n"
			+ "3. Mettre à jour une pizza\n"
			+ "4. Supprimer une pizza\n"
			+ "99. Sortir\n");
	
	private static Pizza[] listePizza;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		String choice;
		
		PizzeriaAdminConsoleApp.initListePizza();
		do{
			System.out.println(menu);
			choice = sc.nextLine();
			switch(choice){
			case "1":
				System.out.println("Liste des pizzas.");
				for(Pizza p : PizzeriaAdminConsoleApp.listePizza){
					System.out.println(p.toString());
				}
				break;
			case "2":
				System.out.println("Ajout d’une nouvelle pizza");
				PizzeriaAdminConsoleApp.addPizza();
				break;
			case "3":
				System.out.println("Mise à jour d’une pizza");
				updatePizza();
				break;
			case "4":
				System.out.println("Suppression d’une pizza");
				deletePizza();
				break;
			case "99":
				System.out.println("Aurevoir \u2639");
				break;
				default:
					System.out.println("Mauvaise entrée.");
				break;
				
			}
		}while(!choice.equals("99"));
		sc.close();
	}
	
	private static void initListePizza(){
		PizzeriaAdminConsoleApp.listePizza = new Pizza[8];
		
		Pizza.setCurrentGlobalId(0);
		PizzeriaAdminConsoleApp.listePizza[0] = new Pizza("PEP", "Pépéroni", 12.5);
		PizzeriaAdminConsoleApp.listePizza[1] = new Pizza("MAR", "Margherita", 14.00);
		PizzeriaAdminConsoleApp.listePizza[2] = new Pizza("REIN", "La Reine", 11.50);
		PizzeriaAdminConsoleApp.listePizza[3] = new Pizza("FRO", "La 4 fromages", 12.00);
		PizzeriaAdminConsoleApp.listePizza[4] = new Pizza("CAN", "La cannibale", 12.50);
		PizzeriaAdminConsoleApp.listePizza[5] = new Pizza("SAV", "La savoyarde", 13.00);
		PizzeriaAdminConsoleApp.listePizza[6] = new Pizza("ORI", "L’orientale", 13.50);
		PizzeriaAdminConsoleApp.listePizza[7] = new Pizza("IND", "L’indienne", 14.00);
	}
	
	private static void addPizza(){
		String code,nom;
		double prix;
		
		System.out.println("Veuillez saisir le code");
		code = sc.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace)");
		nom = sc.nextLine();
		System.out.println("Veuillez saisir le prix");
		prix = Double.parseDouble(sc.nextLine());
		
		Pizza p = new Pizza(code, nom, prix);
		Pizza[] tab = new Pizza[PizzeriaAdminConsoleApp.listePizza.length+1];
		
		for(int i = 0; i < PizzeriaAdminConsoleApp.listePizza.length; i++){
			tab[i] = PizzeriaAdminConsoleApp.listePizza[i];
		}
		tab[PizzeriaAdminConsoleApp.listePizza.length] = p;
		PizzeriaAdminConsoleApp.listePizza = tab;
	}
	
	private static void updatePizza(){
		String code;
		
		for(Pizza p : PizzeriaAdminConsoleApp.listePizza){
			System.out.println(p.toString());
		}
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");
		
		
		code = sc.nextLine();
		if(!code.equals("99")){
			int index = 0;
			for(int i = 0; i < PizzeriaAdminConsoleApp.listePizza.length; i++){
				if(PizzeriaAdminConsoleApp.listePizza[i].getCode().equals(code)){
					index = i;
				}
			}
			System.out.println("Veuillez saisir le code");
			PizzeriaAdminConsoleApp.listePizza[index].setCode(sc.nextLine());
			
			System.out.println("Veuillez saisir le nom (sans espace)");
			PizzeriaAdminConsoleApp.listePizza[index].setNom(sc.nextLine());
			
			System.out.println("Veuillez saisir le prix");
			PizzeriaAdminConsoleApp.listePizza[index].setPrix(Double.parseDouble(sc.nextLine()));
			
			for(Pizza p : PizzeriaAdminConsoleApp.listePizza){
				System.out.println(p.toString());
			}
		}
		
	}
	
	/**
	 * 
	 */
	private static void deletePizza(){
		String code;
		for(Pizza p : PizzeriaAdminConsoleApp.listePizza){
			System.out.println(p.toString());
		}
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");
		
		code = sc.nextLine();
		if(!code.equals("99")){
			//Trouverla pizza
			int index = 0;
			for(int i = 0; i < PizzeriaAdminConsoleApp.listePizza.length; i++){
				if(PizzeriaAdminConsoleApp.listePizza[i].getCode().equals(code)){
					index = i;
				}
			}
			
			//Del la pizza
			PizzeriaAdminConsoleApp.listePizza[index] = null;
			Pizza[] tab = new Pizza[PizzeriaAdminConsoleApp.listePizza.length-1];
			int j = 0;
			for(int i = 0; i < PizzeriaAdminConsoleApp.listePizza.length; i++){
				if(PizzeriaAdminConsoleApp.listePizza[i] != null){
					tab[j] = PizzeriaAdminConsoleApp.listePizza[i];
					j++;
				}
			}
			PizzeriaAdminConsoleApp.listePizza = tab;
		}
	}

}
