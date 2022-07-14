package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TabuleiroDefesa extends Tabuleiro{
    private final ArrayList<Embarcacao> submarinos;
    private final ArrayList<Embarcacao> portaAvioes;
    private final ArrayList<Embarcacao> contraTorpedeiros;
    private final ArrayList<Embarcacao> naviosTanque;
    protected static final int NUM_SUBS = 4;
    protected static final int NUM_TANKS = 2;
    protected static final int NUM_CT = 3;
    private TabuleiroAtaque tAtaque;
    public boolean controleOrientacao = true;
    protected int barcos = 0;

    public TabuleiroDefesa(){
        super();

        // Inicializa arrays de barcos
        submarinos = new ArrayList<>();
        portaAvioes = new ArrayList<>();
        contraTorpedeiros = new ArrayList<>();
        naviosTanque = new ArrayList<>();

        // Cria handlers para os botoes de defesa
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroDefesa.DefenseButtonHandler handler = new TabuleiroDefesa.DefenseButtonHandler(grelha[linha][coluna]);
                grelha[linha][coluna].getBotao().addActionListener(handler);
            }
        }
    }


    public void settAtaque(TabuleiroAtaque tAtaque) {
        this.tAtaque = tAtaque;
    }

    public void trocaOrientacao(){
        controleOrientacao = !controleOrientacao;
    }

    public boolean getControleOrientacao() {
        return controleOrientacao;
    }

    public boolean tabuleiroVerificaAcerto(Posicao p) throws IOException {
        for (Embarcacao e: submarinos){
            if (e.embarcacaoVerificaAcerto(p)){
                if (output != null)
                    output.writeObject(Boolean.TRUE);
                return true;
            }
        }
        for (Embarcacao e: naviosTanque){
            if (e.embarcacaoVerificaAcerto(p)){
                if (output != null)
                    output.writeObject(Boolean.TRUE);
                return true;
            }
        }
        for (Embarcacao e: contraTorpedeiros){
            if (e.embarcacaoVerificaAcerto(p)){
                if (output != null)
                    output.writeObject(Boolean.TRUE);
                return true;
            }
        }
        for (Embarcacao e: portaAvioes){
            if (e.embarcacaoVerificaAcerto(p)){
                if (output != null)
                    output.writeObject(Boolean.TRUE);
                return true;
            }
        }
        grelha[p.getIntLinha()][p.getColuna()].getBotao().setBackground(Color.WHITE);
        if (output != null)
            output.writeObject(Boolean.FALSE);
        return false;
    }

    public void colocarEmbarcacao(Embarcacao e, Posicao p, boolean orientacao) throws PosInvalidaException{
        // Orientacao: 1(vertical), 0(horizontal)

        // Coleta informacoes relevantes
        int tam = e.getTamanho();
        int linha = (int)p.getLinha() - (int)'A';
        int coluna = p.getColuna();

        // Cria array de posicoes para a embarcacao
        Posicao[] posicoes = new Posicao[tam];

        // Botao auxiliar
        JButton botao;

        if (orientacao){
            // Vertical

            // Verifica acesso indevido de posicao
            if(linha + tam > 10)
                throw new PosInvalidaException();

            // Verifica se a posicao ja tem um barco
            for (int i = 0; i < tam; i++){
                if (grelha[linha+i][coluna].getBotao().getBackground().equals(Color.decode("#808080")))
                    throw new PosInvalidaException();
            }

            // Adiciona embarcacao no local desejado
            for (int i = 0; i < tam; i++){
                botao = grelha[linha+i][coluna].getBotao();
                botao.setBackground(Color.decode("#808080"));
                posicoes[i] = grelha[linha+i][coluna];
            }
        } else {
            // Horizontal

            // Verifica acesso indevido de posicao
            if(p.getColuna()+tam > 10)
                throw new PosInvalidaException();

            // Verifica se a posicao ja tem um barco
            for (int i = 0; i < tam; i++){
                if (grelha[linha][coluna+i].getBotao().getBackground().equals(Color.decode("#808080")))
                    throw new PosInvalidaException();
            }

            // Adiciona embarcacao no local desejado
            for (int i = 0; i < tam; i++){
                botao = grelha[linha][coluna+i].getBotao();
                botao.setBackground(Color.decode("#808080"));
                posicoes[i] = grelha[linha][coluna+i];
            }
        }
        // Configura posicoes no barco
        e.setPosicoes(posicoes);

        // Adiciona barco no array
        if (e instanceof Submarino){
            submarinos.add(e);
        }
        else if (e instanceof PortaAvioes){
            portaAvioes.add(e);
        }
        else if (e instanceof ContraTorpedeiro){
            contraTorpedeiros.add(e);
        }
        else if (e instanceof NavioTanque){
            naviosTanque.add(e);
        }
    }

    private class DefenseButtonHandler implements ActionListener {
        private final Posicao posicao;
        public DefenseButtonHandler(Posicao p){
            this.posicao = p;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            // Mais um barco adicionado
            barcos++;

            // Seleciona que barco está sendo posicionado de acordo o numero
            try {
                if (barcos <= NUM_SUBS) {
                    colocarEmbarcacao(new Submarino(), posicao, controleOrientacao);
                } else if (barcos <= NUM_CT + NUM_SUBS) {
                    colocarEmbarcacao(new ContraTorpedeiro(), posicao, controleOrientacao);
                } else if (barcos <= NUM_TANKS + NUM_CT + NUM_SUBS) {
                    colocarEmbarcacao(new NavioTanque(), posicao, controleOrientacao);
                } else {
                    colocarEmbarcacao(new PortaAvioes(), posicao, controleOrientacao);
                }
            } catch (PosInvalidaException e) {
                // Nao foi possivel adicionar o barco
                barcos--;
            }

            // Depois de adicionar os 10 barcos o jogo comeca
            if (barcos == 10){
                // Sinaliza o oponente que esta pronto
                if (output != null){
                    // Sinaliza por rede para multiplayer
                    try {
                        output.writeObject(Boolean.TRUE);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // Apenas comeca para singleplayer
                    tAtaque.ativarBotoes(true);
                }
                // Desativa o tabuleiro de defesa
                ativarBotoes(false);

                // Incrementa os barcos para nao cair na funcao novamente
                barcos++;
            }
        }
    }
}
