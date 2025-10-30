package models.CuentasBancarias;

import exceptions.SaldoInsuficiente;
import models.Usuarios.Cliente;

import java.util.ArrayList;

public abstract class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private Cliente propietario;
    private ArrayList<Transaccion> movimientos;

    public Cuenta(String numeroCuenta, double saldoInicial, Cliente propietario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.propietario = propietario;
        this.movimientos = new ArrayList<>();
    }
    public void depositar(double monto){
        if(monto >0){
            this.saldo += monto;
        }
    }
    public abstract void retirar(double monto) throws SaldoInsuficiente;
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public ArrayList<Transaccion> getMovimientos() {
        return movimientos;
    }
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
