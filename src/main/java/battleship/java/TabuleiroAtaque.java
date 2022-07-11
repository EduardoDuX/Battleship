package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TabuleiroAtaque extends Tabuleiro{
    private static Posicao posicaoAtacada;

    public static void setPosicaoAtacada(Posicao posicaoAtacada) {
        TabuleiroAtaque.posicaoAtacada = posicaoAtacada;
    }

    public void respostaAtaque(boolean acertou) {
        Posicao posNoTab = grelha[posicaoAtacada.getIntLinha()][posicaoAtacada.getColuna()];
        posNoTab.setAtingida(true);
        JButton b = posNoTab.getBotao();
        if (acertou){
            b.setBackground(Color.decode("#990000"));
        } else {
            b.setBackground(Color.WHITE);
        }
        b.setEnabled(false);
        ativarBotoes(false);
    }
    public TabuleiroAtaque(){
        super();
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroAtaque.AttackButtonHandler handler = new TabuleiroAtaque.AttackButtonHandler(grelha[linha][coluna]);
                botoes[linha][coluna].addActionListener(handler);
            }
        }
    }
    public void atacar(){
        if (output != null){
            try {
                output.writeObject(posicaoAtacada);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (oponente != null){
            if (oponente.verificaAcerto(posicaoAtacada)){
                posicaoAtacada.getBotao().setBackground(Color.decode("#990000"));
            } else {
                posicaoAtacada.getBotao().setBackground(Color.WHITE);
            }
            //manda o bot atacar
            if (oponente instanceof JogadorComputador)
                ((JogadorComputador) oponente).atacar();
        }
    }
    private class AttackButtonHandler implements ActionListener {
        private final Posicao posicao;
        public AttackButtonHandler(Posicao posicao){
            this.posicao = posicao;
        }
        @Override
        public void actionPerformed(ActionEvent event) {
            TabuleiroAtaque.posicaoAtacada = posicao;
            atacar();
        }
    }
}
