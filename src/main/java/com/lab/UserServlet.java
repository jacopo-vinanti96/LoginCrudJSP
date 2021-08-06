package com.lab;

import java.util.List;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lab.dao.CredenzialeDao;
import com.lab.dao.UserDao;
import com.lab.dao.impl.CredenzialeDaoImpl;
import com.lab.dao.impl.UserDaoImpl;
import com.lab.dao.model.Credenziale;
import com.lab.dao.model.Persona;

@WebServlet("/User")
public class UserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Sono nella post");
		UserDao<Persona> crud = UserDaoImpl.getInstance();
		String operazione = req.getParameter("operazioneParam");
		switch (operazione) {
		case "crea":
			Persona personaCrea = new Persona();
			personaCrea.setNome(req.getParameter("nomeParam"));
			personaCrea.setCognome(req.getParameter("cognomeParam"));
			personaCrea.setDataNascita(Date.valueOf(req.getParameter("dataParam")));
			crud.creazione(personaCrea);
		break;
		case "modifica":
			Persona personaModifica = new Persona();
			personaModifica.setId(Integer.parseInt(req.getParameter("idParam")));
			personaModifica.setNome(req.getParameter("nomeParam"));
			personaModifica.setCognome(req.getParameter("cognomeParam"));
			personaModifica.setDataNascita(Date.valueOf(req.getParameter("dataParam")));
			crud.modifica(personaModifica);
		break;
		case "elimina":
			crud.eliminazione(Integer.parseInt(req.getParameter("idParam")));
		break;
		case "login":
			Credenziale datiLogin = new Credenziale();
			datiLogin.setUsername(req.getParameter("usernameParam"));
			datiLogin.setPassword(req.getParameter("passwordParam"));
			CredenzialeDao login = CredenzialeDaoImpl.getInstance();
			if (!login.validazione(datiLogin)) {
				System.out.println("I dati inseriti non sono corretti");
				req.getRequestDispatcher("loginError.html").forward(req, resp);
			}
		break;
		default:
			System.out.println("Errore, non è possibile eseguire l' operazione");
			req.getRequestDispatcher("loginError.html").forward(req, resp);
		}
		List<Persona> listaPersone = crud.lettura();
		HttpSession session = req.getSession();
		session.setAttribute("listaPersone", listaPersone);
		req.getRequestDispatcher("listaPersone.jsp").forward(req, resp);
	}
}
