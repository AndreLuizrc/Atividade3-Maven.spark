package app;

import static spark.Spark.*;

public class Aplicacao {
	
	public static void main(String[] args) {
		port(6742);
		
		post("/carro", (request, response) -> (request));
		
		get("/carro/:id", (request, response) -> (response));
		
		put("/carro/update/:id", (request, response) -> (request));
		
		get("/carro/delete/:id", (request, response) -> (request));
		
		get("/carro", (request, response) -> (request));

	}
}
