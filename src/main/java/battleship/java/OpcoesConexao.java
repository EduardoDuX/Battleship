package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class OpcoesConexao extends JFrame{

    public OpcoesConexao() {
        // Cria um frame para opcoes de conexao
        this.setContentPane(new JLabel(new ImageIcon("src/main/java/imagens/imagem_bg.jpeg")));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Cria um botao para criar um jogo
        JButton host = new JButton("Criar jogo");
        host.setBounds(300, 300, 200, 50);
        host.addActionListener(e -> {this.dispose();
            try {
                BatalhaNaval.JogoMultiplayer(true, null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Cria um textArea para o ip
        JTextArea ipText = new JTextArea();
        ipText.setBounds(540, 320, 120, 20);

        // Cria um botao para conectar a um jogo
        JButton join = new JButton("Entrar em um jogo");
        join.setBounds(700, 300, 200, 50);
        join.addActionListener(e -> {
            this.dispose();
            try {
                BatalhaNaval.JogoMultiplayer(false, ipText.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Cria label "OU"
        JLabel ou = new JLabel("Ou");

        // Adiciona componentes a janela
        this.add(host);
        this.add(ou);
        this.add(ipText);
        this.add(join);
        this.setVisible(true);
        this.pack();
    }
}
