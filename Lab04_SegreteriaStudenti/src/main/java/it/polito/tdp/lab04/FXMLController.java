package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Studente;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Corso> box;

	@FXML
	private Button btnIscrittiCorso;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnV;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextArea txtRisultato;

	@FXML
	private Button btnReser;

	@FXML
	void doCercaCorsi(ActionEvent event) {
		txtRisultato.clear();
		String matricola = this.txtMatricola.getText();
		Integer pd;
		try {
			pd = Integer.parseInt(matricola);
		} catch (NumberFormatException e) {
			txtRisultato.setText("Devi inserire una matricola numerica!");
			return;
		}
		Studente s = model.getStudente(pd);
		if (s != null) {
			List<Corso> list = this.model.getCorsiStudente(s.getMatricola());
			for (Corso c : list) {
				txtRisultato.appendText(c.toString() + "\n");
			}
		} else {
			txtRisultato.setText("Nessun studente associato a questa matricola!");
			return;
		}
	}

	@FXML
	void doCercaIscritti(ActionEvent event) {
		txtRisultato.clear();
		Corso corso = this.box.getValue();
		if (corso.getCodins().compareTo("null") == 0) {
			this.txtRisultato.setText("Selezionare un corso!");
			return;
		}
		List<Studente> studenti = this.model.getStudentiCorso(corso);
		for (Studente s : studenti) {
			txtRisultato.appendText(s.toString() + "\n");
		}
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		txtRisultato.clear();
		String matricola = this.txtMatricola.getText();
		Integer pd;
		boolean presente = false;
		try {
			pd = Integer.parseInt(matricola);
		} catch (NumberFormatException e) {
			txtRisultato.setText("Devi inserire una matricola numerica!");
			return;
		}
		Studente s = model.getStudente(pd);
		Corso corso = this.box.getValue();
		if (corso.getCodins().compareTo("null") != 0 && s != null) {
			presente = model.getIscrizione(s, corso);
			if (presente) {
				txtRisultato.setText("Studente già iscritto a questo corso!");
			}
			else {
				txtRisultato.setText("Studente iscritto al corso!");
			}
		}
	}

	@FXML
	void doReset(ActionEvent event) {
		txtRisultato.clear();
		this.txtMatricola.clear();
		this.txtCognome.clear();
		this.txtNome.clear();
		box.setValue(new Corso("null", 0, "Nessun corso selezionato", 0));
	}

	@FXML
	void doVerifica(ActionEvent event) {
		String matricola = this.txtMatricola.getText();
		Integer pd;
		try {
			pd = Integer.parseInt(matricola);
		} catch (NumberFormatException e) {
			txtRisultato.setText("Devi inserire una matricola numerica!");
			return;
		}
		Studente s = model.getStudente(pd);
		this.txtCognome.setText(s.getCognome());
		this.txtNome.setText(s.getNome());
	}

	@FXML
	void initialize() {
		assert box != null : "fx:id=\"box\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrittiCorso != null : "fx:id=\"btnIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnV != null : "fx:id=\"btnV\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReser != null : "fx:id=\"btnReser\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		List<Corso> temp = new ArrayList<>(model.getCorsi());
		box.setValue(new Corso("null", 0, "Nessun corso selezionato", 0));
		box.getItems().addAll(temp);
	}
}
