package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Jogador {
    protected TabuleiroAtaque tAtaque;
    protected TabuleiroDefesa tDefesa;
    protected JFrame pane;
    private int acertos = 0;

    public Jogador(){
        tAtaque = new TabuleiroAtaque();
        tDefesa = new TabuleiroDefesa();
        tDefesa.settAtaque(tAtaque);
        tAtaque.ativarBotoes(false);
        pane = new JFrame("Batalha Naval");
        pane.setVisible(true);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoDefesa = new JLabel("Utilize esse tabuleiro para posicionar seus navios", SwingConstants.CENTER);
        JLabel textoAtaque = new JLabel("Utilize esse tabuleiro atingir os navios do oponente", SwingConstants.CENTER);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(textoDefesa, c);

        c.gridx = 1;
        pane.add(Box.createHorizontalStrut(50),c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        pane.add(textoAtaque, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(tDefesa, c);

        c.gridy = 1;
        c.gridx = 1;
        JButton botao = new JButton(tDefesa.getControleOrientacao() ? "vertical" : "horizontal");
        Jogador.OrientacaoButtonHandler handler = new Jogador.OrientacaoButtonHandler();
        botao.addActionListener(handler);
        botao.setPreferredSize(new Dimension(100,20));
        pane.add(botao, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(tAtaque, c);
        pane.pack();
        pane.setResizable(false);
        pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void verificaVitoria(){
        if (acertos == 30){
            System.exit(0);
        }
    }

    public void comecar(boolean b){
        tDefesa.setComeca(b);
    }

    public void setOponenteSolo(Jogador oponente) {
        tDefesa.setOponente(oponente);
        tAtaque.setOponente(oponente);
    }

    public void setInputOutput(ObjectInputStream input,ObjectOutputStream output) {
        Tabuleiro.setInputOutput(input, output);
        Leitor leitor = new Leitor(input, tDefesa, tAtaque);
        Thread t = new Thread(leitor);
        t.start();
    }
    public void podeAtacar(boolean podeAtacar) {
        tAtaque.ativarBotoes(podeAtacar);
    }

    public boolean verificaAcerto(Posicao p){
        try {
            return tDefesa.tabuleiroVerificaAcerto(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tAtaque.ativarBotoes(true);
        }
    }

    private class OrientacaoButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton b = (JButton) event.getSource();
            tDefesa.trocaOrientacao();
            b.setText(tDefesa.getControleOrientacao() ? "vertical" : "horizontal");
        }
    }
}
