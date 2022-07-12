package battleship.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelInicial extends JFrame {
    static PainelInicial.ButtonHandler handler = new PainelInicial.ButtonHandler();
    PainelInicial() {
        // Abre imagens de personalizacao
        ImageIcon iconeSingle = new ImageIcon("src/main/java/imagens/singleplayer.png");
        ImageIcon iconeMulti = new ImageIcon("src/main/java/imagens/multiplayer.png");
        this.setContentPane(new JLabel(new ImageIcon("src/main/java/imagens/imagem_bg.jpeg")));

        // Cria botao para singleplayer
        JButton botaoSingle = new JButton("SinglePlayer");
        botaoSingle.setFocusable(false);
        botaoSingle.addActionListener(handler);
        botaoSingle.setBounds(350, 300, 200, 50);
        botaoSingle.setIcon(iconeSingle);

        // Cria botao para multiplayer
        JButton botaoMulti = new JButton("MultiPlayer");
        botaoMulti.addActionListener(handler);
        botaoMulti.setBounds(650, 300, 200, 50);
        botaoMulti.setFocusable(false);
        botaoMulti.setIcon(iconeMulti);
        botaoMulti.setEnabled(true);

        // Adiciona os componentes ao frame
        this.add(botaoSingle);
        this.add(botaoMulti);
        this.pack();
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

      public static class ButtonHandler implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent event) {
              // Cria jogo de acordo com botao clicado
              if (((JButton)event.getSource()).getText().equals("SinglePlayer")) {
                  BatalhaNaval.JogoSolo();
                  System.out.println("single");
                  BatalhaNaval.painel.dispose();
              } else {
                  new OpcoesConexao();
                  System.out.println("multi");
                  BatalhaNaval.painel.dispose();
              }
          }
      }
}
