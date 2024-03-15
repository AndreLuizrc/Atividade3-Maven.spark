package dao;

import model.Carro;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CarrosDAO extends DAO{
	
	public CarrosDAO() {
		super();
		conectar();
	}
	
	public void closeConnection() {
		close();
	}
	
	public boolean insertCarro(Carro carro) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO carro (modelo, fabricante, ano, cavalos)"
					+ "VALUES ('" + carro.getModelo() + "','" + carro.getFabricante() + "'," +carro.getAno() + ","
					+ carro.getCavalos() +");");
			
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public Carro getCarroId(int id) {
		Carro carro = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM carro WHERE id= '" + id + "'");
			if(rs.next()) {
				carro = new Carro(rs.getInt("id"),rs.getString("modelo"), rs.getString("fabricante"), rs.getInt("ano"), rs.getInt("cavalos"));
			} else {
				System.out.println("Carro não encontrado");
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return carro;
	}
	
	public List<Carro> get() {
		return get("");
	}

	
	public List<Carro> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Carro> getOrderByModelo() {
		return get("modelo");		
	}
	
	
	public List<Carro> getOrderByAno() {
		return get("ano");		
	}
	
	public List<Carro> getOrderByFabricante() {
		return get("fabricante");		
	}
	
	
	private List<Carro> get(String orderBy) {
		List<Carro> carros = new ArrayList<Carro>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carro" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Carro car = new Carro(rs.getInt("id"), rs.getString("modelo"), rs.getString("fabricante"), 
	        			                rs.getInt("ano"),
	        			                rs.getInt("cavalos"));
	            carros.add(car);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carros;
	}
	
	public boolean excluirCarro(int id) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM carro WHERE id =" + id;
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public boolean atualizarCarro(Carro carro) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE carro SET modelo = '" + carro.getModelo() +"', fabricante = '" + carro.getFabricante() + "' , ano = " + carro.getAno() +
			", cavalos=" + carro.getCavalos() + "WHERE id = " + carro.getId();
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	/*public void getCarros() {
		Carro[] carros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM carro");
			if(rs.next()) {
				rs.last();
				carros = new Carro[rs.getRow()];
				rs.beforeFirst();
				
				for(int i = 0; rs.next(); i++) {
					carros[i] = new Carro(rs.getInt("id"), rs.getString("modelo"),
							rs.getString("fabricante"), rs.getInt("ano"), rs.getInt("cavalos"));
					System.out.println(carros[i]);
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}*/
}
