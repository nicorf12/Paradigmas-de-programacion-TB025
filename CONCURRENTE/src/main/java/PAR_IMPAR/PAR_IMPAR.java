package PAR_IMPAR;

/*
Escribir un programa que utiliza 2 threads, uno imprimiendo números pares y el otro números impares, alternándose
desde 1 hasta un número dado. Implementar sincronización utilizando Locks y Conditions para asegurar que los
números se imprimen en el orden correcto.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PAR_IMPAR {
    ReentrantLock candado = new ReentrantLock();
    Condition numeroEsPar = candado.newCondition();
    Condition numeroEsInpar = candado.newCondition();
    int numero = 1;
    boolean turnoDeLosPares = false;

    public void imprimirPares() {
        int n = 2;

        while (n < 10) {
            candado.lock();
            while (!turnoDeLosPares) {
                try {
                    numeroEsPar.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + n);
            n += 2;
            turnoDeLosPares = false;
            numeroEsInpar.signal();
            candado.unlock();
        }
    }

    void imprimirImpares() {
        int n = 1;
        while (n < 10) {
            candado.lock();
            while (turnoDeLosPares) {
                try {
                    numeroEsInpar.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + n);
            n += 2;
            turnoDeLosPares = true;
            numeroEsPar.signal();
            candado.unlock();
        }
    }
    public static void main(String[] args) {
        PAR_IMPAR pi = new PAR_IMPAR();
        Thread hiloPares = new Thread(){
            public void run(){
                pi.imprimirPares();
            }
        };
        Thread hiloInpares = new Thread(){
            public void run(){
                pi.imprimirImpares();
            }
        };

        hiloPares.start();hiloInpares.start();
    }
}