package model;

public class Carro {
	
	public static int ultimo_id = 0;
	
	private int id;
	private String modelo;
	private String fabricante;
	private int ano;
	private int cavalos;
	
	public Carro() {
		id = -1;
		modelo = "";
		fabricante = "";
		ano = 0;
		cavalos = 0;
	}
	
	public Carro(int id, String modelo, String fabricante, int ano, int cavalos) {
		this.id = id;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.ano = ano;
		this.cavalos = cavalos;
	}
	
	public int getId() {
		return id;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public int getAno() {
		return ano;
	}
	
	public int getCavalos() {
		return cavalos;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public void setCavalos(int cavalos) {
		this.cavalos = cavalos;
	}
}
