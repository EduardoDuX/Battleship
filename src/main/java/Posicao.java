package battleship.java;

import javax.swing.*;

public class Posicao  implements Cloneable {
    private char linha;
    private int coluna;
    private JButton botao;

    private boolean atingida = false;
    public Posicao(char linha, int coluna, JButton botao){
        this.linha = linha;
        this.coluna = coluna;
        this.botao = botao;
    }

    public Posicao(){

    }

    public Posicao clone(){
        Posicao clone = new Posicao();
        clone.linha = this.linha;
        clone.coluna = this.coluna;
        clone.botao = this.botao;
        return clone;
    }

    public char getLinha() {
        return linha;
    }
    public int getIntLinha() {
        return (int)(linha-'A');
    }

    public void setAtingida(boolean atingida) {
        this.atingida = atingida;
    }

    public boolean isAtingida() {
        return atingida;
    }

    public void setLinha(char linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public JButton getBotao() {
        return botao;
    }


//    Tem barco(escondido): cinza
//    Tem barco(destruido): vermelho
//    Fog: Branco
//    Mar: Azul

    @Override
    public String toString()
    {
//        char col = (char)(coluna);
        return linha + String.valueOf(this.coluna);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Posicao){
            if (((Posicao) obj).getLinha() == this.linha && ((Posicao) obj).getColuna() == this.coluna)
                return true;
        }
        return false;
    }
}