package denlavor.project;

import denlavor.project.utilities.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Account {
    private static int accountCounter = 1;

    private Person person;
    private int agency = 760;
    private int accountNumber;
    private Double balance = 0.0;

    public Account(Person person) {
        this.person = person;
        this.accountNumber = new Random().nextInt(10_000_000);

        accountCounter += 1;
    }

    public String getAllData() {
        return "\nPessoa: " + this.getPerson() +
                "\nAgência: " + this.getAgency() +
                "\nConta: " + this.getAccountNumber() +
                "\nSaldo: " + Utils.doubleToString(getBalance());
//                "\nChave pix: " + this.getPixKey() +
//                "\nPix: " + this.getPixValue();
    }

    public String deposit(Double value) throws Exception {
        String balanceOld = Utils.doubleToString(getBalance());

        if(value <= 0) {
            throw new Exception("Não é possível depositar valores negativos!");
        } else {
            setBalance(getBalance() + value);
        }

        return "\n--- Depositando... ---" +
                "\nSaldo anterior: " + balanceOld +
                "\nNovo saldo: " + Utils.doubleToString(getBalance());
    }

    public String withdraw(Double value) throws Exception {
        String balanceOld = Utils.doubleToString(getBalance());

        if(value <= 0 && getBalance() <= 0) {
            throw new Exception("Não é possível sacar valores negativos!");
        } else {
            setBalance(getBalance() - value);
        }

        return "\n--- Sacando... ---" +
                "\nSaldo anterior: " + balanceOld +
                "\nNovo saldo: " + Utils.doubleToString(getBalance());
    }

    public String transfer(Account accountForTransfer, Double value) throws Exception  {
        String balanceOld = Utils.doubleToString(getBalance());

        if(value <= 0 && getBalance() <= 0) {
            throw new Exception("Não foi possível realizar a transferência!");
        }

        setBalance(getBalance() - value);
        accountForTransfer.setBalance(accountForTransfer.getBalance() + value);

        return "\n--- Transferindo para " + accountForTransfer.getPerson().getName() + "... ---" +
                "\nSaldo anterior: " + balanceOld +
                "\nNovo saldo: " + Utils.doubleToString(getBalance());
    }

    public int getAgency() {
       return this.agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
