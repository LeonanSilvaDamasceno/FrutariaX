package Frutaria;

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


public class frutariaCeleste {

    public static void main(String[] args)  {
        int id, op; //'id' é um identificador comum podendo ser visto no 'login', 'op' é o mesmo que opção.
        int[] ids; //Varoável responsável pelo armazenamento de indexes para clientes, produtos e compras

        Scanner leia = new Scanner(System.in);

        String[] pastas = {"clientes/", "produtos/", "compras/"};
        String[] arqIds = {"idclientes.txt", "idprodutos.txt", "idcompras.txt"};

        ids = inicializar(pastas, arqIds);

        do {
            menu_ini();
            op = leia.nextInt();
            switch (op) {
                case 1:
                    id = loginCliente(pastas);
                    menus(id, ids, pastas);
                    break;
                case 2:
                    if (cadastrarCliente(ids[0], pastas[0])) {
                        ids[0]++;
                        gravarId(ids[0], arqIds[0]);
                    }
                    break;
                case 3:
                    System.out.print("Saindo...");
                    break;
            }
        } while (op != 3);
    }

    private static void menu_ini() { //Visualização da tela inicial
        System.out.println("""
                ------------FRUTARIA CELESTE-------------
                1 - Login
                2 - Cadastro
                3 - Sair
                ------------FRUTARIA CELESTE-------------""");

    } //Menu inicial

    private static void menu_fun() { //Visualização de opções dos funcionários
        System.out.println("""
                -----------ADMIN--------------
                
                ==========CLIENTES============
                1 - Listar clientes
                2 - Buscar cliente
                3 - Remover cliente
                
                ==========PRODUTOS============
                4 - Cadastrar produto
                5 - Listar produtos
                6 - Listar compras
                7 - Buscar produtos
                
                ==========COMPRAS=============
                8 - Listar compras
                9 - Buscar compras
                10- Remover compras
                
                ==========RESETAR=============
                11- Resetar clientes
                12- Resetar produtos
                13- Resetar compras
                14- Resetar todos
                15- Sair
                -----------ADMIN--------------""");
    } //Menu para funcionários

    private static void menu_cli() {
        System.out.println("""
                ------------FRUTARIA CELESTE-------------
                1 - Adicionar ao carrinho
                2 - Verificar pedidos
                3 - Remover pedido
                4 - Parceiria
                5 - Concluir
                ------------FRUTARIA CELESTE-------------""");
    } //Menu para clientela

    private static void menus(int idx, int [] ids, String[] pastas)  {
        Scanner leia = new Scanner(System.in);
        int op;
        if (idx >= 0) { //Opções para clientela demarcada por "ids" iguais ou maiores que "0".
            do {
                menu_cli();
                op = leia.nextInt();
                switch (op) {
                    case 1:
                        cadastrarCompras(idx, pastas[2]);
                        break;
                    case 2:
                        buscarCarrinho(idx, pastas[2]);
                        break;
                    case 3:
                        removerCompras(pastas[2], idx);
                        break;
                    case 4:
                        parceria();
                        break;
                    case 5:
                        System.out.println("Enviando pedido...");
                        System.out.println("Pedido enviado!");
                        break;
                }
            } while (op != 5);
        } else {
            if (idx == -7) { //Denota opções de ADMIN usando um valor obtido no método de login
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
                            ids[1] += 1;
                            cadastrarProduto(ids[1], pastas[1]);
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
                            buscarCompras(pastas[2]);
                            break;
                        case 10:
                            removerCompras(pastas[2], idx);
                            break;
                        case 11:
                            resetarCliente(pastas[0]);
                            break;
                        case 12:
                            resetarProdutos(pastas[1]);
                            break;
                        case 13:
                            resetarCompras(pastas[2]);
                            break;
                        case 14:
                            resetar(pastas);
                            break;
                        case 15:
                            System.out.println("Um ótimo dia, Admin!");
                            break;
                    }
                } while (op != 15);
            }
        }
    } //Opções relativas a ementas elencadas atrás tanto para funcionário quanto para cliente

    private static int[] inicializar(String[] path, String[] arqIds) { //Cria diretórios e manuseia indexes
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
                gravarId(0, arqIds[i]); // grava o 'id' 0 para inicializar frutaria
                id[i] = 0;
            } else { // caso o arquivo de id já exista,
                id[i] = lerId(arqIds[i]); // é feita a leitura
            }
        }
        return id;
    }

    private static void parceria() {
        final ImageIcon icon = new ImageIcon("P_L.png");
        JOptionPane.showMessageDialog(null, "Melhores produtos vendidos com alta qualidade!"
                , "HIPERMERCADO DO SR.JÃO", JOptionPane.PLAIN_MESSAGE, icon);
    } //Hipermercado do Sr.Jão é uma parceiria trazida pelo nosso companheiro Luiz Henrique!


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
    } //Usado em outros métodos para gravação e listagem de elementos

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
    } //Responsável por atualizar indexes em cada arquivo de texto criado em "inicializar"


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

    private static void gravarCliente(Clientes c, String path) throws FileNotFoundException { //Salva em arquivo de nome igual ao 'id' do cliente os seus dados.
        PrintWriter pw = new PrintWriter(path + c.id + ".txt");
        pw.println(c.id);
        pw.println(c.usu);
        pw.println(c.senha);
        pw.println(c.nome);
        pw.println(c.endereco);
        pw.println(c.telefone);
        pw.flush();
        pw.close();

    } //Grava o cliente em um arquivo

    private static Clientes lerCliente(int id, String path) throws IOException {
        try {
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
        } catch (Error | Exception e) {
            System.out.println();
            return null;
        }
    } //Usado para fazer operações de listagem e busca de clientes

    private static void listarCliente(String path) {
        File dir = new File(path);
        String[] arquivos = dir.list(); //Reune todos os arquivos do diretório informado a "dir."
        try {
            for (int i = 0; i < arquivos.length; i++) { //Percorre todas as posições existentes até o último caso de index registrado
                int id = Integer.parseInt(arquivos[i].substring(0, arquivos[i].length() - 4));
                Clientes c = lerCliente(id, path);
                if (c != null) {
                    System.out.println(c.id + " - " + c.usu + " - " + c.nome + " - " + c.senha + " - " + c.endereco + " - "
                            + c.telefone);
                }
            }
        } catch (IOException | NullPointerException e) {
                System.out.println("Cliente não identificado.");
                e.printStackTrace();
        }
        if (arquivos.length == 1) {
            System.out.println("Não existem contatos cadastrados.");
        }
    }

    private static int loginCliente(String[] pastas) {
        int id;
        String user, senha, veri_n, veri_s;
        Scanner leia = new Scanner(System.in);
        System.out.println("Digite o usuário: ");
        user = leia.nextLine();
        System.out.println("Digite sua senha: ");
        senha = leia.nextLine();
        if (user.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
            System.out.println("Seja bem vinda(o), Admin!");
            return -7;
        } else {
            try {
                System.out.println("Digite um ID: ");
                id = leia.nextInt();
                BufferedReader bf = new BufferedReader(new FileReader(pastas[0] + id + ".txt"));
                veri_n = bf.readLine(); //Usado para ignorar a primeira linha e partir para o armazenamento de dados da segunda
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

    private static void buscarCliente(String path) {
        Scanner leia = new Scanner(System.in);
        int id;
        Clientes c;
        System.out.println("Por favor, digite o ID da(o) cliente: ");
        id = leia.nextInt();
        try {
            c = lerCliente(id, path);
            if (c != null) {
                System.out.println(c.id + " - " + c.usu + " - " + c.nome + " - " + c.senha + " - " + c.endereco + " - " + c.telefone);
            }
        } catch (IOException e) {
            System.out.println();
        }
    } //Busca por um cliente específico

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
    } //Remove um cliente específico

    private static void resetarCliente(String path) {
        Scanner leia = new Scanner(System.in);
        System.out.println("Tem certeza que deseja apagar os clientes (s/n)? ");
        char op = leia.next().charAt(0);
        if (op == 's') {
            File dir = new File(path);
            if (dir.exists()) {
                String[] arquivos = dir.list();
                try {
                    for (int i = 0; i < arquivos.length; i++) {
                        File arq = new File(path + arquivos[i]);
                        if (arq.delete())
                            System.out.println("Apagando clientes..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                    }
                    dir.delete();
                } catch (NullPointerException e){
                    System.out.println("ERR0R na exclusão de pastas");
                }
            }
            gravarId(0, "idclientes.txt");
        }
    }


    private static void gravarProduto(Produtos c, String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path + c.id + ".txt");
        pw.println(c.id);
        pw.println(c.nome);
        pw.println(c.preco);
        pw.flush();
        pw.close();
    }

    private static void cadastrarProduto(int id, String path) {
        Scanner leia = new Scanner(System.in);
        Produtos c = new Produtos();
        System.out.println("Cadastrando produtos - " + id);
        System.out.println("Nome: ");
        c.nome = leia.nextLine();
        System.out.println("Preço: ");
        c.preco = Double.parseDouble(leia.nextLine());
        c.id = id;
        try {
            gravarProduto(c, path);
            gravarId(c.id, "idprodutos.txt");
            System.out.println("Produto cadastrado com êxito!");
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possivel gravar o produto");
            e.printStackTrace();
        }
    }

    private static Produtos lerProduto(int id, String path) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(path + id + ".txt"));
        Produtos c = new Produtos();
        c.id = Integer.parseInt(bf.readLine());
        c.nome = bf.readLine();
        c.preco = Double.parseDouble(bf.readLine());
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

    private static void buscarProdutos(String path) {
        Scanner leia = new Scanner(System.in);
        int id;
        Produtos c;
        System.out.println("Por favor, digite o ID do produto: ");
        id = leia.nextInt();
        try {
            c = lerProduto(id, path);
            if (c != null) {
                System.out.println(c.id + " - " + c.nome + " - " + c.preco);
            }
        } catch (IOException e) {
            System.out.print("ERR0R" + e);
        }
    }

    private static void removerProdutos(String path) { //boolean removed
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
        Scanner leia = new Scanner(System.in);
        System.out.println("Tem certeza que deseja apagar os produtos (s/n)? ");
        char op = leia.next().charAt(0);
        if (op == 's') {
            File dir = new File(path);
            try {
                if (dir.exists()) {
                    String[] arquivos = dir.list();
                    for (int i = 0; i < arquivos.length; i++) {
                        File arq = new File(path + arquivos[i]);
                        if (arq.delete())
                            System.out.println("Apagando produtos..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                    }
                    dir.delete();
                }
            } catch (NullPointerException e) {
                System.out.println("ERR0R " + e);
            }
            gravarId(0, "idprodutos.txt");
        }
    }


    private static void cadastrarCompras(int id, String path)  { //void
        Scanner leia = new Scanner(System.in);
        int idx, qtd;
        Compras c = new Compras();
        c.idCliente = id;
        c.id = id;
        char op = 's';
        int i = 0;
        c.produtos = new Produtos[50];
        c.preco = 0;
        do {
            listarProdutos("produtos/");
            try {
                System.out.println("Digite o ID do produto: ");
                idx = leia.nextInt();
                BufferedReader bf = new BufferedReader(new FileReader("produtos/" + idx + ".txt"));
                System.out.println("Digite a quantidade que deseja comprar: ");
                qtd = Integer.parseInt(bf.readLine());
                qtd = leia.nextInt();
                Produtos e1 = new Produtos();
                e1.nome = bf.readLine();
                e1.qtd = qtd;
                e1.preco = Double.parseDouble(bf.readLine());
                if (qtd > 0) {
                    c.preco += e1.preco * qtd;
                }
                System.out.println("Deseja informar mais um produto? ");
                c.produtos[i] = e1;
                op = leia.next().charAt(0);
                leia = new Scanner(System.in);
                i++;
            } catch (Error | Exception e) {
                System.out.println("Tente novamente com valores válidos!");
            }
        } while (op != 'n');
        try {
            gravarCompras(c, path);
        } catch (IOException e) {
            System.out.println("Não foi possivel gravar o pedido...");
            e.printStackTrace();
        }
    }

    private static void gravarCompras(Compras c, String path) throws IOException {
        File arquivo = new File(path + c.idCliente + ".txt");
        arquivo.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(path + c.id + ".txt", true));
        bw.append("------------FRUTARIA CELESTE-------------\n");
        bw.append(c.idCliente + "\n");
        for (int i = 0; i < c.produtos.length; i++) {
            Produtos e = c.produtos[i];
            if (e != null)
                try {
                    bw.append("Nome produto: " + e.nome + "\n");
                    bw.append("Quantidade: " + e.qtd + "\n");
                    bw.append("Preço: " + e.preco + "\n\n");
                } catch (IOException err) {
                    err.printStackTrace();
                }
        }
        bw.append("Preço total: R$" + c.preco + ".\n------------FRUTARIA CELESTE-------------\n\n");
        bw.flush();
        bw.close();
    }

    private static void buscarCarrinho(int id, String path) {
        try {
            File local = new File(path + id + ".txt");
            BufferedReader list = new BufferedReader (new FileReader(local));
            String s = list.readLine();
            if (local.exists()) {
                while (!s.equals(null)) {
                    System.out.println(s);
                    s = list.readLine();
                }
            } else {
                System.out.println("Compra não disponível tente novamente após atualizar o sistema ou criar um registro de compra!");
            }
        } catch (Error | Exception e) {
            System.out.println();
        }
    }

    private static void buscarCompras(String path) {
        Scanner leia =  new Scanner(System.in);
        System.out.println("Digite o ID da(o) cliente: ");
        int id = leia.nextInt();
        try {
            File local = new File(path + id + ".txt");
            BufferedReader list = new BufferedReader (new FileReader(local));
            String s = list.readLine();
            if (local.exists()) {
                while (!s.equals("")) {
                    System.out.println(s);
                    s = list.readLine();
                }
            } else {
                System.out.println("Compra não disponível tente novamente após atualizar o sistema ou criar um registro de compra!");
            }
        } catch (Error | Exception e) {
            System.out.println();
        }
    }

    private static void listarCompras(String path) {
        File dir = new File(path);
        String[] arquivos = dir.list();
        for (int i = 0; i < arquivos.length; i++) {
            int id = Integer.parseInt(arquivos[i].substring(0, arquivos[i].length() - 4));
            try {
                File local = new File(path + id + ".txt");
                BufferedReader list = new BufferedReader(new FileReader(local));
                String s = list.readLine();
                if (local.exists()) {
                    while (!s.equals("")) {
                        System.out.println(s);
                        s = list.readLine();
                    }
                } else {
                    System.out.println("Compra não disponível tente novamente após atualizar o sistema ou criar um registro de compra!");
                }
            } catch (Error | Exception e) {
                System.out.println();
            }
            if (arquivos.length == 0) {
                System.out.println("Não existem produtos cadastrados...");
            }
        }
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
                try {
                    for (int i = 0; i < arquivos.length; i++) {
                        File arq = new File(path + arquivos[i]);
                        if (arq.delete())
                            System.out.println("Apagando compras..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                    }
                    dir.delete();
                } catch (NullPointerException e) {
                    System.out.println("ERR0R " + e);
                }
            }
            gravarId(0, "idcompras.txt");
        }
    }


    private static void resetar(String [] pastas) {
        Scanner leia = new Scanner(System.in);
        int num;
        System.out.println("Tem certeza que deseja apagar tudo (s/n)? ");
        char op = leia.next().charAt(0);
        if (op == 83 || op == 115){ //Valores "S" e "s" em Ascii
        for (num = 0; num < 3; num++) {
            File dir = new File(pastas[num]);
            try {
                if (dir.exists()) {
                    String[] arquivos = dir.list();
                    for (int i = 0; i < arquivos.length; i++) {
                        File arq = new File(pastas[num] + arquivos[i]);
                        if (arq.delete())
                            System.out.println("Apagando..." + arquivos[i].substring(0, arquivos[i].length() - 4));
                    }
                    dir.delete();
                }
            } catch (NullPointerException e) {
                System.out.println("ERR0R " + e);
            }
        }
        gravarId(0, "id.txt");
        gravarId(0,"idclientes.txt");
        gravarId(0,"idcompras.txt");
        gravarId(0,"idprodutos.txt");
        }
    }
}
