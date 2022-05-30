/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author Pedro
 */
public class PortaAvioes extends Embarcacao {
    private final int Tamanho = 2;
    private Posicao[] Posicoes = new Posicao[Tamanho];
    
    public void PortaAvioes(){}
    
    public int getTamanho()
    {
        return this.Tamanho;
    }
    
    public void setPosicoes(Posicao posicoes[]){
        for(int i = 0; i < this.Tamanho; i++)
        {
            this.Posicoes[i] = posicoes[i];
        }
    }
    
    //Verifica se uma posicao "pos" coincide com uma das presentes no objeto
    //caso sim indica a cor como Vermelha e retorna positivo
    public boolean verificaPosicao(Posicao pos)
    {
        for(int i = 0; i < this.Tamanho; i++)
        {
            if(this.Posicoes[i].getLinha() == pos.getLinha() && this.Posicoes[i].getColuna() == pos.getColuna())
            {
                Posicoes[i].setCor("Vermelho");
                return true;
            }
        }
        return false;
    }
    @Override
    //Verifica se todas as cores das posicoes do objeto sao vermelhas, caso sim aponta como verdadeiro o atributo destruido
    public void VerificaDestruido()
    {
        for(int i = 0; i < this.Tamanho; i++)
        {
            if(Posicoes[i].getCor().equals("Vermelho") == false)
                return;
        }
        this.Destruido = true;
    }
}
