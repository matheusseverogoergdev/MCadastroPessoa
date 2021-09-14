/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Pessoa;

/**
 *
 * @author User
 */
public class CPessoa {
    /**
     * Repositório de pessoas.
     */
    ArrayList<Pessoa> pessoas = new ArrayList<>();
    int idPessoa = 1;
    
    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public int addIdPessoa() {
        return this.idPessoa++;
    }
    
    public void addPessoa(Pessoa p) {
        this.pessoas.add(p);
    }
    
    public void removePessoa(Pessoa p) {
        this.pessoas.remove(p);
    }
    
    /**
     * Método mok somente para testes, não faz parte da aplicação final.
     */
    public void mokPessoas() {
        Pessoa p1 = new Pessoa();
        p1.setIdPessoa(this.addIdPessoa());
        p1.setNomePessoa("Felispino");
        p1.setCpf("12312312312");
        p1.setEndereco("Mario Quintana");
        p1.setTelefone("51999999999");
        p1.setIdade(39);
        p1.setStatus(true);
        this.addPessoa(p1);
        
        Pessoa p2 = new Pessoa();
        p2.setIdPessoa(this.addIdPessoa());
        p2.setNomePessoa("Juvenal");
        p2.setCpf("32132132132");
        p2.setEndereco("Av. dos Estados");
        p2.setIdade(35);
        p2.setTelefone("51989898989");
        p2.setStatus(false);
        this.addPessoa(p2);
    }
    
    public boolean verCPF(String cpf) {
        boolean verCPF = false;
        for (Pessoa listPes: pessoas){
            if (listPes.getCpf().equals(cpf)) {
                verCPF = true;
                break;
            }
        }
        return verCPF;
    }
    
    public boolean verStatusPes(String cpf) {
        boolean verStatus = false;
        for (Pessoa listPes: pessoas) {
            if (listPes.getCpf().equals(cpf)) {
                verStatus = listPes.getStatus();
                break;
            }
        }
        
        return verStatus;
    }
    
    public Pessoa selecionaPes(String cpf) {
        Pessoa p = new Pessoa();
        for(Pessoa listPes: pessoas) {
            if (listPes.getCpf().equals(cpf)) {
                p = listPes;
                break;
            }
        }
        return p;
    }
    
    public void alteraStatus(String cpf, byte status) {
        for (Pessoa listPes: pessoas) {
            if (listPes.getCpf().equals(cpf)) {
                listPes.setStatus((status==1));
                break;
            }
        }
    }
    
    public boolean deletarPessoa(Pessoa p) {
        boolean remove = this.pessoas.remove(p);
        return remove;
    }
    
    public int pesquisarIdPessoa(String cpf) {
        int idPessoa = 0;
        for (Pessoa listPes: pessoas) {
            if (listPes.getCpf().equals(cpf)) {
                idPessoa = listPes.getIdPessoa();
                break;
            }
        }
        
        return idPessoa;
    }
    
    public String getNomePessoa(int idPessoa) {
        String nome = null;
        for (Pessoa listPes: pessoas) {
            if (listPes.getIdPessoa() == idPessoa) {
                nome = listPes.getNomePessoa();
                break;
            }
        }
        return nome;
    }
    
}
