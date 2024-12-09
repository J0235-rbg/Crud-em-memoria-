package Mod14.domain;

import javax.swing.JOptionPane;

import Mod14.Cliente;
import Mod14.DAO.ClienteMapDAO;
import Mod14.DAO.IClienteDAO;

public class App {
    private static IClienteDAO iClienteDAO;
    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
            "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair.",
            "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }

            opcao = JOptionPane.showInputDialog(null,
            "Opção inválida digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair.",
            "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
        while (isOpcaoValida(opcao)) {

            if (isOpcaosair(opcao)) {
                sair();
            } else if (isCadastro(opcao)){
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado. "
                , "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            cadastrar(dados);
            }else if(isConsultar(opcao)){
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF do Cliente. "
                    , "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            }else if(isExcluir(opcao)){
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF do Cliente. "
                    , "Consultar", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);

            }else {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado. "
                , "Atualizar", JOptionPane.INFORMATION_MESSAGE);
                altualizar(dados);

            }
           
            opcao = JOptionPane.showInputDialog(null,
        "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair.",
        "Green ", JOptionPane.INFORMATION_MESSAGE);
        }

        

        
        
        

    }

    private static void altualizar(String dados){
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        iClienteDAO.alterar(cliente);
        JOptionPane.showMessageDialog(null, "Cadastrado autualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void excluir(String dados){
        iClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso " , "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void consultar(String dados) {
        //validar se foi passado somente o CPF
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null){
            JOptionPane.showMessageDialog(null, "Cliente encontrado " + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Cliente não  encontrado " , "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    private static void cadastrar(String dados) {
       String [] dadosSeparados = dados.split(",");
       //tentar validar se todos os campos estão preenchidos.
       //se não estiver, passar  null no construtor onde o valor e nulo
       //quebra cabeça para resolver dps
       Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
       Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
       if (isCadastrado) {
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
       }else{
        JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
       }
    }
    /**
     * private static boolean isAlterar(String opcao){
        if ("4".equals(opcao)) {
            return true;
        }
        return false;
    }
     */
    

    private static boolean isExcluir(String opcao){
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
       
    }
    private static boolean isOpcaosair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static void sair() {
      JOptionPane.showMessageDialog(null, "Até logo:", "Sair", JOptionPane.INFORMATION_MESSAGE);
      System.exit(0); 
    }
    
    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoValida(String opcao) {
       if ("1".equals(opcao) || "2".equals(opcao)
            || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
                return true;
        }
        return false;
    }
}
