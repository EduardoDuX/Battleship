package battleship;
public class JogadorComputador extends Jogador{

    public JogadorComputador(){
        super();
        pane.setVisible(false);
    }

    //gerar posições e orientações aleatóriamente
//    @Override
//    public void colocarEmbarcacoes(){
//
//        for (int i = 0; i < NUM_SUBS; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new Submarino(),  new Posicao(), true);
//                }   catch(PosInvalidaException e){}
//        }
//
//        for (int i = 0; i < NUM_TANKS; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new NavioTanque(),  new Posicao(), true);
//                }   catch(PosInvalidaException e){}
//        }
//
//        for (int i = 0; i < NUM_CT; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new ContraTorpedeiro(),  new Posicao(), true);
//                }   catch(PosInvalidaException e){}
//        }
//
//        for (int i = 0; i < NUM_PA; i++){
//            try{
//                tDefesa.colocarEmbarcacao(new PortaAvioes(),  new Posicao(), true);
//                }   catch(PosInvalidaException e){}
//        }
//    }

}
