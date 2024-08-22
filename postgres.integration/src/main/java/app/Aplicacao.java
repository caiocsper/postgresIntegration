package app;

import java.util.*;

import dao.DAO;
import dao.ProdutoDAO;
import model.Produto;

public class Aplicacao {
	private ProdutoDAO produtoDAO;
	private Scanner leitor;
	private int lastId;
	
	public Aplicacao() {
		this.produtoDAO = new ProdutoDAO();
		this.leitor = new Scanner(System.in);
		this.lastId = this.produtoDAO.getOrderById(1).get(0).getId();
	}

	public void listarProdutos(int tipoListagem, int tipoOrdenacao) {
		List<Produto> produtos;

		switch(tipoListagem) {
			case 1:
				produtos = this.produtoDAO.getOrderById(tipoOrdenacao);
				break;
			case 2:
				produtos = this.produtoDAO.getOrderByNome(tipoOrdenacao);
				break;
			case 3:
				produtos = this.produtoDAO.getOrderByPreco(tipoOrdenacao);
				break;
			case 4:
				produtos = this.produtoDAO.getOrderByQuantidade(tipoOrdenacao);
				break;
			case 5:
				produtos = this.produtoDAO.getProdutosEmEstoque();
				break;
			default:
				produtos = this.produtoDAO.get();
				break;
		}
		
		if (produtos.size() < 1) {
			System.out.println("\n\n==== Nosso estoque está esgotado === ");
			return;
		}
		
		for (Produto p: produtos) System.out.println(p.toString());
	}
	
	public void inserirProduto(Produto produto) {
		if(this.produtoDAO.insert(produto) == true) {
			System.out.println("\nInserção com sucesso -> " + produto.toString());
		} else {
			System.out.println("\nFalha ao inserir produto -> " + produto.toString());
		}
	}
	
	public int carregaMenuPrincipal() {
		System.out.println("\nDigite o número correspondente para realizar uma operação:");
		System.out.println("\n1 - Listar produtos");
		System.out.println("2 - Buscar produto");
		System.out.println("3 - Inserir produto");
		System.out.println("4 - Atualizar produto");
		System.out.println("5 - Excluir produto");
		System.out.println("6 - Sair");

		int opcao = leitor.nextInt();

		return opcao;
	}
	
	public void carregaMenuListagem() {
		System.out.println("\nDigite o número correspondente para escolher o tipo de listagem:");
		System.out.println("\n1 - Listar por ID");
		System.out.println("2 - Listar por nome");
		System.out.println("3 - Listar por preço");
		System.out.println("4 - Listar por quantidade");
		System.out.println("5 - Listar em estoque");
		System.out.println("6 - Listar sem ordenação");
		System.out.println("7 - Voltar");

		int opcao = leitor.nextInt();
		int tipoOrdenacao = 0;
		
		if (opcao < 1 || opcao > 6) { return; }

		if (opcao > 0 && opcao < 5) {
			System.out.println("\nDigite 1 para listar em ordem decrescente (por padrão toda listagem é em ordem crescente) ou digite um número qualquer para pular esta etapa:");
			tipoOrdenacao = leitor.nextInt();
		}
		
		this.listarProdutos(opcao, tipoOrdenacao == 1 ? 1 : 0);
	}
	
	public void carregaMenuBusca() {
		System.out.println("\nDigite o nome do produto que deseja buscar:");
		String nomeProduto = leitor.next();
		
		this.buscarProduto(nomeProduto);
	}
	
	public void buscarProduto(String nome) {
		List<Produto> produtos = this.produtoDAO.buscarProdutosPorNome(nome);

        if (produtos.size() == 0) {
        	System.out.println("\n==== Não existem produtos com este nome === ");
        } else {
        	for (Produto p: produtos) {
    			System.out.println(p.toString());
    		}
        }
	}
	
	public void carregaMenuInsercao() {
		leitor.nextLine(); // gambiarra
		System.out.println("\nDigite o nome do produto sendo adicionado:");
		String nomeProduto = leitor.nextLine();
		System.out.println("\nDigite o preço do produto sendo adicionado:");
		double precoProduto = leitor.nextDouble();
		System.out.println("\nDigite a quantidade do produto sendo adicionado:");
		int quantidadeProduto = leitor.nextInt();
		
		this.insereProduto(nomeProduto, precoProduto, quantidadeProduto);
	}
	
	
	public void insereProduto(String nome, double preco, int quantidade) {
		this.lastId += 1;
		this.produtoDAO.insert(new Produto(this.lastId, preco, nome, quantidade));
	}

	public void carregaMenuAtualizao() {
		System.out.println("\nDigite o ID do produto que deseja atualizar:");
		int idProduto = leitor.nextInt();
		
		Produto produto = this.produtoDAO.get(idProduto);
		
		leitor.nextLine(); // gambiarra
		System.out.println("\nDigite o novo nome do produto sendo editado - Nome atual: " + produto.getNome());
		produto.setNome(leitor.nextLine());
		System.out.println("\nDigite o novo preço do produto sendo editado - Preço atual: " + produto.getPreco());
		produto.setPreco(leitor.nextDouble());
		System.out.println("\nDigite a nova quantidade do produto sendo editado - Quantidade atual: " + produto.getQuantidade());
		produto.setQuantidade(leitor.nextInt());
		
		this.produtoDAO.update(produto);
	}
	
	public void carregaMenuExclusao() {
		System.out.println("\nDigite o ID do produto que deseja excluir:");
		int idProduto = leitor.nextInt();
		
		this.produtoDAO.delete(idProduto);
	}
	
	public static void main(String[] args) throws Exception {
		Aplicacao app = new Aplicacao();
		
		int opcao = 0;
		
		System.out.println("\n==== Bem vindo ao sistema de gerenciamento de padaria ===");
		do {
			opcao = app.carregaMenuPrincipal();
			
			switch (opcao) {
				case 1:
					app.carregaMenuListagem();
					break;
				case 2:
					app.carregaMenuBusca();
					break;
				case 3:
					app.carregaMenuInsercao();
					break;
				case 4:
					app.carregaMenuAtualizao();
					break;
				case 5:
					app.carregaMenuExclusao();
					break;
				default:
					break;
			}
		} while(opcao > 0 && opcao < 6);
		
		app.leitor.close();
	}
}