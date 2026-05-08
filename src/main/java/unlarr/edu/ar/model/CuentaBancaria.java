package unlarr.edu.ar.model;

import java.util.ArrayList;

public class CuentaBancaria {


    private final String numeroCuenta;
    private double  saldo;
    private String titular;
    private boolean activa;

    private ArrayList<Transaccion> historialTransacciones;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {

        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.activa = true;

        this.historialTransacciones = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public boolean isActiva() {
        return activa;
    }

    public ArrayList<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", titular='" + titular + '\'' +
                ", activa=" + activa +
                '}';
    }
}