import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class KursusApp extends JFrame {
    private JTextField noPendaftaranField, namaSiswaField, alamatField, biayaField;
    private JComboBox<String> jenisKursusComboBox, namaKursusComboBox;
    private JRadioButton fundamentalRadioButton, advancedRadioButton;
    private JTextField biayaPendaftaranField, biayaModulField, totalBiayaField;

    private Map<String, String[]> kursusOptions = new HashMap<>();
    private Map<String, Integer> biayaKursus = new HashMap<>();

    public KursusApp() {
        // Set up the frame
        setTitle("Lembaga Pendidikan Kursus \"SMART\"");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);

        // Initialize course options
        initKursusOptions();

        // Labels and text fields for student information
        JLabel noPendaftaranLabel = new JLabel("No. Pendaftaran:");
        noPendaftaranLabel.setBounds(10, 10, 150, 25);
        add(noPendaftaranLabel);

        noPendaftaranField = new JTextField();
        noPendaftaranField.setBounds(170, 10, 250, 25);
        add(noPendaftaranField);

        JLabel namaSiswaLabel = new JLabel("Nama Siswa:");
        namaSiswaLabel.setBounds(10, 40, 150, 25);
        add(namaSiswaLabel);

        namaSiswaField = new JTextField();
        namaSiswaField.setBounds(170, 40, 250, 25);
        add(namaSiswaField);

        JLabel alamatLabel = new JLabel("Alamat:");
        alamatLabel.setBounds(10, 70, 150, 25);
        add(alamatLabel);

        alamatField = new JTextField();
        alamatField.setBounds(170, 70, 250, 25);
        add(alamatField);

        JLabel jenisKursusLabel = new JLabel("Jenis Kursus:");
        jenisKursusLabel.setBounds(10, 100, 150, 25);
        add(jenisKursusLabel);

        jenisKursusComboBox = new JComboBox<>(new String[]{"Programming", "Design", "Marketing"});
        jenisKursusComboBox.setBounds(170, 100, 250, 25);
        add(jenisKursusComboBox);

        JLabel namaKursusLabel = new JLabel("Nama Kursus:");
        namaKursusLabel.setBounds(10, 130, 150, 25);
        add(namaKursusLabel);

        namaKursusComboBox = new JComboBox<>();
        namaKursusComboBox.setBounds(170, 130, 250, 25);
        add(namaKursusComboBox);

        JLabel biayaLabel = new JLabel("Biaya:");
        biayaLabel.setBounds(10, 160, 150, 25);
        add(biayaLabel);

        biayaField = new JTextField();
        biayaField.setBounds(170, 160, 250, 25);
        biayaField.setEditable(false);
        add(biayaField);

        JLabel paketKursusLabel = new JLabel("Paket Kursus:");
        paketKursusLabel.setBounds(10, 190, 150, 25);
        add(paketKursusLabel);

        fundamentalRadioButton = new JRadioButton("Fundamental");
        fundamentalRadioButton.setBounds(170, 190, 120, 25);
        advancedRadioButton = new JRadioButton("Advanced");
        advancedRadioButton.setBounds(300, 190, 120, 25);

        ButtonGroup paketKursusGroup = new ButtonGroup();
        paketKursusGroup.add(fundamentalRadioButton);
        paketKursusGroup.add(advancedRadioButton);

        add(fundamentalRadioButton);
        add(advancedRadioButton);

        JButton hitungButton = new JButton("HITUNG TOTAL PEMBAYARAN");
        hitungButton.setBounds(10, 220, 300, 25);
        add(hitungButton);

        JLabel biayaPendaftaranLabel = new JLabel("Biaya Pendaftaran:");
        biayaPendaftaranLabel.setBounds(10, 250, 150, 25);
        add(biayaPendaftaranLabel);

        biayaPendaftaranField = new JTextField();
        biayaPendaftaranField.setBounds(170, 250, 250, 25);
        biayaPendaftaranField.setEditable(false);
        add(biayaPendaftaranField);

        JLabel biayaModulLabel = new JLabel("Biaya Modul:");
        biayaModulLabel.setBounds(10, 280, 150, 25);
        add(biayaModulLabel);

        biayaModulField = new JTextField();
        biayaModulField.setBounds(170, 280, 250, 25);
        biayaModulField.setEditable(false);
        add(biayaModulField);

        JLabel totalBiayaLabel = new JLabel("Total Biaya:");
        totalBiayaLabel.setBounds(10, 310, 150, 25);
        add(totalBiayaLabel);

        totalBiayaField = new JTextField();
        totalBiayaField.setBounds(170, 310, 250, 25);
        totalBiayaField.setEditable(false);
        add(totalBiayaField);

        JButton daftarLagiButton = new JButton("DAFTAR LAGI");
        daftarLagiButton.setBounds(10, 340, 150, 25);
        add(daftarLagiButton);

        JButton keluarButton = new JButton("KELUAR");
        keluarButton.setBounds(170, 340, 150, 25);
        add(keluarButton);

        // Action listeners
        jenisKursusComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNamaKursusOptions();
            }
        });

        namaKursusComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBiaya();
            }
        });

        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        daftarLagiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        keluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Yakin mau keluar?", "Konfirmasi keluar Aplikasi", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Initialize nama kursus options
        updateNamaKursusOptions();
    }

    private void initKursusOptions() {
        kursusOptions.put("Programming", new String[]{"Java", "Python", "C++"});
        kursusOptions.put("Design", new String[]{"Graphic Design", "UI/UX Design", "Animation"});
        kursusOptions.put("Marketing", new String[]{"Digital Marketing", "SEO", "Content Marketing"});

        biayaKursus.put("Java", 2000000);
        biayaKursus.put("Python", 2500000);
        biayaKursus.put("C++", 3000000);
        biayaKursus.put("Graphic Design", 1500000);
        biayaKursus.put("UI/UX Design", 2000000);
        biayaKursus.put("Animation", 2500000);
        biayaKursus.put("Digital Marketing", 1800000);
        biayaKursus.put("SEO", 2200000);
        biayaKursus.put("Content Marketing", 2000000);
    }

    private void updateNamaKursusOptions() {
        String selectedJenisKursus = (String) jenisKursusComboBox.getSelectedItem();
        namaKursusComboBox.removeAllItems();
        for (String kursus : kursusOptions.get(selectedJenisKursus)) {
            namaKursusComboBox.addItem(kursus);
        }
    }

    private void updateBiaya() {
        String selectedNamaKursus = (String) namaKursusComboBox.getSelectedItem();
        if (selectedNamaKursus != null) {
            int biaya = biayaKursus.get(selectedNamaKursus);
            biayaField.setText(String.valueOf(biaya));
        } else {
            biayaField.setText("");
        }
    }

    private void calculateTotal() {
        int biayaPendaftaran = 50000; // Example fixed registration fee
        int biayaModul = 0;

        if (fundamentalRadioButton.isSelected()) {
            biayaModul = 500000; // Example fixed fundamental package fee
        } else if (advancedRadioButton.isSelected()) {
            biayaModul = 1000000; // Example fixed advanced package fee
        }

        int biayaKursus = Integer.parseInt(biayaField.getText());

        int totalBiaya = biayaPendaftaran + biayaModul + biayaKursus;

        biayaPendaftaranField.setText(String.valueOf(biayaPendaftaran));
        biayaModulField.setText(String.valueOf(biayaModul));
        totalBiayaField.setText(String.valueOf(totalBiaya));
    }

    private void clearFields() {
        noPendaftaranField.setText("");
        namaSiswaField.setText("");
        alamatField.setText("");
        jenisKursusComboBox.setSelectedIndex(0);
        namaKursusComboBox.removeAllItems();
        biayaField.setText("");
        fundamentalRadioButton.setSelected(false);
        advancedRadioButton.setSelected(false);
        biayaPendaftaranField.setText("");
        biayaModulField.setText("");
        totalBiayaField.setText("");
        updateNamaKursusOptions();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KursusApp().setVisible(true);
            }
        });
    }
}
