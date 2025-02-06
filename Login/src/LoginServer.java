import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServer extends JFrame {

    private final JTextArea serverLogArea;
    private ServerSocket serverSocket;
    private boolean isRunning;

    public LoginServer() {
        // Set up the main frame
        setTitle("Mental Wellness Server Console");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Server status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel("Server Status: ");
        JLabel statusValue = new JLabel("Stopped");
        statusValue.setForeground(Color.RED);
        statusPanel.add(statusLabel);
        statusPanel.add(statusValue);
        
        // Server log area with better styling
        serverLogArea = new JTextArea();
        serverLogArea.setEditable(false);
        serverLogArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        serverLogArea.setBackground(new Color(240, 240, 240));
        serverLogArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JScrollPane scrollPane = new JScrollPane(serverLogArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Server Logs"));

        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton startButton = new JButton("Start Server");
        JButton stopButton = new JButton("Stop Server");
        JButton clearButton = new JButton("Clear Logs");
        
        stopButton.setEnabled(false);
        
        startButton.addActionListener(e -> {
            startServer();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            statusValue.setText("Running");
            statusValue.setForeground(Color.GREEN);
        });
        
        stopButton.addActionListener(e -> {
            stopServer();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            statusValue.setText("Stopped");
            statusValue.setForeground(Color.RED);
        });
        
        clearButton.addActionListener(e -> serverLogArea.setText(""));
        
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(clearButton);

        // Add components to main panel
        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
        
        pack();
        setVisible(true);
    }

    private void startServer() {
        isRunning = true;
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(6000);
                logMessage("Server started on port 6000");

                while (isRunning && !serverSocket.isClosed()) {
                    Socket clientSocket = serverSocket.accept();
                    logMessage("Client connected: " + clientSocket.getInetAddress());
                    new ClientHandler(clientSocket).start();
                }

            } catch (IOException e) {
                if (isRunning) {
                    logMessage("Error starting server: " + e.getMessage());
                }
            }
        }).start();
    }
    
    private void stopServer() {
        isRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                logMessage("Server stopped");
            }
        } catch (IOException e) {
            logMessage("Error stopping server: " + e.getMessage());
        }
    }

    private void logMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            serverLogArea.append("[" + java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + message + "\n");
            serverLogArea.setCaretPosition(serverLogArea.getDocument().getLength());
        });
    }

    private class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                 ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {

                String[] loginDetails = (String[]) input.readObject();
                String email = loginDetails[0];
                String password = loginDetails[1];

                logMessage("Login attempt from " + clientSocket.getInetAddress() + " - Email: " + email);

                if (authenticate(email, password)) {
                    output.writeObject("LOGIN_SUCCESS");
                    logMessage("Login successful for: " + email);
                } else {
                    output.writeObject("LOGIN_FAILURE");
                    logMessage("Login failed for: " + email);
                }

            } catch (IOException | ClassNotFoundException e) {
                logMessage("Error handling client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    logMessage("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private static boolean authenticate(String email, String password) {
            String url = "jdbc:mysql://localhost:3306/my_assignment";
            String dbUser = "root";
            String dbPassword = "your_password";

            try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
                 PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE email = ? AND password_hash = ?")) {

                stmt.setString(1, email);
                stmt.setString(2, password); // In production, use proper password hashing
                
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new LoginServer());
    }
}
