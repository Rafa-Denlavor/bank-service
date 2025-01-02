package denlavor.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		BankBranch.operations();


//		Person gigi = new Person("Gigi", "000.000.000-00", 10, "gigi@gmail.com");
//		Person dessa = new Person("Dessa", "000.111.222-33", 32, "dessa@gmail.com");
//
//		Account gigiAcount = new Account(gigi, "0760", 4456565, 12.90, "CELULAR", "(11) 96030-7271");
//		Account dessaAccount = new Account(dessa, "0999", 343434, 0.0, "EMAIL", dessa.getEmail());
//
////		System.out.println(gigiAcount.deposit(10.0));
////		System.out.println(gigiAcount.withdraw(0.0));
//
//
//
//		System.out.println(dessaAccount.getBalance());
//		System.out.println(gigiAcount.transfer(dessaAccount, 2.90));
//		System.out.println(dessaAccount.getBalance());

		SpringApplication.run(BankApplication.class, args);
	}

}
