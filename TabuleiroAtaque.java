import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabuleiroAtaque extends Tabuleiro{
    private int acertos;

    public TabuleiroAtaque(){
        super();
        acertos = 0;

        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroAtaque.AttackButtonHandler handler = new TabuleiroAtaque.AttackButtonHandler(grelha[linha][coluna]);
                botoes[linha][coluna].addActionListener(handler);
            }
        }
    }

    public void verificaVitoria(Jogador j){
        if (acertos == 30){
            System.out.println("O jogador " + j + "ganhou!");
            System.exit(0);
        }
    }

    private class AttackButtonHandler implements ActionListener {

        private Posicao posicao;

        public AttackButtonHandler(Posicao p){
            this.posicao = p;
        }

        public Posicao getPosicao() {
            return posicao;
        }
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {

            JButton b = (JButton) event.getSource();
            b.setEnabled(false);
            System.out.println(posicao.getLinha());

        } // end method actionPerformed
    }
}
