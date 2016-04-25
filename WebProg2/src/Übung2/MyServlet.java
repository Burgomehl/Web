package Übung2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

@WebServlet("/servlet/MyServlet")
public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parameter = req.getParameter("farbe");
		String seite = req.getParameter("Seite");
		Cookie myCookie = null;
		if (parameter == null || parameter.isEmpty()) {
			parameter = "#ffffff";
		} else {
			myCookie = new Cookie("Farbe", parameter);
			myCookie.setMaxAge(60 * 60 * 24 * 30);
			resp.addCookie(myCookie);
		}
		if (myCookie == null) {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				parameter = cookies[0].getValue();
				myCookie = parameter.startsWith("#") ? cookies[0] : null;
				parameter = parameter.startsWith("#") ? parameter : "#ffffff";
				// out.println("Cookie vorhanden");
			}
		}

		if (myCookie != null && seite != null && !seite.isEmpty()) {
			if (seite.equals("Spiel")) {
				Object attribute = req.getAttribute("submit_blue.x");
				zweiteSeite(resp,attribute);
				
			} else if (seite.equals("Gewinn")) {
			} else if (seite.equals("Statusmeldung")) {
			}
		} else {
			resp.setContentType("text/html");
			ersteSeite(resp,parameter);
		}

	}

	private void zweiteSeite(HttpServletResponse resp,Object att) throws IOException {
		
		PrintWriter out = resp.getWriter();
		out.println(att);
		//OutputStream out = resp.getOutputStream();
//		int width = 500, height = 500;
		resp.setContentType("text/html");
//		BufferedImage i = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics g = i.getGraphics();
//		g.setColor(Color.cyan);
//		g.fillRect(0, 0, width, height);
//		ImageIO.write(i, "jpg", out);
		String test ="<input name=\"myImage\" src=\"../testBild.jpg\" type=\"image\"/>";
		out.println(test);
		out.close();
	}

	private void ersteSeite(HttpServletResponse resp, String parameter) throws IOException {
		PrintWriter out = resp.getWriter();
		String head = "<html><head></head><body bgcolor=" + parameter + ">";
		out.print(head);
		String form = " <form method=\"get\" " + //
				" action=\"../servlet/MyServlet?farbe=red\"> ";
		out.println(form);
		String radio = "<p>farbe:<input name=\"farbe\" value=\"#00ff00\" type=\"radio\">Grün</input> " + //
				"<input name=\"farbe\" value=\"#ff0000\" type=\"radio\">Rot</input> " + //
				"<input name=\"farbe\" value=\"#0000ff\" type=\"radio\">Blau</input> " + //
				"</p>";
		String button = "<input value=\"Abschicken\" type=\"submit\"/>";
		out.println(radio);
		out.print(button);
		out.print("</form>");
		String tail = "</body></html>";
		out.println(tail);
		out.close();
	}
}
