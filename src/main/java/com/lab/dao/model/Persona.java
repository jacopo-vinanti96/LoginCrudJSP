package com.lab.dao.model;

import java.sql.Date;

public class Persona {
    private int id;
    private String nome;
    private String cognome;
    private Date dataNascita;

    public void setId(int id) {
		this.id = id;
	}

    public void setNome(String nome) {
		this.nome = nome;
	}

    public void setCognome(String cognome) {
		this.cognome = cognome;
	}

    public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

    public int getId() {
		return id;
	}

    public String getNome() {
		return nome;
	}

    public String getCognome() {
		return cognome;
	}

    public Date getDataNascita() {
		return dataNascita;
	}

	@Override
	public String toString() {
		return "id: " + id + "\t nome: " + nome + "\t cognome: " + cognome + "\t data di nascita: " + dataNascita;
	}
    
}