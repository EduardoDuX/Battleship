public class Jogador {
    protected TabuleiroAtaque ta;
    protected TabuleiroDefesa td;
    protected static final int NUM_SUBS = 4;
    protected static final int NUM_TANKS = 3;
    protected static final int NUM_CT = 2;
    protected static final int NUM_PA = 1;

    public Jogador(){
        ta = new TabuleiroAtaque();
        td = new TabuleiroDefesa();
    }
    //ler as posicoes e orientacoes
    public void colocarEmbarcacoes(){

        for (int i = 0; i < NUM_SUBS; i++){
            td.colocarEmbarcacao(new Submarino(),  new Posicao(), true);
        }
        for (int i = 0; i < NUM_TANKS; i++){
            td.colocarEmbarcacao(new NavioTanque(),  new Posicao(),true);
        }
        for (int i = 0; i < NUM_CT; i++){
            td.colocarEmbarcacao(new ContraTorpedeiro(),  new Posicao(),true);
        }
        for (int i = 0; i < NUM_PA; i++){
            td.colocarEmbarcacao(new PortaAvioes(),  new Posicao(),true);
        }
    }
}
