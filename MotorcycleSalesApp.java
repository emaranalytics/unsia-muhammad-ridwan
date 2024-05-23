import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorcycleSalesApp extends JFrame {
    private JComboBox<String> brandComboBox;
    private JRadioButton bebekRadioButton, sportRadioButton, maticRadioButton;
    private JTextField priceField, discountField, totalPriceField;
    private JComboBox<String> paymentComboBox;

    public MotorcycleSalesApp() {
        // Set up the frame
        setTitle("Aplikasi Penjualan Motor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);

        // Customer name
        JLabel nameLabel = new JLabel("Nama Pembeli:");
        nameLabel.setBounds(10, 10, 100, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 10, 250, 25);
        add(nameField);

        // Motorcycle data
        JLabel dataMotorLabel = new JLabel("Data Motor");
        dataMotorLabel.setBounds(10, 40, 100, 25);
        add(dataMotorLabel);

        JLabel brandLabel = new JLabel("Merk Motor:");
        brandLabel.setBounds(10, 70, 100, 25);
        add(brandLabel);

        brandComboBox = new JComboBox<>(new String[]{"HONDA", "YAMAHA", "SUZUKI", "KAWASAKI"});
        brandComboBox.setBounds(120, 70, 250, 25);
        brandComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice();
            }
        });
        add(brandComboBox);

        JLabel typeLabel = new JLabel("Jenis Motor:");
        typeLabel.setBounds(10, 100, 100, 25);
        add(typeLabel);

        bebekRadioButton = new JRadioButton("BEBEK");
        sportRadioButton = new JRadioButton("SPORT");
        maticRadioButton = new JRadioButton("MATIK");

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(bebekRadioButton);
        typeGroup.add(sportRadioButton);
        typeGroup.add(maticRadioButton);

        bebekRadioButton.setBounds(120, 100, 80, 25);
        sportRadioButton.setBounds(200, 100, 80, 25);
        maticRadioButton.setBounds(280, 100, 80, 25);

        ActionListener typeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice();
            }
        };

        bebekRadioButton.addActionListener(typeListener);
        sportRadioButton.addActionListener(typeListener);
        maticRadioButton.addActionListener(typeListener);

        add(bebekRadioButton);
        add(sportRadioButton);
        add(maticRadioButton);

        JLabel priceLabel = new JLabel("Harga:");
        priceLabel.setBounds(10, 130, 100, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(120, 130, 250, 25);
        priceField.setEditable(false);
        add(priceField);

        // Payment data
        JLabel paymentLabel = new JLabel("Pembayaran");
        paymentLabel.setBounds(10, 160, 100, 25);
        add(paymentLabel);

        paymentComboBox = new JComboBox<>(new String[]{"TUNAI", "KREDIT"});
        paymentComboBox.setBounds(120, 160, 250, 25);
        paymentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDiscountAndTotal();
            }
        });
        add(paymentComboBox);

        JButton calculateButton = new JButton("Hitung");
        calculateButton.setBounds(120, 190, 100, 25);
        add(calculateButton);

        JLabel discountLabel = new JLabel("Diskon:");
        discountLabel.setBounds(10, 220, 100, 25);
        add(discountLabel);

        discountField = new JTextField();
        discountField.setBounds(120, 220, 250, 25);
        discountField.setEditable(false);
        add(discountField);

        JLabel totalPriceLabel = new JLabel("Total Bayar:");
        totalPriceLabel.setBounds(10, 250, 100, 25);
        add(totalPriceLabel);

        totalPriceField = new JTextField();
        totalPriceField.setBounds(120, 250, 250, 25);
        totalPriceField.setEditable(false);
        add(totalPriceField);

        JButton clearButton = new JButton("Hitung Lagi");
        clearButton.setBounds(120, 280, 100, 25);
        add(clearButton);

        JButton exitButton = new JButton("Selesai");
        exitButton.setBounds(230, 280, 100, 25);
        add(exitButton);

        // Action listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePrice();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Yakin mau keluar?", "Konfirmasi keluar Aplikasi", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void updatePrice() {
        String brand = (String) brandComboBox.getSelectedItem();
        String type = "";
        if (bebekRadioButton.isSelected()) {
            type = "BEBEK";
        } else if (sportRadioButton.isSelected()) {
            type = "SPORT";
        } else if (maticRadioButton.isSelected()) {
            type = "MATIK";
        }

        int price = getPrice(brand, type);
        priceField.setText(String.valueOf(price));
        updateDiscountAndTotal();
    }

    private void updateDiscountAndTotal() {
        String brand = (String) brandComboBox.getSelectedItem();
        String payment = (String) paymentComboBox.getSelectedItem();
        int price = Integer.parseInt(priceField.getText());
        int discount = getDiscount(brand, payment, price);
        int totalPrice = price - discount;

        discountField.setText(String.valueOf(discount));
        totalPriceField.setText(String.valueOf(totalPrice));
    }

    private void calculatePrice() {
        updatePrice();
        updateDiscountAndTotal();
    }

    private int getPrice(String brand, String type) {
        int price = 0;
        switch (brand) {
            case "HONDA":
                if (type.equals("BEBEK")) price = 12000000;
                else if (type.equals("SPORT")) price = 32000000;
                else if (type.equals("MATIK")) price = 15000000;
                break;
            case "YAMAHA":
                if (type.equals("BEBEK")) price = 13500000;
                else if (type.equals("SPORT")) price = 30000000;
                else if (type.equals("MATIK")) price = 14000000;
                break;
            case "SUZUKI":
                if (type.equals("BEBEK")) price = 14000000;
                else if (type.equals("SPORT")) price = 33000000;
                else if (type.equals("MATIK")) price = 13000000;
                break;
            case "KAWASAKI":
                if (type.equals("BEBEK")) price = 12500000;
                else if (type.equals("SPORT")) price = 28000000;
                else if (type.equals("MATIK")) price = 13500000;
                break;
        }
        return price;
    }

    private int getDiscount(String brand, String payment, int price) {
        int discount = 0;
        if (payment.equals("TUNAI")) {
            discount = price * 20 / 100;
        } else if (payment.equals("KREDIT")) {
            switch (brand) {
                case "HONDA":
                    discount = price * 8 / 100;
                    break;
                case "YAMAHA":
                    discount = price * 7 / 100;
                    break;
                case "SUZUKI":
                    discount = price * 6 / 100;
                    break;
                case "KAWASAKI":
                    discount = price * 5 / 100;
                    break;
            }
        }
        return discount;
    }

    private void clearFields() {
        brandComboBox.setSelectedIndex(0);
        bebekRadioButton.setSelected(false);
        sportRadioButton.setSelected(false);
        maticRadioButton.setSelected(false);
        paymentComboBox.setSelectedIndex(0);
        priceField.setText("");
        discountField.setText("");
        totalPriceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MotorcycleSalesApp().setVisible(true);
            }
        });
    }
}
