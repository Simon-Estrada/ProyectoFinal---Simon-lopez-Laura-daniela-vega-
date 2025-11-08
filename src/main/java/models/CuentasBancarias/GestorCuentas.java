package models.CuentasBancarias;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GestorCuentas {
    private ArrayList<Account> accountsList;
    private static final String ARCHIVO = "cuentas.txt";

    public GestorCuentas() {
        accountsList = new ArrayList<>();
    }
    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }
    public boolean cargarCuentas(){
        File archivo = new File(ARCHIVO);
        if(!archivo.exists()){
            return  true;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea = reader.readLine())!=null){
                String [] data = linea.split(",");
                if(data.length <7) continue;
                Account account = new Account(
                        data[0],
                        data[1],
                        Double.parseDouble(data[2]),
                        data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6])
                );
                accountsList.add(account);
            }
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guardarCuentas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {

            for (Account account : accountsList) {
                writer.println(
                        account.getAccountNumber() + "," +
                                account.getAccountType() + "," +
                                account.getBalance() + "," +
                                account.getClientId() + "," +
                                account.getInterestRate() + "," +
                                account.getOverdraftLimit() + "," +
                                account.getTransactionVolume()
                );
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private String generarNumeroCuenta() {
        String base = String.format("%08d", new Random().nextInt(100000000));
        String numeroCuenta = "BUQ-"+base;
        while(existeCuenta(numeroCuenta)) {
            base = String.format("%08d", new Random().nextInt(100000000));
            numeroCuenta = "BUQ-" + base;
        }
        return numeroCuenta;
    }
    public Account buscarCuenta(String accountNumber) {
        for (Account account : accountsList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    public ArrayList<Account> getCuentasCliente(String clientId) {
        ArrayList<Account> cuentasCliente = new ArrayList<>();

        for (Account account : accountsList) {
            if (account.getClientId().equals(clientId)) {
                cuentasCliente.add(account);
            }
        }

        return cuentasCliente;
    }
    public Account crearCuenta(String clientId, String tipo, double saldoInicial) {
        String numeroCuenta = generarNumeroCuenta();
        double interes = 0.0;
        double sobregiro = 0.0;
        double volumen = 0.0;

        switch (tipo) {
            case "AHORRO":
                interes = 2.5;
                break;
            case "CORRIENTE":
                sobregiro = 2000000.0;
                break;
            case "EMPRESARIAL":
                volumen = 0.0;
                break;
        }
        return new Account(
                numeroCuenta,
                tipo,
                saldoInicial,
                clientId,
                interes,
                sobregiro,
                volumen
        );

    }
    public boolean agregarCuenta(Account account) {
        accountsList.add(account);
        return guardarCuentas();
    }
    public boolean eliminarCuenta(String accountNumber) {
        accountsList.removeIf(c -> c.getAccountNumber().equals(accountNumber));
        return guardarCuentas();
    }
    public boolean depositar(String accountNumber, double monto) {
        Account account = buscarCuenta(accountNumber);

        if (account == null) {
            return false;
        }

        if (account.depositar(monto)) {
            return guardarCuentas();
        }

        return false;
    }
    public boolean retirar(String accountNumber, double monto) {
        Account account = buscarCuenta(accountNumber);

        if (account == null) {
            return false;
        }

        if (account.retirar(monto)) {
            return guardarCuentas();
        }

        return false;
    }


    public boolean realizarTransferencia(String cuentaOrigenNum, String cuentaDestinoNum, double monto) {
        Account cuentaOrigen = buscarCuenta(cuentaOrigenNum);
        Account cuentaDestino = buscarCuenta(cuentaDestinoNum);

        if (cuentaOrigen == null || cuentaDestino == null) {
            return false;
        }

        if (monto <= 0) {
            return false;
        }

        if (cuentaOrigen.retirar(monto)) {
            cuentaDestino.depositar(monto);
            return guardarCuentas();
        }

        return false;
    }

    public double consultarSaldo(String accountNumber) {
        Account account = buscarCuenta(accountNumber);

        if (account != null) {
            return account.getBalance();
        }

        return -1;
    }

    public void aplicarInteresesCuentasAhorro() {
        for (Account account : accountsList) {
            if ("AHORRO".equals(account.getAccountType())) {
                account.aplicarIntereses();
            }
        }
        guardarCuentas();
    }

    public boolean existeCuenta(String accountNumber) {
        return buscarCuenta(accountNumber) != null;
    }

    public String getInfoCuenta(String accountNumber) {
        Account account= buscarCuenta(accountNumber);

        if (account == null) {
            return "Cuenta no encontrada";
        }
        StringBuilder info = new StringBuilder();
        info.append("Número: ").append(account.getAccountNumber()).append("\n");
        info.append("Tipo: ").append(account.getAccountType()).append("\n");
        info.append("Saldo: $").append(String.format("%.2f", account.getBalance())).append("\n");;
        info.append(account.getInfoEspecifica());

        return info.toString();
    }
}


