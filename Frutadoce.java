package frutaria;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frutadoce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int t_ini = -7, t_log, t_cad, t_f = -7, t_e = -7, t_c = -7, t_car = -7,  t_v = -7, t_cin = -7, id = 0, id_counter, id_cont = 0; //O

	        //Ala de butões
	        String [] ini   = {"Login", "Cadastro", "Sair"};
	        String [] ops_G = {"Confirmar", "Cancelar"};
	        String [] ops_F = {"Verificar estoque", "Lucros", "Verificar usuários", "Log-out"};
	        String [] est_F = {"Adicionar alimentos", "Remover alimentos", "Concluir"};
	        String [] ver_F = {"Próximo", "Anterior","Pesquisar por ID", "Sair"};
	        String [] ops_C = {"Comprar produtos", "Promoções da vez", "Parceiro", "Log-out"};
	        String [] fru   = {"Adicionar", "Carrinho", "Finalizar compra"};
	        String [] car   = {"Remover produtos", "Ok"};

	        //Ala de armazenamento de valores
	        String [][][] frutas =  new String [99][2][2];
	        String [][][] orders =  new String [6897][1][1];
	        String [][][][][] log = new String [6897][2][2][2][2];

	        //Ala de criação campos de texto
	        JTextField usu = new JTextField();
	        JTextField sen = new JTextField();
	        JTextField idx = new JTextField();
	        JTextField end = new JTextField();
	        JTextField tel = new JTextField();
	        JTextField vid = new JTextField();

	        Object [] o_log = {"Identificador:", idx,"Usuário: ", usu, "Senha: ", sen, }; //Ala de agrupamento entre mensagens e campos de texto
	        Object [] o_cad = {"Nome: ", usu, "Senha: ", sen, "Endereço: ", end, "Telefone: ", tel};
	        Object [] v_ids = {"Digite o id logo a baixo:", vid};
	        Object [] e_fru = {frutas};

	        while (t_ini != 2) { //Enquanto opção "Sair" não for selecionada na tela inicial
	            t_ini = JOptionPane.showOptionDialog(null, "Seja bem vinda(o) a frutaria Celeste onde a qualidade vem em variedade!",
	                                            "Frutaria Celeste: Tela inicial", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ini, ini[2]);
	            if (t_ini == 1) { //Opção de cadastro
	                t_cad = JOptionPane.showOptionDialog(null, o_cad, "Tela de Cadastro", JOptionPane.DEFAULT_OPTION,
	                        JOptionPane.PLAIN_MESSAGE, null, ops_G, ops_G[1]);

	                if (t_cad == 0) { //Opção concluir de cadastro
	                    if (id <= 6897 && !usu.getText().equals("") && !sen.getText().equals("") && !end.getText().equals("") && !tel.getText().equals("")) {
	                        log[id][0][0][0][0] = usu.getText() ;
	                        log[id][0][0][0][1] = sen.getText();
	                        log[id][0][0][1][1] = end.getText();
	                        log[id][0][1][1][1] = tel.getText();
	                        log[id][1][1][1][1] = "Não";
	                        id++;
	                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com êxito!", "Cadastro concluído!",
	                                JOptionPane.INFORMATION_MESSAGE);
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
	                                    t_cin = JOptionPane.showOptionDialog(null, "Bem vindo Sr(a)," + usu.getText() +
	                                                "como podemos lhe ajudar hoje?", "Frutaria Celeste",
	                                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops_C, ops_C[3]);
	                                    if (t_cin == 0) { //Opção "Comprar produtos"
	                                        while (t_c != 2) {
	                                            t_c = JOptionPane.showOptionDialog(null, e_fru, "Produtos",
	                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, e_fru, e_fru[2]);
	                                            while (t_car != 2) {
	                                                t_car = JOptionPane.showOptionDialog(null, e_fru, "Produtos",
	                                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, e_fru, e_fru[2]);
	                                            }
	                                        }
	                                    }
	                                    if (t_cin == 1) { //Opção "Promoções da vez"

	                                    }
	                                    if (t_cin == 2) { //Opção "Parceiros"

	                                    }
	                                }
	                        } else {
	                            if (idx.getText().equalsIgnoreCase("admin")){}
	                            else {
	                                JOptionPane.showOptionDialog(null, "Dados incorretos, tente novamente!\n",
	                                        "ERR0R", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, ops_G, ops_G[1]);
	                            }
	                        }
	                    } catch (NumberFormatException e) { //Para situações onde não são colocadas nenhuma informação na tela de Login
	                        if (idx.getText().equalsIgnoreCase("admin")) {}
	                        else {
	                            JOptionPane.showOptionDialog(null, "Dados incorretos, tente novamente!\n",
	                                    "ERR0R", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, ops_G, ops_G[1]);
	                        }
	                    }

	                    if ((usu.getText().equalsIgnoreCase("Laila") && sen.getText().equalsIgnoreCase("admin")) //Logins de funcionários
	                            || (usu.getText().equalsIgnoreCase("TheLegend27") && sen.getText().equalsIgnoreCase("admin"))) {
	                        while (t_f != 3) { //Enquanto diferente de 'Log-out'
	                            t_f = JOptionPane.showOptionDialog(null, "Um ótimo dia de trabalho, Sr(a)" + usu.getText() + "!",
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
}
public static String Protuto() {
	String [][][] frutas =  new String [99][2][2];
	frutas[0][0][0]="Banana";
	frutas[0][0][1]="30";
	frutas[0][1][1]="1.90";
	
	frutas[1][0][0]="Uva";
	frutas[1][0][1]="30";
	frutas[1][1][1]="10.99";
	
	frutas[2][0][0]="Morango";
	frutas[2][0][1]="30";
	frutas[2][1][1]="13.99";
	
	frutas[3][0][0]="Limão";
	frutas[3][0][1]="60";
	frutas[3][1][1]="6.50";
	
	frutas[4][0][0]="Mamão";
	frutas[4][0][1]="28";
	frutas[4][1][1]="5.99";
	
	frutas[5][0][0]="Maça";
	frutas[5][0][1]="40";
	frutas[5][1][1]="7.50";
	
	frutas[6][0][0]="Melancia";
	frutas[6][0][1]="28";
	frutas[6][1][1]="14.99";
	
	frutas[7][0][0]="Laranja";
	frutas[7][0][1]="40";
	frutas[7][1][1]="8.25";
	
	frutas[8][0][0]="Melão";
	frutas[8][0][1]="30";
	frutas[8][1][1]="6.00";
	
	frutas[9][0][0]="Abacaxi";
	frutas[9][0][1]="30";
	frutas[9][1][1]="5.00";
	
	frutas[10][0][0]="Coco";
	frutas[10][0][1]="30";
	frutas[10][1][1]="10.99";
	
	frutas[11][0][0]="Pera";
	frutas[11][0][1]="30";
	frutas[11][1][1]="15.99";
	
	frutas[12][0][0]="Maracuja";
	frutas[12][0][1]="30";
	frutas[12][1][1]="12.99";
	
	
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
