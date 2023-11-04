package usecases;

import datatransferobject.Request;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;
import entities.BankAccount;

import java.util.Scanner;

public class ConverterDevice {
    private IRequestHandle requestHandle;

    public ConverterDevice(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    public Response runConversion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose how many euros (€) you want to convert : ");
        double euroRate = scanner.nextDouble();

        double poundRate = euroRate * 0.88;
        double dollarRate = euroRate * 1.18;
        double yuanRate = euroRate * 7.95;
        double dinarRate = euroRate * 3.87;
        double yenRate = euroRate * 129.84;

        System.out.println("Equal to :");
        System.out.println("Pound (£) : " + poundRate);
        System.out.println("Dollar ($) : " + dollarRate);
        System.out.println("Yuan (¥ - chinese) : " + yuanRate);
        System.out.println("Dinar : " + dinarRate);
        System.out.println("Yen (¥ - japanese): " + yenRate);

        return new Response("Conversion successful.");
    }
}
