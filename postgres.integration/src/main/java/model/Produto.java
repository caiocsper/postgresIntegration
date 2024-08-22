package model;

public class Produto {
	private int id;
	private double preco;
	private String nome;
	private int quantidade;
	
	public Produto() {
		this.id = -1;
		this.preco = 0.00;
		this.nome = "";
		this.quantidade = 0;
	}
	
	public Produto(int id, double preco, String nome, int quantidade) {
		this.id = id;
		this.preco = preco;
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Usuario [id = " + id + ", nome = " + nome + ", preço = " + preco + ", quantidade = " + quantidade + "]";
	}	
}