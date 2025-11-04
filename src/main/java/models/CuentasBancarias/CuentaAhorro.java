//package models.CuentasBancarias;
//
//import exceptions.SaldoInsuficiente;
//import models.Usuarios.Client;
//
//public class CuentaAhorro extends Cuenta{
//    private double tasaInteres;
//
//    public CuentaAhorro(String numeroCuenta, double saldoInicial, Client propietario, double tasaInteres) {
//        super(numeroCuenta, saldoInicial, propietario);
//        this.tasaInteres = tasaInteres;
//    }
//
//    @Override
//    public void retirar(double monto) throws SaldoInsuficiente{
//        if(monto > getSaldo()){
//            throw new SaldoInsuficiente("Saldo insuficiente");
//        }
//        setSaldo(getSaldo() - monto);
//        System.out.println("Retiro de "+monto+" realizado. Nuevo saldo: "
//        + getSaldo());
//    }
//    public void aplicarIntereses(){
//        double interesGanado = getSaldo()*(tasaInteres/12);
//        depositar(interesGanado);
//    }
//    public double getTasaInteres() {
//        return tasaInteres;
//    }
//    public void setTasaInteres(double tasaInteres) {
//        this.tasaInteres = tasaInteres;
//    }
//}
//