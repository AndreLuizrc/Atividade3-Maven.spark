package app;

import static spark.Spark.*;
import service.CarrosService;


public class Aplicacao {
	
	private static CarrosService carrosService = new CarrosService();
	
    public static void main(String[] args) {
        port(6783);
        
        staticFiles.location("/public");
        
        post("/carro/insert", (request, response) -> carrosService.insert(request, response));

        get("/carro/:id", (request, response) -> carrosService.get(request, response));
        
        get("/carro/list/:orderby", (request, response) -> carrosService.getAll(request, response));

        get("/carro/update/:id", (request, response) -> carrosService.getToUpdate(request, response));
        
        post("/carro/update/:id", (request, response) -> carrosService.update(request, response));
           
        get("/carro/delete/:id", (request, response) -> carrosService.delete(request, response));

             
    }
}