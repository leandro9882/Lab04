package it.polito.tdp.lab04.model;

public class Studente {
	int matricola;
	String cognome;
	String nome;
	String CDS;
	
	public Studente(int matricola, String cognome, String nome, String CDS) {
		
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.CDS = CDS;
	}

	/**
	 * @return the matricola
	 */
	public int getMatricola() {
		return matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cDS
	 */
	public String getCDS() {
		return CDS;
	}

	/**
	 * @param cDS the cDS to set
	 */
	public void setCDS(String cDS) {
		CDS = cDS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.matricola+ ", " + nome + ", " + cognome + ", " + CDS;
	}
	
}
