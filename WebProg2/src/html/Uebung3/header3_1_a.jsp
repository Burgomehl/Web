<html>
<head>
<title>Anfragekopf anzeigen</title>
</head>
<body>
	<table border="1">
		<%
			java.util.Enumeration<String> header = request.getHeaderNames();
			while (header.hasMoreElements()) {
				java.lang.String ele = header.nextElement();
				java.lang.String start = "";
				java.lang.String ende = "";
				if (ele.equals("host")) {
					start = "<b>";
					ende = "</b>";
				}
				out.println("<tr><td>" + start + ele + ende + ": </td>");
				java.lang.String content = request.getHeader(ele);
				out.println("<td>" + start + content + ende + "</td>");
				out.println("<tr/>");
			}
		%>
	</table>
</body>
</html>