package models.CuentasBancarias;

public class Account {
    private String accountNumber;
    private String accountType;
    private double balance;
    private String clientId;
    private double interestRate;
    private double overdraftLimit;
    private double transactionVolume;

    public Account(String accountNumber, String accountType, double balance, String clientId, double overdraftLimit, double transactionVolume, double interestRate) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.clientId = clientId;
        this.interestRate = interestRate;
        this.overdraftLimit = overdraftLimit;
        this.transactionVolume = transactionVolume;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getTransactionVolume() {
        return transactionVolume;
    }

    public void setTransactionVolume(double transactionVolume) {
        this.transactionVolume = transactionVolume;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            this.balance += monto;
        }
        if ("EMPRESARIAL".equals(this.accountType)) {
            this.transactionVolume += monto;
        }
        return true;
    }
    public boolean retirar(double monto) {
        if (monto <= 0) {
            return false;
        }
        if ("CORRIENTE".equals(this.accountType)) {
            if (this.balance + this.overdraftLimit >= monto) {
                this.balance -= monto;
                return true;
            }
            return false;
        }
        if (this.balance >= monto) {
            this.balance -= monto;
            if ("EMPRESARIAL".equals(this.accountType)) {
                this.transactionVolume += monto;
            }
            return true;
        }
        return false;
    }
    public void aplicarIntereses() {
        if ("AHORRO".equals(this.accountType)) {
            double interes = this.balance * (this.interestRate / 100);
            this.balance += interes;
        }
    }
    public String getInfoEspecifica() {
        switch (this.accountType) {
            case "AHORRO":
                return "Tasa de interés: " + interestRate + "%";
            case "CORRIENTE":
                return "Sobregiro disponible: $" + String.format("%.2f", overdraftLimit);
            case "EMPRESARIAL":
                return "Volumen de transacciones: $" + String.format("%.2f", transactionVolume);
            default:
                return "";
        }
    }
    @Override
    public String toString() {
        return accountNumber + " - " + accountType + " - $" +
                String.format("%.2f", balance);
    }

}

