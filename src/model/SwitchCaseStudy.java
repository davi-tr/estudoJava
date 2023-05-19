package model;
import java.util.Scanner;

public class SwitchCaseStudy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        String dia;

        switch (x){
            case 1:
                dia = "Domingo";
                break;
            case 2:
                dia = "Segunda";
                break;
            case 3:
                dia = "Terça";
                break;
            case 4:
                dia = "Quarta";
                break;
            case 5:
                dia = "Quinta";
                break;
            case 6:
                dia = "Sexta";
                break;
            case 7:
                dia = "Sabado";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + x + " Valor deve ser maior 0 e menor ou igual a 7");
        }
        System.out.println("Dia da semana = " + dia);


    }
}
