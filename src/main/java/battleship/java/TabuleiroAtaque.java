package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TabuleiroAtaque extends Tabuleiro{
    public TabuleiroAtaque(){
        super();
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroAtaque.AttackButtonHandler handler = new TabuleiroAtaque.AttackButtonHandler(grelha[linha][coluna]);
                botoes[linha][coluna].addActionListener(handler);
            }
        }
    }
    public void atacar(Posicao posicao, boolean acertou){
        Posicao posNoTab = grelha[posicao.getIntLinha()][posicao.getColuna()];
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
    private class AttackButtonHandler implements ActionListener {
        private final Posicao posicao;
        private boolean acertou = false;
        public AttackButtonHandler(Posicao posicao){
            this.posicao = posicao;
        }
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                output.writeObject(posicao);
                acertou = input.readBoolean();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            atacar(posicao, acertou);
        }
    }
}
