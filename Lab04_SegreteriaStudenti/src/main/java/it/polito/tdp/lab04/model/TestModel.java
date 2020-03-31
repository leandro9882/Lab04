package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		Studente s=model.getStudente(146101);
    	System.out.println(s.toString());
	}

}
