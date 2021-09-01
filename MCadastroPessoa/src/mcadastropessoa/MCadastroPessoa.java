/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcadastropessoa;

import controller.CPessoa;
import java.util.Scanner;
import model.Pessoa;

/**
 *
 * @author User
 */
public class MCadastroPessoa {

    public static Scanner ler = new Scanner(System.in);
    public static CPessoa cadPessoas = new CPessoa();

    /**
     * Função lê texto serve para resolver problemas do Java com o console na
     * leitura de Strings e Chars
     *
     * @return
     */
    public static String leTexto() {
        Scanner leTexto = new Scanner(System.in);
        return leTexto.nextLine();
    }

    public static void menu() {
        System.out.println("-- Cadastro --");
        System.out.println("1 - Cadastrar Pessoa");
        System.out.println("2 - Alterar Status Pessoa");
        System.out.println("3 - Atualizar Pessoa");
        System.out.println("4 - Deletar Pessoa");
        System.out.println("5 - Imprimir Pessoas");
        System.out.println("0 - Sair da Aplicação");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadPessoa() {
        boolean system;
        do {
            String nomePessoa;
            String cpf;
            String endereco;
            String telefone;
            int idade;
            boolean status;

            System.out.println("----- Cadastro de Pessoas -----");
            System.out.println("");

            System.out.print("Informe o nome da pessoa: ");
            nomePessoa = leTexto();

            boolean testaCPF;
            do {
                System.out.print("Informe o CPF: ");
                cpf = leTexto();
                if (cpf.length() != 11) {
                    testaCPF = true;
                    System.out.println("CPF incorreto, tente novamente!");
                } else {
                    testaCPF = cadPessoas.verCPF(cpf);
                    if (testaCPF) {
                        System.out.println("CPF já cadastrado, tente novamente!");
                    }
                }
            } while (testaCPF);

            System.out.print("Informe o endereço: ");
            endereco = leTexto();

            boolean testaTelefone;
            do {
                System.out.print("Informe o telefone: ");
                telefone = leTexto();
                if (telefone.length() != 10 && telefone.length() != 11) {
                    testaTelefone = true;
                    System.out.println("Telefone informado está incorreto.");
                } else {
                    testaTelefone = false;
                }
            } while (testaTelefone);

            System.out.print("Informe a idade: ");
            idade = ler.nextInt();

            System.out.print("Pessoa está ativa no cadastro? 1- Sim | 2- Não: ");
            byte statusN = ler.nextByte();
            status = statusN == 1;

            Pessoa p = new Pessoa(cadPessoas.addIdPessoa(), nomePessoa, cpf, endereco, telefone, idade, status);
            cadPessoas.addPessoa(p);

            char continuar;
            System.out.print("Deseja continuar cadastrando? S/N: ");

            do {
                continuar = ler.next().toUpperCase().charAt(0);
                system = (continuar == 'S');

                if (!system && continuar != 'N') {
                    System.out.print("\nVocê digitou uma letra inválida, as opção válidas são S ou N!\n"
                            + "Digite novamente:");
                }

            } while (!system && continuar != 'N');
            System.out.println("\n\n\n");
        } while (system);
    }

    public static void alteraStatus() {
        System.out.println("-- Altera Status --");
        System.out.print("\nInforme o Cpf: ");
        String cpf = leTexto();
        if (cadPessoas.verCPF(cpf)) {
            byte status;
            if (cadPessoas.verStatusPes(cpf)) {
                System.out.print("Status ativo. Quer inativar esta pessoa? 0 - sim, 1 - não: ");
                status = ler.nextByte();
            } else {
                System.out.print("Status inativo. Quer ativar esta pessoa? 1 - sim, 0 - não: ");
                status = ler.nextByte();
            }
            cadPessoas.alteraStatus(cpf, status);
        } else {
            System.out.println("Esse CPF não existe!");
            System.out.println("Deseja cadastrar um novo CPF? 1- Sim, 2- Não");
            byte c = ler.nextByte();
            if (c == 1) {
                cadPessoa();
            }
        }
    }
    
    public static void atualizaPessoa() {
        System.out.println("-- Atualiza Pessoa --");
        System.out.print("\nInforme o Cpf: ");
        String cpf = leTexto();
        if (cadPessoas.verCPF(cpf)) {
            Pessoa p = new Pessoa();
            p = cadPessoas.selecionaPes(cpf);
            byte op;
            do {
                System.out.println("Atualização de dados da pessoa: " + p.getNomePessoa());
                System.out.println("1- Atualizar Tudo");
                System.out.println("2- Nome da pessoa");
                System.out.println("3- Endereço");
                System.out.println("4- Telefone");
                System.out.println("5- Idade");
                System.out.println("0- Sair\n");
                System.out.print("Digite o numero da opção: ");
                op = ler.nextByte();
                
                switch (op) {
                    case 1:
                        System.out.print("Nome atual (" + p.getNomePessoa() + ")\nInforme o nome: ");
                        p.setNomePessoa(leTexto());
                        System.out.print("\nEndereço atual (" + p.getEndereco() + ")\nInforme o endereço: ");
                        p.setEndereco(leTexto());
                        System.out.print("\nTelefone atual (" + p.getTelefone() + ")\nInforme o telefone: ");
                        p.setTelefone(leTexto());
                        System.out.print("\nIdade atual ("+ p.getIdade() + ")\nInforme a idade: ");
                        p.setIdade(ler.nextInt());
                        break;
                    case 2:
                        System.out.print("Nome atual (" + p.getNomePessoa() + ")\nInforme o nome: ");
                        p.setNomePessoa(leTexto());
                        break;
                    case 3:
                        System.out.print("\nEndereço atual (" + p.getEndereco() + ")\nInforme o endereço: ");
                        p.setEndereco(leTexto());
                        break;
                    case 4:
                        System.out.print("\nTelefone atual (" + p.getTelefone() + ")\nInforme o telefone: ");
                        p.setTelefone(leTexto());
                        break;
                    case 5:
                        System.out.print("\nIdade atual ("+ p.getIdade() + ")\nInforme a idade: ");
                        p.setIdade(ler.nextInt());
                        break;
                    case 0:
                        op = 0;
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");
                        break;
                }
                
            } while(op != 0);
            System.out.println("Atualização concluída com sucesso!");
        } else {
            System.out.println("CPF não existe!");
            System.out.println("Deseja cadastrar esse CPF? 1- Sim || 0- Não");
            int c = ler.nextInt();
            if (c == 1) {
                cadPessoa();
            }
        }
    }
    
    public static void imprimePessoas() {
        for (Pessoa listPes: cadPessoas.getPessoas()) {
            System.out.println("---");
            System.out.println("Id: " + listPes.getIdPessoa());
            System.out.println("Nome: " + listPes.getNomePessoa());
            System.out.println("CPF: " + listPes.getCpf());
            System.out.println("Telefone: " + listPes.getTelefone());
            System.out.println("Endereço: " + listPes.getEndereco());
            System.out.print("Status: ");
            if (listPes.getStatus()) {
                System.out.println("Ativo\n");
            } else {
                System.out.println("Inativo\n");
            }
        }
    }
    
    public static void main(String[] args) {
        cadPessoas.mokPessoas();
        int opM;
        String s = "s";
        
        do {
            
            menu();
            opM = ler.nextInt();
            switch(opM) {
                case 1:
                    cadPessoa();
                    break;
                case 2:
                    alteraStatus();
                    break;
                case 3:
                    atualizaPessoa();
                    break;
                case 4:
                    break;
                case 5:
                    imprimePessoas();
                    break;
                case 0:
                    s = "n";
                    break;
                default:
                    break;
                    
            }
        } while(s.equalsIgnoreCase("s"));
        
        System.out.println("Aplicação encerrada pelo usuário!");
        
    }

}
