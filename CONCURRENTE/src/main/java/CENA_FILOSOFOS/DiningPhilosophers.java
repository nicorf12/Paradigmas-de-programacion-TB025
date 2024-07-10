package CENA_FILOSOFOS;

import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private Semaphore leftFork;
    private Semaphore rightFork;
    private int id;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                System.out.println("Filosofo " + id + " esta pensando.");
                Thread.sleep((long) (Math.random() * 10000)); // tiempo de pensamiento

                // Adquirir los tenedores de forma alternativa para evitar deadlock
                if (id % 2 == 0) {
                    rightFork.acquire();
                    leftFork.acquire();
                } else {
                    leftFork.acquire();
                    rightFork.acquire();
                }

                // Tiempo de comer
                System.out.println("Filosofo " + id + " empezo a comer.");
                Thread.sleep((long) (Math.random() * 5000)); // tiempo de comer

                // Libera ambos tenedores
                leftFork.release();
                rightFork.release();
                System.out.println("Filosofo " + id + " deja de comer.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 5;
        Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

        // Inicializa los tenedores
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }

        // Inicializa los filósofos
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);
            philosophers[i].start();
        }
    }
}