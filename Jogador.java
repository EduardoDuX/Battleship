package battleship;
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
            try{             
                td.colocarEmbarcacao(new Submarino(),  new Posicao(), true);
                }   catch(PosInvalidaException e){}       
        }
        
        for (int i = 0; i < NUM_TANKS; i++){
            try{             
                td.colocarEmbarcacao(new NavioTanque(),  new Posicao(), true);
                }   catch(PosInvalidaException e){}
        }
        
        for (int i = 0; i < NUM_CT; i++){
            try{             
                td.colocarEmbarcacao(new ContraTorpedeiro(),  new Posicao(), true);
                }   catch(PosInvalidaException e){}
        }
        
        for (int i = 0; i < NUM_PA; i++){
            try{             
                td.colocarEmbarcacao(new PortaAvioes(),  new Posicao(), true);
                }   catch(PosInvalidaException e){}
        }
    }
}
