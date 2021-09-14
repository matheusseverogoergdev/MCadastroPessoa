/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Carro;

/**
 *
 * @author User
 */
public class CCarro {
    ArrayList<Carro> carros = new ArrayList<>();
    int idCarro = 1;
    
    public int addIdCarro() {
        return this.idCarro++;
    }

    public ArrayList<Carro> getCarros() {
        return this.carros;
    }
    
    public void addCarro(Carro c) {
        this.carros.add(c);
    }
    
    public boolean removeCarro(Carro c) {
        boolean rc = this.carros.remove(c);
        return rc;
    }
    
    public void mokCarros() {
        Carro c1 = new Carro();
        c1.setIdCarro(this.addIdCarro());
        c1.setPlaca("CHS-6647");
        c1.setMarca("Peugeot");
        c1.setModelo("405 GRI");
        c1.setAnoFabricacao(1995);
        c1.setAnoModelo(1996);
        c1.setCor("Verde");
        c1.setnPortas(5);
        c1.setIdPessoa(1);
        this.addCarro(c1);
        
        Carro c2 = new Carro();
        c2.setIdCarro(this.addIdCarro());
        c2.setPlaca("IPP-1234");
        c2.setMarca("GM");
        c2.setModelo("Corsa");
        c2.setAnoFabricacao(1996);
        c2.setAnoModelo(1996);
        c2.setCor("Cinza");
        c2.setnPortas(3);
        c2.setIdPessoa(2);
        this.addCarro(c2);
    }
    
    public boolean verPlaca(String placa) {
        boolean placaEncontrada = false;
        for (Carro listCar: carros){
            if (listCar.getPlaca().equalsIgnoreCase(placa)) {
                placaEncontrada = true;
                break;
            }
        }
        return placaEncontrada;
    }
    
    public boolean verificarAnoModelo(int anoFabricacao, int anoModelo) {
        boolean teste = (anoModelo == anoFabricacao) || (anoModelo == anoFabricacao + 1);
        return teste;
    }
    
    public Carro selecionaCarro(String placa) {
        Carro c = new Carro();
        for(Carro listCarros: carros) {
            if (listCarros.getPlaca().equalsIgnoreCase(placa)) {
                c = listCarros;
                break;
            }
        }
        return c;
    }
    
}
