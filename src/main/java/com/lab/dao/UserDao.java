package com.lab.dao;

import java.util.List;

public interface UserDao<T> {
	//Ritorna una lista di oggetti con i dati provenienti dal db
	List<T> lettura();
	//Crea un inserimento nel db con i dati presenti nell' oggetto
	void creazione(T t);
	//Modifica un inserimento nel db con i dati presenti nell' oggetto
	void modifica(T t);
	//Elimina un inserimento
	void eliminazione(int id);
}
