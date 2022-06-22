package battleship;
public class JogadorComputador extends Jogador{

    public JogadorComputador(){
        super();
    }

    //gerar posições e orientações aleatóriamente
    @Override
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
