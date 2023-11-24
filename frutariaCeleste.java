import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class frutariaCeleste {
    public static void main(String[] args) {

        int t_ini = -7, t_log, t_cad, t_f = -7, t_e = -7, t_c = -7, t_car = -7, t_v = -7, t_cin = -7, id = 0, id_counter, id_cont = 0;

        //Ala de butões
        String[] ini   = {"Login", "Cadastro", "Sair"};
        String[] ops_G = {"Confirmar", "Cancelar"};
        String[] ops_F = {"Verificar estoque", "Lucros", "Verificar usuários", "Log-out"};
        String[] est_F = {"Adicionar alimentos", "Remover alimentos", "Concluir"};
        String[] ver_F = {"Próximo", "Anterior", "Pesquisar por ID", "Sair"};
        String[] ops_C = {"Comprar produtos", "Promoções da vez", "Parceiro", "Log-out"};
        String[] fru   = {"Adicionar", "Próxima página", "Carrinho", "Finalizar compra"};
        String[] car   = {"Remover produtos", "Ok"};

        //Ala de armazenamento de valores
        Produtos[] pro = new Produtos[50];
        String[][][] orders = new String[6897][1][1];
        String[][][][][] log = new String[6897][2][2][2][2];

        //Ala de criação campos de texto
        JTextField usu = new JTextField();
        JTextField sen = new JTextField();
        JTextField idx = new JTextField();
        JTextField end = new JTextField();
        JTextField tel = new JTextField();
        JTextField vid = new JTextField();

        pro = produtos(pro);

        Object[] o_log = {"Identificador:", idx, "Usuário: ", usu, "Senha: ", sen,}; //Ala de agrupamento entre mensagens e campos de texto
        Object[] o_cad = {"Nome: ", usu, "Senha: ", sen, "Endereço: ", end, "Telefone: ", tel};
        Object[] v_ids = {"Digite o id logo a baixo:", vid};
        Object[] c_pro = l_1(pro);

        while (t_ini != 2) { //Enquanto opção "Sair" não for selecionada na tela inicial
            t_ini = JOptionPane.showOptionDialog(null, "Seja bem vinda(o) a frutaria Celeste onde a qualidade vem em variedade!",
                    "Frutaria Celeste: Tela inicial", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ini, ini[2]);
            if (t_ini == 1) { //Opção de cadastro
                t_cad = JOptionPane.showOptionDialog(null, o_cad, "Tela de Cadastro", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, ops_G, ops_G[1]);

                if (t_cad == 0) { //Opção concluir de cadastro
                    if (id <= 6897 && !usu.getText().equals("") && !sen.getText().equals("") && !end.getText().equals("") && !tel.getText().equals("")) {
                        log[id][0][0][0][0] = usu.getText();
                        log[id][0][0][0][1] = sen.getText();
                        log[id][0][0][1][1] = end.getText();
                        log[id][0][1][1][1] = tel.getText();
                        log[id][1][1][1][1] = "Não";
                        JOptionPane.showMessageDialog(null, "Seja bem vinda(o), " + log[0][0][0] +
                                        "!\nSeu número de identificador usado para o login é o n° \"" + (id) + "\".", "Cadastro concluído!",
                                JOptionPane.INFORMATION_MESSAGE);
                        id++;
                    }
                    if (usu.getText().equals("") || sen.getText().equals("") || end.getText().equals("") || tel.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor, tente novamente " + "com todos os campos preenchidos",
                                "ERR0R, campos vazios foram detectados...", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (t_ini == 0) { //Opção 'Login'
                t_log = JOptionPane.showOptionDialog(null, o_log, "Tela de Login", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, ops_G, ops_G[1]);
                if (t_log == 0) { //Se opção "Confirmar" de 'Login'
                    try {
                        if (usu.getText().equals(log[Integer.parseInt(idx.getText())][0][0][0][0]) //Verificação de clientela
                                && sen.getText().equals(log[Integer.parseInt(idx.getText())][0][0][0][1])) {

                            while (t_cin != 3) { //Enquanto diferente de "Log-Out"
                                t_cin = JOptionPane.showOptionDialog(null, "Bem vindo Sr(a), " + usu.getText() +
                                                "como podemos lhe ajudar hoje?", "Frutaria Celeste",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_C, ops_C[3]);
                                if (t_cin == 0) { //Opção "Comprar produtos"
                                    while (t_c != 3) {
                                        t_c = JOptionPane.showOptionDialog(null, c_pro, "Produtos",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, fru, fru[2]);
                                        while (t_car != 2) {
                                            t_car = JOptionPane.showOptionDialog(null, c_pro, "Produtos",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, c_pro, c_pro[2]);
                                        }
                                    }
                                }
                                if (t_cin == 1) { //Opção "Promoções da vez"

                                }
                                if (t_cin == 2) { //Opção "Parceiros"

                                }
                            }
                        } else {
                            if (idx.getText().equalsIgnoreCase("admin")) {
                            } else {
                                JOptionPane.showOptionDialog(null, "Dados incorretos, tente novamente!\n",
                                        "ERR0R", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, ops_G, ops_G[1]);
                            }
                        }
                    } catch (NumberFormatException e) { //Para situações onde não são colocadas nenhuma informação na tela de Login
                        if (idx.getText().equalsIgnoreCase("admin")) {
                        } else {
                            JOptionPane.showOptionDialog(null, "Dados incorretos, tente novamente!\n",
                                    "ERR0R", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, ops_G, ops_G[1]);
                        }
                    }

                    if ((usu.getText().equalsIgnoreCase("Laila") && sen.getText().equalsIgnoreCase("admin")) //Logins de funcionários
                            || (usu.getText().equalsIgnoreCase("TheLegend27") && sen.getText().equalsIgnoreCase("admin"))) {
                        while (t_f != 3) { //Enquanto diferente de 'Log-out'
                            t_f = JOptionPane.showOptionDialog(null, "Um ótimo dia de trabalho, Sr(a) " + usu.getText() + "!",
                                    "Opções de administração", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_F, ops_F[3]);
                            if (t_f == 0) {//Se opção "Verificar de estoque"

                            }
                            if (t_f == 1) { //Se opção "Lucros"

                            }
                            if (t_f == 2) { //Se opção "Verificar 'usuários'"
                                while (t_v != 3) {
                                    try {
                                        t_v = JOptionPane.showOptionDialog(null, "ID " + id_cont + ":\n" +
                                                        "Usuário: " + log[id_cont][0][0][0][0] + "\nEndereço: " + log[id_cont][0][0][1][1]
                                                        + "\nTelefone: " + log[id_cont][0][1][1][1] + "\nSolicitou entrega: " + log[id_cont][1][1][1][1], "Listagem de usuários",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ver_F, ver_F[3]);
                                        if (t_v == 0) //Opção "Próximo"
                                            id_cont++;
                                        if (t_v == 1) //Opção "Anterior"
                                            id_cont--;
                                        if (t_v == 2) { //Opção "Escolher id"
                                            JOptionPane.showOptionDialog(null, v_ids, "Verificação por ID",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ops_G, ops_G[1]);
                                            id_cont = Integer.parseInt(vid.getText());
                                        }
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        JOptionPane.showOptionDialog(null, "Não há um usuário com este ID!\n",
                                                "ERR0R", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, ops_G, ops_G[1]);
                                        id_cont = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static Produtos[] produtos (Produtos [] pro){ //Listagem do nome do produto, quantidade e valor sequencialmente.
        pro[0].nome  = "Banana";
        pro[0].qtd   = 30;
        pro[0].preco = 1.90;

        pro[1].nome  = "Uva";
        pro[1].qtd   = 30;
        pro[1].preco = 10.99;

        pro[2].nome  = "Morango";
        pro[2].qtd   = 30;
        pro[2].preco = 13.99;

        pro[3].nome  = "Limão";
        pro[3].qtd   = 60;
        pro[3].preco = 6.50;

        pro[4].nome  = "Mamão";
        pro[4].qtd   = 28;
        pro[4].preco = 5.99;

        pro[5].nome  = "Maça";
        pro[5].qtd   = 40;
        pro[5].preco = 7.50;

        pro[6].nome  = "Melancia";
        pro[6].qtd   = 28;
        pro[6].preco = 14.99;

        pro[7].nome  = "Laranja";
        pro[7].qtd   = 30;
        pro[7].preco = 8.25;

        pro[8].nome  = "Melão";
        pro[8].qtd   = 30;
        pro[8].preco = 6.00;

        pro[9].nome  = "Abacaxi";
        pro[9].qtd   = 30;
        pro[9].preco = 5.00;

        pro[10].nome  = "Coco";
        pro[10].qtd   = 30;
        pro[10].preco = 10.99;

        pro[11].nome  = "Pêra";
        pro[11].qtd   = 30;
        pro[11].preco = 15.99;

        pro[12].nome  = "Maracuja";
        pro[12].qtd   = 30;
        pro[12].preco = 12.99;

        pro[13].nome   = "Tangerina";
        pro[13].qtd   = 30;
        pro[13].preco = 8.69;

        pro[14].nome  = "Batata";
        pro[14].qtd   = 30;
        pro[14].preco = 2.80;

        pro[15].nome  = "Cebola";
        pro[15].qtd   = 30;
        pro[15].preco = 4.50;

        pro[16].nome  = "Cenoura";
        pro[16].qtd   = 30;
        pro[16].preco = 3.50;

        pro[17].nome  = "Tomate";
        pro[17].qtd   = 30;
        pro[17].preco = 4.50;

        pro[18].nome  = "Alface";
        pro[18].qtd   = 30;
        pro[18].preco = 6.50;

        pro[19].nome  = "Brócolis";
        pro[19].qtd   = 30;
        pro[19].preco = 6.50;

        pro[20].nome  = "Cóuve-flor";
        pro[20].qtd   = 30;
        pro[20].preco = 5.00;

        pro[21].nome  = "Repolho";
        pro[21].qtd   = 30;
        pro[21].preco = 3.00;

        pro[22].nome  = "Caju";
        pro[22].qtd   = 30;
        pro[22].preco = 4.25;

        pro[23].nome  = "Abacate";
        pro[23].qtd   = 30;
        pro[23].preco = 8.00;

        pro[24].nome  = "Goiaba";
        pro[24].qtd   = 30;
        pro[24].preco = 3.99;

        pro[25].nome  = "Kiwi";
        pro[25].qtd   = 30;
        pro[25].preco = 5.00;

        pro[26].nome  = "Pessego";
        pro[26].qtd   = 30;
        pro[26].preco = 5.00;

        pro[27].nome  = "Baobá";
        pro[27].qtd   = 30;
        pro[27].preco = 10.99;

        return pro;
    }

    public static Object [] l_1 (Produtos [] pro){
        pro = produtos(pro);
        Object [] one = {
                "1.  Nome: " + pro[0].nome + ". Estoque: " + pro[0].qtd + ". Preço: R$" + pro[0].preco + ".", "",
                "2.  Nome: " + pro[1].nome + ". Estoque: " + pro[1].qtd + ". Preço: R$" + pro[1].preco + ".", "",
                "3.  Nome: " + pro[2].nome + ". Estoque: " + pro[2].qtd + ". Preço: R$" + pro[2].preco + ".", "",
                "4.  Nome: " + pro[3].nome + ". Estoque: " + pro[3].qtd + ". Preço: R$" + pro[3].preco + ".", "",
                "5.  Nome: " + pro[4].nome + ". Estoque: " + pro[4].qtd + ". Preço: R$" + pro[4].preco + ".", "",
                "6.  Nome: " + pro[5].nome + ". Estoque: " + pro[5].qtd + ". Preço: R$" + pro[5].preco + ".", "",
                "7.  Nome: " + pro[6].nome + ". Estoque: " + pro[6].qtd + ". Preço: R$" + pro[6].preco + ".", "",
                "8.  Nome: " + pro[7].nome + ". Estoque: " + pro[7].qtd + ". Preço: R$" + pro[7].preco + ".", "",
                "9.  Nome: " + pro[8].nome + ". Estoque: " + pro[8].qtd + ". Preço: R$" + pro[8].preco + ".", "",
                "10. Nome: " + pro[9].nome + ". Estoque: " + pro[9].qtd + ". Preço: R$" + pro[9].preco + ".", "",};
        return one;
    }

    public static Object [] l_2 (Produtos [] pro){
        pro = produtos(pro);
        Object [] two = {
                "11. Nome: " + pro[10].nome + ". Estoque: " + pro[10].qtd + ". Preço: R$" + pro[10].preco + ".", "",
                "12. Nome: " + pro[11].nome + ". Estoque: " + pro[11].qtd + ". Preço: R$" + pro[11].preco + ".", "",
                "13. Nome: " + pro[12].nome + ". Estoque: " + pro[12].qtd + ". Preço: R$" + pro[12].preco + ".", "",
                "14. Nome: " + pro[13].nome + ". Estoque: " + pro[13].qtd + ". Preço: R$" + pro[13].preco + ".", "",
                "15. Nome: " + pro[14].nome + ". Estoque: " + pro[14].qtd + ". Preço: R$" + pro[14].preco + ".", "",
                "16. Nome: " + pro[15].nome + ". Estoque: " + pro[15].qtd + ". Preço: R$" + pro[15].preco + ".", "",
                "17. Nome: " + pro[16].nome + ". Estoque: " + pro[16].qtd + ". Preço: R$" + pro[16].preco + ".", "",
                "18. Nome: " + pro[17].nome + ". Estoque: " + pro[17].qtd + ". Preço: R$" + pro[17].preco + ".", "",
                "19. Nome: " + pro[18].nome + ". Estoque: " + pro[18].qtd + ". Preço: R$" + pro[18].preco + ".", "",
                "20. Nome: " + pro[19].nome + ". Estoque: " + pro[19].qtd + ". Preço: R$" + pro[19].preco + ".", "",};
        return two;
    }

    public static Object [] l_3 (Produtos [] pro){
        pro = produtos(pro);
        Object [] three = {
                "21. Nome: " + pro[20].nome + ". Estoque: " + pro[20].qtd + ". Preço: R$" + pro[20].preco + ".", "",
                "22. Nome: " + pro[21].nome + ". Estoque: " + pro[21].qtd + ". Preço: R$" + pro[21].preco + ".", "",
                "23. Nome: " + pro[22].nome + ". Estoque: " + pro[22].qtd + ". Preço: R$" + pro[22].preco + ".", "",
                "24. Nome: " + pro[23].nome + ". Estoque: " + pro[23].qtd + ". Preço: R$" + pro[23].preco + ".", "",
                "25. Nome: " + pro[24].nome + ". Estoque: " + pro[24].qtd + ". Preço: R$" + pro[24].preco + ".", "",
                "26. Nome: " + pro[25].nome + ". Estoque: " + pro[25].qtd + ". Preço: R$" + pro[25].preco + ".", "",
                "27. Nome: " + pro[26].nome + ". Estoque: " + pro[26].qtd + ". Preço: R$" + pro[26].preco + ".", "",
                "28. Nome: " + pro[27].nome + ". Estoque: " + pro[27].qtd + ". Preço: R$" + pro[27].preco + ".", "",
                "29. Nome: " + pro[28].nome + ". Estoque: " + pro[28].qtd + ". Preço: R$" + pro[28].preco + ".", "",
                "30. Nome: " + pro[29].nome + ". Estoque: " + pro[29].qtd + ". Preço: R$" + pro[29].preco + ".", "",
        };
        return three;
    }

    public static Object [] l_4 (Produtos [] pro){
        pro = produtos(pro);
        Object [] four = {
                "31. Nome: " + pro[30].nome + ". Estoque: " + pro[30].qtd + ". Preço: R$" + pro[30].preco + ".", "",
                "32. Nome: " + pro[31].nome + ". Estoque: " + pro[31].qtd + ". Preço: R$" + pro[31].preco + ".", "",
                "33. Nome: " + pro[32].nome + ". Estoque: " + pro[32].qtd + ". Preço: R$" + pro[32].preco + ".", "",
                "34. Nome: " + pro[33].nome + ". Estoque: " + pro[33].qtd + ". Preço: R$" + pro[33].preco + ".", "",
                "35. Nome: " + pro[34].nome + ". Estoque: " + pro[34].qtd + ". Preço: R$" + pro[34].preco + ".", "",
                "36. Nome: " + pro[35].nome + ". Estoque: " + pro[35].qtd + ". Preço: R$" + pro[35].preco + ".", "",
                "37. Nome: " + pro[36].nome + ". Estoque: " + pro[36].qtd + ". Preço: R$" + pro[36].preco + ".", "",
                "38. Nome: " + pro[37].nome + ". Estoque: " + pro[37].qtd + ". Preço: R$" + pro[37].preco + ".", "",
                "39. Nome: " + pro[38].nome + ". Estoque: " + pro[38].qtd + ". Preço: R$" + pro[38].preco + ".", "",
                "40. Nome: " + pro[39].nome + ". Estoque: " + pro[39].qtd + ". Preço: R$" + pro[39].preco + ".", "",
        };
        return four;
    }

    public static Object [] l_5 (Produtos [] pro){
        pro = produtos(pro);
        Object [] five = {
                "41. Nome: " + pro[30].nome + ". Estoque: " + pro[30].qtd + ". Preço: R$" + pro[30].preco + ".", "",
                "42. Nome: " + pro[31].nome + ". Estoque: " + pro[31].qtd + ". Preço: R$" + pro[31].preco + ".", "",
                "43. Nome: " + pro[32].nome + ". Estoque: " + pro[32].qtd + ". Preço: R$" + pro[32].preco + ".", "",
                "44. Nome: " + pro[33].nome + ". Estoque: " + pro[33].qtd + ". Preço: R$" + pro[33].preco + ".", "",
                "45. Nome: " + pro[34].nome + ". Estoque: " + pro[34].qtd + ". Preço: R$" + pro[34].preco + ".", "",
                "46. Nome: " + pro[35].nome + ". Estoque: " + pro[35].qtd + ". Preço: R$" + pro[35].preco + ".", "",
                "47. Nome: " + pro[36].nome + ". Estoque: " + pro[36].qtd + ". Preço: R$" + pro[36].preco + ".", "",
                "48. Nome: " + pro[37].nome + ". Estoque: " + pro[37].qtd + ". Preço: R$" + pro[37].preco + ".", "",
                "49. Nome: " + pro[38].nome + ". Estoque: " + pro[38].qtd + ". Preço: R$" + pro[38].preco + ".", "",
                "50. Nome: " + pro[39].nome + ". Estoque: " + pro[39].qtd + ". Preço: R$" + pro[39].preco + ".", "",
        };
        return five;
    }

    public static Produtos [] add (Produtos [] pro){
        int index, t_add = -7;

        String [] ops_Add = {"Adicionar","Finalizar"};

        JTextField add = new JTextField();
        JTextField adn = new JTextField();
        JTextField adq = new JTextField();
        JTextField adp = new JTextField();

        Object [] adf  = {"Número do produto: ", add, "Nome:", adn, "Quantidade:", adq, "Preço:", adp};

        while (t_add != 1) {
            t_add = JOptionPane.showOptionDialog(null, adf, "Adicionar produto", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_Add, ops_Add[1]);
            if (!add.getText().equals("") && !adn.getText().equals("") && !adq.getText().equals("") && !adp.getText().equals("")) {
                try {
                    index = Integer.parseInt(add.getText());
                    pro[index].nome  = adn.getText();
                    pro[index].qtd   = Integer.parseInt(adq.getText());
                    pro[index].preco = Double.parseDouble(adp.getText());

                } catch (Error e) {
                    JOptionPane.showMessageDialog(null, "ERR0R", "Números digitados são inválidos!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERR0R", "Preencha todos os campos corretamente!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return pro;
    }

    public static Produtos [] rem (Produtos [] pro){
        int index, t_rem = -7, t_confirm;

        String [] ops_Rem = {"Remover","Finalizar"};
        String [] ops_Con = {"Confirmar","Cancelar"};

        JTextField c_rem = new JTextField();

        Object [] rep  = {"Número do produto a ser removido: ", c_rem};

        while (t_rem != 1) {
            t_rem = JOptionPane.showOptionDialog(null, rep, "Remover produto", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_Rem, ops_Rem[1]);
            if (!c_rem.getText().equals("")) {
                try {
                    index = Integer.parseInt(c_rem.getText());

                    Object [] confirm = {index, ". Nome: " + pro[index].nome + ". Estoque: " + pro[index].qtd + ". Preço: R$" + pro[index].preco + "."};

                    t_confirm = JOptionPane.showOptionDialog(null, confirm, "Deseja remover o produto abaixo?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_Con, ops_Con[1]);

                    if (t_confirm == 0){
                        pro[index].nome  = "Produto indisponível.";
                        pro[index].qtd   = 0;
                        pro[index].preco = 0;
                        JOptionPane.showMessageDialog(null, "Produto removido com êxito!", "Operação concluída!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Error e) {
                    JOptionPane.showMessageDialog(null, "ERR0R", "Número digitado é inválido!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERR0R", "Escolha um número e tente novamente!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return pro;
    }
}
/*
JOptionPane.showOptionDialog(null, "msg", "title", JOptionPane.DEFAULT_OPTION, 3, null, array, array[1]);

JTextField  = new JTextField;
var = nome_JTextField.getText();

Object []  = {};

log [id][0][0][0] = nome;
log [id][0][0][1] = senha;
log [id][0][1][1] = endereço;
log [id][1][1][1] = telefone;

frutas [x][0] = "Nome: Fruta";
frutas [x][0] = "Preço: XX.XX";
*/