/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcadastropessoa;

import controller.CPessoa;
import controller.CCarro;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import model.Pessoa;
import model.Carro;

/**
 *
 * @author User
 */
public class MCadastroPessoa {

    public static Scanner ler = new Scanner(System.in);
    public static CPessoa cadPessoas = new CPessoa();
    public static CCarro cadCarros = new CCarro();

    /**
     * Função lê texto serve para resolver problemas do Java com o console na
     * leitura de Strings e Chars
     *
     * @return
     */
    public static String leTexto() {
        Scanner leTexto = new Scanner(System.in);
        return leTexto.nextLine().trim().replaceAll("  +", " ");
    }
    
    public static String leTexto(boolean validacao) {
        Scanner leTexto = new Scanner(System.in);
        String leitura;
        if (validacao == true) {
            do {
                System.out.print("Campo obrigatório.\nPreencha-o: ");
                leitura = leTexto.nextLine().trim();
            } while(leitura.isBlank());
        } else {
            leitura = leTexto.nextLine().trim();
        }
        return leitura;
    }
    
    public static String leNomeCompleto() {
        Scanner leNome = new Scanner(System.in);
        String nome;
        boolean condition;
        do {
            nome = leNome.nextLine().toUpperCase().trim().replaceAll("  +", " ");
            condition = (nome.isBlank());
            if (condition) {
                System.out.print("Você não preencheu o nome completo, preencha corretamente: ");
            } else {
                if (nome.length() > 1) {
                    String[] palavras = nome.split("\\s");
                    nome = "";
                    for(String p:palavras) {
                        String primeiraLetra = p.substring(0,1);
                        String letrasRestantes = p.substring(1).toLowerCase();
                        nome += primeiraLetra + letrasRestantes + " ";
                    }
                }
            }
        } while(condition);
        return nome.trim();
    }
    
    public static String leCpf() {
        Scanner leCpf = new Scanner(System.in);
        String cpf;
        boolean condition;
        do {
            cpf = leCpf.nextLine().trim().replaceAll("  +", " ");
            condition = (cpf.isBlank() || cpf.length() != 11);
            if (condition) {
                System.out.print("Você não preencheu o seu CPF corretamente (11 dígitos), preencha-o novamente: ");
            }
        } while(condition);
        return cpf;
    }
    
    public static void menuPrincipal() {
        System.out.println("-- Menu Principal --");
        System.out.println("1 - Menu de Pessoas");
        System.out.println("2 - Menu de Carros");
        System.out.println("0 - Encerrar o Programa");
        System.out.print("Escolha uma opção: ");
    }
    
    public static void menuPessoa() {
        System.out.println("\n-- Menu Pessoa --");
        System.out.println("1 - Cadastrar Pessoa");
        System.out.println("2 - Alterar Status Pessoa");
        System.out.println("3 - Atualizar Pessoa");
        System.out.println("4 - Deletar Pessoa");
        System.out.println("5 - Imprimir Pessoas");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
    
    public static void menuCarro() {
        System.out.println("\n-- Menu Carro --");
        System.out.println("1 - Cadastrar Carro");
        System.out.println("2 - Atualizar Carro");
        System.out.println("3 - Deletar Carro");
        System.out.println("4 - Imprimir Carros");
        System.out.println("0 - Voltar");
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

            System.out.println("----- Cadastro de Pessoas -----\n");
            
            System.out.print("Informe o nome da pessoa: ");
            nomePessoa = leNomeCompleto();

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
                System.out.print("Digite aqui o número da opção desejada:");
                op = ler.nextByte();
                
                switch (op) {
                    case 1:
                        System.out.print("Nome atual (" + p.getNomePessoa() + ")\nInforme o nome: ");
                        p.setNomePessoa(leNomeCompleto());
                        System.out.print("\nEndereço atual (" + p.getEndereco() + ")\nInforme o endereço: ");
                        p.setEndereco(leTexto());
                        System.out.print("\nTelefone atual (" + p.getTelefone() + ")\nInforme o telefone: ");
                        p.setTelefone(leTexto());
                        System.out.print("\nIdade atual ("+ p.getIdade() + ")\nInforme a idade: ");
                        p.setIdade(ler.nextInt());
                        break;
                    case 2:
                        System.out.print("Nome atual (" + p.getNomePessoa() + ")\nInforme o nome: ");
                        p.setNomePessoa(leNomeCompleto());
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
        if (!cadPessoas.getPessoas().isEmpty()) {
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
        } else {
            System.out.println("Não existem pessoas cadastradas.");
        }
    }
    
    public static void deletarPessoa() {
        System.out.println("-- Deletar Pessoa --");
        boolean delP;
        do {
            delP = false;
            System.out.print("\nInforme o Cpf: ");
            String cpf = leTexto();
            if (cadPessoas.verCPF(cpf)) {
                Pessoa p = new Pessoa();
                p = cadPessoas.selecionaPes(cpf);
                System.out.println("Deseja realmente deletar esta pessoa? "+p.getNomePessoa()
                + " 1 - Sim | 0 - Não");
                int i = ler.nextInt();
                if (i == 1) {
                    if (cadPessoas.deletarPessoa(p)) {
                        System.out.println("Pessoa deletada com sucesso!");
                    } else {
                        System.out.println("Ocorreu um erro ao deletar!");
                    }
                } else {
                    System.out.println("Ok, entendemos sua decisão.");
                }
            } else {
                System.out.println("Esse CPF é inválido.");
                System.out.print("Deseja tentar novamente? 1 - Sim | 0 - Não: ");
                int i = ler.nextInt();
                delP = (i == 1);
            }
        } while(delP);
    }
    
    public static void cadastrarCarro() {
        boolean system;
        do {
            System.out.println("\n\n--- Cadastro de Carro ---");
            Carro c = new Carro();
            boolean testaPlaca;
            do {
                System.out.print("Digite a placa no formato ABC1D23: ");
                String placa = leTexto().toUpperCase();
                testaPlaca = placa.length() == 7 && !cadCarros.verPlaca(placa);
                if (!testaPlaca) {
                    System.out.println("Placa já cadastrada ou digitada incorretamente, tente novamente!");
                } else {
                    c.setPlaca(placa);
                }
            } while (!testaPlaca);
            
            System.out.print("Digite a marca: ");
            c.setMarca(leTexto().toUpperCase());
            
            System.out.print("Digite o modelo: ");
            c.setModelo(leTexto().toUpperCase());
            
            Calendar cal = GregorianCalendar.getInstance();
            int anoAtual = cal.get(Calendar.YEAR);
            boolean verificaAnoFabricacao;
            do {
                System.out.print("Digite o ano de fabricação: ");
                c.setAnoFabricacao(ler.nextInt());
                verificaAnoFabricacao = c.getAnoFabricacao() > anoAtual;
                
                if (verificaAnoFabricacao) {
                    System.out.println("Ano fabricação inválido, tente novamente!");
                }
                
            } while(verificaAnoFabricacao);
                
            boolean verificaAnoModelo;
            do {
                System.out.print("Digite o ano do modelo: ");
                int anoModelo = ler.nextInt();
                verificaAnoModelo = cadCarros.verificarAnoModelo(c.getAnoFabricacao(), anoModelo);
                if (verificaAnoModelo) {
                    c.setAnoModelo(anoModelo);
                } else {
                    System.out.println("Ano modelo inválido, tente novamente!");
                }
            } while(!verificaAnoModelo);
                
            System.out.print("Digite a cor: ");
            c.setCor(leTexto().toUpperCase());
            
            System.out.print("Digite o número de portas: ");
            c.setnPortas(ler.nextInt());
            
            boolean verificaCpf;
            do {
                System.out.print("Informe o CPF do proprietário: ");
                String cpf = leTexto();
                verificaCpf = cadPessoas.verCPF(cpf);
                if (verificaCpf) {
                    c.setIdPessoa(cadPessoas.pesquisarIdPessoa(cpf));
                } else {
                    System.out.println("CPF inválido, tente novamente!");
                }
            } while(!verificaCpf);
            
            cadCarros.addCarro(c);
            System.out.println("Carro sob a placa: " + c.getPlaca()
                    + " e atribuido ao proprietário(a) " + cadPessoas.getNomePessoa(c.getIdPessoa()));
            
            char continuar;
            do {
                System.out.print("Deseja continuar cadastrando carros? S/N: ");
                continuar = ler.next().toUpperCase().charAt(0);
            } while(continuar != 'N' && continuar != 'S');
            system = continuar == 'S';
            System.out.println("");
        } while(system);
    }
    
    public static void imprimirCarros() {
        System.out.println("--- Lista de Carros ---");
        for (Carro listCar: cadCarros.getCarros()) {
            System.out.println("Placa: " + listCar.getPlaca());
            System.out.println("Marca: " + listCar.getMarca());
            System.out.println("Modelo: " + listCar.getModelo());
            System.out.println("Proprietário: " + cadPessoas.getNomePessoa(listCar.getIdPessoa()));
            System.out.println("");
        }
    }
    
    public static void atualizarCarro() {
        System.out.println("--- Atualizar Carro ---");
        System.out.print("\nInforme a placa: ");
        String placa = leTexto();
        if (cadCarros.verPlaca(placa)) {
            Carro c = cadCarros.selecionaCarro(placa);
            byte op;
            System.out.print("Deseja realmente atualizar este carro?"
                + "\nProprietário: " + cadPessoas.getNomePessoa(c.getIdPessoa())
                + "\nPlaca: " + c.getPlaca()
                + "\nMarca: " + c.getMarca()
                + "\nModelo: " + c.getModelo()
                + "\nCor: " + c.getCor()
                + "\nDigite 1 para sim, 0 para não: ");
            op = ler.nextByte();
            switch (op) {
                case 1:
                    boolean update;
                    do {
                        System.out.print("\nAtualizações:"
                                + "\n1- Cor:"
                                + "\n2- Proprietário"
                                + "\n0- Sair"
                                + "\nDigite a opção: ");
                        op = ler.nextByte();
                        switch (op) {
                            case 1:
                                System.out.print("Informe a cor: ");
                                c.setCor(leTexto());
                                update = false;
                                System.out.println("Cor alterada com sucesso.");
                                break;
                            case 2:
                                boolean verificaCpf;
                                do {
                                    System.out.print("Informe o CPF do novo proprietário: ");
                                    String cpf = leTexto();
                                    verificaCpf = cadPessoas.verCPF(cpf);
                                    if (verificaCpf) {
                                        c.setIdPessoa(cadPessoas.pesquisarIdPessoa(cpf));
                                    } else {
                                        System.out.println("CPF inválido, tente novamente!");
                                    }
                                } while(!verificaCpf);
                                update = false;
                                System.out.println("Proprietário alterado com sucesso.");
                                break;
                            case 0:
                                System.out.println("Cancelando a operação.");
                                update = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                update = true;
                                break;
                        }
                    } while(update);
                    break;
                case 0:
                    System.out.println("Operação cancelada.");
                    break;
                default:
                    System.out.println("Opção inválida, digite 1 para sim ou 0 para não.");
                    break;
            }
        } else {
            System.out.println("Essa placa não foi encontrada ou é inválida!");
        }
    }
    
    public static void deletarCarro() {
        System.out.println("--- Deletar Carro ---");
        boolean continuar = false;
        do {
            System.out.print("\nInforme a placa: ");
            String placa = leTexto();
            if (cadCarros.verPlaca(placa)) {
                Carro c = new Carro();
                c = cadCarros.selecionaCarro(placa);
                System.out.print("Deseja realmente deletar este carro?"
                    + "\nProprietário: " + cadPessoas.getNomePessoa(c.getIdPessoa())
                    + "\nPlaca: " + c.getPlaca()
                    + "\nMarca: " + c.getMarca()
                    + "\nModelo: " + c.getModelo()
                    + "\nCor: " + c.getCor()
                    + "\nDigite 1 para sim, 0 para não: ");
                byte op = ler.nextByte();
                if (op == 1) {
                    if (cadCarros.removeCarro(c)) {
                        System.out.println("Carro deletado com sucesso!");
                    } else {
                        System.out.println("Ocorreu um erro ao deletar o carro. Entre em contato com o desenvolvedor do sistema.");
                    }
                } else {
                    System.out.println("A remoção do carro foi cancelada.");
                }
            } else {
                System.out.println("A placa é inválida ou não foi encontrada.");
                System.out.print("Deseja tentar deletar novamente? 1 - Sim | 0 - Não: ");
                byte op = ler.nextByte();
                continuar = op == 1;
            }
        } while(continuar);
    }
    
    public static void main(String[] args) {
        cadPessoas.mokPessoas();
        cadCarros.mokCarros();
        byte opM;
        String s = "s";
        
        do {
            menuPrincipal();
            opM = ler.nextByte();
            switch(opM) {
                case 1:
                    menuPessoa();
                    opM = ler.nextByte();
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
                            deletarPessoa();
                            break;
                        case 5:
                            imprimePessoas();
                            break;
                        case 0:
                            // Voltar
                            break;
                        default:
                            System.out.println("Opção inválida tente novamente.\n");
                            break;  
                    }
                    break;
                case 2:
                    menuCarro();
                    opM = ler.nextByte();
                    switch(opM) {
                        case 1:
                            cadastrarCarro();
                            break;
                        case 2:
                            atualizarCarro();
                            break;
                        case 3:
                            deletarCarro();
                            break;
                        case 4:
                            imprimirCarros();
                            break;
                        case 0:
                            // Voltar
                            break;
                        default:
                            System.out.println("Opção inválida tente novamente.\n");
                            break;
                    }
                    break;
                case 0:
                    s = "n";
                    break;
                default:
                    System.out.println("Opção inválida tente novamente.\n");
            }
            System.out.println("");
        } while(s.equalsIgnoreCase("s"));
        
        System.out.println("Aplicação encerrada pelo usuário!");
        
    }

}
