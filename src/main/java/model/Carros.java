package model;

public class Carros {
	
	private int id;
	private String modelo;
	private String fabricante;
	private int ano;
	private int cavalos;
	
	public Carros() {
		id = -1;
		modelo = "Exemplo";
		fabricante = "Exemplo";
		ano = 0000;
		cavalos = 000;
	}
	
	public Carros(int id, String modelo, String fabricante, int ano, int cavalos) {
		this.id = id;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.ano = ano;
		this.cavalos = cavalos;
	}
}
