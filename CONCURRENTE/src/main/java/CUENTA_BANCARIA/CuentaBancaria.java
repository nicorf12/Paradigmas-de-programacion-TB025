package CUENTA_BANCARIA;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CuentaBancaria {
    private int saldo;
    private final ReentrantReadWriteLock lock;

    public CuentaBancaria(int saldo) {
        this.saldo = saldo;
        this.lock = new ReentrantReadWriteLock();
    }

    public int verSaldo() {
        lock.readLock().lock();
        int saldoActual = saldo;
        lock.readLock().unlock();
        return saldoActual;
    }

    public void depositar(int cantidad) {
        lock.writeLock().lock();
        saldo += cantidad;
        lock.writeLock().unlock();
    }

    public void extraer(int cantidad) {
        lock.writeLock().lock();
        saldo -= cantidad;
        lock.writeLock().unlock();
    }
}
