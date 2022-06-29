package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tabuleiro extends JPanel {
    protected Posicao[][] grelha;
    protected JPanel painel = new JPanel();
    protected JButton[][] botoes;

    protected Jogador oponente;

    public Tabuleiro() {
        botoes = new JButton[10][10];
        painel.setLayout(new GridLayout(10, 10));
        this.setLayout(new BorderLayout());
        grelha = new Posicao[10][10];
        int aux = 'A';
        for (int linha = 0; linha < 10; linha++) {
            for (int coluna = 0; coluna < 10; coluna++) {
                botoes[linha][coluna] = new JButton((char) aux + "" + coluna);
                botoes[linha][coluna].setBackground(Color.decode("#4169E1"));
                grelha[linha][coluna] = new Posicao((char) aux, coluna,botoes[linha][coluna]);
                painel.add(botoes[linha][coluna]);
            }
            aux++;
        }
        this.add(painel, BorderLayout.CENTER);
    }


    public void ativarBotoes(boolean bol){
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                if (!grelha[linha][coluna].isAtingida())
                    grelha[linha][coluna].getBotao().setEnabled(bol);
            }
        }
    }
    public ArrayList<Posicao> getPosicoes(){
        ArrayList<Posicao> posicoes = new ArrayList<>();
        for (int linha = 0; linha < 10; linha++) {
            for (int coluna = 0; coluna < 10; coluna++) {
                posicoes.add(grelha[linha][coluna].clone());
            }
        }
        return posicoes;
    }


    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }
}
