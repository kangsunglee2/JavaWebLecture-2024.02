package ch07_dao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ch07/city/list")
public class Ex02_List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String district = request.getParameter("district");
		district = (district == null || district.equals("")) ? "Kyonggi" : district;
		String num_ = request.getParameter("num");
		int num = (num_ == null || num_.equals("")) ? 10 : Integer.parseInt(num_);
		String offset_ = request.getParameter("offset");
		int offset = (offset_ == null || offset_.equals("")) ? 0 : Integer.parseInt(offset_);
		
		CityDao cDao = new CityDao();
		List<City> list = cDao.getCityList(district, num, offset);
//		list.forEach(x -> System.out.println(x));

//		RequestDispatcher rd = request.getRequestDispatcher("/ch07/list.jsp");
//		request.setAttribute("list", list);
//		rd.forward(request, response);
		
		String data = "";
		for (City c: list) {
			data += "<tr>";
			data += "	<td>" + c.getId() +"</td>";
			data += "	<td>" + c.getName() +"</td>";
			data += "	<td>" + c.getCountryCode() +"</td>";
			data += "	<td>" + c.getDistrict() +"</td>";
			data += "	<td>" + c.getPopulation() +"</td>";
			data += "</tr>";
		};
		
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "	<meta charset=\"UTF-8\">"
				+ "	<title>City List</title>"
				+ "	<style>"
				+ "		th, td { padding: 3px; }"
				+ "	</style>"
				+ "</head>"
				+ "<body style=\"margin: 50px;\">"
				+ "	<h1>City List</h1>"
				+ "	<hr>"
				+ "	<table border=\"1\">"
				+ "		<tr><th>아이디</th><th>도시명</th><th>국가코드</th><th>지역명</th><th>인구수</th></tr>";
		html += data;
		html += "	</table>"
				+ "</body>"
				+ "</html>";
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(html);
	}

}