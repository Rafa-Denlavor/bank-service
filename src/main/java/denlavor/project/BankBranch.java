package denlavor.project;

import denlavor.project.utilities.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankBranch {

    static Scanner input = new Scanner(System.in).useDelimiter("\r?\n");

    static ArrayList<Account> accounts = new ArrayList<Account>();

    // TO DO: Remover após as validações de todos os métodos
    static Person personTest = new Person("Rafaella Cristina", "51438849855", 18, "rafa@gmail.com");
    static Account accountTest = new Account(personTest);

    static Person personTest2 = new Person("Andressa Ferreira", "43343434443", 22, "dessa@gmail.com");
    static Account accountTest2 = new Account(personTest2);

    public static void operations() {
        // TO DO: Remover após as validações de todos os métodos
        accounts.add(accountTest);
        accounts.add(accountTest2);

        System.out.println("\n------------------------------------------------------");
        System.out.println("---------------- Agência Denlavor Tech ---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("|**** Selecione uma operação que deseja realizar ****|");
        System.out.println("------------------------------------------------------");
        System.out.println("|                   [1] Criar conta                  |");
        System.out.println("|                   [2] Depositar                    |");
        System.out.println("|                   [3] Sacar                        |");
        System.out.println("|                   [4] Transferir                   |");
        System.out.println("|                   [5] Listar contas                |");
        System.out.println("|                   [6] Deletar conta                |");
        System.out.println("|                   [7] Sair                         |");
        System.out.println("------------------------------------------------------");

        int operation = input.nextInt();;

        switch (operation) {
            case 1:
                createAccount();
                break;

            case 2:
                deposit();
                break;

            case 3:
                withdraw();
                break;

            case 4:
                transfer();
                break;

            case 5:
                accountList();
                break;

            case 6:
                deleteAccount();
                break;

            case 7:
                exit();
                break;

            default:
                System.out.println("Opção inválida!");
                operations();
                break;
        }
    }

    public static void createAccount() {
        System.out.println("\nNome: ");
        String name = input.next();

        System.out.println("\nCPF: ");
        String cpf = input.next();

        System.out.println("\nIdade: ");
        String age = input.next();

        System.out.println("\nEmail: ");
        String email = input.next();

        Person newPerson = new Person(name, cpf,  Integer.parseInt(age), email);
        Account newAccount = new Account(newPerson);

        accounts.add(newAccount);
        System.out.println("-----------------------------------------------------------");
        System.out.println("Conta criada com sucesso!" +
                "\nCliente: " + newPerson.getName() +
                "\nAgência: " + newAccount.getAgency() +
                "\nNúmero da conta: " + newAccount.getAccountNumber());
        System.out.println("-----------------------------------------------------------");

        operations();
    }

    public static void withdraw() {
        boolean restart = true;
        Account account = null;
        Double value = 0.0;
        String balanceOld;

        while(restart) {
            try {
                System.out.println("Qual o número da conta que deseja sacar?");
                int accountNumber = input.nextInt();

                account = getAccount(accountNumber);
                restart = false;
            } catch(InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite apenas números inteiros. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }
        }

        restart = true;

        if (account == null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Número da conta não existe. Tente novamente!");
            System.out.println("-----------------------------------------------------------");

            deposit();
        }

        while(restart) {
            try {
                System.out.println("Qual valor deseja sacar?");
                value = input.nextDouble();
                restart = false;
            } catch(InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO] Número da conta não existe. Tente novamente!");
                System.out.println("-----------------------------------------------------------");
            }
        }

        restart = true;

        if (value <= 0) {
            System.out.println("Valor inválido!");
        }

        balanceOld = Utils.doubleToString(account.getBalance());
        account.setBalance(account.getBalance() - value);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Saque de " + Utils.doubleToString(value) + " efetuado com sucesso!\n" +
                "Para: " + account.getPerson().getName() +
                "\nSaldo anterior: " + balanceOld +
                "\nNovo saldo: " + Utils.doubleToString(account.getBalance()));
        System.out.println("-----------------------------------------------------------");

        operations();
    }

    public static void transfer() {
        boolean restart = true;
        int accountNumberOrigin = 0;
        int accountNumberDestination = 0;
        double value = 0;

        while(restart) {
            try {
                System.out.println("Qual o número da conta de origem?");
                accountNumberOrigin = input.nextInt();
                restart = false;
            } catch (InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite apenas números inteiros. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }

        }

        while(restart) {
            try {
                System.out.println("Qual o número da conta de destino?");
                accountNumberDestination = input.nextInt();
                restart = false;
            } catch (InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite apenas números inteiros. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }
        }

        Account accountOrigin = getAccount(accountNumberOrigin);
        Account accountDestination = getAccount(accountNumberDestination);

        if(accountOrigin == null || accountDestination == null) {
            throw new RuntimeException("Uma das contas não existe!");
        }

        while(restart) {
            try {
                System.out.println("Qual valor deseja depositar?");
                value = input.nextDouble();
                restart = false;
            } catch (InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite valores em real. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }
        }

        if(value <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }

        if(accountOrigin.getBalance() < value) {
            throw new RuntimeException("Saldo insuficiente na conta de origem.");
        }

        accountOrigin.setBalance(accountOrigin.getBalance() - value);
        accountDestination.setBalance(accountDestination.getBalance() + value);

        String balanceOrigin = Utils.doubleToString(accountOrigin.getBalance());
        String balanceDestination = Utils.doubleToString(accountDestination.getBalance());

        System.out.println("-----------------------------------------------------------");
        System.out.println("Transferência de " + Utils.doubleToString(value) + " efetuada com sucesso!\n"  +
                "De: "+ accountOrigin.getPerson().getName() + "\n" +
                "Para: " + accountDestination.getPerson().getName() +
                "\nSaldo origem: " + balanceOrigin +
                "\nSaldo destino: " + balanceDestination);
        System.out.println("-----------------------------------------------------------");
    }

    public static void accountList() {
        if(accounts.isEmpty()) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Esta agência não possui contas ativas.");
            System.out.println("-----------------------------------------------------------");

            operations();
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("--------------------- CONTAS ATIVAS -----------------------");
        System.out.println("-----------------------------------------------------------");
        for(int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println(
                    "\n| " + (i + 1) + "° conta |" +
                            account.getAllData()

            );
        }
        System.out.println("\n-----------------------------------------------------------");

        operations();
    }

    public static void deposit() {
        boolean restart = true;
        int accountNumber = 0;
        double value = 0;

        while(restart) {
            try {
                System.out.println("Qual o número da conta de destino?");
                accountNumber = input.nextInt();

                restart = false;
            } catch (InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite apenas números inteiros. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }
        }


        Account account = getAccount(accountNumber);

        if(account == null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Número da conta não existe. Tente novamente!");
            System.out.println("-----------------------------------------------------------");

            deposit();
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Tipo de conta:");
        System.out.println("[1] Corrente");
        System.out.println("[2] Poupança");
        System.out.println("-----------------------------------------------------------");
        int accountType = input.nextInt();

        while(restart) {
            try {
                System.out.println("Qual valor deseja depositar?");
                value = input.nextDouble();

                restart = false;
            } catch (InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite apenas valores em real. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }
        }

        if(value <= 0) {
            System.out.println("Valor inválido!");
        }

        switch (accountType) {
            case 1:
                String balanceOld = Utils.doubleToString(account.getBalance());
                account.setBalance(account.getBalance() + value);

                System.out.println("-----------------------------------------------------------");
                System.out.println("Deposito de " + Utils.doubleToString(value)  + " efetuado com sucesso!\n"  +
                        "\nConta: Corrente" +
                        "\nPara: " + account.getPerson().getName() +
                        "\nSaldo anterior: " + balanceOld +
                        "\nNovo saldo: " + Utils.doubleToString(account.getBalance()));
                System.out.println("-----------------------------------------------------------");
                break;
            case 2:
                String savingsbalanceOld = Utils.doubleToString(account.getSavingsBalance());
                account.setSavingsBalance(account.getSavingsBalance() + value);

                System.out.println("-----------------------------------------------------------");
                System.out.println("Deposito de " + Utils.doubleToString(value)  + " efetuado com sucesso!\n"  +
                        "\nConta: Poupança" +
                        "\nPara: " + account.getPerson().getName() +
                        "\nSaldo anterior: " + savingsbalanceOld +
                        "\nNovo saldo: " + Utils.doubleToString(account.getSavingsBalance()));
                System.out.println("-----------------------------------------------------------");
                break;
            default:
                String balanceOldDefault = Utils.doubleToString(account.getBalance());
                account.setBalance(account.getBalance() + value);

                System.out.println("-----------------------------------------------------------");
                System.out.println("--- [ATENÇÃO] CONTA CORRENTE SELECIONADA AUTOMATICAMENTE---");
                System.out.println("-----------------------------------------------------------");
                System.out.println("Deposito de " + Utils.doubleToString(value)  + " efetuado com sucesso!\n"  +
                        "\nConta: Corrente" +
                        "\nPara: " + account.getPerson().getName() +
                        "\nSaldo anterior: " + balanceOldDefault +
                        "\nNovo saldo: " + Utils.doubleToString(account.getBalance()));
                System.out.println("-----------------------------------------------------------");
                break;
        }

        operations();
    }

    public static void deleteAccount() {
        boolean restart = true;
        int accountNumber = 0;

        while(restart) {
            try {
                System.out.println("Qual o número da conta que deve ser deletada?");
                accountNumber = input.nextInt();
            } catch (InputMismatchException err) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("[ATENÇÃO]  Entrada inválida. Digite apenas números inteiros. ");
                System.out.println("-----------------------------------------------------------\n");
                input.next();
            }
        }

        Account account = getAccount(accountNumber);

        if(account == null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Esta conta não existe. Tente novamente!");
            System.out.println("-----------------------------------------------------------");

            deleteAccount();
        }

        int index = accounts.indexOf(account);
        accounts.remove(index);

        System.out.println("-----------------------------------------------------------");
        System.out.println("[INFO] Conta deletada com sucesso!");
        System.out.println("-----------------------------------------------------------");

        operations();
    }

    public static void exit() {
        System.out.println("Ficaremos a disposição!");
        System.exit(0);
    }

    private static Account getAccount(int accountNumber) {
        Account account = null;

        if(!accounts.isEmpty()) {
            for(Account c: accounts) {{
                if(c.getAccountNumber() == accountNumber) {
                    account = c;
                }
            }}
        }

        return account;
    }
}
