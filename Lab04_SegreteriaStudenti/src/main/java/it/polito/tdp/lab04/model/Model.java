package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.model.Studente;
import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
private CorsoDAO dao;
	
	public Model() {
		dao = new CorsoDAO();
	}
	public List<Corso> getCorsi(){
		return dao.getTuttiICorsi();
	}
	public List<Studente> getStudentiCorso(Corso corso){
		return dao.getStudentiIscrittiAlCorso(corso);
	}
	public Studente getStudente(int matricola) {
		return dao.getStudente(matricola);
	}
	public List<Corso> getCorsiStudente(int matricola){
		return dao.getCorsiStudente(matricola);
	}
	public boolean getIscrizione(Studente studente,Corso corso) {
		return dao.inscriviStudenteACorso(studente, corso);
	}
}
