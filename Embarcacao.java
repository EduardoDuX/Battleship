public abstract class Embarcacao {
    protected Boolean destruido = false;
    protected int tamanho;

    protected Posicao[] posicoes;
    public void VerificaDestruido()
    {
        for(int i = 0; i < this.tamanho; i++)
        {
            if(posicoes[i].getCor() != "Vermelho")
                return;
        }
        this.destruido = true;
    }

    public Boolean getDestruido() {
        return destruido;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Posicao[] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Posicao[] posicoes){
        this.posicoes = posicoes;
    }
}
