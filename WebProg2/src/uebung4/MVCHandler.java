package uebung4;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/MVCHandler")
public class MVCHandler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		XmlReaderBean bean = ProjectFactory.getReader();
		String name = req.getParameter("Name");
		String seite = req.getParameter("Seite");

		if (seite != null && !seite.isEmpty()) {
			if (seite.equals("Suche")) {
				String suche = req.getParameter("suche");
				bean.setSearchedPerson(suche);
				System.out.println(bean.getSearchedPerson());
				req.setAttribute("xml", bean);
				confSide("../html/Uebung4/MVC4_1_a_Suche.jspx", req, resp);
			} else if (seite.equals("Listenausgabe")) {
				req.setAttribute("xml", bean);
				confSide("../html/Uebung4/MVC4_1_a.jspx", req, resp);
			}
		} else {
			if (name != null && !name.isEmpty()) {
				Person person = ProjectFactory.getPerson();
				req.setAttribute("person", person);
				confSide("../html/Uebung4/MVC4_1_a2_Person.jspx", req, resp);
			} else {
				confSide("../html/Uebung4/MVC4_1_Startseite.jspx", req, resp);
			}
		}
	}
	
	private void confSide(String side, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String jsp = side;
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}
}
