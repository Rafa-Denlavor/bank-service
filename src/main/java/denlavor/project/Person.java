package denlavor.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private static int personCounter = 1;

    private String name;
    private String cpf;
    private int age;
    private String email;

    public Person(String name, String cpf, int age, String email) {
        this.name = name;
        this.cpf= cpf;
        this.age = age;
        this.email = email;

        personCounter += 1;
    }

    public String getAllData() {
        return "\nNome: " + this.getName() +
                "\nCPF: " + this.getCpf() +
                "\nIdade: " + this.getAge() +
                "\nEmail: " + this.getEmail();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


