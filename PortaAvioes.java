public class PortaAvioes extends Embarcacao {
    private final int tamanho = 2;
    private Posicao[] posicoes = new Posicao[tamanho];
    
    public void setPosicoes(){
        for(int i = 0; i< tamanho; i++)
        {
            posicoes[i] = new Posicao();
        }
    }
    public void VerificaDestruido()
    {
        for(int i = 0; i < this.tamanho; i++)
        {
            if(posicoes[i].getCor() != "Vermelho")
                return;
        }
        this.destruido = true;
    }
}
