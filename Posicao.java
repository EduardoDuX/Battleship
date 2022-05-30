/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author Pedro
 */
public class Posicao {
    char Linha;
    int Coluna;
    String Cor;
    public void Posicao(){}
    
    public void setPosicao(char Vetor[])
    {
        this.Linha = Vetor[0];
        this.Coluna = (int)Vetor[1];
    }
    public void setLinha(char linha)
    {
        this.Linha = linha;
    }
    public char getLinha()
    {
        return this.Linha;
    }
    
    public void setColuna(int coluna)
    {
        this.Coluna = coluna;
    }
    public int getColuna()
    {
        return this.Coluna;
    }
    
    public void setCor(String cor)
    {
        this.Cor = cor;
    }
    
    public String getCor()
    {
        return this.Cor;
    }
}
