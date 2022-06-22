public class Posicao {
    private char linha;
    private int coluna;
    private String cor;

    public char getLinha() {
        return linha;
    }

    public void setLinha(char linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public String getCor() {
        return cor;
    }


    //    Tem barco(escondido): cinza
//    Tem barco(destruido): vermelho
//    Fog: Branco
//    Mar: Azul
    public void setCor(String cor) {
        this.cor = cor;
    }
}