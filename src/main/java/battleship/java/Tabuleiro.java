package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Tabuleiro extends JPanel {
    protected Posicao[][] grelha;
    protected Jogador oponente;
    protected static ObjectOutputStream output;
    public Tabuleiro() {

        // Cria matriz de posicoes 10x10
        grelha = new Posicao[10][10];

        // Cria layout 10x10
        this.setLayout(new GridLayout(10, 10));

        // Cria e adiciona todas as posicoes e botoes
        int aux = 'A';
        for (int linha = 0; linha < 10; linha++) {
            for (int coluna = 0; coluna < 10; coluna++) {
                JButton botao = new JButton((char) aux + "" + coluna);
                botao.setBackground(Color.decode("#4169E1"));
                botao.setFocusable(false);
                grelha[linha][coluna] = new Posicao((char) aux, coluna, botao);
                this.add(botao);
            }
            aux++;
        }
    }

    // Ativa os botoes to tabuleiro, menos os atingidos
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

    public static void setOutput(ObjectOutputStream o) {
        output = o;
    }
    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }
}
