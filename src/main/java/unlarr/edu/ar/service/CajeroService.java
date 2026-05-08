package unlarr.edu.ar.service;

import java.time.LocalDateTime;

import unlarr.edu.ar.exceptions.CuentaInactivaException;
import unlarr.edu.ar.exceptions.LimiteExtraccionExcedidoException;
import unlarr.edu.ar.exceptions.SaldoInsuficienteException;

import unlarr.edu.ar.model.CuentaBancaria;
import unlarr.edu.ar.model.TipoTransaccion;
import unlarr.edu.ar.model.Transaccion;

public class CajeroService {

    // DEPOSITO
    
    public void depositar(CuentaBancaria cuenta, double monto)
            throws CuentaInactivaException {

        if (!cuenta.isActiva()) {
            throw new CuentaInactivaException(
                    "La cuenta está inactiva"
            );
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0");
            return;
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);

        Transaccion transaccion = new Transaccion(
                TipoTransaccion.DEPOSITO,
                monto,
                "Depósito realizado"
        );

        cuenta.agregarTransaccion(transaccion);

        registrarLog(
                TipoTransaccion.DEPOSITO,
                monto,
                cuenta.getSaldo()
        );

        System.out.println("Depósito realizado correctamente");
    }

    // EXTRACCION

    public void extraer(CuentaBancaria cuenta, double monto)
            throws CuentaInactivaException,
            SaldoInsuficienteException,
            LimiteExtraccionExcedidoException {

        if (!cuenta.isActiva()) {
            throw new CuentaInactivaException(
                    "La cuenta está inactiva"
            );
        }

        if (monto > 10000) {
            throw new LimiteExtraccionExcedidoException(
                    "No se puede extraer más de $10.000"
            );
        }

        if (monto > cuenta.getSaldo()) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente"
            );
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser positivo");
            return;
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);

        Transaccion transaccion = new Transaccion(
                TipoTransaccion.EXTRACCION,
                monto,
                "Extracción realizada"
        );

        cuenta.agregarTransaccion(transaccion);

        registrarLog(
                TipoTransaccion.EXTRACCION,
                monto,
                cuenta.getSaldo()
        );

        System.out.println("Extracción realizada correctamente");
    }

    // TRANSFERENCIA

    public void transferir(CuentaBancaria origen,
                           CuentaBancaria destino,
                           double monto)
            throws CuentaInactivaException,
            SaldoInsuficienteException {

        if (!origen.isActiva()) {
            throw new CuentaInactivaException(
                    "La cuenta origen está inactiva"
            );
        }

        if (!destino.isActiva()) {
            throw new CuentaInactivaException(
                    "La cuenta destino está inactiva"
            );
        }

        if (monto > origen.getSaldo()) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente para transferir"
            );
        }

        if (monto <= 0) {
            System.out.println("Monto inválido");
            return;
        }

        origen.setSaldo(origen.getSaldo() - monto);

        destino.setSaldo(destino.getSaldo() + monto);

        Transaccion transaccionOrigen = new Transaccion(
                TipoTransaccion.TRANSFERENCIA,
                monto,
                "Transferencia enviada a "
                        + destino.getNumeroCuenta()
        );

        Transaccion transaccionDestino = new Transaccion(
                TipoTransaccion.TRANSFERENCIA,
                monto,
                "Transferencia recibida de "
                        + origen.getNumeroCuenta()
        );

        origen.agregarTransaccion(transaccionOrigen);

        destino.agregarTransaccion(transaccionDestino);

        registrarLog(
                TipoTransaccion.TRANSFERENCIA,
                monto,
                origen.getSaldo()
        );

        System.out.println("Transferencia realizada correctamente");
    }

    // CONSULTA DE SALDO

    public void consultarSaldo(CuentaBancaria cuenta)
            throws CuentaInactivaException {

        if (!cuenta.isActiva()) {
            throw new CuentaInactivaException(
                    "La cuenta está inactiva"
            );
        }

        System.out.println(
                "Saldo actual: $" + cuenta.getSaldo()
        );

        Transaccion transaccion = new Transaccion(
                TipoTransaccion.CONSULTA,
                0,
                "Consulta de saldo"
        );

        cuenta.agregarTransaccion(transaccion);

        registrarLog(
                TipoTransaccion.CONSULTA,
                0,
                cuenta.getSaldo()
        );
    }

    // HISTORIAL

    public void mostrarHistorial(CuentaBancaria cuenta) {

        if (cuenta == null) {
            System.out.println("La cuenta no existe");
            return;
        }

        System.out.println("\n===== HISTORIAL =====");

        for (Transaccion t :
                cuenta.getHistorialTransacciones()) {

            System.out.println(t);
        }
    }

    // LOGGING Y AUDITORIA

    private void registrarLog(TipoTransaccion tipo,
                              double monto,
                              double saldo) {

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(LocalDateTime.now());
        sb.append("] ");

        sb.append(tipo);
        sb.append(": $");
        sb.append(monto);

        sb.append(" | Saldo: $");
        sb.append(saldo);

        System.out.println(sb.toString());
    }
}