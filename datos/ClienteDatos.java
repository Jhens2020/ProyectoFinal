package datos;

import java.util.ArrayList;
import java.util.List;

public class ClienteDatos {
    List<Cliente> listaClientes = new ArrayList<Cliente>();

    public List<Cliente> list() {
        return listaClientes;
    }

    public void create(Cliente c) {
        listaClientes.add(c);
    }

    public void delete(Cliente c) {
        listaClientes.remove(c);
    }

    public void delete(int id) {
        boolean existe = false;
        for (Cliente c : listaClientes) {
            System.out.println("Deleted:"+c.getId() + "\t" + c.getNombre());
            if (id == c.getId()) {
                try {
                    listaClientes.remove(c);

                } catch (java.util.ConcurrentModificationException e2) {
                    
                    System.out.println("Contacto si existe e2="+e2);
                }
                
                
                existe= true;
            }
        }
        if(!existe) {
            System.out.println("Contacto no existe");
        }
    }

}


