<%@ page contentType="text/html; charset=ISO-8859-1" 
import="java.util.ArrayList" import="java.util.List" import="com.lab.dao.model.Persona" %>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta content="text/html; charset=ISO-8859-1">
	<title>Lista Persone</title>
</head>
<body>
	<h1>Lista delle persone registrate</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Data di nascita</th>
			</tr>
		</thead>
		<tbody>
			<% List<Persona> listaPersone = (List<Persona>) session.getAttribute("listaPersone");
				for (Persona persona : listaPersone) { %>
				<tr>
					<td><%= persona.getId() %></td>
					<td><%= persona.getNome() %></td>
					<td><%= persona.getCognome() %></td>
					<td><%= persona.getDataNascita() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<h2>Area gestionale</h2>
	<h3>Aggiungi una persona</h3>
	<form method="POST">
		<input type="hidden" name="operazioneParam" value="crea">
		<label> Nome
		<input type="text" name="nomeParam">		
		</label>
		<label> Cognome
		<input type="text" name="cognomeParam">		
		</label>
		<label> Data di nascita
		<input type="text" name="dataParam" placeholder="Formato: AAAA-MM-GG">		
		</label>
		<button type="submit">Crea</button>
	</form>
	<h3>Modifica una persona</h3>
	<form method="POST">
		<input type="hidden" name="operazioneParam" value="modifica">
		<label> ID
		<input type="number" name="idParam">		
		</label>
		<label> Nome
		<input type="text" name="nomeParam">		
		</label>
		<label> Cognome
		<input type="text" name="cognomeParam">		
		</label>
		<label> Data di nascita
		<input type="text" name="dataParam" placeholder="Formato: AAAA-MM-GG">		
		</label>
		<button type="submit">Modifica</button>
	</form>
	<h3>Elimina una persona</h3>
	<form method="POST">
		<input type="hidden" name="operazioneParam" value="elimina">
		<label> ID
		<input type="number" name="idParam">		
		</label>
		<button type="submit">Elimina</button>
	</form>
		
</body>
</html>