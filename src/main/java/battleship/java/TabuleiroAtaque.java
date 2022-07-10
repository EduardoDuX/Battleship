package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TabuleiroAtaque extends Tabuleiro{
    private boolean acertou;
    private boolean esperando = true;
    private Lock chave;
    private Condition cond;
    private static Posicao posicao;


    public void setEsperando(boolean esperando) {
        this.esperando = esperando;
        System.out.println("flag modificada");
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
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
    public TabuleiroAtaque(){
        super();
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroAtaque.AttackButtonHandler handler = new TabuleiroAtaque.AttackButtonHandler(grelha[linha][coluna]);
                botoes[linha][coluna].addActionListener(handler);
            }
        }
        chave = new ReentrantLock();
        cond = chave.newCondition();
    }
    public void atacar(Posicao posicao){
        try {
            output.writeObject(posicao);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private class AttackButtonHandler implements ActionListener {
        private final Posicao posicao;
        public AttackButtonHandler(Posicao posicao){
            this.posicao = posicao;
        }
        @Override
        public void actionPerformed(ActionEvent event) {
            TabuleiroAtaque.posicao = posicao;
            atacar(posicao);
        }
    }
}
