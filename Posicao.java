public class Posicao {
    private char linha;
    private int coluna;
    private String cor;

    public char getLinha() {
        return linha;
    }

    public void setLinha(char linha) {
        if (0 < linha && linha < 11)
            this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        if (Character.isLetter(coluna) && (int) coluna  >= (int)'a' && (int) coluna <= (int)'j' )
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
        if (cor == "cinza" || cor == "vermelho" || cor == "azul" || cor == "branco")
            this.cor = cor;
    }
}