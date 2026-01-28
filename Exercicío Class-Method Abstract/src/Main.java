import java.util.Locale;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import entities.Person;
import entities.NaturalPerson;
import entities.LegalEntity;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in, "UTF-8");
            
        System.out.print("Digite o número de contribuintes: ");
        Integer n = input.nextInt();

        List<Person> list = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            System.out.printf("Contribuinte #%d dados: %n", i+ 1);
            System.out.print("Pessoa natural ou jurídica (n/j)? ");
            char type = input.next().charAt(0);

            System.out.print("Nome: ");
            input.nextLine();
            String name = input.nextLine();
            System.out.print("Renda anual: ");
            Double anualIncome = input.nextDouble();

            if (type == 'n' || type == 'N') {
                System.out.print("Gastos com saúde: ");
                Double healthExpenditures = input.nextDouble();

                list.add(new NaturalPerson(name, anualIncome, healthExpenditures));
            } else if (type == 'j' || type =='J') {
                System.out.print("Número de funcionários: ");
                Integer numberOfEmployees = input.nextInt();

                list.add(new LegalEntity(name, anualIncome, numberOfEmployees));
            }
        }

        Double sum = 0.0;
        System.out.println();
        System.out.println("Impostos pagos: ");
        for (Person person : list) {
            System.out.printf("%s - $ %.2f%n", person.getName(), person.individualTax());
            sum += person.individualTax();
        }

        System.out.println();
        System.out.printf("TOTAL DE IMPOSTOS: $ %.2f%n", sum);

        input.close();
    }
} 