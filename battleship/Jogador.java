package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jogador {
    protected TabuleiroAtaque tAtaque;
    protected TabuleiroDefesa tDefesa;
    protected JFrame pane;

    protected static final int NUM_SUBS = 4;
    protected static final int NUM_TANKS = 3;
    protected static final int NUM_CT = 2;
    protected static final int NUM_PA = 1;


    public Jogador(){
        tAtaque = new TabuleiroAtaque();
        tDefesa = new TabuleiroDefesa();
        pane = new JFrame();
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
//        pane.add(Box.createHorizontalStrut(50),c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(tAtaque, c);
        pane.pack();
        pane.setResizable(false);
        pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    //ler as posicoes e orientacoes
//    public void colocarEmbarcacoes(){
//
//        for (int i = 0; i < NUM_SUBS; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new Submarino(),  new Posicao(), controleOrientacao);
//            }   catch(PosInvalidaException e){}
//        }
//
//        for (int i = 0; i < NUM_TANKS; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new NavioTanque(),  new Posicao(), controleOrientacao);
//            }   catch(PosInvalidaException e){}
//        }
//
//        for (int i = 0; i < NUM_CT; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new ContraTorpedeiro(),  new Posicao(), controleOrientacao);
//            }   catch(PosInvalidaException e){}
//        }
//
//        for (int i = 0; i < NUM_PA; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new PortaAvioes(),  new Posicao(), controleOrientacao);
//            }   catch(PosInvalidaException e){}
//        }
//    }


    private class OrientacaoButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton b = (JButton) event.getSource();
            tDefesa.trocaOrientacao();
            b.setText(tDefesa.getControleOrientacao() ? "vertical" : "horizontal");
        }
    }
}
