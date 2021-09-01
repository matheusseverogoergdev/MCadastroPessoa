/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class Pessoa {

    private int idPessoa;
    private String nomePessoa;
    private String cpf;
    private String endereco;
    private String telefone;
    private int idade;
    private boolean status; // Identifica se a pessoa está ativa ou não.
    
    /**
     * Construtor vazio para podermos usarmos os nossos Getters e Setters.
     */
    public Pessoa() {
        // Construtor vazio
    }
    
    /**
     * Construtor com todos os atributos como parâmetro.
     * Serve para acessar todos os atributos.
     * @param idPessoa
     * @param nomePessoa
     * @param cpf
     * @param endereco
     * @param telefone
     * @param idade
     * @param status 
     */
    public Pessoa(int idPessoa, String nomePessoa, String cpf, String endereco, String telefone, int idade, boolean status) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.idade = idade;
        this.status = status;
    }

    
    /**
     * Pega o id com visibilidade privada da pessoa.
     * @return 
     */
    public int getIdPessoa() {
        return idPessoa;
    }

    /**
     * Insere o id com visibilidade privada da pessoa.
     * @param idPessoa 
     */
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * Pega o nome com visibilidade privada da pessoa.
     * @return 
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * Insere o nome com visibilidade privada da pessoa.
     * @param nomePessoa 
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nPessoa{" + "idPessoa=" + idPessoa + ", nomePessoa=" + nomePessoa + ", cpf=" + cpf + ", endereco=" + endereco + ", telefone=" + telefone + ", idade=" + idade + ", status=" + status + "}\n";
    }
    
}
