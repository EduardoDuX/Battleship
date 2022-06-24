package battleship;

import javax.swing.*;

public class Posicao {
    private char linha;
    private int coluna;

    private JButton botao;
    public Posicao(char linha, int coluna, JButton botao) {
        this.linha = linha;
        this.coluna = coluna;
        this.botao = botao;
    }

    public Posicao() {

    }

    public char getLinha() {
        return linha;
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
}