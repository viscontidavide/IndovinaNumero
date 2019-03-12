package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {

	private NumeroModel model;
	

	public void setModel(NumeroModel model) {
		this.model = model;
	}


	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox boxControllopartita;

	@FXML
	private TextField txtRimasti;
	// numero di tentativi rimasti ancora da provare

	@FXML
	private HBox boxControlloTentativi;

	@FXML
	private TextField txtTentativo;
	// tentativo inserito dall'utente

	@FXML
	private TextArea txtMessaggi;

	@FXML
	void handleNuovaPartita(ActionEvent event) {
	
		// Gestione dell'interfaccia
		boxControllopartita.setDisable(true);
		boxControlloTentativi.setDisable(false);
		txtMessaggi.clear();
		txtRimasti.setText(Integer.toString(model.getTMAX()));
		
		model.newGame(); //comunico al modello inizio partita
	}

	@FXML
	void handleProvaTentativo(ActionEvent event) {

		// Leggi il valore del tentativo
		String ts = txtTentativo.getText();

		// Controlla se � valido
		int tentativo ;
		try {
			tentativo = Integer.parseInt(ts);
		} catch (NumberFormatException e) {
			// la stringa inserita non � un numero valido
			txtMessaggi.appendText("Non � un numero valido\n");
			return ;
		}
		
		if(!model.tentativoValido(tentativo)) {
			txtMessaggi.appendText("Range non valido\n");
			return;
		}
			
		int risultato = model.tentativo(tentativo);
		
		if(risultato == 0) {
			txtMessaggi.appendText("Complimenti hai indovinato in "+model.getTentativiFatti()+" tentativi");
			boxControllopartita.setDisable(false);
			boxControlloTentativi.setDisable(true);
		}
		else if (risultato <0) {
			txtMessaggi.appendText("Tentativo troppo BASSO\n");
		}
		else {
			txtMessaggi.appendText("Tentativo troppo ALTO\n");
		}
		

		// Aggiornare interfaccia con n. tentativi rimasti
		txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));

		if(!model.isInGioco()) {
			//la partita � finita
			if(risultato!=0) {
				txtMessaggi.appendText("Hai perso");
				txtMessaggi.appendText(String.format("\nIl numero segreto era %d", model.getSegreto()));
				boxControllopartita.setDisable(false);
				boxControlloTentativi.setDisable(true);
			}
		}
	}

	@FXML
	void initialize() {
		assert boxControllopartita != null : "fx:id=\"boxControllopartita\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
		assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

	}
}
