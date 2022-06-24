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

    public void verificaVitoria(){
        if (acertos == 31){
            JOptionPane.showMessageDialog(painel, "Você venceu");
            System.exit(0);
        }
    }

    public void atacar(Posicao posicao){
        Posicao posNoTab = grelha[posicao.getIntLinha()][posicao.getColuna()];
        posNoTab.setAtingida(true);
        JButton b = posNoTab.getBotao();
        if (oponente.verificaAcerto(posicao)){
            b.setBackground(Color.decode("#990000"));
            acertos++;
            verificaVitoria();
        } else {
            b.setBackground(Color.WHITE);
        }
        b.setEnabled(false);
        oponente.podeAtacar(true);
        ativarBotoes(false);
        if (oponente instanceof JogadorComputador){
            ((JogadorComputador) oponente).atacar();
        }
    }

    private class AttackButtonHandler implements ActionListener {

        private Posicao posicao;

        public AttackButtonHandler(Posicao posicao){
            this.posicao = posicao;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            atacar(posicao);
        } // end method actionPerformed
    }
}
