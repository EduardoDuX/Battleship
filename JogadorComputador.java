public class JogadorComputador extends Jogador{

    public JogadorComputador(){
        super();
    }

    //gerar posições e orientações aleatóriamente
    public void colocarEmbarcacoes(){

        for (int i = 0; i < NUM_SUBS; i++){
            td.colocarEmbarcacao(new Submarino(),new Posicao(), true);
        }
        for (int i = 0; i < NUM_TANKS; i++){
            td.colocarEmbarcacao(new NavioTanque(), new Posicao(), true);
        }
        for (int i = 0; i < NUM_CT; i++){
            td.colocarEmbarcacao(new ContraTorpedeiro(), new Posicao(), true);
        }
        for (int i = 0; i < NUM_PA; i++){
            td.colocarEmbarcacao(new PortaAvioes(), new Posicao(), true);
        }
    }

}
