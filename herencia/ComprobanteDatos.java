package herencia;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteDatos {
    List<Comprobante> listaComprobante = new ArrayList<Comprobante>();

    public List<Comprobante> list() {
        return listaComprobante;
    }

    public void create(Comprobante c) {
        listaComprobante.add(c);
    }

    public void delete(Comprobante c) {
        listaComprobante.remove(c);
    }

    public void delete(int id) {
        boolean existe = false;
        for (Comprobante c : listaComprobante) {
            System.out.println("Deleted:"+c.getId() + "\t" + c.getCliente());
            if (id == c.getId()) {
                try {
                    listaComprobante.remove(c);

                } catch (java.util.ConcurrentModificationException e2) {
                    
                    System.out.println("Contacto si exist e2="+e2);
                }
                existe= true;
            }
        }
        if(!existe) {
            System.out.println("Contacto no existe");
        }
    }

}


