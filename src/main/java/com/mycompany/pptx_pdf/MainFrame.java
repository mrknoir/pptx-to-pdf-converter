package com.mycompany.pptx_pdf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private JTextField filePathField;
    private JButton browseButton;
    private JButton convertButton;
    private JProgressBar progressBar;
    private File selectedFile;

    public MainFrame() {
        // --- 1. Main Window Setup (Dark Theme) ---
        setTitle("PPTX to PDF Converter");
        setSize(550, 320); // Slightly taller to accommodate the new header panel spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30)); 

        // --- 2. Build the Architectural Header Panel (Logo + Text) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 30, 30));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0)); // Flow layout centers them with fixed horizontal spacing
        headerPanel.setBorder(new EmptyBorder(30, 0, 20, 0)); // Precise top/bottom padding

        // Create the Logo Label (Scales image dynamically)
        JLabel logoLabel = new JLabel();
        try {
            // Load the logo from the resources folder
            java.net.URL logoUrl = getClass().getResource("logo.png");
            if (logoUrl != null) {
                // A. Scale the image down beautifully (e.g., 50x50)
                ImageIcon rawIcon = new ImageIcon(logoUrl);
                Image appIcon = rawIcon.getImage();
                Image scaledImage = appIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                logoLabel.setIcon(new ImageIcon(scaledImage));

                // B. Feature: Universal Taskbar Icon
                // This replaces the Java cup universal icon for taskbar and window corner
                setIconImage(appIcon);
            }
        } catch (Exception ex) {
            System.out.println("Could not load logo.png, using defaults.");
        }

        // Create the Text Label
        JLabel titleLabel = new JLabel("PPTX to PDF Converter");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(240, 240, 240)); 
        
        // Assemble the header
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        
        add(headerPanel, BorderLayout.NORTH); // Add the complete panel

        // --- 3. Center Panel (File Selection) ---
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); 

        filePathField = new JTextField(25);
        filePathField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        filePathField.setBackground(new Color(50, 50, 50));
        filePathField.setForeground(Color.WHITE);
        filePathField.setCaretColor(Color.WHITE);
        filePathField.setEditable(false); 
        filePathField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        browseButton = createModernButton("Browse...");
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(filePathField, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        centerPanel.add(browseButton, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // --- 4. Bottom Panel (Convert Button & Progress Bar) ---
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 15));
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(new EmptyBorder(10, 25, 30, 25));

        convertButton = createModernButton("Convert to PDF");
        convertButton.setBackground(new Color(0, 122, 204)); 
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        convertButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 180)),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)));

        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(100, 8)); 
        progressBar.setForeground(new Color(0, 122, 204));
        progressBar.setBackground(new Color(50, 50, 50));
        progressBar.setBorderPainted(false);
        progressBar.setIndeterminate(true); 
        progressBar.setVisible(false); 

        bottomPanel.add(convertButton, BorderLayout.NORTH);
        bottomPanel.add(progressBar, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        setupActions();
    }

    // --- Helper Method ---
    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); 
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90)),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        return button;
    }

    // --- Actions/Threading ---
    private void setupActions() {
        browseButton.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(this, "Select a PPTX File", FileDialog.LOAD);
            fileDialog.setFile("*.pptx;*.ppt"); 
            fileDialog.setVisible(true); 

            String directory = fileDialog.getDirectory();
            String filename = fileDialog.getFile();

            if (directory != null && filename != null) {
                selectedFile = new File(directory, filename);
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        convertButton.addActionListener(e -> {
        
            
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a PowerPoint file first!", 
                    "No File Selected", 
                    JOptionPane.WARNING_MESSAGE);
                return; 
            }
            
            if (!selectedFile.exists()) {
                JOptionPane.showMessageDialog(this, 
                    "The selected file no longer exists. Please browse for it again.", 
                    "File Not Found", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String fileName = selectedFile.getName().toLowerCase();
            if (!fileName.endsWith(".pptx") && !fileName.endsWith(".ppt")) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid format! Please select a valid PowerPoint file (.ppt or .pptx).", 
                    "Wrong File Type", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            convertButton.setEnabled(false); 
            convertButton.setText("Converting...");
            progressBar.setVisible(true);

            String originalPath = selectedFile.getAbsolutePath();
            String pdfPath = originalPath.substring(0, originalPath.lastIndexOf('.')) + ".pdf";
            File destinationFile = new File(pdfPath);

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                
                @Override
                protected Void doInBackground() throws Exception {
                    ConverterService.convert(selectedFile, destinationFile);
                    return null;
                }

                @Override
                protected void done() {
                    progressBar.setVisible(false);
                    convertButton.setText("Convert to PDF");
                    convertButton.setEnabled(true);

                    try {
                        get(); 
                        JOptionPane.showMessageDialog(MainFrame.this, 
                            "Success! File saved to:\n" + destinationFile.getAbsolutePath(), 
                            "Conversion Complete", 
                            JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (Exception ex) {
                        String errorMsg = ex.getMessage();
                        
                        if (errorMsg != null && (errorMsg.contains("officeHome") || errorMsg.contains("auto-detected"))) {
                            JOptionPane.showMessageDialog(MainFrame.this, 
                                "LibreOffice could not be found on your system!\n\n" +
                                "This app requires LibreOffice to perform the conversion.\n" +
                                "Please download and install it from libreoffice.org", 
                                "Dependency Missing", 
                                JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(MainFrame.this, 
                                "An error occurred during conversion:\n" + errorMsg, 
                                "Conversion Error", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                        ex.printStackTrace(); 
                    }
                }
            };
            worker.execute();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}