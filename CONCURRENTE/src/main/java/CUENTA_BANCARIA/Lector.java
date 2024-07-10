package CUENTA_BANCARIA;

import java.util.ArrayList;

public class Lector {
    public void leer(ArrayList<CuentaBancaria> usuarios){
        int i=1;
        for (CuentaBancaria usu : usuarios) {
            System.out.println("Usuario "+ i + ": " + usu.verSaldo());
            i++;
        }
    }
}
