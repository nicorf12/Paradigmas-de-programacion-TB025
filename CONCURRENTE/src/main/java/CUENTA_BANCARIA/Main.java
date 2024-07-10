package CUENTA_BANCARIA;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CuentaBancaria usu1 = new CuentaBancaria(5000);
        CuentaBancaria usu2 = new CuentaBancaria(7500);
        CuentaBancaria usu3 = new CuentaBancaria(300);

        Thread de2a1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    new Transaccion(usu2,usu1,2500).ejecutar();
                    //System.out.println("El Usuario2 le dio a Usuario1 $2500.\nUsu1: "+usu1.verSaldo()+"\nUsu2: "+usu2.verSaldo());
                }
            }
        };

        Thread de1a3 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    new Transaccion(usu1,usu3,500).ejecutar();
                    //System.out.println("El Usuario1 le dio a Usuario3 $500.\nUsu3: "+usu3.verSaldo()+"\nUsu1: "+usu1.verSaldo());
                }
            }
        };

        de2a1.start();de1a3.start();
        de2a1.join();de1a3.join();


        Lector lector1 = new Lector();
        Lector lector2 = new Lector();
        ArrayList<CuentaBancaria> usuarios = new ArrayList<>();
        usuarios.add(usu1);
        usuarios.add(usu2);
        usuarios.add(usu3);
        Escritor escritor = new Escritor();

        Thread lecturas1 = new Thread(){
            @Override
            public void run() {
                lector1.leer(usuarios);
            }
        };
        Thread lecturas2 = new Thread(){
            @Override
            public void run() {
                lector2.leer(usuarios);
            }
        };
        Thread escrituras = new Thread(){
            @Override
            public void run() {
                escritor.escribir(usuarios);
            }
        };

        escrituras.start();lecturas2.start();lecturas1.start();
        escrituras.join();lecturas2.join();lecturas1.join();
    }
}
