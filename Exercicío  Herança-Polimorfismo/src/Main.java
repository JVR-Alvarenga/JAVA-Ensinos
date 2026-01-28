import java.util.Locale;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import entities.Employee;
import entities.OutsourceEmployee;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in, "UTF-8");
            
        System.out.print("Quantos funcionarios: ");
        int n = input.nextInt();

        List<Employee> employees = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            System.out.printf("Funcionarario #%d dados: %n", i+ 1);
            System.out.print("Terceirizado (y/n)? ");
            char cargo = input.next().charAt(0);

            System.out.print("Nome: ");
            input.nextLine();
            String name = input.nextLine();
            
            System.out.print("Horas: ");
            int hours = input.nextInt();
            
            System.out.print("Valor por hora: ");
            double valuePerHour = input.nextDouble();
            
            if (cargo == 'y' || cargo == 'Y') {
                System.out.print("Custo adicional: ");
                double additionalCharge = input.nextDouble();

                OutsourceEmployee employee = new OutsourceEmployee(name, hours, valuePerHour, additionalCharge);
                employees.add(employee);
            } else {
                Employee employee = new Employee(name, hours, valuePerHour);
                employees.add(employee);
            }
        }

        System.out.println();
        System.out.println("Pagamentos: ");
        for (Employee emp : employees) {
            System.out.printf("%s - $ %.2f%n", emp.getName(), emp.payment());
        }

        input.close();
    }
} 