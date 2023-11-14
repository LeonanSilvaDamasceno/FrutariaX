import javax.swing.*;

public class Yaa {
    private static JTextField dep;
    public static void main(String[] args) {
        int p1 = -1, yu = 0, id, p2 = -1, ultimate, handholder, y, u = 0, k = 0; //"handholder" e "ultimate" auxíliam o processo de transações armazenando e operando valores quando necessário,"p1" e "p2" denotam as opções do JOptionPane,"yu" contagem e ID,
        String[] names = new String[200]; //Dados de 'login' dos clientes, nome e senha
        String[] pass = new String[200];
        Double[] cashex = new Double[200]; //Saldo presente na conta dos clientes
        String[] free = {"Abrir conta", "Acessar conta", "Estatísticas", "Listar clientes", "Sair do programa"}; //Opções para JOptionPane
        String[] bird = {"Escolher produtos", "Verificar Carrinho", "Remover produtos", "Concluir compra", "Cancelar compra", "Sair da conta"};
        JTextField nome = new JTextField(); //Campos JOptionPane
        JTextField senha = new JTextField();
        JTextField c = new JTextField();
        JTextField s = new JTextField();
        JTextField dep = new JTextField();
        JTextField saq = new JTextField();
        JTextField pag = new JTextField();
        JTextField tm = new JTextField();
        JTextField tc = new JTextField();
        Object[] op = {"Seja bem vinda(o), é um prazer saber que você optou por nosso banco!\n\nPor favor, para abrir uma conta, digite seu nome logo a seguir:", nome, "A senha a ser usada com a conta neste nome:", senha}; //Mensagens e variáveis de armazenamento "bruto" do JOptionPane
        Object[] op2 = {"Um ótimo dia! Poderia nos informar seus dados de login?\n\nConta:", c, "Senha", s};
        Object[] dd = {"Quanto será depositado?", dep};
        Object[] ss = {"Qual será o valor do saque?", saq};
        Object[] pp = {"Qual é o valor do pagamento?", pag};
        Object[] tt = {"De quanto será a transferência?", tm, "Qual é o número da conta que receberá este valor?", tc};
        while (p1 != 4) {
            p1 = JOptionPane.showOptionDialog(null, "Olá! Assim que possível, escolha uma opção:", "Menu inicial", JOptionPane.DEFAULT_OPTION, 2, null, free, free[4]); //Criação de JOptionPane com opções do menu inicial
            if (p1 == 0) { //Opção 1, Abrir conta
                if (yu <= 200) {
                    JOptionPane.showMessageDialog(null, op, "Abrir conta", JOptionPane.INFORMATION_MESSAGE); //Caixa de diálogo com cliente para coleta de dados 'login'
                    names[yu] = nome.getText(); //Refinação dos dados "brutos" para 'string'
                    pass[yu] = senha.getText();
                    cashex[yu] = 0.0; //Atribuição de valor monetário inicial da conta
                    JOptionPane.showMessageDialog(null, "Cadastro completo, sua conta será reconhecida pelo id \"" + yu + "\" não o esqueça por favor! Ele será usado para efetuar o login então é muito importante!", "Login concluído!", JOptionPane.INFORMATION_MESSAGE);
                    yu++; //Número de ID de cada cliente, serve também como contagem
                } else
                    JOptionPane.showMessageDialog(null, "Sentimos muito, o número máximo de clientes já atingido. Tente novamente mais tarde...");
            }
            if (p1 == 1) { //Segunda opção, tela de 'Login'
                JOptionPane.showMessageDialog(null, op2, "Acessar conta", JOptionPane.INFORMATION_MESSAGE);
                id = Integer.parseInt(c.getText()); //Verificação de ID especificado pela(o) utilizador(a)
                if ((s.getText()).equals(pass[id])) { //Autenticação ID
                    while (p2 != 5) { //Manter acesso até que "Sair da conta" seja selecionado
                        p2 = JOptionPane.showOptionDialog(null, "Seja bem vinda(o) " + names[id] + ".", "Menu", JOptionPane.DEFAULT_OPTION, 2, null, bird, bird[5]); //Ementa de operações do banco
                        if (p2 == 0) { //Opção 1, depósito
                            JOptionPane.showMessageDialog(null, dd, "Depósito", JOptionPane.QUESTION_MESSAGE);
                            cashex[id] += Integer.parseInt(dep.getText());
                        }
                        if (p2 == 1) { //Opção 2 Saque
                            JOptionPane.showMessageDialog(null, ss, "Saque", JOptionPane.QUESTION_MESSAGE);
                            handholder = Integer.parseInt(saq.getText()); //Refinamento do 'input'
                            if (cashex[id] - handholder < 0 || handholder > 300) //Restrições do Saque
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente ou indisponível!", "ERR0R", JOptionPane.ERROR_MESSAGE);
                            else
                                cashex[id] -= handholder;
                        }
                        if (p2 == 2) { //Opção 3 Verificar saldo
                            JOptionPane.showMessageDialog(null, "O valor presente em sua conta no atual momento é de R$" + cashex[id] + ".", "Saldo conta " + id + ":", JOptionPane.INFORMATION_MESSAGE);
                        }
                        if (p2 == 3) { //Opção 4 Pagar outro alguém
                            JOptionPane.showMessageDialog(null, pp, "Pagamento", JOptionPane.QUESTION_MESSAGE);
                            handholder = Integer.parseInt(pag.getText());
                            if (cashex[id] - handholder < 0) //Verificação de saldo
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "ERR0R", JOptionPane.ERROR_MESSAGE);
                            else
                                cashex[id] -= handholder; //Transação com êxito!
                        }
                        if (p2 == 4) { //Quinta opção transferência, para outra pessoa do mesmo banco
                            JOptionPane.showMessageDialog(null, tt, "Pagamento", JOptionPane.QUESTION_MESSAGE);
                            handholder = Integer.parseInt(tm.getText());
                            if (cashex[id] - handholder < 0) //Verificação do saldo do 'user'
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "ERR0R", JOptionPane.ERROR_MESSAGE);
                            else { //Verificação da conta da pessoa que receberá a transferência a partir do seu ID armazenado em tc
                                ultimate = Integer.parseInt(tc.getText());
                                if (names[ultimate].length() > 0) {
                                    cashex[id] -= handholder; //Saindo do 'user'
                                    cashex[ultimate] += handholder; //Entrando no(a) recebedor(a)
                                }
                            }
                        }
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Senha incorreta!", "ERR0R", JOptionPane.ERROR_MESSAGE); //Caso de Erro do Login
            }
            if (p1 == 2) { //Terceira opção do 'menu' inicial que configura o cliente
                for (y = 0; y < yu - 1; y++) {
                    if (cashex[y] > cashex[y + 1])
                        u = y;
                    else
                        u = y + 1;
                    if (cashex[y] < cashex[y + 1])
                        k = y;
                    else
                        k = y + 1;
                }
                JOptionPane.showMessageDialog(null, "Cliente ouro= \"" + names[u] + "\" Cliente azul= \"" + names[k] + "\".", "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
            }
            if (p1 == 3) { //Informações de todos os clientes
                for (y = 0; y < yu; y++) {
                    JOptionPane.showMessageDialog(null, "Cliente n°" + y + ": " + names[y] + ".\nSenha: " + pass[y] + ".\nSaldo: R$" + cashex[y] + ".", "Listagem de clientes", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
