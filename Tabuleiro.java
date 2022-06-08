import javax.swing.*;
import java.awt.*;

public class Tabuleiro extends JPanel {
    protected Posicao[][] grelha;
    private JPanel painel = new JPanel();
    private JButton[][] botoes = new JButton[10][10];

    public Tabuleiro(){
        painel.setLayout(new GridLayout(10,10));
        this.setLayout(new BorderLayout());


        grelha = new Posicao[10][10];
        int aux = (int)'A';
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                grelha[linha][coluna] = new Posicao();
                grelha[linha][coluna].setColuna(coluna);
                grelha[linha][coluna].setLinha((char)aux);
                botoes[linha][coluna] = new JButton((char)aux +""+  coluna);
                botoes[linha][coluna].setBackground(Color.decode("#4169E1"));
                painel.add(botoes[linha][coluna]);
            }
            aux++;
        }
        this.add(painel, BorderLayout.CENTER);
    }
}
