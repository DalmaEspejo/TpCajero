package unlarr.edu.ar;

import java.util.Scanner;

import unlarr.edu.ar.exceptions.CuentaInactivaException;
import unlarr.edu.ar.exceptions.LimiteExtraccionExcedidoException;
import unlarr.edu.ar.exceptions.SaldoInsuficienteException;

import unlarr.edu.ar.model.CuentaBancaria;

import unlarr.edu.ar.service.CajeroService;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CajeroService cajero = new CajeroService();

        // =========================
        // CREACION DE CUENTAS
        // =========================

        CuentaBancaria cuenta1 =
                new CuentaBancaria(
                        "001",
                        "Juan Perez",
                        50000
                );

        CuentaBancaria cuenta2 =
                new CuentaBancaria(
                        "002",
                        "Maria Lopez",
                        30000
                );

        CuentaBancaria cuenta3 =
                new CuentaBancaria(
                        "003",
                        "Carlos Diaz",
                        10000
                );

        int opcion;

        do {

            System.out.println("\n==============================");
            System.out.println("      CAJERO AUTOMATICO");
            System.out.println("==============================");

            System.out.println("1. Depositar");
            System.out.println("2. Extraer");
            System.out.println("3. Transferir");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Mostrar historial");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();

            try {

                switch (opcion) {

                    case 1:

                        System.out.print(
                                "Ingrese cuenta (1-2-3): "
                        );

                        int cuentaDep = scanner.nextInt();

                        System.out.print(
                                "Ingrese monto: "
                        );

                        double montoDep =
                                scanner.nextDouble();

                        CuentaBancaria cuentaDeposito =
                                obtenerCuenta(
                                        cuentaDep,
                                        cuenta1,
                                        cuenta2,
                                        cuenta3
                                );

                        cajero.depositar(
                                cuentaDeposito,
                                montoDep
                        );

                        break;

                    case 2:

                        System.out.print(
                                "Ingrese cuenta (1-2-3): "
                        );

                        int cuentaExt = scanner.nextInt();

                        System.out.print(
                                "Ingrese monto: "
                        );

                        double montoExt =
                                scanner.nextDouble();

                        CuentaBancaria cuentaExtraccion =
                                obtenerCuenta(
                                        cuentaExt,
                                        cuenta1,
                                        cuenta2,
                                        cuenta3
                                );

                        cajero.extraer(
                                cuentaExtraccion,
                                montoExt
                        );

                        break;

                    case 3:

                        System.out.print(
                                "Cuenta origen (1-2-3): "
                        );

                        int origen =
                                scanner.nextInt();

                        System.out.print(
                                "Cuenta destino (1-2-3): "
                        );

                        int destino =
                                scanner.nextInt();

                        System.out.print(
                                "Ingrese monto: "
                        );

                        double montoTrans =
                                scanner.nextDouble();

                        CuentaBancaria cuentaOrigen =
                                obtenerCuenta(
                                        origen,
                                        cuenta1,
                                        cuenta2,
                                        cuenta3
                                );

                        CuentaBancaria cuentaDestino =
                                obtenerCuenta(
                                        destino,
                                        cuenta1,
                                        cuenta2,
                                        cuenta3
                                );

                        cajero.transferir(
                                cuentaOrigen,
                                cuentaDestino,
                                montoTrans
                        );

                        break;

                    case 4:

                        System.out.print(
                                "Ingrese cuenta (1-2-3): "
                        );

                        int cuentaSaldo =
                                scanner.nextInt();

                        CuentaBancaria cuentaConsulta =
                                obtenerCuenta(
                                        cuentaSaldo,
                                        cuenta1,
                                        cuenta2,
                                        cuenta3
                                );

                        cajero.consultarSaldo(
                                cuentaConsulta
                        );

                        break;

                    case 5:

                        System.out.print(
                                "Ingrese cuenta (1-2-3): "
                        );

                        int cuentaHist =
                                scanner.nextInt();

                        CuentaBancaria cuentaHistorial =
                                obtenerCuenta(
                                        cuentaHist,
                                        cuenta1,
                                        cuenta2,
                                        cuenta3
                                );

                        cajero.mostrarHistorial(
                                cuentaHistorial
                        );

                        break;

                    case 6:

                        System.out.println(
                                "Saliendo del sistema..."
                        );

                        break;

                    default:

                        System.out.println(
                                "Opcion invalida"
                        );
                }

            } catch (CuentaInactivaException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );

            } catch (SaldoInsuficienteException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );

            } catch (
                    LimiteExtraccionExcedidoException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );
            }

        } while (opcion != 6);

        scanner.close();
    }

    // =========================
    // METODO AUXILIAR
    // =========================

    public static CuentaBancaria obtenerCuenta(
            int opcion,
            CuentaBancaria cuenta1,
            CuentaBancaria cuenta2,
            CuentaBancaria cuenta3) {

        switch (opcion) {

            case 1:
                return cuenta1;

            case 2:
                return cuenta2;

            case 3:
                return cuenta3;

            default:
                return null;
        }
    }
}