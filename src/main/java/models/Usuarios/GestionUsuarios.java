package models.Usuarios;

import java.io.*;
import java.util.ArrayList;

public class GestionUsuarios {
    private ArrayList<Admin> adminList;
    private ArrayList<Cashier> cashierList;
    private ArrayList<Client> clientList;

    private static final String ARCHIVO = "usuarios.txt";

    public GestionUsuarios() {
        adminList = new ArrayList<>();
        cashierList = new ArrayList<>();
        clientList = new ArrayList<>();
        cargarUsuarios();
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public ArrayList<Cashier> getCashierList() {
        return cashierList;
    }

    public void setCashierList(ArrayList<Cashier> cashierList) {
        this.cashierList = cashierList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }
    public boolean cargarUsuarios() {
        File archivo = new File(ARCHIVO);
        if(!archivo.exists()){
            return true;
        }
        adminList.clear();
        cashierList.clear();
        clientList.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea = reader.readLine())!=null){
                String[]datos = linea.split(",");
                String tipo = datos[0];

                switch(tipo.trim()){
                    case "Admin":
                        Admin admin = new Admin(datos[1], datos[2], datos[3], datos[4], datos[5]);
                        adminList.add(admin);
                        break;
                    case "Cashier":
                        Cashier cashier = new Cashier(datos[1], datos[2], datos[3], datos[4], datos[5]);
                        cashierList.add(cashier);
                        break;
                    case "Client":
                        Client client = new Client(datos[1], datos[2], datos[3], datos[4], datos[5],datos[6] );
                        clientList.add(client);
                        break;
                }
            }
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guardarUsuarios() {
        try(PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))){
            for(Admin admin : adminList){
                writer.println("Admin,"+
                        admin.getId()+ ","
                + admin.getName() + ","
                + admin.getEmail()+ ","
                + admin.getPassword()+ ","
                + admin.getDepo());
            }
            for (Cashier cashier : cashierList) {
                writer.println("Cashier," +
                        cashier.getId() + "," +
                        cashier.getName() + "," +
                        cashier.getEmail() + "," +
                        cashier.getPassword() + "," +
                        cashier.getWorkerId());
            }
            for (Client client : clientList) {
                writer.println("Client," +
                        client.getId() + "," +
                        client.getName() + "," +
                        client.getEmail() + "," +
                        client.getPassword() + "," +
                        client.getPhone() + "," +
                        client.getAdress());
            }
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean agregarCliente(Client client) {
        clientList.add(client);
        return guardarUsuarios();
    }

    public boolean agregarCajero(Cashier cashier) {
        cashierList.add(cashier);
        return guardarUsuarios();
    }

    public boolean agregarAdmin(Admin admin) {
        adminList.add(admin);
        return guardarUsuarios();
    }
    public boolean eliminarCliente(String id) {
        clientList.removeIf(c -> c.getId().equals(id));
        return guardarUsuarios();
    }

    public boolean eliminarCajero(String id) {
        cashierList.removeIf(c -> c.getId().equals(id));
        return guardarUsuarios();
    }

    public boolean eliminarAdmin(String id) {
        adminList.removeIf(a -> a.getId().equals(id));
        return guardarUsuarios();
    }
    public boolean modificarCajero(String id, String nombre, String email, String password) {
        Cashier cajero = buscarCajero(id);
        if (cajero != null) {
            cajero.setName(nombre);
            cajero.setEmail(email);
            cajero.setPassword(password);
            guardarUsuarios();
            return true;
        }
        return false;
    }
    public boolean modificarCliente(String id, String nombre, String email,
                                    String password, String telefono, String direccion) {
        Client cliente = buscarCliente(id);
        if (cliente != null) {
            cliente.setName(nombre);
            cliente.setEmail(email);
            cliente.setPassword(password);
            cliente.setPhone(telefono);
            cliente.setAdress(direccion);
            guardarUsuarios();
            return true;
        }
        return false;
    }
    public boolean modificarAdmin(String id, String nombre, String email,
                                    String password, String depo) {
        Admin admin = buscarAdmin(id);
        if (admin != null) {
            admin.setName(nombre);
            admin.setEmail(email);
            admin.setPassword(password);
            guardarUsuarios();
            return true;
        }
        return false;
    }
    public Client buscarCliente(String id) {
        for (Client cliente : clientList) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    public Cashier buscarCajero(String id) {
        for (Cashier cajero : cashierList) {
            if (cajero.getId().equals(id)) {
                return cajero;
            }
        }
        return null;
    }
    public Admin buscarAdmin(String id) {
        for (Admin admin : adminList) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }
    private Object usuarioPorId(String id){
        if(buscarCajero(id)!= null){
            return buscarCajero(id);
        }
        if(buscarCliente(id)!= null){
            return buscarCliente(id);
        }
        if(buscarAdmin(id)!= null){
            return buscarAdmin(id);
        }
        return null;
    }
    public boolean addCashier(String id, String name, String email, String password, String workerId) {
        if (usuarioPorId(id) != null) {
            return false;
        }
        Cashier newCashier = new Cashier(id, name, email, password, workerId);
        cashierList.add(newCashier);
        return guardarUsuarios();
    }
    public boolean addAdmin(String id, String name, String email, String password, String depo){
        if(usuarioPorId(id)!= null){
            return false;
        }
        Admin newAdmin = new Admin (id, name, email,password, depo);
        adminList.add(newAdmin);
        return guardarUsuarios();
    }

}

