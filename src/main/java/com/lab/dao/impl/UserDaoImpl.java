package com.lab.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lab.dao.UserDao;
import com.lab.dao.model.Persona;

public class UserDaoImpl implements UserDao<Persona> {
	//Dichiaro il costruttore come private
	private UserDaoImpl() {
		
	}
	//Dichiaro l' instanza in metodo Lazy
	private static UserDaoImpl instance = null;
	//Dichiaro i dati del server
	private String DB_URL = "jdbc:mysql://localhost:3306/laboratorio";
    private String USER = "root";
    private String PASS = "root";
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    jdbc:mysql://localhost:3306/?user=root
    
    public static UserDaoImpl getInstance() {
    	if (instance == null) {
    		instance = new UserDaoImpl();
    	}
    	return instance;
    }

    @Override
    public List<Persona> lettura(){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE         
        String queryLettura = "SELECT id, nome, cognome, data_nascita FROM persone";
        List<Persona> persone = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            	e.printStackTrace();
        }
        try(    
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS );
            Statement statement = connection.createStatement();
        ) {             
            try(ResultSet resultSet = statement.executeQuery(queryLettura);){
                while (resultSet.next()){
                    //Crea nuova persona
                    Persona persona = new Persona();
                    //Aggiunge i dati
                    persona.setId(resultSet.getInt("id"));
                    persona.setNome(resultSet.getString("nome"));
                    persona.setCognome(resultSet.getString("cognome"));
                    persona.setDataNascita(resultSet.getDate("data_nascita"));
                    persone.add(persona);
                }             
            }         
        } catch (SQLException e){             
            e.printStackTrace();
        }
        return persone;     
    }
    
    @Override
    public void creazione(Persona persona){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE
        int rowCount;
        try {
        Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        try(    
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS );
            Statement  stmt = connection.createStatement();
        ) {             
            System.out.println("Iserimento della persona in corso...");
            String sql = "INSERT INTO persone (nome, cognome, data_nascita) VALUES ('"+persona.getNome()+"', '"+persona.getCognome()+"', '"+persona.getDataNascita()+"')";
            rowCount = stmt.executeUpdate(sql);
            System.out.println("Manipolazioni eseguite:"+rowCount);

        } catch (SQLException | NullPointerException e){       
            e.printStackTrace();
        }     
    }
    
    @Override
    public void modifica(Persona persona){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE
        int rowCount = 0;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            	e.printStackTrace();
        }
        try(    
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS );
            Statement stmt = connection.createStatement();
        ) {             
            System.out.println("Modifica della persona in corso...");
            String sql = "UPDATE persone SET nome='"+persona.getNome()+"', cognome='"+persona.getCognome()+"', data_nascita='"+persona.getDataNascita()+"' WHERE id="+persona.getId();
            rowCount = stmt.executeUpdate(sql);
        } catch (SQLException | NullPointerException e){           
            e.printStackTrace();
        }     
        System.out.println("Manipolazioni eseguite:"+rowCount);
    }
    
    @Override
    public void eliminazione(int id){
        // TRY WITH RESOURCES che extends/implements l'interfaccia CLOSABLE OPPURE AUTOCLOSEABLE
        int rowCount=0;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            	e.printStackTrace();
        }
        try(    
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS );
            Statement stmt = connection.createStatement();
        ) {             
            System.out.println("Eliminazione della persona in corso...");
            String sql = "DELETE FROM persone WHERE id="+id;
            rowCount = stmt.executeUpdate(sql);
        } catch (SQLException e){             
            e.printStackTrace();
        }     
        System.out.println("Manipolazioni eseguite:"+rowCount);
    }
}
