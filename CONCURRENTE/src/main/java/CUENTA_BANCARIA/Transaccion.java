package CUENTA_BANCARIA;

public class Transaccion {
    private final CuentaBancaria origen;
    private final CuentaBancaria destino;
    private final int cantidad;

    public Transaccion(CuentaBancaria origen, CuentaBancaria destino, int cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    public void ejecutar() {
        origen.extraer(cantidad);
        destino.depositar(cantidad);
    }
}
