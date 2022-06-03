public class Jogador {
    private TabuleiroAtaque ta;
    private TabuleiroDefesa td;
    private static final int NUM_SUBS = 4;
    private static final int NUM_TANKS = 3;
    private static final int NUM_CT = 2;
    private static final int NUM_PA = 1;

    public Jogador(){
        ta = new TabuleiroAtaque();
        td = new TabuleiroDefesa();
    }
    //ler as posicoes e orientacoes
    public void colocarEmbarcacoes(){

        for (int i = 0; i < NUM_SUBS; i++){
            td.colocarEmbarcacao(new Submarino(), Posicao p, boolean orientacao);
        }
        for (int i = 0; i < NUM_TANKS; i++){
            td.colocarEmbarcacao(new NavioTanque(), Posicao p, boolean orientacao);
        }
        for (int i = 0; i < NUM_CT; i++){
            td.colocarEmbarcacao(new ContraTorpedeiro(), Posicao p, boolean orientacao);
        }
        for (int i = 0; i < NUM_PA; i++){
            td.colocarEmbarcacao(new PortaAvioes(), Posicao p, boolean orientacao);
        }
    }
}
