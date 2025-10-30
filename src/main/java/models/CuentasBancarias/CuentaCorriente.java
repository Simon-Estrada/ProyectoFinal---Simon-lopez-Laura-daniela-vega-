package models.CuentasBancarias;

import exceptions.SaldoInsuficiente;
import models.Usuarios.Cliente;

public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, Cliente propietario, double limiteSobregiro) {
        super(numeroCuenta, saldoInicial, propietario);
        this.limiteSobregiro = limiteSobregiro;
    }
    @Override
    public void retirar(double monto) throws SaldoInsuficiente{
        double saldoDisponibleTotal = getSaldo() + limiteSobregiro;
        if(monto > saldoDisponibleTotal){
            double necesario = monto - saldoDisponibleTotal;
            throw new SaldoInsuficiente("El monto excede el saldo disponible.");
        }
        setSaldo(getSaldo() - monto);
        if(getSaldo()<0){
            System.out.println("ADVERTENCIA: Se ha incurrido en un sobregiro. Saldo actual: " + getSaldo());
        } else {
            System.out.println("Retiro de " + monto + " realizado. Nuevo saldo: " + getSaldo());
        }
    }
    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public void setLimiteSobregiro(double limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }
}
