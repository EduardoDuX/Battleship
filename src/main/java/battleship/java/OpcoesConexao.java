package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpcoesConexao {
    private final JFrame frameConexao;

    public OpcoesConexao() {
        // Cria um frame para opcoes de conexao
        frameConexao = new JFrame("Opções de conexão");
        frameConexao.setLayout(new FlowLayout());
        frameConexao.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Cria um botao para criar um jogo
        JButton host = new JButton("Criar jogo");
        host.addActionListener(e -> {frameConexao.dispose();
            try {
                BatalhaNaval.JogoMultiplayer(true, null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Cria um textArea para o ip
        JTextArea ipText = new JTextArea();
        ipText.setPreferredSize(new Dimension(120,20));
        ipText.setEditable(true);

        // Cria um botao para conectar a um jogo
        JButton join = new JButton("Entrar em um jogo");
        join.addActionListener(e -> {
            frameConexao.dispose();
            try {
                BatalhaNaval.JogoMultiplayer(false, ipText.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Cria label "OU"
        JLabel ou = new JLabel("Ou");

        // Adiciona componentes a janela
        frameConexao.add(host);
        frameConexao.add(ou);
        frameConexao.add(ipText);
        frameConexao.add(join);
        frameConexao.setVisible(true);
        frameConexao.pack();
    }
}
