import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class SignUp extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JDateChooser dateOfBirthChooser;
    private JComboBox<String> genderComboBox;

    public SignUp() {
        setTitle("Create Your Account");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(new Color(240, 248, 255));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 248, 255));
        JLabel titleLabel = new JLabel("Welcome!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(41, 128, 185));
        titlePanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        // Style components
        usernameField = createStyledTextField();
        emailField = createStyledTextField();
        passwordField = createStyledPasswordField();
        dateOfBirthChooser = new JDateChooser();
        dateOfBirthChooser.setPreferredSize(new Dimension(200, 35));
        
        String[] genders = {"Select Gender", "Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setPreferredSize(new Dimension(200, 35));
        genderComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Add components with proper spacing
        addFormRow(formPanel, "Username", usernameField, 0, gbc);
        addFormRow(formPanel, "Email", emailField, 1, gbc);
        addFormRow(formPanel, "Password", passwordField, 2, gbc);
        addFormRow(formPanel, "Date of Birth", dateOfBirthChooser, 3, gbc);
        addFormRow(formPanel, "Gender", genderComboBox, 4, gbc);

        // Sign Up button
        JButton signUpButton = new JButton("Create Account");
        signUpButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(41, 128, 185));
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(new SignUpAction());
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(signUpButton);

        // Add all panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private void addFormRow(JPanel panel, String labelText, Component component, int row, GridBagConstraints gbc) {
        gbc.gridy = row;
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(52, 73, 94));
        
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(component, gbc);
    }

   private class SignUpAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        java.util.Date dateOfBirth = dateOfBirthChooser.getDate();
        String gender = (String) genderComboBox.getSelectedItem();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || 
            dateOfBirth == null || gender.equals("Select Gender")) {
            showErrorDialog("Please fill in all fields");
            return;
        }

        String formattedDateOfBirth = new java.sql.Date(dateOfBirth.getTime()).toString();

        // Send data to server
        sendDataToServer(username, email, password, formattedDateOfBirth, gender);
    }

    private void sendDataToServer(String username, String email, String password, String dateOfBirth, String gender) {
        try (Socket socket = new Socket("localhost", 5000);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

            String[] userDetails = {username, email, password, dateOfBirth, gender};
            output.writeObject(userDetails);

            JOptionPane.showMessageDialog(SignUp.this, 
                "Data sent to server successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
            showErrorDialog("Error connecting to server: " + ex.getMessage());
        }
    }

        private void showErrorDialog(String please_fill_in_all_fields) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new SignUp());
    }
}
