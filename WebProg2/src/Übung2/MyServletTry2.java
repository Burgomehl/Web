package Übung2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/Farbspiel")
public class MyServletTry2 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		String farbe = req.getParameter("farbe");
		String seite = req.getParameter("Seite");
		String gewinn = req.getParameter("gewinn");

		if (session.isNew()) {
			session.setMaxInactiveInterval(60 * 60 * 24 * 30);
			session.setAttribute("Punkte", 0);
		} else if (farbe != null && !farbe.isEmpty()) {
			session.setAttribute("Farbe", farbe);
		}

		int punkte = (int) session.getAttribute("Punkte");

		PrintWriter out = resp.getWriter();
		String parameter;

		if (session.getAttribute("Farbe") == null) {
			parameter = "#ffffff";
		} else {
			parameter = (String) session.getAttribute("Farbe");
		}
		String xCoord = req.getParameter("myImage.x");
		String yCoord = req.getParameter("myImage.y");

		if (seite != null && seite.equals("Spiel")) {
			Cookie tmp = new Cookie("Zeitablauf", "tmp");
			tmp.setMaxAge(30);
			resp.addCookie(tmp);
		}

		if (seite != null && seite.equals("Gewinnseite") && punkte < 3) {
			seite = "Spiel";
		}

		if (xCoord != null && yCoord != null) {
			Cookie[] cookies = req.getCookies();
			seite = "Spiel";
			boolean cookieGefunden = false;
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("Zeitablauf")) {
					Cookie tmp = new Cookie("Zeitablauf", "tmp");
					tmp.setMaxAge(30);
					resp.addCookie(tmp);
					cookieGefunden = true;
				}
			}
			if (!cookieGefunden) {
				punkte = 0;
				session.setAttribute("Punkte", punkte);
			}

			if (punkte < 3 && xCoord.equals(yCoord)) {
				session.setAttribute("Punkte", ++punkte);
			} else if (punkte > 2) {
				seite = "Gewinnseite";
				Cookie tmp = new Cookie("Gewinnablauf", "tmp");
				tmp.setMaxAge(30);
				resp.addCookie(tmp);
			}
		}

		if (parameter.equals("#ffffff")) {
			seite = null;
		}
		boolean cookieGefunden = false;
		if (gewinn != null) {
			Cookie[] cookies = req.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("Gewinnablauf")) {
					Cookie tmp = new Cookie("Gewinnablauf", "tmp");
					tmp.setMaxAge(30);
					resp.addCookie(tmp);
					cookieGefunden = true;
				}
			}
			if (punkte >= 3) {
				seite = "Bestätigung";
				punkte = 0;
				session.setAttribute("Punkte", punkte);
			}
		}

		String head = "<html><head></head><body bgcolor=" + parameter + ">";
		out.print(head);
		String form = " <form>";
		out.println(form);

		if (seite == null) {
			ersteSeite(out);
		} else if (seite.equals("Spiel")) {
			zweiteSeite(out, punkte);
		} else if (seite.equals("Gewinnseite")) {
			dritteSeite(out);
		} else if (seite.equals("Bestätigung")) {
			if (cookieGefunden) {
				out.print("Herzlichen glückwunsch Sie haben ein " + gewinn + " gewonnen.");
			} else {
				out.println("Die Zeit ist abgelaufen, dein Gewinn ist verfallen.");
			}
		}

		out.print("</form>");
		String tail = "</body></html>";
		out.println(tail);
		out.close();
	}

	private void dritteSeite(PrintWriter out) {
		out.println("hi du hast gewonnen such dir schnell etwas aus");
		String radio = "<p>Gewinne:<input name=\"gewinn\" value=\"Auto\" type=\"radio\">Auto</input> " + //
				"<input name=\"gewinn\" value=\"Haus\" type=\"radio\">Haus</input> " + //
				"<input name=\"gewinn\" value=\"Yacht\" type=\"radio\">Yacht</input> " + //
				"</p>";
		String button = "<input value=\"gewinn fordern\" type=\"submit\"/>";
		out.println(radio);
		out.print(button);
	};

	private void zweiteSeite(PrintWriter out, int punkte) throws IOException {
		String bereitsGetroffene = "Du hast bereits " + punkte + "/3 mal die Diagonale getroffen <br/>";
		out.print(bereitsGetroffene);
		String test = "<input name=\"myImage\" src=\"../testBild.jpg\" type=\"image\"/>";
		out.println(test);
	}

	private void ersteSeite(PrintWriter out) throws IOException {

		String radio = "<p>farbe:<input name=\"farbe\" value=\"#00ff00\" type=\"radio\">Grün</input> " + //
				"<input name=\"farbe\" value=\"#ff0000\" type=\"radio\">Rot</input> " + //
				"<input name=\"farbe\" value=\"#0000ff\" type=\"radio\">Blau</input> " + //
				"</p>";
		String button = "<input value=\"Abschicken\" type=\"submit\"/>";
		out.println(radio);
		out.print(button);

	}

}
