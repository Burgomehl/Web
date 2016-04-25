import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/MyServletJpeg")
public class MyServletJpeg extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int height = 500;
		int width = 500;

		String widthS = req.getParameter("width");
		width = Integer.valueOf(widthS);

		String heightS = req.getParameter("height");
		height = Integer.valueOf(heightS);

		resp.setContentType("image/jpeg");
		OutputStream out = resp.getOutputStream();

		BufferedImage i = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = i.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);

		GregorianCalendar gc = new GregorianCalendar();
		int year = gc.get(Calendar.YEAR);
		int month = gc.get(Calendar.MONTH + 1);
		int day = gc.get(Calendar.DAY_OF_MONTH);
		int hours = gc.get(Calendar.HOUR_OF_DAY);
		int seconds = gc.get(Calendar.SECOND);
		int minute = gc.get(Calendar.MINUTE);
		String yearS = String.valueOf(hours) + //
				":" + String.valueOf(minute) + //
				":" + String.valueOf(seconds) + //
				"  " + String.valueOf(day) + //
				"." + String.valueOf(month) + //
				"." + String.valueOf(year);

		char[] data = yearS.toCharArray();
		g.drawChars(data, 0, data.length, (int) (width / 2) - data.length, (int) (height / 2));
		g.dispose();
		ImageIO.write(i, "jpg", out);
		out.close();
	}
}
