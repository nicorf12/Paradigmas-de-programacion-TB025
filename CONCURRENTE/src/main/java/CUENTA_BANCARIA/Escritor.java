package CUENTA_BANCARIA;

import java.util.ArrayList;

public class Escritor {
    public void escribir(ArrayList<CuentaBancaria> usuarios){
        CuentaBancaria Banco = new CuentaBancaria(10000);
        for (CuentaBancaria usu : usuarios) {
            new Transaccion(Banco,usu,50000).ejecutar();
        }
    }
}
