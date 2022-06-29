package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpcoesConexao {
    private String ip;
    private final JFrame frameConexao;

    public OpcoesConexao() {
        frameConexao = new JFrame();
        frameConexao.setLayout(new FlowLayout());
        frameConexao.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea ipText = new JTextArea();
        ipText.setPreferredSize(new Dimension(120,20));
        JButton host = new JButton("Criar jogo");
        host.addActionListener(e -> {frameConexao.dispose();
            try {
                BatalhaNaval.JogoMultiplayer(true, "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton join = new JButton("Entrar em um jogo");
        join.addActionListener(e -> {
            ip = ipText.getText();
            frameConexao.dispose();
            try {
                BatalhaNaval.JogoMultiplayer(false,ip);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JLabel ou = new JLabel("Ou");

        frameConexao.add(host);
        frameConexao.add(ou);
        frameConexao.add(ipText);
        frameConexao.add(join);
        frameConexao.setVisible(true);
        frameConexao.pack();
    }
}
