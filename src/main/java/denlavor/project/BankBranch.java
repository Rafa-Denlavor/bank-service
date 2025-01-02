package denlavor.project;

import denlavor.project.utilities.Utils;

import java.util.ArrayList;
import java.util.Collection;
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

        System.out.println(accountTest.getAccountNumber() + " + " + accountTest2.getAccountNumber() + '\n');

        System.out.println("\n------------------------------------------------------");
        System.out.println("---------------- Agência Denlavor Tech ---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("|**** Selecione uma operação que deseja realizar ****|");
        System.out.println("------------------------------------------------------");
        System.out.println("|               Opção 1 - Criar conta                |");
        System.out.println("|               Opção 2 - Depositar                  |");
        System.out.println("|               Opção 3 - Sacar                      |");
        System.out.println("|               Opção 4 - Transferir                 |");
        System.out.println("|               Opção 5 - Listar contas              |");
        System.out.println("|               Opção 6 - Deletar conta              |");
        System.out.println("|               Opção 7 - Sair                       |");
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
        System.out.println("Qual o número da conta que deseja sacar?");
        int accountNumber = input.nextInt();

        Account account = getAccount(accountNumber);

        if(account == null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Número da conta não existe. Tente novamente!");
            System.out.println("-----------------------------------------------------------");

            deposit();
        }

        System.out.println("Qual valor deseja sacar?");
        Double value = input.nextDouble();

        if(value <= 0) {
            System.out.println("Valor inválido!");
        }

        String balanceOld = Utils.doubleToString(account.getBalance());
        account.setBalance(account.getBalance() - value);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Saque de " + Utils.doubleToString(value)  + " efetuado com sucesso!\n"  +
                "Para: " + account.getPerson().getName() +
                "\nSaldo anterior: " + balanceOld +
                "\nNovo saldo: " + Utils.doubleToString(account.getBalance()));
        System.out.println("-----------------------------------------------------------");

        operations();
    }

    public static void transfer() {
        System.out.println("Qual o número da conta de origem?");
        int accountNumberOrigin = input.nextInt();

        System.out.println("Qual o número da conta de destino?");
        int accountNumberDestination = input.nextInt();

        Account accountOrigin = getAccount(accountNumberOrigin);
        Account accountDestination = getAccount(accountNumberDestination);

        if(accountOrigin == null || accountDestination == null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Uma das contas não existe! Vamos tentar novamente?");
            System.out.println("-----------------------------------------------------------\n");

            transfer();
        }

        System.out.println("Qual valor deseja depositar?");
        Double value = input.nextDouble();

        if(value <= 0) {
            System.out.println("Valor inválido!");
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

        operations();
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
                    "\n" + (i + 1) + "° conta" +
                            "\nNome: " + account.getPerson().getName() +
                            "\nNúmero da conta: " + account.getAccountNumber()

            );

        }
        System.out.println("-----------------------------------------------------------");

        operations();
    }

    public static void deposit() {
        System.out.println("Qual o número da conta de destino?");
        int accountNumber = input.nextInt();

        Account account = getAccount(accountNumber);

        if(account == null) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[ATENÇÃO] Número da conta não existe. Tente novamente!");
            System.out.println("-----------------------------------------------------------");

            deposit();
        }

        System.out.println("Qual valor deseja depositar?");
        Double value = input.nextDouble();

        if(value <= 0) {
            System.out.println("Valor inválido!");
        }

        String balanceOld = Utils.doubleToString(account.getBalance());
        account.setBalance(account.getBalance() + value);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Deposito de " + Utils.doubleToString(value)  + " efetuado com sucesso!\n"  +
                "Para: " + account.getPerson().getName() +
                "\nSaldo anterior: " + balanceOld +
                "\nNovo saldo: " + Utils.doubleToString(account.getBalance()));
        System.out.println("-----------------------------------------------------------");

        operations();
    }

    public static void deleteAccount() {
        System.out.println("Qual o número da conta que deve ser deletada?");
        int accountNumber = input.nextInt();

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
