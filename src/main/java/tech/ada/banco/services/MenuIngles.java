package tech.ada.banco.services;

public class MenuIngles extends Menu {

    @Override
    public void mostrar() {
        System.out.println("Choose one of the options.");
        System.out.println("0 - End our app.");
        System.out.println("1 - Open an account.");
        System.out.println("2 - To put money.");
        System.out.println("3 - To retrieve money.");
        System.out.println("4 - Do a transfer pix.");
        System.out.println("5 - Know how money i have.");
    }
}
