import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.sql.*;

public class Server extends JFrame {

    private JTextArea dataArea;
    private JPanel mainPanel;
    private JLabel statusLabel;

    public Server() {
        setTitle("Server Management Console");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set custom look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create main panel with gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(36, 41, 46), 
                                                    w, h, new Color(48, 54, 61));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Server Management Console");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        statusLabel = new JLabel("● Offline", SwingConstants.RIGHT);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        statusLabel.setForeground(Color.RED);
        headerPanel.add(statusLabel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create styled text area
        dataArea = new JTextArea();
        dataArea.setEditable(false);
        dataArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        dataArea.setBackground(new Color(30, 34, 39));
        dataArea.setForeground(Color.WHITE);
        dataArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(dataArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(48, 54, 61), 1));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setOpaque(false);
        JLabel connectedClientsLabel = new JLabel("Connected Clients: 0");
        connectedClientsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        connectedClientsLabel.setForeground(Color.WHITE);
        statusPanel.add(connectedClientsLabel);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Start the server in a new thread
        new Thread(this::startServer).start();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            SwingUtilities.invokeLater(() -> {
                statusLabel.setText("● Online");
                statusLabel.setForeground(Color.GREEN);
            });
            appendText("Server started successfully");
            appendText("Listening on port 5000...\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                appendText("New client connected from: " + clientSocket.getInetAddress().getHostAddress());

                // Handle the client in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            appendText("ERROR: Server failed to start - " + e.getMessage());
            SwingUtilities.invokeLater(() -> {
                statusLabel.setText("● Error");
                statusLabel.setForeground(Color.RED);
            });
        }
    }

    private void handleClient(Socket clientSocket) {
        try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
            // Read data from client
            String[] userDetails = (String[]) input.readObject();
            String username = userDetails[0];
            String email = userDetails[1];
            String password = userDetails[2];
            String dateOfBirth = userDetails[3];
            String gender = userDetails[4];

            appendText("\nReceived new user registration:");
            appendText("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            appendText("Username: " + username);
            appendText("Email: " + email);
            appendText("Password: " + "*".repeat(password.length()));
            appendText("Date of Birth: " + dateOfBirth);
            appendText("Gender: " + gender);
            appendText("━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

            // Save to database
            saveToDatabase(username, email, password, dateOfBirth, gender);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            appendText("ERROR: Failed to process client data - " + e.getMessage());
        }
    }

    private void saveToDatabase(String username, String email, String password, String dateOfBirth, String gender) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");
            String query = "INSERT INTO users (username, email, password_hash, date_of_birth, gender) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // Consider using password hashing
            stmt.setString(4, dateOfBirth);
            stmt.setString(5, gender);
            stmt.executeUpdate();

            appendText("✓ Data successfully saved to database");
        } catch (SQLException ex) {
            ex.printStackTrace();
            appendText("ERROR: Database operation failed - " + ex.getMessage());
        }
    }

    private void appendText(String text) {
        SwingUtilities.invokeLater(() -> {
            dataArea.append(text + "\n");
            dataArea.setCaretPosition(dataArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Server server = new Server();
            server.setVisible(true);
        });
    }
}
