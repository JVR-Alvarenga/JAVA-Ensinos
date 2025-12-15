import java.util.Locale;
import java.util.Scanner;
import entities.Bank;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name;
        char action, stop;
        int account;
        Bank client;

        System.out.print("Digite a conta: ");
        account = input.nextInt();
        System.out.print("Digite o nome: ");
        input.nextLine();
        name = input.nextLine();

        System.out.print("Deseja fazer deposito inicial? (s/n): ");
        action = input.next().charAt(0);

        if (action == 's' || action == 'S') {
            System.out.print("Digite o valor do deposito inicial: ");
            double initialDeposit = input.nextDouble();

            client = new Bank(account, name, initialDeposit);
        } else {
            client = new Bank(account, name);
        }

        System.out.println("Seus dados: ");
        System.out.println(client);

        do {
            System.out.println("Quer fazer um deposito ou saque? (d/s): ");
            action = input.next().charAt(0);
    
            if (action == 'd' || action == 'D') {
                System.out.print("Digite o valor do deposito: ");
                double depositAmount = input.nextDouble();
    
                client.deposit(depositAmount);
            } else if (action == 's' || action == 'S') {
                System.out.print("Digite o valor do saque: ");
                double withdrawalAmount = input.nextDouble();
    
                client.withdrawal(withdrawalAmount);
            }
            System.out.println("Dados da conta atualizados:");
            System.out.println(client);
            input.nextLine();

            System.out.print("Deseja continuar? (s/n): ");
            stop = input.next().charAt(0);
        } while (stop != 'n' && stop != 'N');


        input.close();
    }
}