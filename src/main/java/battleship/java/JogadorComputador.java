package battleship.java;
import java.util.ArrayList;
import java.util.Random;

public class JogadorComputador extends Jogador{
    Random numAl;
    protected static final int NUM_SUBS = 4;
    protected static final int NUM_CT = 3;
    protected static final int NUM_TANKS = 2;
    protected static final int NUM_PA = 1;
    ArrayList<Posicao> posicoesNaoAtacadas;
    public JogadorComputador(){
        // Cria um novo jogador e desativa a tela
        super();
        pane.setVisible(false);

        // Cria um gerador aletorio e um array de posicoes a atacar
        numAl = new Random();
        posicoesNaoAtacadas = tAtaque.getPosicoes();
    }
    // Gerador de posicoes aleatorias
    private Posicao geraPosicao(){
        Posicao pos = new Posicao();
        pos.setLinha((char) (numAl.nextInt(10) + 'A'));
        pos.setColuna(numAl.nextInt(10));
        return pos;
    }
    // Posiciona embarcacoes aleatoriamente
    public void colocarEmbarcacoes(){
        boolean posicaoExistente = false;
        // Alocando Submarinos aleatoriamente
        for (int i = 0; i < NUM_SUBS; i++){
            // Laço para garantir a inserção da embarcação caso haja erro
            while(!posicaoExistente) {
                try {
                    // Criando posição aleatórias
                    Posicao pos = geraPosicao();
                    tDefesa.colocarEmbarcacao(new Submarino(), pos, numAl.nextBoolean());
                } catch (PosInvalidaException e) {
                    continue;
                }
                posicaoExistente = true;
            }
            posicaoExistente = false;
        }

        for (int i = 0; i < NUM_CT; i++){
            while(!posicaoExistente) {
                try {
                    Posicao pos = geraPosicao();
                    tDefesa.colocarEmbarcacao(new ContraTorpedeiro(), pos, numAl.nextBoolean());
                } catch (PosInvalidaException e) {
                    continue;
                }
                posicaoExistente = true;
            }
            posicaoExistente = false;
        }

        for (int i = 0; i < NUM_TANKS; i++){
            while(!posicaoExistente) {
                try {
                    Posicao pos = geraPosicao();
                    tDefesa.colocarEmbarcacao(new NavioTanque(), pos, numAl.nextBoolean());
                } catch (PosInvalidaException e) {
                    continue;
                }
                posicaoExistente = true;
            }
            posicaoExistente = false;
        }

        for (int i = 0; i < NUM_PA; i++){
            while(!posicaoExistente) {
                try {
                    Posicao pos = geraPosicao();
                    tDefesa.colocarEmbarcacao(new PortaAvioes(), pos, numAl.nextBoolean());
                } catch (PosInvalidaException e) {
                    continue;
                }
                posicaoExistente = true;
            }
            posicaoExistente = false;
        }

        // Desativa os botoes do tabuleiro de defesa e ativa os de ataque
        tDefesa.ativarBotoes(false);
        tAtaque.ativarBotoes(true);
    }

    // Ataca uma casa nao atacada do oponente
    public void atacar(){
        // Seleciona a casa aleatoriamente
        int index = Math.abs(numAl.nextInt() % posicoesNaoAtacadas.size());

        // "Mira" na posicao gerada
        TabuleiroAtaque.setPosicaoAtacada(posicoesNaoAtacadas.get(index));

        // Ataca a posicao e remove do array de possiveis ataques
        tAtaque.atacar();
        posicoesNaoAtacadas.remove(index);
    }
}
