package battleship;

import java.util.Random;

public class JogadorComputador extends Jogador{
    Random numAl = new Random();
    public JogadorComputador(){
        super();
        pane.setVisible(false);
    }

    //gerar posições e orientações aleatóriamente
//    @Override
    public void ColocarEmbarcacoes(){
    Posicao pos = new Posicao();

    boolean posicaoExistente = false;

        // Alocando Submarinos aleatoriamente
        for (int i = 0; i < NUM_SUBS; i++){
            // Laço para garantir a inserção da embarcação caso haja erro
            while(!posicaoExistente) {
                try {
                    // Criando posição aleatórias
                    pos.setLinha((char) (numAl.nextInt(10) + 'A'));
                    pos.setColuna(numAl.nextInt(10));

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
                    pos.setLinha((char) (numAl.nextInt(10) + 'A'));
                    pos.setColuna(numAl.nextInt(10));
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
                    pos.setLinha((char) (numAl.nextInt(10) + 'A'));
                    pos.setColuna(numAl.nextInt(10));
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
                    pos.setLinha((char) (numAl.nextInt(10) + 'A'));
                    pos.setColuna(numAl.nextInt(10));
                    tDefesa.colocarEmbarcacao(new PortaAvioes(), pos, numAl.nextBoolean());
                } catch (PosInvalidaException e) {
                    continue;
                }
                posicaoExistente = true;
            }
            posicaoExistente = false;
        }
    }

}
