package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TabuleiroAtaque extends Tabuleiro{
    private static Posicao posicaoAtacada;
    protected int acertos;

    public static void setPosicaoAtacada(Posicao posicaoAtacada) {
        TabuleiroAtaque.posicaoAtacada = posicaoAtacada;
    }

    public void verificaVitoria(){
        acertos++;
        if (acertos == 30){
            if (oponente == null || oponente instanceof JogadorComputador){
                JOptionPane.showMessageDialog(this, "Você venceu!");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(oponente.pane, "O oponente venceu");
                System.exit(0);
            }
        }
    }


    // Recebe feedback do ataque realizado
    public void respostaAtaque(boolean acertou) {
        // Seleciona a posicao atacada a desativa
        Posicao posNoTab = grelha[posicaoAtacada.getIntLinha()][posicaoAtacada.getColuna()];
        posNoTab.setAtingida(true);
        JButton b = posNoTab.getBotao();
        b.setEnabled(false);

        // Muda cor de acordo com o feedback
        if (acertou){
            b.setBackground(Color.decode("#990000"));
            verificaVitoria();
        } else {
            b.setBackground(Color.WHITE);
        }

        // Desativa botoes, vez do oponente
        ativarBotoes(false);
    }
    public TabuleiroAtaque(){
        super();
        // Cria handlers para os botoes de ataque
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroAtaque.AttackButtonHandler handler = new TabuleiroAtaque.AttackButtonHandler(grelha[linha][coluna]);
                grelha[linha][coluna].getBotao().addActionListener(handler);
            }
        }
    }
    public void atacar(){
        if (output != null){
            // Ataca o oponente pela rede caso tenha um output configurado
            try {
                output.writeObject(posicaoAtacada);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Ataca o oponente local caso contrário
            if (oponente.verificaAcerto(posicaoAtacada)){
                posicaoAtacada.getBotao().setBackground(Color.decode("#990000"));
                verificaVitoria();
            } else {
                posicaoAtacada.getBotao().setBackground(Color.WHITE);
            }
            // Desativa a posicao atacada
            posicaoAtacada.setAtingida(true);
            posicaoAtacada.getBotao().setEnabled(false);

            // Caso oponente seja um bot, ele revida
            if (oponente instanceof JogadorComputador)
                ((JogadorComputador) oponente).atacar();
        }
    }
    private class AttackButtonHandler implements ActionListener {
        private final Posicao posicao;
        public AttackButtonHandler(Posicao posicao){
            this.posicao = posicao;
        }
        @Override
        public void actionPerformed(ActionEvent event) {
            // "Mira" na posicao selecionada
            TabuleiroAtaque.posicaoAtacada = posicao;

            // Ataca
            atacar();
        }
    }
}
