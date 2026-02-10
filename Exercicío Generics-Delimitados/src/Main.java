import java.util.Locale;
import java.util.Scanner;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;

import model.entities.Product;
import model.services.CalculationService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       List<Product> list = new ArrayList<>();

        String path = "C:\\temp\\in.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while(line != null) {
                String[] fields = line.split(",");
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();
            }
            
            Product x = CalculationService.max(list);
            System.out.println("Most expensive product: ");
            System.out.println(x);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}  