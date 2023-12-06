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
import javax.swing.ImageIcon;

public class Frutadoce {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int id = 0, idx, op = 6;
        int[] ids = new int[3];
        String path = "compras/";

        Scanner leia = new Scanner(System.in);

        String[] pastas = {"clientes/", "produtos/", "compras/"};
        String[] arqIds = {"idclientes.txt", "idprodutos.txt", "idcompras.txt"};

        Compras c = new Compras();
        ids = inicializar(pastas, arqIds);

        do {
            menu_ini();
            op = leia.nextInt();
            switch (op) {
                case 1:
                    idx = loginCliente(pastas);
                    menus(path, idx, ids, pastas);
                    break;
                case 2:
                    if (cadastrarCliente(ids[0], pastas[0])) {
                        ids[0]++;
                        gravarId(ids[0], arqIds[0]);
                    }
                    id = inicializar(path);
                    break;
                case 3:
                    System.out.print("Saindo...");
                    break;
            }
        } while (op != 3);
    }

    private static void menu_ini() {
        System.out.println("""
                ------------FRUTARIA CELESTE-------------
                1 - Login
                2 - Cadastro
                3 - Sair
                ------------FRUTARIA CELESTE-------------""");

    }

    private static void menu_fun() {
        System.out.println("""
                -----------ADMIN--------------
                1 - Listar clientes
                2 - Buscar cliente
                3 - Remover cliente
                4 - Cadastrar produto
                5 - Listar produtos
                6 - Buscar produtos
                7 - Remover produtos
                8 - Listar compras
                9 - Remover compras
                10- Resetar compras
                11- Resetar produtos
                12- Resetar clientes
                13- Resetar todos
                14- Sair
                -----------ADMIN--------------""");
    }

    private static void menu_cli() {
        System.out.println("""
                ------------FRUTARIA CELESTE-------------
                1 - Adicionar ao carrinho
                2 - Remover pedido
                3 - Parceiria
                4 - Concluir
                ------------FRUTARIA CELESTE-------------""");
    }

    private static void menus(String path, int idx, int[] id, String[] pastas) throws IOException {
        Scanner leia = new Scanner(System.in);
        int op = -1;
        if (idx > 0) {
            do {
                menu_cli();
                op = leia.nextInt();
                switch (op) {
                    case 1:
                        cadastrarCompras(idx, pastas);
                        break;
                    case 2:
                        removerCompras(path, idx);
                        break;
                    case 3:
                        parceiria();
                        break;
                    case 4:
                        System.out.println("Enviando pedido...");
                        System.out.println("Pedido enviado!");
                }
            } while (op != 4);
        } else {
            do {
                menu_fun();
                op = leia.nextInt();
                switch (op) {
                    case 1:
                        listarCliente(pastas[0]);
                        break;
                    case 2:
                        buscarCliente(pastas[0]);
                        break;
                    case 3:
                        removerCliente(pastas[0]);
                        break;
                    case 4:
                        if(cadastrarProduto(id[1], pastas[1])) {
                        	id[1]++;
                        	gravarId(id[1],"idprodutos.txt");
                        }
                        break;
                    case 5:
                        listarProdutos(pastas[1]);
                        break;
                    case 6:
                    	buscarProdutos(pastas[1]);
                        break;
                    case 7:
                    	removerProdutos(pastas[1]);
                        break;
                    case 8:
                    	listarCompras(pastas[2]);
                        break;
                    case 9:
                        removerCompras(pastas[2], idx);
                        break;
                    case 10:
                        resetarCompras(pastas[2]);
                        break;
                    case 11:
                        resetarProdutos(pastas[1]);
                        break;
                    case 12:
                        resetarCliente(pastas[0]);
                        break;
                    case 13:
                        resetar(path);
                        break;
                    case 14:
                        System.out.print("Um ótimo dia, Admin!");
                        break;
                }
            } while (op != 13);
        }
    }

    private static void listarCompras(String string) {
		// TODO Auto-generated method stub
		
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
            gravarId(0);
        } else {
            id = lerId();
        }
        return id;
    }

    private static int[] inicializar(String[] path, String[] arqIds) {
        int[] id = new int[arqIds.length];
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
                    System.out.println("Não foi possível criar o ID...");
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

    private static void parceiria() {
        final ImageIcon icon = new ImageIcon("P_L.png");
        JOptionPane.showMessageDialog(null, "Melhores produtos vendidos com alta qualidade!"
                , "HIPERMERCADO DO SR.JÃO", JOptionPane.PLAIN_MESSAGE, icon);
    }

    private static int lerId() {
        BufferedReader bf;
        int id = 0;
        try {
            bf = new BufferedReader(new FileReader("id.txt"));
            id = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("ERROR, ID não encontrado!");
            e.printStackTrace();
        }
        return id;
    }

    private static int lerId(String arq) {
        BufferedReader bf;
        int id = 0;
        try {
            bf = new BufferedReader(new FileReader(arq));
            id = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("ID não encontrado");
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
            e.printStackTrace();
        }
    }

    private static void gravarId(int id) {
        PrintWriter pw;
        try {
            pw = new PrintWriter("id.txt");
            pw.println(id);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private static boolean cadastrarCliente(int ids, String path) {
        Scanner leia = new Scanner(System.in);
        Clientes c = new Clientes();
        System.out.println("Cadastrando cliente - " + ids);
        System.out.println("Usuário: ");
        c.usu = leia.nextLine();
        System.out.println("Senha: ");
        c.senha = leia.nextLine();
        System.out.println("Nome: ");
        c.nome = leia.nextLine();
        System.out.println("Endereço: ");
        c.endereco = leia.nextLine();
        System.out.println("Telefone: ");
        c.telefone = leia.nextLine();
        c.id = ids;
        try {
            gravarCliente(c, path);
        } catch (FileNotFoundException e) {
            System.out.println("Infelizmente não foi possível gravar os dados do cliente.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void gravarCliente(Clientes c, String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path + c.id + ".txt");
        pw.println(c.id);
        pw.println(c.usu);
        pw.println(c.senha);
        pw.println(c.nome);
        pw.println(c.endereco);
        pw.println(c.telefone);
        pw.flush();
        pw.close();

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
                System.out.println("Cliente não identificado.");
                e.printStackTrace();
            }
        }
        if (arquivos.length == 1) {
            System.out.println("Não existem contatos cadastrados.");
        }
    }

    private static int loginCliente(String[] pastas) throws IOException {
        int id;
        String user, senha, veri_n, veri_s;
        Scanner leia = new Scanner(System.in);
        System.out.println("Digite o usuário: ");
        user = leia.nextLine();
        System.out.println("Digite sua senha: ");
        senha = leia.nextLine();
        if (user.equals("admin") && senha.equals("admin")) {
            System.out.println("Seja bem vindo, Admin!");
            return -7;
        } else {
            try {
                System.out.println("Digite um ID: ");
                id = leia.nextInt();
                BufferedReader bf = new BufferedReader(new FileReader(pastas[0] + id + ".txt"));
                veri_n = bf.readLine();
                veri_n = bf.readLine();
                veri_s = bf.readLine();
                if (veri_n.equals(user) && veri_s.equals(senha)) {
                    System.out.println("Seja bem vindo, " + user + "!");
                    return id;
                } else {
                    System.out.println("Dados de login incorretos!");
                    return -1;
                }
            } catch (Error | Exception e) {
                System.out.println("Tente novamente com valores válidos!");
                return -1;
            }
        }
    }

    private static Clientes buscarCliente(String path) {
        Scanner leia = new Scanner(System.in);
        int id;
        Clientes c = new Clientes();
        System.out.println("Por favor, digite o ID do cliente: ");
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
        System.out.println("Qual é o ID do cliente a ser removido? ");
        id = leia.nextInt();
        File arquivo = new File(path + id + ".txt");
        if (!arquivo.exists()) {
            System.out.println("Cliente inexistente!");
        } else {
            if (arquivo.delete())
                System.out.println("Cliente removido.");
            else
                System.out.println("Cliente não removido - ERR0R");
        }
    }

    private static void resetarCliente(String path) {
        path = "clientes/";
        Scanner leia = new Scanner(System.in);
        System.out.println("Tem certeza que deseja apagar os clientes (s/n)? ");
        char op = leia.next().charAt(0);
        if (op == 's') {
            File dir = new File(path);
            if (dir.exists()) {
                String[] arquivos = dir.list();
                for (int i = 0; i < arquivos.length; i++) {
                    File arq = new File(path + arquivos[i]);
                    if (arq.delete())
                        System.out.println("Apagando clientes..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                }
                dir.delete();
            }
            gravarId(0);
        }
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
        System.out.println(" Nome: ");
        c.nome = leia.nextLine();
        System.out.println(" Preço: ");
        c.preco = Double.parseDouble(leia.nextLine());
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
                System.out.println(c.id + " - " + c.nome + " - " + c.preco);
            } catch (IOException e) {
                System.out.println("Produto não encontrado...");
                e.printStackTrace();
            }
        }
        if (arquivos.length == 0) {
            System.out.println("Não existem produtos cadastrados...");
        }
    }

    private static Produtos buscarProdutos(String path) {
        Scanner leia = new Scanner(System.in);
        int id;
        Produtos c = new Produtos();
        System.out.println("Por favor, digite o ID do produto: ");
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
        System.out.println("Qual o ID do produto a ser removido?");
        id = leia.nextInt();
        File arquivo = new File(path + id + ".txt");
        if (!arquivo.exists()) {
            System.out.println("Produto inexistente!");
        } else {
            if (arquivo.delete())
                System.out.println("Produto removido.");
            else
                System.out.println("Produto não removido - ERR0R");
        }
    }

    private static void resetarProdutos(String path) {
        path = "produtos/";
        Scanner leia = new Scanner(System.in);
        System.out.println("Tem certeza que deseja apagar os produtos (s/n)? ");
        char op = leia.next().charAt(0);
        if (op == 's') {
            File dir = new File(path);
            if (dir.exists()) {
                String[] arquivos = dir.list();
                for (int i = 0; i < arquivos.length; i++) {
                    File arq = new File(path + arquivos[i]);
                    if (arq.delete())
                        System.out.println("Apagando produtos..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                }
                dir.delete();
            }
            gravarId(0);
        }
    }

    private static boolean cadastrarCompras(int id, String[] path)  {
        Scanner leia = new Scanner(System.in);
        Compras c = new Compras();
        c.idCliente = id;
        c.id = id;
        char op = 's';
        int i = 0;
        c.produtos = new Produtos[50];
        c.preco = 0;
        do {
            Produtos e1 = new Produtos();
            e1=buscarProdutos(path[1]);
            System.out.println("Quantidade: ");
            e1.qtd = Integer.parseInt(leia.nextLine());
            leia = new Scanner(System.in);
            c.preco += e1.preco;
            System.out.println("Deseja informar mais um produto? ");
            c.produtos[i] = e1;
            op = leia.next().charAt(0);
            leia = new Scanner(System.in);
            i++;
        } while (op != 'n');

        try {
            gravarCompras(c, path[2]);
        } catch (IOException e) {
            System.out.println("Não foi possivel gravar o pedido...");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void gravarCompras(Compras c, String path) throws IOException {
        File arquivo = new File(path + c.idCliente + ".txt");
        arquivo.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(path + c.id + ".txt", true));
        bw.append(c.id + "\n");
        bw.append(c.idCliente + "\n");
        for (int i = 0; i < c.produtos.length; i++) {
            Produtos e = c.produtos[i];
            if (e != null)
                try {
                    bw.append("Produtos:\n");
                    bw.append(e.id+"\n");
                    bw.append(e.nome + "\n");
                    bw.append(e.qtd + "\n");
                    bw.append(e.preco + "\n");
                } catch (IOException err) {
                    err.printStackTrace();
                }
        }
        bw.append(String.valueOf(c.preco));
        bw.flush();
        bw.close();
    }

    private static void removerCompras(String path, int idx) {
        Scanner leia = new Scanner(System.in);
        if (idx>=0){
            File arquivo = new File(path + idx + ".txt");
            if (!arquivo.exists()) {
                System.out.println("Compra inexistente!");
            } else {
                if (arquivo.delete())
                    System.out.println("Compra removida!");
                else
                    System.out.println("Compra não removida - ERR0R!!!");
            }
        }
        else {
            Compras c = new Compras();
            System.out.println("Qual é o ID da compra a ser removida? ");
            int id = leia.nextInt();
            File arquivo = new File(path + id + ".txt");
            if (!arquivo.exists()) {
                System.out.println("Compra inexistente!");
            } else {
                if (arquivo.delete())
                    System.out.println("Compra removida!");
                else
                    System.out.println("Compra não removida - ERR0R!!!");
            }
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
                        System.out.println("Apagando compras..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                }
                dir.delete();
            }
            gravarId(0);
        }
    }

    private static void resetar(String path) {
        Scanner leia = new Scanner(System.in);
        System.out.println("Tem certeza que deseja apagar tudo (s/n)? ");
        char op = leia.next().charAt(0);
        if (op == 's') {
            File dir = new File(path);
    
            if (dir.exists()) {
                String[] arquivos = dir.list();
                for (int i = 0; i < arquivos.length; i++) {
                    File arq = new File(path + arquivos[i]);
                    if (arq.delete())
                        System.out.println("Apagando clientes..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                }
                dir.delete();
            }
            path = "compras/";
            dir = new File(path);
            if (dir.exists()) {
                String[] arquivos = dir.list();
                for (int i = 0; i < arquivos.length; i++) {
                    File arq = new File(path + arquivos[i]);
                    if (arq.delete())
                        System.out.println("Apagando clientes..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                }
                dir.delete();
            }
            path = "clientes/";
            dir = new File(path);
            if (dir.exists()) {
                String[] arquivos = dir.list();
                for (int i = 0; i < arquivos.length; i++) {
                    File arq = new File(path + arquivos[i]);
                    if (arq.delete())
                        System.out.println("Apagando clientes..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                }
                dir.delete();
            }
            gravarId(0);
        }
    }
    private static Compras lerCompras(int id, String path) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(path+id+".txt"));
		Compras c = new Compras();
		c.id = Integer.parseInt(bf.readLine());
		c.idCliente = Integer.parseInt(bf.readLine());
		String s="";
		int n=0;
		Produtos [] produtos = new Produtos[100];
		do {
			while(!s.equals("Produtos:")) {
				s=bf.readLine();
				if(s==null) break; //fim de arquivo
			}
			if(s== null) break;
			Produtos e = new Produtos();
			e.id =Integer.parseInt(bf.readLine()); 
			e.nome = bf.readLine();
			e.preco = Double.parseDouble(bf.readLine());
			//System.out.println(e.rua+" "+e.num+" "+e.bairro);
			produtos[n] = e;
			n++;
			s="";
		}while(s!=null);
		bf.close();
		c.produtos = produtos;
		return c;
	}

}
