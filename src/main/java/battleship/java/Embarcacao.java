package battleship.java;

import javax.swing.*;
import java.awt.*;

public abstract class Embarcacao {
    protected boolean destruido = false;
    protected int tamanho;

    protected Posicao[] posicoes;
    public void verificaDestruido()
    {
        for(int i = 0; i < this.tamanho; i++)
        {
            if(!posicoes[i].getBotao().getBackground().equals(Color.decode("#FF0000")))
                return;
        }
        this.destruido = true;
    }

    public Boolean getDestruido() {
        return destruido;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Posicao[] getPosicoes() {
        return posicoes;
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
