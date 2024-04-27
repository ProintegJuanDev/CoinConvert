import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazUsuario extends JFrame implements ActionListener {
    private JTextField cantidadField;
    private JComboBox<String> monedaComboBox;
    private JButton convertirButton;

    public InterfazUsuario() {
        // Configuración de la ventana
        setTitle("Conversor de Moneda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        // Componentes de la interfaz
        cantidadField = new JTextField();
        monedaComboBox = new JComboBox<>(new String[]{"Dólar", "Euro", "Libra Esterlina", "Yen Japonés", "Won sur-coreano", "Peso Colombiano"});
        convertirButton = new JButton("Convertir");

        // Agregar componentes a la ventana
        add(cantidadField);
        add(monedaComboBox);
        add(convertirButton);

        // Escuchar el evento del botón
        convertirButton.addActionListener(this);

        // Mostrar la ventana
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Manejar evento del botón
        if (e.getSource() == convertirButton) {
            // Obtener la cantidad ingresada por el usuario
            double cantidad = Double.parseDouble(cantidadField.getText());

            // Obtener la moneda seleccionada por el usuario
            String moneda = (String) monedaComboBox.getSelectedItem();

            // Determinar si la conversión es de COP a otra moneda o viceversa
            boolean aCOP = moneda.equals("Peso Colombiano");

            // Realizar la conversión y mostrar el resultado
            if (aCOP) {
                String monedaDestino = (String) JOptionPane.showInputDialog(this, "Seleccione la moneda a la que desea convertir:",
                        "Conversión a Peso Colombiano", JOptionPane.PLAIN_MESSAGE, null,
                        new String[]{"Dólar", "Euro", "Libra Esterlina", "Yen Japonés", "Won sur-coreano"}, "Dólar");

                double resultado = convertirAMoneda(cantidad, monedaDestino);
                JOptionPane.showMessageDialog(this, cantidad + " " + moneda + " equivale a " + resultado + " " + monedaDestino);
            } else {
                double resultado = convertirAPesoColombiano(cantidad, moneda);
                JOptionPane.showMessageDialog(this, cantidad + " " + moneda + " equivale a " + resultado + " Peso Colombiano");
            }
        }
    }

    // Método para convertir de COP a otra moneda
    private double convertirAMoneda(double cantidad, String monedaDestino) {
        switch (monedaDestino) {
            case "Dólar":
                return cantidad / TasasDeCambio.USD_TO_COP;
            case "Euro":
                return cantidad / TasasDeCambio.EUR_TO_COP;
            case "Libra Esterlina":
                return cantidad / TasasDeCambio.GBP_TO_COP;
            case "Yen Japonés":
                return cantidad / TasasDeCambio.JPY_TO_COP;
            case "Won sur-coreano":
                return cantidad / TasasDeCambio.KRW_TO_COP;
            default:
                return 0.0;
        }
    }

    // Método para convertir de otra moneda a COP
    private double convertirAPesoColombiano(double cantidad, String monedaOrigen) {
        switch (monedaOrigen) {
            case "Dólar":
                return cantidad * TasasDeCambio.USD_TO_COP;
            case "Euro":
                return cantidad * TasasDeCambio.EUR_TO_COP;
            case "Libra Esterlina":
                return cantidad * TasasDeCambio.GBP_TO_COP;
            case "Yen Japonés":
                return cantidad * TasasDeCambio.JPY_TO_COP;
            case "Won sur-coreano":
                return cantidad * TasasDeCambio.KRW_TO_COP;
            default:
                return 0.0;
        }
    }
}
