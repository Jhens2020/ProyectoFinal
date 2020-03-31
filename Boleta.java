import java.awt.*;
import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;

//import java.awt.event.*;
import javax.swing.table.*;

import datos.Cliente;
import datos.ClienteDatos;
import datos.Productos;
import datos.ProductosDatos;

import herencia.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Boleta extends JFrame {

    private static final long serialVersionUID = 1L;
    
    int clientId = 0;
    ClienteDatos clientDatos = new ClienteDatos();
    String[] clientColumns = { "Id", "Nombre","DNI"};
    String[][] clientMatriz = new String[0][clientColumns.length];
    DefaultTableModel model = new DefaultTableModel(clientMatriz, clientColumns);
    JTable contactoTable = new JTable(model);
    JScrollPane clientSP = new JScrollPane();
    
    int productId = 0;
    ProductosDatos productosDatos = new ProductosDatos();
    String[] productsColumns = { "Id","Producto","Precio"};
    String[][] productsMatriz = new String[0][productsColumns.length];
    DefaultTableModel model2 = new DefaultTableModel(productsMatriz, productsColumns);
    JTable productTable = new JTable(model2);
    JScrollPane productSP = new JScrollPane();

    int comprobanteId = 0;
    ComprobanteDatos comprobanteDatos = new ComprobanteDatos();
    String[] comprobanteColumns = { "Id","Cliente","Productos","Fecha","Precio Unitario", "Precio Total"};
    String[][] comprobanteMatriz = new String[0][comprobanteColumns.length];
    DefaultTableModel model3 = new DefaultTableModel(comprobanteMatriz, comprobanteColumns);
    JTable comprobanteTable = new JTable(model3);
    JScrollPane comprobanteSP = new JScrollPane();

    public Boleta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("CRUD");
        JMenu m2 = new JMenu("AYUDA");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Contactos");
        JMenuItem m12 = new JMenuItem("Productos");
        JMenuItem m13 = new JMenuItem("Comprobantes");
        JMenuItem m19 = new JMenuItem("Salir");

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
        JLabel clientLblNombre = new JLabel("Ingrese Contacto:");
        JTextField clientTxtNombre = new JTextField();

        JLabel clientLblDNI = new JLabel("Ingrese DNI:");
        JTextField clientTxtDNI = new JTextField();

        JButton btnadd = new JButton("Agregar");
        JButton button;
        button = new JButton("Eliminar");
        clientSP.setViewportView(contactoTable);
        clientPanel.add(clientLblNombre);
        clientPanel.add(clientTxtNombre);
        clientPanel.add(clientLblDNI);
        clientPanel.add(clientTxtDNI);
        clientPanel.add(btnadd);
        clientPanel.add(button);
        clientPanel.add(clientSP);

        btnadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientId++;
                Cliente d = new Cliente();
                d.setId(clientId);
                d.setNombre(clientTxtNombre.getText());
                clientDatos.create(d);
                d.setDocumento(clientTxtDNI.getText());
                
                List<Cliente> miLista = clientDatos.list();
                clientMatriz = new String[miLista.size()][clientColumns.length];
                for (int i = 0; i < miLista.size(); i++) {
                    clientMatriz[i][0] = miLista.get(i).getId() + "";
                    clientMatriz[i][1] = miLista.get(i).getNombre() + "";
                    clientMatriz[i][2] = miLista.get(i).getDni() + "";
                }
                model = new DefaultTableModel(clientMatriz, clientColumns);
                contactoTable = new JTable(model);
                clientSP.setViewportView(contactoTable);
             }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (contactoTable.getSelectedRow() != -1) {
                    String ids = null;
                    int[] row = contactoTable.getSelectedRows();
                    ids = (String) contactoTable.getValueAt(row[0], 0);
                    System.out.println("Table element selected es: " + ids);
                    int id = Integer.parseInt(ids);
                    clientTxtNombre.setText(" " + id);
                    
                    model.removeRow(contactoTable.getSelectedRow());
                    try {
                        clientDatos.delete(id);
                    } catch (java.util.ConcurrentModificationException e2) {
                        System.out.println("Contacto si exist e2="+e2);
                    }
                }
            }
        });

        JPanel productoPanel = new JPanel();

        productoPanel.setLayout(new BoxLayout(productoPanel, BoxLayout.Y_AXIS));

        JLabel clientLblProducto = new JLabel("Ingrese el Producto:");
        JTextField clientTxtProducto = new JTextField();

        JLabel clientLblPrecio = new JLabel("Ingrese el Precio:");
        JTextField clientTxtPrecio = new JTextField();

        JButton btnadd2 = new JButton("Agregar");
        JButton button2;
        button2 = new JButton("Eliminar");
        productSP.setViewportView(productTable);
        productoPanel.add(clientLblProducto);
        productoPanel.add(clientTxtProducto);
        productoPanel.add(clientLblPrecio);
        productoPanel.add(clientTxtPrecio);
        productoPanel.add(btnadd2);
        productoPanel.add(button2);
        productoPanel.add(productSP);

        btnadd2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productId++;
                Productos d = new Productos();
                d.setId(productId);
                d.setProductos(clientTxtProducto.getText());
                productosDatos.create(d);
                d.setPrecio(clientTxtPrecio.getText());  

                List<Productos> miLista = productosDatos.list();
                productsMatriz = new String[miLista.size()][productsColumns.length];
                for (int i = 0; i < miLista.size(); i++) {
                    productsMatriz[i][0] = miLista.get(i).getId() + "";
                    productsMatriz[i][1] = miLista.get(i).getProductos() + "";
                    productsMatriz[i][2] = miLista.get(i).getPrecio() + "";
                }
                model2 = new DefaultTableModel(productsMatriz, productsColumns);
                productTable = new JTable(model2);
                productSP.setViewportView(productTable);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (productTable.getSelectedRow() != -1) {
                    String ids = null;
                    int[] row = productTable.getSelectedRows();
                    ids = (String) productTable.getValueAt(row[0], 0);
                    System.out.println("Table element selected es: " + ids);
                    int id = Integer.parseInt(ids);
                    clientTxtProducto.setText(" " + id);
                    
                    model2.removeRow(productTable.getSelectedRow());
                    try {
                        productosDatos.delete(id);
                    } catch (java.util.ConcurrentModificationException e2) {
                        System.out.println("Contacto si exist e2="+e2);
                    }
                }
            }
        });

        //Crud Comprobante
        JPanel comprobantePanel = new JPanel();
        comprobantePanel.setLayout(new BoxLayout(comprobantePanel, BoxLayout.Y_AXIS));

        JLabel comprobanteLblCliente = new JLabel("Ingrese el Cliente:");
        JTextField comprobanteTxtCliente = new JTextField();

        JLabel comprobanteLblProductos = new JLabel("Ingrese los Productos:");
        JTextField comprobanteTxtProductos = new JTextField();

        JLabel comprobanteLblFecha = new JLabel("Ingrese la Fecha:");
        JTextField comprobanteTxtFecha = new JTextField();

        JLabel comprobanteLblPrecioU = new JLabel("Ingrese el Precio Unitario:");
        JTextField comprobanteTxtPrecioU= new JTextField();

        JLabel comprobanteLblPrecioT = new JLabel("Ingrese el Precio Total:");
        JTextField comprobanteTxtPrecioT = new JTextField();

        JButton btnadd3 = new JButton("Agregar");
        JButton button3;
        button3 = new JButton("Eliminar");

        comprobanteSP.setViewportView(comprobanteTable);

        comprobantePanel.add(comprobanteLblCliente);
        comprobantePanel.add(comprobanteTxtCliente);

        comprobantePanel.add(comprobanteLblProductos);
        comprobantePanel.add(comprobanteTxtProductos);

        comprobantePanel.add(comprobanteLblFecha);
        comprobantePanel.add(comprobanteTxtFecha);

        comprobantePanel.add(comprobanteLblPrecioU);
        comprobantePanel.add(comprobanteTxtPrecioU);

        comprobantePanel.add(comprobanteLblPrecioT);
        comprobantePanel.add(comprobanteTxtPrecioT);

        comprobantePanel.add(btnadd3);
        comprobantePanel.add(button3);
        comprobantePanel.add(comprobanteSP);

        btnadd3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comprobanteId++;
                Comprobante d = new Comprobante();

                // crea la tabla
                d.setId(comprobanteId);
                d.setCliente(comprobanteTxtCliente.getText());
                d.setProductos(comprobanteTxtProductos.getText());
                d.setFecha(comprobanteTxtFecha.getText());
                d.setPrecioU(comprobanteTxtPrecioU.getText());
                d.setPrecioT(comprobanteTxtPrecioT.getText());

                comprobanteDatos.create(d);

                List<Comprobante> miLista = comprobanteDatos.list();
                comprobanteMatriz = new String[miLista.size()][comprobanteColumns.length];
                for (int i = 0; i < miLista.size(); i++) {
                    comprobanteMatriz[i][0] = miLista.get(i).getId() + "";
                    comprobanteMatriz[i][1] = miLista.get(i).getCliente() + "";
                    comprobanteMatriz[i][2] = miLista.get(i).getProductos() + "";
                    comprobanteMatriz[i][3] = miLista.get(i).getFecha() + "";
                    comprobanteMatriz[i][4] = miLista.get(i).getPrecioU() + "";
                    comprobanteMatriz[i][5] = miLista.get(i).getPrecioT() + "";
                }
                model3 = new DefaultTableModel(comprobanteMatriz, comprobanteColumns);
                comprobanteTable = new JTable(model3);
                comprobanteSP.setViewportView(comprobanteTable);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (comprobanteTable.getSelectedRow() != -1) {
                    String ids = null;
                    int[] row = comprobanteTable.getSelectedRows();
                    ids = (String) comprobanteTable.getValueAt(row[0], 0);
                    System.out.println("Table element selected es: " + ids);
                    int id = Integer.parseInt(ids);
                    comprobanteTxtCliente.setText(" " + id);

                    model3.removeRow(comprobanteTable.getSelectedRow());
                    try {
                        comprobanteDatos.delete(id);
                    } catch (java.util.ConcurrentModificationException e2) {
                        System.out.println("Contacto si exist e2=" + e2);
                    }
                }
            }
        });
        //end Crud Conprobante
        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Ir a contactos");
                JOptionPane.showMessageDialog(null, clientPanel, "Contactos", JOptionPane.PLAIN_MESSAGE);
            }
        });
        m12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Ir a productos");
                JOptionPane.showMessageDialog(null, productoPanel, "Productos", JOptionPane.PLAIN_MESSAGE);
            }
        });
        m13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Ir a Comprobantes");
                JOptionPane.showMessageDialog(null, comprobantePanel, "Comprobantes", JOptionPane.PLAIN_MESSAGE);
            }
        });
        m19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m19);

        JPanel footPanel = new JPanel();
        JLabel footLblCopy = new JLabel("ProyectoFinal-POO");
        footPanel.add(footLblCopy);

        add(BorderLayout.NORTH, mb);
        add(BorderLayout.SOUTH, footPanel);
    }

    public static void main(String args[]) {
        Boleta ex = new Boleta();
        ex.setVisible(true);
    }

}