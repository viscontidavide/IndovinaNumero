package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel() {
		inGioco = false;
	}
	
	public void newGame() {
		inGioco = true;
		// Logica del gioco
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
	}
	
	public int tentativo(int t) {
		//controolo se partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata");
		}
		//controllo se input è nel range corretto
		if(t<1 || t>NMAX) {
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d", 1, NMAX));
		}
		//gestisci tentativo
		this.tentativiFatti++;
		if(tentativiFatti == this.TMAX) {
			inGioco = false;
		}
		if(t == this.segreto) {
			this.inGioco = false;
			return 0;
		}
		if(t>this.segreto) {
			return 1; 
		}

		return -1;

	}
	
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false;
		}
		else {
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}

	
	
}
