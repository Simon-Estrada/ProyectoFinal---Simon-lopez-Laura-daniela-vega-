//package models.CuentasBancarias;
//
//import exceptions.SaldoInsuficiente;
//import models.Usuarios.Client;
//
//public class CuentaEmpresarial extends Cuenta{
//    private double limiteCredito;
//
//    public CuentaEmpresarial(String numeroCuenta, double saldoInicial, Client propietario, double limiteCredito){
//        super(numeroCuenta, saldoInicial, propietario);
//        this.limiteCredito = limiteCredito;
//    }
//    @Override
//    public void retirar(double monto) throws SaldoInsuficiente{
//        double saldoDisponibleTotal= getSaldo() + limiteCredito;
//
//        if(monto > saldoDisponibleTotal){
//            throw new SaldoInsuficiente("El monto excede el saldo disponible y el limite de credito empresarial.");
//        }
//        setSaldo(getSaldo() - monto);
//        if (getSaldo() < 0) {
//            System.out.println("ADVERTENCIA: Uso de la línea de crédito empresarial. Saldo actual: " + getSaldo());
//        } else {
//            System.out.println("Retiro de " + monto + " realizado. Nuevo saldo: " + getSaldo());
//        }
//    }
//    public double getLimiteCredito() {
//        return limiteCredito;
//    }
//
//    public void setLimiteCredito(double limiteCredito) {
//        this.limiteCredito = limiteCredito;
//    }
//}
//