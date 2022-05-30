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
    
    public String setCor()
    {
    }
    
    public String getCor()
    {
        return this.Cor;
    }
}
