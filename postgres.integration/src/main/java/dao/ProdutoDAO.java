package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO extends DAO {
	
	public ProdutoDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Produto produto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO produto (id, preco, nome, quantidade) "
				       + "VALUES ("+produto.getId()+ ", '" + produto.getPreco() + "', '"  
				       + produto.getNome() + "', '" + produto.getQuantidade() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Produto get(int id) {
		Produto produto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id = " + id;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 produto = new Produto(rs.getInt("id"), rs.getDouble("preco"), rs.getString("nome"), rs.getInt("quantidade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	
	public List<Produto> get() {
		return get("", 0);
	}

	
	public List<Produto> getOrderById(int orderType) {
		return get("id", orderType);		
	}
	
	
	public List<Produto> getOrderByNome(int orderType) {
		return get("nome", orderType);		
	}
	
	
	public List<Produto> getOrderByPreco(int orderType) {
		return get("preco", orderType);		
	}
	
	public List<Produto> getOrderByQuantidade(int orderType) {
		return get("quantidade", orderType);		
	}
	
	
	private List<Produto> get(String orderBy, int orderType) {	
	
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto";
			
			if (orderBy.trim().length() != 0) sql += " ORDER BY " + orderBy + (orderType == 0 ? " ASC" : " DESC");

			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);

	        while(rs.next()) {	            	
	        	Produto produto = new Produto(rs.getInt("id"), rs.getDouble("preco"), rs.getString("nome"), rs.getInt("quantidade"));
	        	produtos.add(produto);
	        }
	        
	        st.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}


	public List<Produto> getProdutosEmEstoque() {
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE produto.quantidade >= 1";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Produto produto = new Produto(rs.getInt("id"), rs.getDouble("preco"), rs.getString("nome"), rs.getInt("quantidade"));
	        	produtos.add(produto);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	public boolean update(Produto produto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE produto SET preco = '" + produto.getPreco() + "', nome = '"  
				       + produto.getNome() + "', quantidade = '" + produto.getQuantidade() + "'"
					   + " WHERE id = " + produto.getId();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM produto WHERE id = " + id;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public List<Produto> buscarProdutosPorNome(String nome) {
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE nome ILIKE '%" + nome + "%'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {	            	
	        	Produto produto = new Produto(rs.getInt("id"), rs.getDouble("preco"), rs.getString("nome"), rs.getInt("quantidade"));
	        	produtos.add(produto);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}	
}