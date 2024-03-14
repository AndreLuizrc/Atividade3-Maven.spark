package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.CarrosDAO;
import model.Carro;
import spark.Request;
import spark.Response;


public class CarrosService {

	private CarrosDAO carroDAO = new CarrosDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_MODELO = 2;
	private final int FORM_ORDERBY_ANO = 3;
	private final int FORM_ORDERBY_FABRICANTE = 4;
	
	
	public CarrosService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Carro(), FORM_ORDERBY_MODELO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Carro(), orderBy);
	}

	
	public void makeForm(int tipo, Carro carro, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umCarro = "";
		if(tipo != FORM_INSERT) {
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/carro/list/1\">Novo Carro</a></b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";
			umCarro += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/carro/";
			String name, modelo, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Carro";
				modelo = "Jetta, Lancer, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + carro.getId();
				name = "Atualizar Produto (ID " + carro.getId() + ")";
				modelo = carro.getModelo();
				buttonLabel = "Atualizar";
			}
			umCarro += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Modelo: <input class=\"input--register\" type=\"text\" name=\"modelo\" value=\""+ modelo +"\"></td>";
			umCarro += "\t\t\t<td>Fabricante: <input class=\"input--register\" type=\"text\" name=\"fabricante\" value=\""+ carro.getFabricante() +"\"></td>";
			umCarro += "\t\t\t<td>Ano: <input class=\"input--register\" type=\"text\" name=\"ano\" value=\""+ carro.getAno() +"\"></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Cavalos: <input class=\"input--register\" type=\"text\" name=\"cavalos\" value=\""+ carro.getCavalos() + "\"></td>";
			umCarro += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";
			umCarro += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Carro (ID " + carro.getId() + ")</b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Modelo: "+ carro.getModelo() +"</td>";
			umCarro += "\t\t\t<td>Fabricante: "+ carro.getFabricante() +"</td>";
			umCarro += "\t\t\t<td>Ano: "+ carro.getAno() +"</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Data de fabricação: "+ carro.getCavalos() + "</td>";
			umCarro += "\t\t\t<td>&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-CARRO>", umCarro);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Carros</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/carro/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/carro/list/" + FORM_ORDERBY_MODELO + "\"><b>Modelo</b></a></td>\n" +
        		"\t<td><a href=\"/carro/list/" + FORM_ORDERBY_FABRICANTE + "\"><b>Fabricante</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Carro> carros;
		if (orderBy == FORM_ORDERBY_ID) {                 	carros = carroDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_MODELO) {		carros = carroDAO.getOrderByModelo();
		} else if (orderBy == FORM_ORDERBY_FABRICANTE) {	carros = carroDAO.getOrderByFabricante();
		} else if (orderBy == FORM_ORDERBY_ANO) {           carros = carroDAO.getOrderByAno();
		} else {											carros = carroDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Carro car : carros) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + car.getId() + "</td>\n" +
            		  "\t<td>" + car.getModelo() + "</td>\n" +
            		  "\t<td>" + car.getFabricante() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/carro/" + car.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/carro/update/" + car.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteCarro('" + car.getId() + "', '" + car.getModelo() + "', '" + car.getFabricante() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-CARRO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String modelo = request.queryParams("modelo");
		String fabricante = request.queryParams("fabricante");
		int ano = Integer.parseInt(request.queryParams("ano"));
		int cavalos = Integer.parseInt(request.queryParams("cavalos"));
		
		String resp = "";
		
		Carro carro = new Carro(-1, modelo, fabricante, ano, cavalos);
		
		if(carroDAO.insertCarro(carro) == true) {
            resp = "Carro (" + modelo + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Carro (" + modelo + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Carro carro = (Carro) carroDAO.getCarroId(id);
		
		if (carro != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, carro, FORM_ORDERBY_MODELO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Carro " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Carro carro = (Carro) carroDAO.getCarroId(id);
		
		if (carro != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, carro, FORM_ORDERBY_MODELO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Carro " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Carro carro = carroDAO.getCarroId(id);
        String resp = "";       

        if (carro != null) {
        	carro.setModelo(request.queryParams("modelo"));
        	carro.setFabricante(request.queryParams("fabricante"));
        	carro.setAno(Integer.parseInt(request.queryParams("ano")));
        	carro.setCavalos(Integer.parseInt(request.queryParams("cavalos")));
        	carroDAO.atualizarCarro(carro);
        	response.status(200); // success
            resp = "Produto (ID " + carro.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Carro (ID " + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Carro carro = carroDAO.getCarroId(id);
        String resp = "";       

        if (carro != null) {
            carroDAO.excluirCarro(id);
            response.status(200); // success
            resp = "Carro (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Carro (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}