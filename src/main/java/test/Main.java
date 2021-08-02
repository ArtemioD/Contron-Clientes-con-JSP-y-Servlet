package test;

import datos.ClienteDao;
import dominio.Cliente;
import java.util.*;

public class Main {
    public static void main(String[] args) { 
       List<Cliente> clientes = new ClienteDao().listar();
        for (Cliente cliente : clientes) {
            System.out.println("cliente = " + cliente);   
        }
    }
    
}
