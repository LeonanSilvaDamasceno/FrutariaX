package frutaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frutadoce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int  id = 0;


		// Ala de armazenamento de valores
		Produtos[] pro = new Produtos[50];
		String[][][] orders = new String[6897][1][1];
		String[][][][][] log = new String[6897][2][2][2][2];

		// Ala de criação campos de texto
		JTextField usu = new JTextField();
		JTextField sen = new JTextField();
		JTextField idx = new JTextField();
		JTextField end = new JTextField();
		JTextField tel = new JTextField();
		JTextField vid = new JTextField();
		String pastas[] = { "clientes/", "produtos/", "compras/" };
		String arqIds[] = { "idclientes.txt", "idprodutos.txt", "idcompras.txt" };
		int ids[] = new int[3];
		ids = inicializar(pastas, arqIds);
		pro = produtos(pro);
		for (int i = 0; i < 1; i++) {
			if (cadastrarProduto(ids[1], pastas[1])) {
				ids[1]++;
				gravarId(ids[1], arqIds[1]);
			}
		}
		listarProdutos(pastas[1]);

		for (int i = 0; i < 5; i++) {
			if (cadastrarCliente(ids[0], pastas[0])) {
				ids[0]++;
				gravarId(ids[0], arqIds[0]);
			}
		}
		
		
		listarCliente(pastas[0]);
		
		

		Object[] o_log = { "Identificador:", idx, "Usuário: ", usu, "Senha: ", sen, }; // Ala de agrupamento entre
																						// mensagens e campos de texto
		Object[] o_cad = { "Nome: ", usu, "Senha: ", sen, "Endereço: ", end, "Telefone: ", tel };
		Object[] v_ids = { "Digite o id logo a baixo:", vid };
		String path = "compras/";
		int op =6;
		Scanner leia = new Scanner(System.in);

		id = inicializar(path);
		Compras c = new Compras();
		do {
			menu();
			op = leia.nextInt();
			switch(op) {
			case 1:
				if(cadastrarCompras(id, path)) {
					id++;
					gravarId(id);
				};
				break;
			case 2:
				c = buscarCompras(path);
				if(c!= null) {
					System.out.println(c.id+ " - "+ c.idCliente+ " - "+ c.produtos);
				}else {
					System.out.println("Compra inexistente!");
				}
				break;
			case 3:
				listarCompras(path);
				break;
			case 4:
				removerCompras(path);
				break;
			case 5:
				resetarCompras(path);
				id = 0;
				gravarId(0);
				break;
			case 6:
				gravarId(id);
				System.out.println("Saindo...");
				break;				
			}
		}while(op!=6);
	}

	

	public static Produtos[] produtos(Produtos[] prods) { // Listagem do nome do produto, quantidade e valor
															// sequencialmente.
		Produtos pro = new Produtos();
		pro.nome = "Banana";
		pro.qtd = 30;
		pro.preco = 1.90;
		prods[0] = pro;
		pro.nome = "Uva";
		pro.qtd = 30;
		pro.preco = 10.99;
		prods[1] = pro;
		pro.nome = "Morango";
		pro.qtd = 30;
		pro.preco = 13.99;
		prods[2] = pro;
		for (int i = 3; i < 50; i++)
			prods[i] = pro;
		/*
		 * pro[3].nome = "Limão"; pro[3].qtd = 60; pro[3].preco = 6.50;
		 * 
		 * pro[4].nome = "Mamão"; pro[4].qtd = 28; pro[4].preco = 5.99;
		 * 
		 * pro[5].nome = "Maça"; pro[5].qtd = 40; pro[5].preco = 7.50;
		 * 
		 * pro[6].nome = "Melancia"; pro[6].qtd = 28; pro[6].preco = 14.99;
		 * 
		 * pro[7].nome = "Laranja"; pro[7].qtd = 30; pro[7].preco = 8.25;
		 * 
		 * pro[8].nome = "Melão"; pro[8].qtd = 30; pro[8].preco = 6.00;
		 * 
		 * pro[9].nome = "Abacaxi"; pro[9].qtd = 30; pro[9].preco = 5.00;
		 * 
		 * pro[10].nome = "Coco"; pro[10].qtd = 30; pro[10].preco = 10.99;
		 * 
		 * pro[11].nome = "Pêra"; pro[11].qtd = 30; pro[11].preco = 15.99;
		 * 
		 * pro[12].nome = "Maracuja"; pro[12].qtd = 30; pro[12].preco = 12.99;
		 * 
		 * pro[13].nome = "Tangerina"; pro[13].qtd = 30; pro[13].preco = 8.69;
		 * 
		 * pro[14].nome = "Batata"; pro[14].qtd = 30; pro[14].preco = 2.80;
		 * 
		 * pro[15].nome = "Cebola"; pro[15].qtd = 30; pro[15].preco = 4.50;
		 * 
		 * pro[16].nome = "Cenoura"; pro[16].qtd = 30; pro[16].preco = 3.50;
		 * 
		 * pro[17].nome = "Tomate"; pro[17].qtd = 30; pro[17].preco = 4.50;
		 * 
		 * pro[18].nome = "Alface"; pro[18].qtd = 30; pro[18].preco = 6.50;
		 * 
		 * pro[19].nome = "Brócolis"; pro[19].qtd = 30; pro[19].preco = 6.50;
		 * 
		 * pro[20].nome = "Cóuve-flor"; pro[20].qtd = 30; pro[20].preco = 5.00;
		 * 
		 * pro[21].nome = "Repolho"; pro[21].qtd = 30; pro[21].preco = 3.00;
		 * 
		 * pro[22].nome = "Caju"; pro[22].qtd = 30; pro[22].preco = 4.25;
		 * 
		 * pro[23].nome = "Abacate"; pro[23].qtd = 30; pro[23].preco = 8.00;
		 * 
		 * pro[24].nome = "Goiaba"; pro[24].qtd = 30; pro[24].preco = 3.99;
		 * 
		 * pro[25].nome = "Kiwi"; pro[25].qtd = 30; pro[25].preco = 5.00;
		 * 
		 * pro[26].nome = "Pessego"; pro[26].qtd = 30; pro[26].preco = 5.00;
		 * 
		 * pro[27].nome = "Baobá"; pro[27].qtd = 30; pro[27].preco = 10.99;
		 */
		return prods;
	}

	

	public static Produtos[] add(Produtos[] pro) {
		int index, t_add = -7;

		String[] ops_Add = { "Adicionar", "Finalizar" };

		JTextField add = new JTextField();
		JTextField adn = new JTextField();
		JTextField adq = new JTextField();
		JTextField adp = new JTextField();

		Object[] adf = { "Número do produto: ", add, "Nome:", adn, "Quantidade:", adq, "Preço:", adp };

		while (t_add != 1) {
			t_add = JOptionPane.showOptionDialog(null, adf, "Adicionar produto", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, ops_Add, ops_Add[1]);
			if (!add.getText().equals("") && !adn.getText().equals("") && !adq.getText().equals("")
					&& !adp.getText().equals("")) {
				try {
					index = Integer.parseInt(add.getText());
					pro[index].nome = adn.getText();
					pro[index].qtd = Integer.parseInt(adq.getText());
					pro[index].preco = Double.parseDouble(adp.getText());

				} catch (Error e) {
					JOptionPane.showMessageDialog(null, "ERR0R", "Números digitados são inválidos!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERR0R", "Preencha todos os campos corretamente!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return pro;
	}

	public static Produtos[] rem(Produtos[] pro) {
		int index, t_rem = -7, t_confirm;

		String[] ops_Rem = { "Remover", "Finalizar" };
		String[] ops_Con = { "Confirmar", "Cancelar" };

		JTextField c_rem = new JTextField();

		Object[] rep = { "Número do produto a ser removido: ", c_rem };

		while (t_rem != 1) {
			t_rem = JOptionPane.showOptionDialog(null, rep, "Remover produto", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, ops_Rem, ops_Rem[1]);
			if (!c_rem.getText().equals("")) {
				try {
					index = Integer.parseInt(c_rem.getText());

					Object[] confirm = { index, ". Nome: " + pro[index].nome + ". Estoque: " + pro[index].qtd
							+ ". Preço: R$" + pro[index].preco + "." };

					t_confirm = JOptionPane.showOptionDialog(null, confirm, "Deseja remover o produto abaixo?",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_Con, ops_Con[1]);

					if (t_confirm == 0) {
						pro[index].nome = "Produto indisponível.";
						pro[index].qtd = 0;
						pro[index].preco = 0;
						JOptionPane.showMessageDialog(null, "Produto removido com êxito!", "Operação concluída!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Error e) {
					JOptionPane.showMessageDialog(null, "ERR0R", "Número digitado é inválido!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERR0R", "Escolha um número e tente novamente!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return pro;
	}

	private static int lerId(String arq) {
		BufferedReader bf;
		int id = 0;
		try {
			bf = new BufferedReader(new FileReader(arq));
			id = Integer.parseInt(bf.readLine());
			bf.close();
		} catch (IOException | NumberFormatException e) {
			System.err.println("Id não encontrado");
			e.printStackTrace();
		}
		return id;
	}

	private static void gravarId(int id, String arq) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(arq);
			pw.println(id);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int[] inicializar(String path[], String arqIds[]) {
		int id[] = new int[arqIds.length];
		for (int i = 0; i < path.length; i++) {
			File dir = new File(path[i]);
			if (!dir.exists()) { // Verifica se o diretório contendo contatos ja existe
				dir.mkdir(); // cria se não existir
			}
		}
		for (int i = 0; i < arqIds.length; i++) {
			File arquivo = new File(arqIds[i]);
			if (!arquivo.exists()) { // verifica se o arquivo de id já existe
				try {
					arquivo.createNewFile(); // cria se não existir
				} catch (IOException e) {
					System.out.println("Não foi possível criar o ID");
					e.printStackTrace();
				}
				gravarId(0, arqIds[i]); // grava o id=0 para inicializar a agenda
				id[i] = 0;
			} else { // caso o arquivo de id já exista,
				id[i] = lerId(arqIds[i]); // é feita a leitura
			}
		}
		return id;
	}

	private static void gravarProduto(Produtos c, String path) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(path + c.id + ".txt");
		pw.println(c.id);
		pw.println(c.nome);
		pw.println(c.preco);
		pw.println(c.qtd);
		pw.flush();
		pw.close();
	}

	private static boolean cadastrarProduto(int id, String path) {
		Scanner leia = new Scanner(System.in);
		Produtos c = new Produtos();
		System.out.println("Cadastrando produtos - " + id);
		System.out.println("nome: ");
		c.nome = leia.nextLine();
		System.out.println("preco: ");
		c.preco = Double.parseDouble(leia.nextLine());
		System.out.println("quantidade: ");
		c.qtd = Integer.parseInt(leia.nextLine());
		c.id = id;
		try {
			gravarProduto(c, path);
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possivel gravar o produto");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static Produtos lerProduto(int id, String path) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(path + id + ".txt"));
		Produtos c = new Produtos();
		c.id = Integer.parseInt(bf.readLine());
		c.nome = bf.readLine();
		c.preco = Double.parseDouble(bf.readLine());
		c.qtd = Integer.parseInt(bf.readLine());
		bf.close();
		return c;
	}

	private static void listarProdutos(String path) {
		File dir = new File(path);
		String[] arquivos = dir.list();
		for (int i = 0; i < arquivos.length; i++) {
			int id = Integer.parseInt(arquivos[i].substring(0, arquivos[i].length() - 4));
			try {
				Produtos c = lerProduto(id, path);
				System.out.println(c.id + " - " + c.nome + " - " + c.preco + " - " + c.qtd);
			} catch (IOException e) {
				System.out.println("Contato não encontrado");
				e.printStackTrace();
			}
		}
		if (arquivos.length == 0) {
			System.out.println("Não tem contato cadastrado");
		}
	}

	private static Produtos buscarProdutos(String path) {
		Scanner leia = new Scanner(System.in);
		int id;
		Produtos c = new Produtos();
		System.out.println("Qual o id do produtos?");
		id = leia.nextInt();
		try {
			c = lerProduto(id, path);

		} catch (IOException e) {
			return null;
		}

		return c;
	}

	private static void removerProdutos(String path) {

		Scanner leia = new Scanner(System.in);
		int id;
		System.out.println("Qual o id do produto a ser removido?");
		id = leia.nextInt();
		File arquivo = new File(path + id + ".txt");
		if (!arquivo.exists()) {
			System.out.println("Produtos inexistente!");
		} else {
			if (arquivo.delete())
				System.out.println("Produtos removido");
			else
				System.out.println("Produtos não removido - erro");
		}

	}

	public static Clientes[] add(Clientes[] pro) {
		int index, t_add = -7;

		String[] ops_Add = { "Adicionar", "Finalizar" };

		JTextField add = new JTextField();
		JTextField adn = new JTextField();
		JTextField adq = new JTextField();
		JTextField adp = new JTextField();

		Object[] adf = { "Número do cliente: ", add, "Usu:", adn, "Nome:", adn, "Senha:", adn, "Endereco:", adq,
				"Telefone:", adp };

		while (t_add != 1) {
			t_add = JOptionPane.showOptionDialog(null, adf, "Adicionar produto", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, ops_Add, ops_Add[1]);
			if (!add.getText().equals("") && !adn.getText().equals("") && !adq.getText().equals("")
					&& !adp.getText().equals("")) {
				try {
					index = Integer.parseInt(add.getText());
					pro[index].usu = adq.getText();
					pro[index].nome = adn.getText();
					pro[index].senha = adq.getText();
					pro[index].endereco = adp.getText();
					pro[index].telefone = adq.getText();
				} catch (Error e) {
					JOptionPane.showMessageDialog(null, "ERR0R", "Números digitados são inválidos!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERR0R", "Preencha todos os campos corretamente!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return pro;
	}

	public static Clientes[] rem(Clientes[] pro) {
		int index, t_rem = -7, t_confirm;

		String[] ops_Rem = { "Remover", "Finalizar" };
		String[] ops_Con = { "Confirmar", "Cancelar" };

		JTextField c_rem = new JTextField();

		Object[] rep = { "Número do cliente a ser removido: ", c_rem };

		while (t_rem != 1) {
			t_rem = JOptionPane.showOptionDialog(null, rep, "Remover cliente ", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, ops_Rem, ops_Rem[1]);
			if (!c_rem.getText().equals("")) {
				try {
					index = Integer.parseInt(c_rem.getText());

					Object[] confirm = { index,
							"Usu" + pro[index].usu + ". Nome: " + pro[index].nome + ". Senha: " + pro[index].senha
									+ ". Endereço: R$" + pro[index].endereco + ". Telefone: " + pro[index].telefone
									+ "." };

					t_confirm = JOptionPane.showOptionDialog(null, confirm, "Deseja remover o produto abaixo?",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_Con, ops_Con[1]);

					if (t_confirm == 0) {
						pro[index].usu = "Usuario indisponível.";
						pro[index].nome = "Cliente indisponível.";
						pro[index].senha = "senha indisponivel";
						pro[index].endereco = "endereco indisponivel";
						pro[index].telefone = "Numero indisponivel";
						JOptionPane.showMessageDialog(null, "Produto removido com êxito!", "Operação concluída!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Error e) {
					JOptionPane.showMessageDialog(null, "ERR0R", "Número digitado é inválido!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERR0R", "Escolha um número e tente novamente!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return pro;
	}

	private static void gravarCliente(Clientes c, String path) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(path + c.id + ".txt");
		pw.println(c.id);
		pw.println(c.usu);
		pw.println(c.nome);
		pw.println(c.senha);
		pw.println(c.endereco);
		pw.println(c.telefone);
		pw.flush();
		pw.close();
	}

	private static boolean cadastrarCliente(int ids, String path) {
		Scanner leia = new Scanner(System.in);
		Clientes c = new Clientes();
		System.out.println("Cadastrando cliente - " + ids);
		System.out.println("Usu: ");
		c.usu = leia.nextLine();
		System.out.println("nome: ");
		c.nome = leia.nextLine();
		System.out.println("Senha: ");
		c.senha = leia.nextLine();
		System.out.println("Endereço: ");
		c.endereco = leia.nextLine();
		c.id = ids;
		try {
			gravarCliente(c, path);
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possivel gravar Cliente");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static Clientes lerCliente(int id, String path) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(path + id + ".txt"));
		Clientes c = new Clientes();
		c.id = Integer.parseInt(bf.readLine());
		c.usu = bf.readLine();
		c.nome = bf.readLine();
		c.senha = bf.readLine();
		c.endereco = bf.readLine();
		c.telefone = bf.readLine();
		bf.close();
		return c;
	}

	private static void listarCliente(String path) {
		File dir = new File(path);
		String[] arquivos = dir.list();
		for (int i = 0; i < arquivos.length; i++) {
			int id = Integer.parseInt(arquivos[i].substring(0, arquivos[i].length() - 4));
			try {
				Clientes c = lerCliente(id, path);
				System.out.println(c.id + " - " + c.usu + " - " + c.nome + " - " + c.senha + " - " + c.endereco + " - "
						+ c.telefone);
			} catch (IOException e) {
				System.out.println("Contato não encontrado");
				e.printStackTrace();
			}
		}
		if (arquivos.length == 0) {
			System.out.println("Não tem contato cadastrado");
		}
	}

	private static Clientes buscarCliente(String path) {
		Scanner leia = new Scanner(System.in);
		int id;
		Clientes c = new Clientes();
		System.out.println("Qual o id do Cliente?");
		id = leia.nextInt();
		try {
			c = lerCliente(id, path);

		} catch (IOException e) {
			return null;
		}

		return c;
	}

	private static void removerCliente(String path) {

		Scanner leia = new Scanner(System.in);
		int id;
		System.out.println("Qual o id do Cliente a ser removido?");
		id = leia.nextInt();
		File arquivo = new File(path + id + ".txt");
		if (!arquivo.exists()) {
			System.out.println("Cliente inexistente!");
		} else {
			if (arquivo.delete())
				System.out.println("Cliente removido");
			else
				System.out.println("Cliente não removido - erro");
		}

	}

	private static void resetarCompras(String path) {
		Scanner leia = new Scanner(System.in);
		System.out.println("Tem certeza que deseja apagar as compras (s/n)? ");
		char op = leia.next().charAt(0);
		if (op == 's') {
			File dir = new File(path);
			if (dir.exists()) {
				String[] arquivos = dir.list();
				for (int i = 0; i < arquivos.length; i++) {
					File arq = new File(path + arquivos[i]);
					if (arq.delete())
						System.out.println("Apagando compras " + arquivos[i].substring(0, arquivos[i].length() - 4));

				}
				dir.delete();
			}
			gravarId(0);
		}

	}

	private static void listarCompras(String path) {
		File dir = new File(path);
		String[] arquivos = dir.list();
		for (int i = 0; i < arquivos.length; i++) {
			int id = Integer.parseInt(arquivos[i].substring(0, arquivos[i].length() - 4));
			try {
				Compras c = lerCompras(id, path);
				System.out.println(c.id + " - " + c.idCliente + " - ");

				for (int j = 0; j < c.produtos.length; j++) {

					if (c.produtos[j] != null) {
						System.out.println("     Produtos " + (j + 1));
						System.out.println("           nome: " + c.produtos[j].qtd+ "\n           : "
						+ c.produtos[j].preco + "");
					}
				}
			} catch (IOException e) {
				System.out.println("Compra não encontrada");
				e.printStackTrace();
			}
		}
		if (arquivos.length == 0) {
			System.out.println("Não tem compra cadastrada");
		}

	}

	private static Compras buscarCompras(String path) {
		Scanner leia = new Scanner(System.in);
		int id;
		Compras c = new Compras();
		System.out.println("Qual o id da compra?");
		id = leia.nextInt();
		try {
			c = lerCompras(id, path);

		} catch (IOException e) {
			return null;
		}

		return c;
	}

	private static void removerCompras(String path) {

		Scanner leia = new Scanner(System.in);
		int id;
		Compras c = new Compras();
		System.out.println("Qual o id da compra a ser removido?");
		id = leia.nextInt();
		File arquivo = new File(path + id + ".txt");
		if (!arquivo.exists()) {
			System.out.println("Compra inexistente!");
		} else {
			if (arquivo.delete())
				System.out.println("Compra removido");
			else
				System.out.println("Compra não removido - erro");
		}

	}

	private static boolean cadastrarCompras(int id, String path) {
		Scanner leia = new Scanner(System.in);
		Compras c = new Compras();
		System.out.println("Cadastrando contato - " + id);
		System.out.println("Cliente: ");
		c.idCliente = Integer.parseInt(leia.nextLine());
		c.id = id;
		char op = 's';
		int i = 0;
		c.produtos = new Produtos[50];
		System.out.println("Cadastrando endereços: ");
		do {
			Produtos e1 = new Produtos();
			System.out.println(" Nome: ");
			e1.nome = leia.nextLine();
			System.out.println("Quantidade: ");
			e1.qtd = Integer.parseInt(leia.nextLine());
			leia = new Scanner(System.in);
			System.out.println(" Preço: ");
			e1.preco = Double.parseDouble(leia.nextLine());
			System.out.println("Deseja informar mais um produto? ");
			c.produtos[i] = e1;
			op = leia.next().charAt(0);
			leia = new Scanner(System.in);
			i++;
		} while (op != 'n');

		try {
			gravarCompras(c, path);
		} catch (IOException e) {
			System.out.println("Não foi possivel gravar o contato");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void menu() {
		System.out.println("-------------------------" + "\n1 - Cadastrar" + "\n2 - Buscar" + "\n3 - Listar"
				+ "\n4 - Remover" + "\n5 - Resetar" + "\n6 - Sair" + "\n-------------------------");

	}

	private static Compras lerCompras(int id, String path) throws IOException {
		Scanner leia = new Scanner(System.in);
		BufferedReader bf = new BufferedReader(new FileReader(path + id + ".txt"));
		Compras c = new Compras();
		c.id = Integer.parseInt(bf.readLine());
		c.idCliente = Integer.parseInt(leia.nextLine());
		String s = "";
		int n = 0;
		Produtos[] produtos = new Produtos[50];
		do {
			while (!s.equals("Endereço:")) {
				s = bf.readLine();
				if (s == null)
					break; // fim de arquivo
			}
			if (s == null)
				break;
			Produtos e = new Produtos();
			e.nome = bf.readLine();
			e.qtd = Integer.parseInt(bf.readLine());
			e.preco = Double.parseDouble(bf.readLine());
			// System.out.println(e.rua+" "+e.num+" "+e.bairro);
			produtos[n] = e;
			n++;
			s = "";
		} while (s != null);
		bf.close();
		c.produtos = produtos;
		return c;
	}

	private static int inicializar(String path) {
		int id = 0;
		File dir = new File(path);
		if (!dir.exists()) { // Verifica se o diretório contendo contatos ja existe
			dir.mkdir(); // cria se não existir
		}
		File arquivo = new File("id.txt");
		if (!arquivo.exists()) { // verifica se o arquivo de id já existe
			try {
				arquivo.createNewFile(); // cria se não existir
			} catch (IOException e) {
				System.out.println("Não foi possível criar o ID");
				e.printStackTrace();
			}
			gravarId(0); // grava o id=0 para inicializar a agenda
		} else { // caso o arquivo de id já exista,
			id = lerId(); // é feita a leitura
		}
		return id;
	}

	private static void gravarCompras(Compras c, String path) throws IOException {
		// PrintWriter pw = new PrintWriter(path+c.id+".txt");
		File arquivo = new File(path + c.id + ".txt");
		arquivo.createNewFile();

		BufferedWriter bw = new BufferedWriter(new FileWriter(path + c.id + ".txt", true));
		bw.append(c.id + "\n");
		bw.append(c.idCliente + "\n");
		for (int i = 0; i < c.produtos.length; i++) {
			Produtos e = c.produtos[i];
			if (e != null)
				try {
					bw.append("Produtos:\n");
					bw.append(e.nome + "\n");
					bw.append(e.qtd + "\n");
					bw.append(e.preco + "\n");
				} catch (IOException err) {
					err.printStackTrace();
				}
		}
		bw.flush();
		bw.close();
	}

	private static void gravarId(int id) {
		PrintWriter pw;
		try {
			pw = new PrintWriter("id.txt");
			pw.println(id);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int lerId() {
		BufferedReader bf;
		int id = 0;
		try {
			bf = new BufferedReader(new FileReader("id.txt"));
			id = Integer.parseInt(bf.readLine());
			bf.close();
		} catch (IOException | NumberFormatException e) {
			System.err.println("Id não encontrado");
			e.printStackTrace();
		}
		return id;
	}

}

/*
 * JOptionPane.showOptionDialog(null, "msg", "title",
 * JOptionPane.DEFAULT_OPTION, 3, null, array, array[1]);
 * 
 * JTextField = new JTextField; var = nome_JTextField.getText();
 * 
 * Object [] = {};
 * 
 * log [id][0][0][0] = nome; log [id][0][0][1] = senha; log [id][0][1][1] =
 * endereço; log [id][1][1][1] = telefone;
 * 
 * frutas [x][0] = "Nome: Fruta"; frutas [x][0] = "Preço: XX.XX";
 */
