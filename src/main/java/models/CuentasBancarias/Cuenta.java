//package models.CuentasBancarias;
//
//import exceptions.SaldoInsuficiente;
//import models.Usuarios.Client;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public abstract class Cuenta {
//    private String numeroCuenta;
//    private double saldo;
//    private Client propietario;
//    private ArrayList<Transaccion> movimientos;
//    private LocalDate fechaApertura;
//
//    public Cuenta(String numeroCuenta, double saldoInicial, Client propietario) {
//        this.numeroCuenta = numeroCuenta;
//        this.saldo = saldoInicial;
//        this.propietario = propietario;
//        this.movimientos = new ArrayList<>();
//        this.fechaApertura = LocalDate.now();
//    }
//    public void depositar(double monto){
//        if(monto <=0){
//            throw new IllegalArgumentException("El monto debe ser mayor a cero");
//        }
//        this.saldo += monto;
//        registrarMovimiento("Deposito", monto, "Deposito en cuenta");
//        System.out.println("Deposito exitoso");
//    }
//
//    public abstract void retirar(double monto) throws SaldoInsuficiente;
//
//    public void transferir(Cuenta cuentaDestino, double monto) throws SaldoInsuficiente{
//        if (cuentaDestino == null) {
//            throw new IllegalArgumentException("La cuenta destino no puede ser null");
//        }
//
//        if (monto <= 0) {
//            throw new IllegalArgumentException("El monto debe ser mayor a cero");
//        }
//        if (this.numeroCuenta.equals(cuentaDestino.getNumeroCuenta())) {
//            throw new IllegalArgumentException("No puede transferir a la misma cuenta");
//        }
//        this.retirar(monto);
//        cuentaDestino.depositar(monto);
//
//        this.registrarMovimiento("TRANSFERENCIA_SALIDA", monto,
//                "Transferencia a cuenta " + cuentaDestino.getNumeroCuenta());
//        cuentaDestino.registrarMovimiento("TRANSFERENCIA_ENTRADA", monto,
//                "Transferencia desde cuenta " + this.numeroCuenta);
//        System.out.println("Transferencia con exito");
//    }
//    private void registrarMovimiento(String tipo, double monto, String descripcion) {
//        Transaccion transaccion = new Transaccion(
//                tipo,
//                monto,
//                LocalDateTime.now(),
//                descripcion
//        );
//        movimientos.add(transaccion);
//    }
//    public String getNumeroCuenta() {
//        return numeroCuenta;
//    }
//
//    public double getSaldo() {
//        return saldo;
//    }
//
//    public Client getPropietario() {
//        return propietario;
//    }
//
//    public ArrayList<Transaccion> getMovimientos() {
//        return movimientos;
//    }
//    protected void setSaldo(double saldo) {
//        this.saldo = saldo;
//    }
//    public LocalDate getFechaApertura() {
//        return fechaApertura;
//    }
//
//}
//