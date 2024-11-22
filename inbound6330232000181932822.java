import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; //para sa keyboard or mouse
import java.awt.event.ActionListener; //mo react sa interaction
import java.awt.image.ColorModel; 
import java.util.HashMap;
import java.util.Map;

//if not running try copy paste made by VisualStudio
//Members
//Julius Bomedian0
//Georgina Manikan
//Lance Descallar

public class CurrencyConvertersGUI extends JFrame {
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    

    private Map<String, Double> exchangeRates;

    public CurrencyConvertersGUI() {
        setTitle("Currency Converter");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        getContentPane().setBackground(Color.BLACK);

       

        // set value sa kwarta pila iya value sa lain nason
        exchangeRates = new HashMap<>();
        exchangeRates.put("US Dollar", 1.0);     // reference money
        exchangeRates.put("Euro", 0.94);    // 1 Dollar to euro
        exchangeRates.put("British Pound", 0.79);    // 1 Dollar to GBP
        exchangeRates.put("Japanese Yen", 156.1);   // 1 Dollar to japanese yen
        exchangeRates.put("Philippine Peso", 58.0);    // 1 Dollar to 58.0 philippine money
        exchangeRates.put("Rupee", 0.012);
        exchangeRates.put("Canadian Dollar", 0.72);
        exchangeRates.put("Australian Dollar" , 0.65 );
        exchangeRates.put("Chinese Yuan", 0.14);
        exchangeRates.put("Korean Won", 0.00071);
        exchangeRates.put("Malaysian Ringgit", 4.45);
        
        JLabel fromLabel = new JLabel("From:");
        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));

        JLabel toLabel = new JLabel("To:");
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());

        resultLabel = new JLabel("Converted Amount: -");

        add(fromLabel);
        add(fromCurrency);
        add(toLabel);
        add(toCurrency);
        add(amountLabel);
        add(amountField);
        add(new JLabel()); // void para sa layout
        add(convertButton);
        add(new JLabel()); // void para sa outcome
        add(resultLabel);

        setVisible(true); // para ma print sa GUI
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText());

                double fromRate = exchangeRates.get(from);
                double toRate = exchangeRates.get(to);

                double convertedAmount = amount * (toRate / fromRate);
                resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CashConverterGUI::new);
    }
}