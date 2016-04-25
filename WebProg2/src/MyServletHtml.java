import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/MyServletHtml")
public class MyServletHtml extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Enumeration<String> parameter = req.getParameterNames();
		String head = "<html><head></head><body>";
		out.print(head);
		while (parameter.hasMoreElements()) {
			String elem = parameter.nextElement();
			String value = req.getParameter(elem);
			String body = "<br/> "+ elem + ": " + value;
			out.println(body);
		}
		String tail = "</body></html>";
		out.println(tail);
		out.close();
	}
}
