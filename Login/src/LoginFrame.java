import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;

public class LoginFrame extends JFrame {

    JTextField emailField;
    JPasswordField passwordField;

    public LoginFrame() {
        // Set the frame properties
        setTitle("Mental Wellness Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create gradient panel for background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(76, 175, 80), w, h, new Color(27, 94, 32));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new GridLayout(1, 2, 20, 0));

        // Left side panel with logo and branding
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(80, 40, 80, 40));

        ImageIcon logoIcon = new ImageIcon("logo.png"); // Add your logo image
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel systemLabel = new JLabel("MENTAL WELLNESS");
        systemLabel.setFont(new Font("Montserrat", Font.BOLD, 32));
        systemLabel.setForeground(Color.WHITE);
        systemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("MONITORING & SELF-CARE");
        subtitleLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        subtitleLabel.setForeground(new Color(255, 255, 255, 220));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(systemLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(subtitleLabel);
        leftPanel.add(Box.createVerticalGlue());

        // Right side panel with login form
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 255, 255, 240));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));

        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Montserrat", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(33, 33, 33));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel = new JLabel("Please login to continue");
        loginLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));
        loginLabel.setForeground(new Color(117, 117, 117));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Styled input fields
        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(76, 175, 80)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(76, 175, 80)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Styled login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setMaximumSize(new Dimension(300, 40));
        loginButton.setFont(new Font("Montserrat", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(76, 175, 80));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticate(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new MenuFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sign-up panel with modern styling
        JPanel signupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signupPanel.setOpaque(false);
        
        JLabel noAccountLabel = new JLabel("Don't have an account? ");
        noAccountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JButton signupButton = new JButton("Sign up");
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signupButton.setForeground(new Color(76, 175, 80));
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp();
                dispose();
            }
        });

        // Add components to right panel with proper spacing
        rightPanel.add(welcomeLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(loginLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        rightPanel.add(new JLabel("Email Address"));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(emailField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(new JLabel("Password"));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(loginButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signupPanel.add(noAccountLabel);
        signupPanel.add(signupButton);
        rightPanel.add(signupPanel);

        // Add panels to main panel
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);
    }

   private boolean authenticate(String email, String password) {
    try (Socket socket = new Socket("localhost", 6000);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

            String[] loginDetails = {email, password};
            output.writeObject(loginDetails);

            JOptionPane.showMessageDialog(null, 
                "Data sent to server successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
            showErrorDialog("Error connecting to server: " + ex.getMessage());
        }
        return false;
    }



    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }

    private void showErrorDialog(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
