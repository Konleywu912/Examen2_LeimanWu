/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examen2_leimanwu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibroGUI extends JFrame {

    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnEditar, btnEliminar, btnLimpiar;

    // Lista para almacenar libros
    private ArrayList<Libros> libros = new ArrayList<>();

    public LibroGUI() {
        setTitle("Gestión Completa de Libros");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Tabla de libros
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Título", "Autor", "Editorial", "ISBN", "Categoría", "Precio", "Stock"});
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaLibros);
        scroll.setBounds(10, 10, 760, 350);
        add(scroll);

        // Botones abajo
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(100, 400, 120, 50);
        add(btnAgregar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(240, 400, 120, 50);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(380, 400, 120, 50);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar Tabla");
        btnLimpiar.setBounds(520, 400, 120, 50);
        add(btnLimpiar);

        // Eventos botones
        btnAgregar.addActionListener(e -> abrirDialogoAgregar());
        btnEditar.addActionListener(e -> abrirDialogoEditar());
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnLimpiar.addActionListener(e -> limpiarTabla());

        setVisible(true);
    }

    // Método para abrir diálogo para agregar libro
    private void abrirDialogoAgregar() {
        JDialog dialog = new JDialog(this, "Agregar Libro", true);
        dialog.setLayout(null);
        dialog.setSize(400, 450);
        dialog.setLocationRelativeTo(this);

        // Labels y campos
        JLabel lblTitulo = crearLabelCentrado("Título:", 50, 20, 100, 25, dialog);
        JTextField txtTitulo = crearTextField(50, 50, 300, 30, dialog);

        JLabel lblAutor = crearLabelCentrado("Autor:", 50, 90, 100, 25, dialog);
        JTextField txtAutor = crearTextField(50, 120, 300, 30, dialog);

        JLabel lblEditorial = crearLabelCentrado("Editorial:", 50, 160, 100, 25, dialog);
        JTextField txtEditorial = crearTextField(50, 190, 300, 30, dialog);

        JLabel lblIsbn = crearLabelCentrado("ISBN:", 50, 230, 100, 25, dialog);
        JTextField txtIsbn = crearTextField(50, 260, 300, 30, dialog);

        JLabel lblCategoria = crearLabelCentrado("Categoría:", 50, 300, 100, 25, dialog);
        JTextField txtCategoria = crearTextField(50, 330, 300, 30, dialog);

        JLabel lblPrecio = crearLabelCentrado("Precio:", 50, 370, 100, 25, dialog);
        JTextField txtPrecio = crearTextField(50, 400, 120, 30, dialog);

        JLabel lblStock = crearLabelCentrado("Stock:", 230, 370, 100, 25, dialog);
        JTextField txtStock = crearTextField(230, 400, 120, 30, dialog);

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 440, 100, 30);
        dialog.add(btnGuardar);

        btnGuardar.addActionListener(ev -> {
            try {
                String titulo = txtTitulo.getText().trim();
                String autor = txtAutor.getText().trim();
                String editorial = txtEditorial.getText().trim();
                String isbn = txtIsbn.getText().trim();
                String categoria = txtCategoria.getText().trim();
                int precio = Integer.parseInt(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());

                if (titulo.isEmpty() || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "El título y autor son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = libros.size() + 1; // auto id simple

                Libros libro = new Libros(id, titulo, autor, editorial, isbn, categoria, precio, stock);
                libros.add(libro);

                modeloTabla.addRow(new Object[]{id, titulo, autor, editorial, isbn, categoria, precio, stock});

                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Precio y Stock deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    // Método para abrir diálogo para editar libro seleccionado
    private void abrirDialogoEditar() {
        int fila = tablaLibros.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un libro en la tabla para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener datos del libro seleccionado
        int id = (int) modeloTabla.getValueAt(fila, 0);
        String tituloActual = (String) modeloTabla.getValueAt(fila, 1);
        String autorActual = (String) modeloTabla.getValueAt(fila, 2);
        String editorialActual = (String) modeloTabla.getValueAt(fila, 3);
        String isbnActual = (String) modeloTabla.getValueAt(fila, 4);
        String categoriaActual = (String) modeloTabla.getValueAt(fila, 5);
        int precioActual = (int) modeloTabla.getValueAt(fila, 6);
        int stockActual = (int) modeloTabla.getValueAt(fila, 7);

        JDialog dialog = new JDialog(this, "Editar Libro", true);
        dialog.setLayout(null);
        dialog.setSize(400, 450);
        dialog.setLocationRelativeTo(this);

        JLabel lblTitulo = crearLabelCentrado("Título:", 50, 20, 100, 25, dialog);
        JTextField txtTitulo = crearTextField(50, 50, 300, 30, dialog);
        txtTitulo.setText(tituloActual);

        JLabel lblAutor = crearLabelCentrado("Autor:", 50, 90, 100, 25, dialog);
        JTextField txtAutor = crearTextField(50, 120, 300, 30, dialog);
        txtAutor.setText(autorActual);

        JLabel lblEditorial = crearLabelCentrado("Editorial:", 50, 160, 100, 25, dialog);
        JTextField txtEditorial = crearTextField(50, 190, 300, 30, dialog);
        txtEditorial.setText(editorialActual);

        JLabel lblIsbn = crearLabelCentrado("ISBN:", 50, 230, 100, 25, dialog);
        JTextField txtIsbn = crearTextField(50, 260, 300, 30, dialog);
        txtIsbn.setText(isbnActual);

        JLabel lblCategoria = crearLabelCentrado("Categoría:", 50, 300, 100, 25, dialog);
        JTextField txtCategoria = crearTextField(50, 330, 300, 30, dialog);
        txtCategoria.setText(categoriaActual);

        JLabel lblPrecio = crearLabelCentrado("Precio:", 50, 370, 100, 25, dialog);
        JTextField txtPrecio = crearTextField(50, 400, 120, 30, dialog);
        txtPrecio.setText(String.valueOf(precioActual));

        JLabel lblStock = crearLabelCentrado("Stock:", 230, 370, 100, 25, dialog);
        JTextField txtStock = crearTextField(230, 400, 120, 30, dialog);
        txtStock.setText(String.valueOf(stockActual));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 440, 100, 30);
        dialog.add(btnGuardar);

        btnGuardar.addActionListener(ev -> {
            try {
                String titulo = txtTitulo.getText().trim();
                String autor = txtAutor.getText().trim();
                String editorial = txtEditorial.getText().trim();
                String isbn = txtIsbn.getText().trim();
                String categoria = txtCategoria.getText().trim();
                int precio = Integer.parseInt(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());

                if (titulo.isEmpty() || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "El título y autor son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Actualizar objeto en lista
                for (Libros lib : libros) {
                    if (lib.getId() == id) {
                        lib.setTitulo(titulo);
                        lib.setAutor(autor);
                        lib.setEditorial(editorial);
                        lib.setIsbn(isbn);
                        lib.setCategoria(categoria);
                        lib.setPrecio(precio);
                        lib.setStock(stock);
                        break;
                    }
                }

                // Actualizar tabla
                modeloTabla.setValueAt(titulo, fila, 1);
                modeloTabla.setValueAt(autor, fila, 2);
                modeloTabla.setValueAt(editorial, fila, 3);
                modeloTabla.setValueAt(isbn, fila, 4);
                modeloTabla.setValueAt(categoria, fila, 5);
                modeloTabla.setValueAt(precio, fila, 6);
                modeloTabla.setValueAt(stock, fila, 7);

                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Precio y Stock deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    // Método para eliminar libro seleccionado
    private void eliminarLibro() {
        int fila = tablaLibros.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un libro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar este libro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            int id = (int) modeloTabla.getValueAt(fila, 0);

            // Eliminar de lista
            libros.removeIf(lib -> lib.getId() == id);

            // Eliminar de tabla
            modeloTabla.removeRow(fila);
        }
    }

    // Método para limpiar la tabla y lista
    private void limpiarTabla() {
        libros.clear();
        modeloTabla.setRowCount(0);
    }

    // Método para crear JLabel con texto centrado
    private JLabel crearLabelCentrado(String texto, int x, int y, int ancho, int alto, Container contenedor) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setBounds(x, y, ancho, alto);
        contenedor.add(label);
        return label;
    }

    // Método para crear JTextField con posición y tamaño
    private JTextField crearTextField(int x, int y, int ancho, int alto, Container contenedor) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, ancho, alto);
        contenedor.add(campo);
        return campo;
    }
}