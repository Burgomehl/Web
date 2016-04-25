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
		XmlReaderBean bean = XmlReaderBean.createInstance();
		String name = req.getParameter("Name");
		String seite;
		if(name != null && !name.isEmpty()){
			Person person = Person.createInstance();
			req.setAttribute("person", person);
			String jsp = "../html/Uebung4/MVC4_1_a2_Person.jspx";
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
		}else{
		req.setAttribute("xml",bean);
		String jsp = "../html/Uebung4/MVC4_1_a.jspx";
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
		}
	}
}
