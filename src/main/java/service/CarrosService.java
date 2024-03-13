package service;

import dao.CarrosDAO;
import model.Carro;
import spark.Request;
import spark.Response;

public class CarrosService {
	private CarrosDAO carrosDAO = new CarrosDAO(); 
	
	public Object insert(Request request, Response response) {
		String modelo = request.queryParams("modelo");
		String fabricante = request.queryParams("fabricante");
		int ano = Integer.parseInt(request.queryParams("ano"));
		int cavalos = Integer.parseInt(request.queryParams("cavalos"));
		
		int id = 1;
		
		Carro carro = new Carro(1,modelo,fabricante,ano,cavalos);
		
		carrosDAO.insertCarro(carro);
		
		response.status(201);
		
		return id;
	}
}
