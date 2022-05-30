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
    
    public void setPosicoes(){
        for(int i = 0; i< Tamanho; i++)
        {
            Posicoes[i] = new Posicao();
        }
    }
    public void VerificaDestruido()
    {
        for(int i = 0; i < this.Tamanho; i++)
        {
            if(Posicoes[i].getCor() != "Vermelho")
                return;
        }
        this.Destruido = true;
    }
}
