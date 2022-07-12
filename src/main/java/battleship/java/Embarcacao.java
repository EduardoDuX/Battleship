package battleship.java;

import javax.swing.*;
import java.awt.*;

public abstract class Embarcacao {

    protected int tamanho;
    protected Posicao[] posicoes;

    public int getTamanho() {
        return tamanho;
    }

    public void setPosicoes(Posicao[] posicoes){
        this.posicoes = posicoes;
    }

    public boolean embarcacaoVerificaAcerto(Posicao p){
        for (Posicao pos: posicoes) {
            if (pos.equals(p)){
              System.out.println(pos);
                JButton b = pos.getBotao();
                b.setBackground(Color.decode("#990000"));
              return true;
            }
        }
        return false;
    }

}
