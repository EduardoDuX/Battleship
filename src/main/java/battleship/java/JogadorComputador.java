package battleship.java;
import java.util.ArrayList;
import java.util.Random;

public class JogadorComputador extends Jogador{
    Random numAl;
    
    ArrayList<Posicao> posicoesJaAtacadas;
    public JogadorComputador(){
        super();
        pane.setVisible(false);
        numAl = new Random();
        posicoesJaAtacadas = tAtaque.getPosicoes();
    }

    //gerar posições e orientações aleatóriamente

    private Posicao geraPosicao(){
        Posicao pos = new Posicao();
        pos.setLinha((char) (numAl.nextInt(10) + 'A'));
        pos.setColuna(numAl.nextInt(10));
        return pos;
    }
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
        tDefesa.ativarBotoes(false);
        tAtaque.ativarBotoes(true);
    }

    public void atacar(){
        int index = Math.abs(numAl.nextInt() % posicoesJaAtacadas.size());
        tAtaque.atacar(posicoesJaAtacadas.get(index));
        posicoesJaAtacadas.remove(index);
    }
}
