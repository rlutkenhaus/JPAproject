package application;


import javax.persistence.Column;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Student;

public class Menu {
	
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}

	public static void runMenu() {
		// TODO Auto-generated method stub
		
	
		boolean goAgain = true;
		System.out.println("What would you like to do?");
		while (goAgain) {
			System.out.println("Select from the menu.");
			System.out.println(" 1 Create a student entry");
			System.out.println(" 2 Read a student entry");
			System.out.println(" 3 Update a student entry");
			System.out.println(" 4 Delete a student entry");
			System.out.println(" 5 Exit the program");
			
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				createStudent();
			} else if (selection == 2) {
				readStudent();
			} else if (selection == 3) {
				updateStudent();
			} else if (selection == 4) {
				deleteStudent();
			} else {
				System.out.println("Exiting program");
				goAgain = false;
			}

		}
	}

	private static void createStudent() {
		// TODO Auto-generated method stub
	
		System.out.println("Name: ");
		String name = in.nextLine();
		System.out.println("Email: ");
		String email = in.nextLine();
		System.out.println("Phone: ");
		String phone = in.nextLine();
		System.out.println("Address: ");
		String address = in.nextLine();
		System.out.println("Position: ");
		String position = in.nextLine();
		System.out.println("Pay rate: ");
		Double payRate = Double.parseDouble(in.nextLine());
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudentEmployees");

		EntityManager em = emfactory.createEntityManager();
		
		em.getTransaction().begin();
		Student toAdd = new Student(name, email, phone, address, position, payRate);
		
		em.persist(toAdd);
		em.getTransaction().commit();
		emfactory.close();
		//runMenu();
	}
	
	public static void readStudent(){

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudentEmployees");
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		System.out.println("Enter name: ");
		String selectedName = in.nextLine();
		
		TypedQuery<Student> selectedNameQuery = em.createQuery("select name from Student name where name.name = :selectedname", Student.class);
		selectedNameQuery.setParameter("selectedname", selectedName);
		List<Student> results = selectedNameQuery.getResultList();
		 
		for(Student a : results){
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.println("Pay rate: $" + df.format(a.getPayRate()));
		}
		
		em.close();
		emfactory.close();
		//runMenu();
	}

	public static void updateStudent(){

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudentEmployees");
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		System.out.println("Student ID to update: ");
		int tempID = Integer.parseInt(in.nextLine());
		Student toUpdate = em.find(Student.class, tempID);
		
		boolean goAgain = true;
		System.out.println("What would you like to do?");
		while (goAgain) {
			System.out.println("Select from the menu.");
			System.out.println(" 1 Update the student's name");
			System.out.println(" 2 Update the student's email");
			System.out.println(" 3 Update the student's phone number");
			System.out.println(" 4 Update the student's address");
			System.out.println(" 5 Update the student's position");
			System.out.println(" 6 Update the student's pay rate");
			System.out.println(" 7 Return to menu");
			
		int selection = in.nextInt();
		in.nextLine();
		if(selection == 1){
			System.out.println("New name: ");
			String name = in.nextLine();
			toUpdate.setName(name);
		} else if(selection == 2){
			System.out.println("New email: ");
			String email = in.nextLine();
			toUpdate.setEmail(email);
		} else if(selection == 3){
			System.out.println("New phone: ");
			String phone = in.nextLine();
			toUpdate.setPhone(phone);
		} else if(selection == 4){
			System.out.println("New address: ");
			String address = in.nextLine();
			toUpdate.setAddress(address);
		} else if(selection == 5){
			System.out.println("New position: ");
			String position = in.nextLine();
			toUpdate.setPosition(position);
		} else if(selection == 6){
			System.out.println("New pay rate: ");
			Double payRate = Double.parseDouble(in.nextLine());
			toUpdate.setPayRate(payRate);
		} else {
			System.out.println("Exiting program");
			goAgain = false;
		}
		}
		em.getTransaction().commit();
		em.close();
		emfactory.close();
		//runMenu();
	}
	
	public static void deleteStudent(){

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudentEmployees");
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		System.out.println("Student ID to delete: ");
		int tempID = Integer.parseInt(in.nextLine());
		Student toRemove = em.find(Student.class, tempID);

		em.remove(toRemove);
		
		em.getTransaction().commit();
		em.close();
		emfactory.close();
		
		//runMenu();
	}

}
