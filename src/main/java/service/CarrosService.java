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
		
		String resp = "";
		
		Carro carro = new Carro(1,modelo,fabricante,ano,cavalos);
		
		if(carrosDAO.insertCarro(carro) == true) {
			resp = "Carro( "+modelo+" ) inserido!";
			response.status(201);
		}else{
			resp = "Carro ( "+modelo+" ) não inserido!";
			response.status(400);
		};
		
		
		return resp;
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Carro carro = carrosDAO.getCarroId(id);
		
		String resp = "";
		
		if(carro != null) {
			response.status(200);
			resp = "Carro ( "+carro.getModelo()+ " ) encontrado!";
		} else {
			response.status(400);
			resp = "Carro ( "+id+" ) não encontrado!";
		}
		
		return resp;
	}
	
	public Object Update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Carro carro = carrosDAO.getCarroId(id);
		
		String resp = "";
		
		if(carro != null) {
			carro.setModelo(request.queryParams("modelo"));
			carro.setFabricante(request.queryParams("fabricante"));
			carro.setAno(Integer.parseInt(request.queryParams("ano")));
			carro.setCavalos(Integer.parseInt(request.queryParams("cavalos")));
			
			carrosDAO.atualizarCarro(carro);
			
			response.status(200);
			
			resp = "Carro ( "+carro.getId()+" ) atualizado!";
		} else {
			response.status(400);
			
			resp = "Carro ( "+id+" ) não encontrado!";
		}
		
		return resp;
	}
	
	public Object delete(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		String resp = "'";
		if( carrosDAO.excluirCarro(id) == true) {
			response.status(200);
			resp = "Carro ( "+id+" ) excluido!";
		} else {
			response.status(404);
			resp = "Carro ( "+id+" ) não encontrado!";
		}
		
		return resp;
	}
	
	
}
