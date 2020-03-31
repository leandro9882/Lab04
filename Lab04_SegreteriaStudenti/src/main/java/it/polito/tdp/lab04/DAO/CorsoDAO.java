package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();
		List<String> nomi = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// System.out.println(codins + " " + numeroCrediti + " " + nome + " " +
				// periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));

			}
			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		List<Studente> result = new ArrayList<>();
		String sql = "SELECT s.matricola,s.cognome,s.nome,s.CDS\n" + "FROM studente s, corso c, iscrizione i\n"
				+ "WHERE c.codins=? AND s.matricola=i.matricola AND i.codins=c.codins\n"
				+ "GROUP BY s.matricola,s.cognome,s.nome,s.CDS;";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));
				result.add(s);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;

	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		boolean presente = false;
		String sql = "SELECT s.matricola\n" + "FROM studente s, iscrizione i\n"
				+ "WHERE s.matricola=? AND i.matricola=s.matricola AND i.codins=?;";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				presente = true;
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if(!presente) {
			//aggiungere studente al corso aggiornando il database
			 String SQL="INSERT INTO iscrizione(matricola,codins) VALUE(?,?);";
			 try {
					Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(SQL);
					st.setInt(1, studente.getMatricola());
					st.setString(2, corso.getCodins());
					int res= st.executeUpdate();
					conn.close();
			 } catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}

		return presente;
	}

	public Studente getStudente(int matricola) {
		Studente sTemp = null;
		String sql = "SELECT s.matricola,s.cognome, s.nome,s.CDS FROM studente s WHERE s.matricola=?;";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				sTemp = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));

			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return sTemp;
	}

	public List<Corso> getCorsiStudente(int matricola) {
		List<Corso> lista = new ArrayList<>();
		String sql = "SELECT c.codins,c.crediti,c.nome,c.pd FROM studente s, iscrizione i, corso c WHERE s.matricola=? AND i.matricola=s.matricola GROUP BY c.codins,c.crediti,c.nome,c.pd;";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				lista.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));

			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

}
