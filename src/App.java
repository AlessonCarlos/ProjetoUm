import javax.swing.JOptionPane;

public class App {
    private static IclienteDAO clienteDAO = new clienteMapDAO();
    private static Cliente clienteCadastrado = null;

    public static void main(String[] args) {
        String opcao;
        while (true) {
            opcao = JOptionPane.showInputDialog(null, "Digite:\n1 para cadastro\n2 para consulta\n3 para exclusão\n4 para alteração\n5 para sair", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

            while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") && !opcao.equals("4") && !opcao.equals("5")) {
                JOptionPane.showMessageDialog(null, "Opção inválida. Digite de 1 a 5.", "Cadastro", JOptionPane.WARNING_MESSAGE);
                opcao = JOptionPane.showInputDialog(null, "Digite:\n1 para cadastro\n2 para consulta\n3 para exclusão\n4 para alteração\n5 para sair", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }

            if (opcao.equals("1")) {
                String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                String cpfStr = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                String telStr = JOptionPane.showInputDialog(null, "Digite o telefone do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                String end = JOptionPane.showInputDialog(null, "Digite o endereço do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                String numStr = JOptionPane.showInputDialog(null, "Digite o número do endereço do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                String cidade = JOptionPane.showInputDialog(null, "Digite a cidade do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                String estado = JOptionPane.showInputDialog(null, "Digite o estado do cliente:", "Cadastro", JOptionPane.QUESTION_MESSAGE);

                try {
                    Long cpf = Long.parseLong(cpfStr);
                    Long tel = Long.parseLong(telStr);
                    Integer num = Integer.parseInt(numStr);

                    Cliente novoCliente = new Cliente(nome, cpf, tel, end, num, cidade, estado);

                    if (clienteDAO.cadastrar(novoCliente)) {
                        clienteCadastrado = novoCliente;
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente com o CPF informado já existe.", "Cadastro", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente. Verifique os formatos de CPF, telefone e número.", "Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            } else if (opcao.equals("2")) {
                if (clienteCadastrado != null) {
                    JOptionPane.showMessageDialog(null, "Nome: " + clienteCadastrado.getNome() +
                            "\nCPF: " + clienteCadastrado.getCpf() +
                            "\nTelefone: " + clienteCadastrado.getTel() +
                            "\nEndereço: " + clienteCadastrado.getEnd() +
                            "\nNúmero: " + clienteCadastrado.getNumero() +
                            "\nCidade: " + clienteCadastrado.getCidade() +
                            "\nEstado: " + clienteCadastrado.getEstado(),
                            "Consulta", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.", "Consulta", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (opcao.equals("3")) {
                if (clienteCadastrado != null) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o cliente cadastrado?", "Exclusão", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        clienteDAO.excluir(clienteCadastrado.getCpf());
                        clienteCadastrado = null;
                        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado para excluir.", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (opcao.equals("4")) {
                if (clienteCadastrado != null) {
                    String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome:", "Alteração", JOptionPane.QUESTION_MESSAGE);
                    String novoCpfStr = JOptionPane.showInputDialog(null, "Digite o novo CPF:", "Alteração", JOptionPane.QUESTION_MESSAGE);
                    String novoTelStr = JOptionPane.showInputDialog(null, "Digite o novo telefone:", "Alteração", JOptionPane.QUESTION_MESSAGE);
                    String novoEnd = JOptionPane.showInputDialog(null, "Digite o novo endereço:", "Alteração", JOptionPane.QUESTION_MESSAGE);
                    String novoNumStr = JOptionPane.showInputDialog(null, "Digite o novo número:", "Alteração", JOptionPane.QUESTION_MESSAGE);
                    String novaCidade = JOptionPane.showInputDialog(null, "Digite a nova cidade:", "Alteração", JOptionPane.QUESTION_MESSAGE);
                    String novoEstado = JOptionPane.showInputDialog(null, "Digite o novo estado:", "Alteração", JOptionPane.QUESTION_MESSAGE);

                    try {
                        Long novoCpf = Long.parseLong(novoCpfStr);
                        Long novoTel = Long.parseLong(novoTelStr);
                        Integer novoNum = Integer.parseInt(novoNumStr);

                        Cliente clienteAlterado = new Cliente(novoNome, novoCpf, novoTel, novoEnd, novoNum, novaCidade, novoEstado);

                        clienteDAO.alterar(clienteAlterado);
                        clienteCadastrado = clienteAlterado;
                        JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso.", "Alteração", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao alterar cliente. Verifique os formatos de CPF, telefone e número.", "Alteração", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado para alterar.", "Alteração", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (opcao.equals("5")) {
                JOptionPane.showMessageDialog(null, "Saindo do programa.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }
}
