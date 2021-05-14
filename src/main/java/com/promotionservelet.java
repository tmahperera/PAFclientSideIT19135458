package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Promotions;

/**
 * Servlet implementation class promotion
 */
@WebServlet("/promotionservelet")
public class promotionservelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Promotions rObj = new Promotions();

	public promotionservelet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String result = rObj.insertPromotion(request.getParameter("promotionName"),
				request.getParameter("promotionType"), request.getParameter("discountPercentage"),
				request.getParameter("startDate"), request.getParameter("endDate"));

		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> param = getParasMap(request);
		String result = rObj.updatepromotion(param.get("hidpromotionIDSave").toString(),
				param.get("promotionName").toString().toString().replace("+", " "),
				param.get("promotionType").toString(), param.get("discountPercentage").toString(),
				param.get("startDate").toString(), param.get("endDate").toString().toString().replace("+", " "));

		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> param = getParasMap(request);

		String result = rObj.deletePromotion(param.get("promotionID").toString());

		response.getWriter().write(result);
	}

}
