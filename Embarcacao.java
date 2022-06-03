public abstract class Embarcacao {
    protected Boolean destruido = false;
    protected int tamanho;

    protected Posicao[] posicoes = new Posicao[tamanho];
    public Boolean getDestruido() {
        return destruido;
    }

    public void setDestruido(Boolean destruido) {
        this.destruido = destruido;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Posicao[] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Posicao[] posicoes) {
        this.posicoes = posicoes;
    }

    public abstract void VerificaDestruido();
}
