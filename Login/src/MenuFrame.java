import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font; import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import com.itextpdf.text.Font.FontFamily;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;


public class MenuFrame extends JFrame {
    private Color primaryColor = new Color(41, 128, 185); // Professional blue
    private Color accentColor = new Color(52, 152, 219); // Lighter blue
    private Color textColor = new Color(44, 62, 80); // Dark gray
    private java.awt.Font titleFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24);
    private java.awt.Font menuFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
    private int offset = 0;
    private final int limit = 1;
    private Image backgroundImage;

    public MenuFrame() {
        setTitle("Mental Health Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
    backgroundImage = ImageIO.read(new File("C:\\Users\\user\\Desktop\\background.jpg")); 
} catch (IOException e) {
    e.printStackTrace();
}

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
               if (backgroundImage != null) {
                    // Draw the background image scaled to fit the panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());
         mainPanel.setOpaque(false); // Make panel transparent to show background

        // Set the content pane to our custom panel
        setContentPane(mainPanel);

       JMenuBar menuBar = new JMenuBar();
       menuBar.setOpaque(false);

        // Data Menu
        JMenu dataMenu = new JMenu("Data");
        JMenuItem submenu1 = new JMenuItem("users");
        JMenuItem submenu2 = new JMenuItem("userprofiles");
        JMenuItem submenu3 = new JMenuItem("usersettings");
        JMenuItem submenu4 = new JMenuItem("actiivitylogs");
        JMenuItem submenu5 = new JMenuItem("assessmentquestions");
        JMenuItem submenu6 = new JMenuItem("assessmentresponses");
        JMenuItem submenu7 = new JMenuItem("assessments");
        JMenuItem submenu8 = new JMenuItem("communitycomments");
        JMenuItem submenu9 = new JMenuItem("communityposts");
        JMenuItem submenu10 = new JMenuItem("goals");
        JMenuItem submenu11 = new JMenuItem("moodlogs");
        JMenuItem submenu12 = new JMenuItem("notifications");
        JMenuItem submenu13 = new JMenuItem("recommendations");
        JMenuItem submenu14 = new JMenuItem("supportcontacts");
        JMenuItem submenu15 = new JMenuItem("userrecommmendations");
      
        
        dataMenu.add(submenu1);
        dataMenu.add(submenu2);
        dataMenu.add(submenu3);
        dataMenu.add(submenu4);
        dataMenu.add(submenu5);
        dataMenu.add(submenu6);
        dataMenu.add(submenu7);
        dataMenu.add(submenu8);
        dataMenu.add(submenu9);
        dataMenu.add(submenu10);
        dataMenu.add(submenu11);
        dataMenu.add(submenu12);
        dataMenu.add(submenu13);
        dataMenu.add(submenu14);
        dataMenu.add(submenu15);

        // Add action listeners for each submenu to open new frames
        submenu1.addActionListener(e -> openDataFrame("users"));
        submenu2.addActionListener(e -> openDataFrame("userprofiles"));
        submenu3.addActionListener(e -> openDataFrame("usersettings"));
        submenu4.addActionListener(e -> openDataFrame("activitylogs"));
        submenu5.addActionListener(e -> openDataFrame("assessmentquestions"));
        submenu6.addActionListener(e -> openDataFrame("assessmentresponses"));
        submenu7.addActionListener(e -> openDataFrame("assessments"));
        submenu8.addActionListener(e -> openDataFrame("communitycomments"));
        submenu9.addActionListener(e -> openDataFrame("communityposts"));
        submenu10.addActionListener(e -> openDataFrame("goals"));
        submenu11.addActionListener(e -> openDataFrame("moodlogs"));
        submenu12.addActionListener(e -> openDataFrame("notifications"));
        submenu13.addActionListener(e -> openDataFrame("recommendations"));
        submenu14.addActionListener(e -> openDataFrame("supportcontacts"));
        submenu15.addActionListener(e -> openDataFrame("userrecommendations"));

        menuBar.add(dataMenu);

        // Reports Menu
        JMenu reportsMenu = new JMenu("Reports");
       
     
        
        menuBar.add(reportsMenu);
        // Inside your MenuFrame constructor or initialization method
JMenuItem dailyGoalsReportMenuItem = new JMenuItem("Generate Daily Goals Report");

dailyGoalsReportMenuItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
           // Define your background and introduction
String backgroundText = "This report analyzes male users who are above 18 years of age. The data has been extracted from the user database, and each user has a verified account.";
String introductionText = "The report focuses on extracting male users who have completed their profile and are older than 18 years as per the database records.";

        generateDailyGoalsReport(backgroundText, introductionText);
    }
});

// Add the menu item to the existing menu
reportsMenu.add(dailyGoalsReportMenuItem);

        // Inside your Menu frame's constructor or initializer
JMenuItem generateActivityReportMenuItem = new JMenuItem("Generate Activity Report");
generateActivityReportMenuItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Define your background and introduction
String backgroundText = "This report focuses on users whose activity duration exceeds one hour on the mental health awareness platform. Engaging users in meaningful activities is crucial for promoting mental well-being. Prolonged interaction with supportive resources and community discussions can significantly enhance emotional resilience, reduce feelings of isolation, and provide users with coping strategies for managing mental health challenges such as anxiety and depression.";
String introductionText = "The purpose of this report is to identify users who have logged activity sessions lasting more than one hour. By analyzing their engagement patterns, we can gain insights into the effectiveness of the resources provided, such as therapeutic content, peer support forums, and mindfulness exercises. Understanding how users interact with the platform can help us refine our approach to foster a supportive environment that encourages users to seek help and actively participate in their mental health journey.";

// Call the method with the defined text


        generateActivityReport(backgroundText, introductionText); // Call the method to generate the activity report
    }
});

// Add the menu item to your existing menu
reportsMenu.add(generateActivityReportMenuItem);

        // Inside your Menu frame's constructor or initializer
JMenuItem generateReportMenuItem = new JMenuItem("Generate Assessment Report");
generateReportMenuItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String backgroundText = "This report analyzes the performance of users in assessments designed to gauge their understanding of mental health topics. Assessments are an essential tool in the platform’s educational efforts, aiming to inform users about recognizing mental health issues, understanding coping mechanisms, and knowing when to seek help. By evaluating user scores, we can identify knowledge gaps and enhance our educational resources to better address user needs.";
        String introductionText = "This report focuses on users who have achieved scores greater than 5 points in their assessments. The objective is to highlight high-performing users, analyze their strengths in understanding mental health concepts, and identify areas for improvement. By examining the distribution of scores, we can assess the effectiveness of our educational tools and ensure that they are aligned with our goal of increasing mental health literacy and ultimately reducing stigma surrounding mental health issues.";
        generateAssessmentReport(backgroundText, introductionText); // Call the method to generate the report
    }
});

// Assuming you have a JMenu or a similar component, you add the item to it
reportsMenu.add(generateReportMenuItem);  // Add the menu item to your menu

        JMenuItem menuItemMaleUsersReport = new JMenuItem("Male Users who are above 18yrs Report");
reportsMenu.add(menuItemMaleUsersReport);

     menuItemMaleUsersReport.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String backgroundText="This report provides an overview of male users who are above the age of 18 on the mental health awareness platform. Understanding the demographics of our users is vital for tailoring content and interventions that resonate with different groups. Men often face unique mental health challenges, including societal stigma that may prevent them from seeking help. By focusing on this demographic, we aim to address their specific needs and promote a more inclusive conversation about mental health.";
        String introductionText="The objective of this report is to extract and analyze data on male users who are older than 18 years. We will examine their participation levels, interaction patterns, and overall contributions to the platform. This analysis will help us develop targeted outreach strategies and programs that encourage men to engage more openly with mental health resources, ultimately fostering a supportive community that aids in reducing rates of anxiety, depression, and suicidal ideation.";
        
        generateMaleUsersReport(backgroundText, introductionText);
    }
});


        setJMenuBar(menuBar);
        setVisible(true);
    }

   private void generateMaleUsersReport(String backgroundText, String introductionText) {
    String jdbcURL = "jdbc:mysql://localhost:3306/my_assignment"; // Update with your DB URL
    String dbUsername = "root"; // Update with your DB username
    String dbPassword = "your_password"; // Update with your DB password
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String query = "SELECT user_id, username, email, date_of_birth, TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age, created_at " +
                   "FROM users WHERE gender = 'Male' AND TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) > 18";

    Document document = new Document();
    FileOutputStream fileOut = null;

    // Use JFileChooser to prompt user for the file location
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Report");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Documents", "pdf"));
    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        // Append .pdf if the user didn't provide it
        if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
            fileToSave = new File(fileToSave + ".pdf");
        }

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Check if ResultSet is empty
            if (!rs.isBeforeFirst()) { // No rows found
                JOptionPane.showMessageDialog(null, "No male users found above 18.");
                return;
            }

            fileOut = new FileOutputStream(fileToSave);
            PdfWriter.getInstance(document, fileOut);
            document.open();

            // Add title to the PDF
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Males Above 18 Years Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Empty line for spacing

            // Add background section
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Paragraph background = new Paragraph("Background", sectionFont);
            background.setAlignment(Element.ALIGN_LEFT);
            document.add(background);
            document.add(new Paragraph(backgroundText, bodyFont)); // Add background text

            document.add(new Paragraph(" ")); // Empty line for spacing

            // Add introduction section
            Paragraph intro = new Paragraph("Introduction", sectionFont);
            intro.setAlignment(Element.ALIGN_LEFT);
            document.add(intro);
            document.add(new Paragraph(introductionText, bodyFont)); // Add introduction text

            document.add(new Paragraph(" ")); // Empty line for spacing

            // Create table with column headers
            PdfPTable table = new PdfPTable(6); // 6 columns: user_id, username, email, date_of_birth, age, created_at
            table.setWidthPercentage(100);

            // Add table headers
            table.addCell("User ID");
            table.addCell("Username");
            table.addCell("Email");
            table.addCell("Date of Birth");
            table.addCell("Age");
            table.addCell("Created At");

            // Populate table with data
            while (rs.next()) {
                table.addCell(String.valueOf(rs.getInt("user_id")));
                table.addCell(rs.getString("username"));
                table.addCell(rs.getString("email"));
                table.addCell(rs.getString("date_of_birth"));
                table.addCell(String.valueOf(rs.getInt("age"))); // Adding the calculated age
                table.addCell(rs.getString("created_at"));
            }

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null, "Male Users Report generated successfully!");

            // Automatically open the file after generation
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileToSave);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage());
        } 
    }
}

public void generateAssessmentReport(String backgroundText, String introductionText) {
    String jdbcURL = "jdbc:mysql://localhost:3306/my_assignment";
    String dbUsername = "root";
    String dbPassword = "your_password"; // Update to your actual password
    String pdfPath = "Assessments_Score_Above_5.pdf";
     java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Connect to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");

        // Create SQL query to select assessments with score > 5
        String sql = "SELECT assessment_id, user_id, assessment_type, score, assessment_date, created_at FROM assessments WHERE score > 5";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(Desktop.isDesktopSupported()){
                            try{
                                File myfile = new File("Assessments_Score_Above_5.pdf");
                                Desktop.getDesktop().open(myfile); //for ubuntu replace this line with , Runtime.getRuntime().exec("evince " + "HitTable.pdf");  //evince is a pdf opening prog in ubuntu
                            }catch(IOException ex){}
                            }

        // Create a PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();

        // Add title to the PDF
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph title = new Paragraph("Assessments Report - Scores Above 5", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" ")); // Add some space
 // Add the background section
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Paragraph background = new Paragraph("Background", sectionFont);
            background.setAlignment(Element.ALIGN_LEFT);
            document.add(background);
            document.add(new Paragraph(backgroundText, bodyFont));  // Add background text

            document.add(new Paragraph(" ")); // Empty line for spacing

            // Add the introduction section
            Paragraph intro = new Paragraph("Introduction", sectionFont);
            intro.setAlignment(Element.ALIGN_LEFT);
            document.add(intro);
            document.add(new Paragraph(introductionText, bodyFont));  // Add introduction text

            document.add(new Paragraph(" ")); // Empty line for spacing
        // Create a table with 6 columns (for assessment_id, user_id, assessment_type, score, assessment_date, created_at)
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100); // Set table width to 100% of the page

        // Add table headers
        table.addCell("Assessment ID");
        table.addCell("User ID");
        table.addCell("Assessment Type");
        table.addCell("Score");
        table.addCell("Assessment Date");
        table.addCell("Created At");

        // Loop through the result set and add rows to the table
        while (resultSet.next()) {
            table.addCell(resultSet.getString("assessment_id"));
            table.addCell(resultSet.getString("user_id"));
            table.addCell(resultSet.getString("assessment_type"));
            table.addCell(resultSet.getString("score"));
            table.addCell(resultSet.getString("assessment_date"));
            table.addCell(resultSet.getString("created_at"));
        }

        // Add the table to the PDF document
        document.add(table);

        // Close the document
        document.close();

        // Close the database connection
        conn.close();

        // Inform the user that the report was generated
        JOptionPane.showMessageDialog(null, "Report generated successfully at: " + pdfPath);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage());
    }
}

public void generateActivityReport(String backgroundText, String introductionText) {
    String jdbcURL = "jdbc:mysql://localhost:3306/my_assignment";
    String dbUsername = "root";
    String dbPassword = "your_password"; // Update to your actual password
    String pdfPath = "One_Hour.pdf";
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Connect to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");

        // Create SQL query to select activities with duration > 1 hour
        String sql = "SELECT activity_id, user_id, activity_type, activity_duration, activity_notes, activity_date, created_at " +
                     "FROM activitylogs WHERE activity_duration >= '01:00:00'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Create a PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();

        // Add title to the PDF
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph title = new Paragraph("Activities Report - Duration More Than 1 Hour", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" ")); // Add some space

            // Add the background section
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Paragraph background = new Paragraph("Background", sectionFont);
            background.setAlignment(Element.ALIGN_LEFT);
            document.add(background);
            document.add(new Paragraph(backgroundText, bodyFont));  // Add background text

            document.add(new Paragraph(" ")); // Empty line for spacing

            // Add the introduction section
            Paragraph intro = new Paragraph("Introduction", sectionFont);
            intro.setAlignment(Element.ALIGN_LEFT);
            document.add(intro);
            document.add(new Paragraph(introductionText, bodyFont));  // Add introduction text

            document.add(new Paragraph(" ")); // Empty line for spacing
        // Create a table with 7 columns (for activity_id, user_id, activity_type, activity_duration, activity_notes, activity_date, created_at)
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100); // Set table width to 100% of the page

        // Add table headers
        table.addCell("Activity ID");
        table.addCell("User ID");
        table.addCell("Activity Type");
        table.addCell("Duration");
        table.addCell("Notes");
        table.addCell("Activity Date");
        table.addCell("Created At");

        // Loop through the result set and add rows to the table
        while (resultSet.next()) {
            table.addCell(resultSet.getString("activity_id"));
            table.addCell(resultSet.getString("user_id"));
            table.addCell(resultSet.getString("activity_type"));
            table.addCell(resultSet.getString("activity_duration"));
            table.addCell(resultSet.getString("activity_notes"));
            table.addCell(resultSet.getString("activity_date"));
            table.addCell(resultSet.getString("created_at"));
        }

        // Add the table to the PDF document
        document.add(table);

        // Close the document
        document.close();

        // Close the database connection
        conn.close();
if(Desktop.isDesktopSupported()){
                            try{
                                File myfile = new File("One_Hour.pdf");
                                Desktop.getDesktop().open(myfile); //for ubuntu replace this line with , Runtime.getRuntime().exec("evince " + "HitTable.pdf");  //evince is a pdf opening prog in ubuntu
                            }catch(IOException ex){}
                            }
        // Inform the user that the report was generated
        JOptionPane.showMessageDialog(null, "Activity Report generated successfully at: " + pdfPath);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error generating activity report: " + e.getMessage());
    }
}

public void generateDailyGoalsReport(String backgroundText, String introductionText) {
    String jdbcURL = "jdbc:mysql://localhost:3306/my_assignment";
    String dbUsername = "root";
    String dbPassword = "your_password"; // Update to your actual password
    String pdfPath = "Daily_Goals_Report.pdf";
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Connect to the database
        conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);

        // Create SQL query to select daily goals
        String sql = "SELECT goal_id, user_id, goal_description, goal_type, target_date, is_achieved, created_at " +
                     "FROM goals WHERE goal_type = 'Daily'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Create a PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();
       // Add the background text
        Font backgroundFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
        Paragraph background = new Paragraph("Background", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        background.setAlignment(Element.ALIGN_LEFT);
        document.add(background);
        document.add(new Paragraph(backgroundText, backgroundFont));
        
        document.add(new Paragraph(" ")); // Empty line for spacing

        // Add the introduction text
        Paragraph intro = new Paragraph("Introduction", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        intro.setAlignment(Element.ALIGN_LEFT);
        document.add(intro);
        document.add(new Paragraph(introductionText, backgroundFont));
        
        document.add(new Paragraph(" ")); // Empty line for spacing
        // Add title to the PDF
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph title = new Paragraph("Daily Goals Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" ")); // Add some space

        // Create a table with 7 columns (for goal_id, user_id, goal_description, goal_type, target_date, is_achieved, created_at)
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100); // Set table width to 100% of the page

        // Add table headers
        table.addCell("Goal ID");
        table.addCell("User ID");
        table.addCell("Goal Description");
        table.addCell("Goal Type");
        table.addCell("Target Date");
        table.addCell("Is Achieved");
        table.addCell("Created At");

        // Loop through the result set and add rows to the table
        while (resultSet.next()) {
            table.addCell(resultSet.getString("goal_id"));
            table.addCell(resultSet.getString("user_id"));
            table.addCell(resultSet.getString("goal_description"));
            table.addCell(resultSet.getString("goal_type"));
            table.addCell(resultSet.getString("target_date"));
            table.addCell(resultSet.getString("is_achieved").equals("1") ? "Yes" : "No");
            table.addCell(resultSet.getString("created_at"));
        }

        // Add the table to the PDF document
        document.add(table);

        // Close the document
        document.close();

        // Close the database connection
        conn.close();

        // Now that the document is closed and written, open the PDF
        if (Desktop.isDesktopSupported()) {
            try {
                File myfile = new File(pdfPath);
                if (myfile.exists()) {
                    Desktop.getDesktop().open(myfile);
                } else {
                    JOptionPane.showMessageDialog(null, "The file " + pdfPath + " doesn't exist.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Inform the user that the report was generated
        JOptionPane.showMessageDialog(null, "Daily Goals Report generated successfully at: " + pdfPath);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error generating daily goals report: " + e.getMessage());
    }
}

 
 private void openDataFrame(String tableName) {
    // Create new frame with the table name
    JFrame dataFrame = new JFrame(tableName);
    dataFrame.setSize(1024, 768); // Larger default size
    dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    dataFrame.setLocationRelativeTo(null);

    // Set custom font
    java.awt.Font mainFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14); 
    java.awt.Font headerFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16);
    
    // Main panel with padding
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    mainPanel.setBackground(new Color(240, 240, 240));

    // Table panel
    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(new Color(51, 153, 255), 2),
        tableName,
        TitledBorder.LEFT,
        TitledBorder.TOP,
        headerFont,
        new Color(51, 51, 51)
    ));

    // Customize table appearance
    JTable table = new JTable() {
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component comp = super.prepareRenderer(renderer, row, column);
            if (row % 2 == 0) {
                comp.setBackground(Color.WHITE);
            } else {
                comp.setBackground(new Color(245, 245, 250));
            }
            if (isCellSelected(row, column)) {
                comp.setBackground(new Color(186, 220, 255));
            }
            return comp;
        }
    };
    
table.setFont(mainFont);
    table.setRowHeight(25);
    table.getTableHeader().setFont(headerFont);
    table.getTableHeader().setBackground(new Color(51, 153, 255));
    table.getTableHeader().setForeground(Color.green);
    table.getTableHeader().setOpaque(true); // Make header opaque
    ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(SwingConstants.LEFT); // Align header text left
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setShowGrid(true);
    table.setGridColor(new Color(220, 220, 220));
    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    scrollPane.getViewport().setBackground(Color.WHITE); 
    
    // Load data from the database
    loadDataFromDatabase(tableName, table);
    
    // Button panel with modern styling
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setBackground(new Color(240, 240, 240));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

    // Navigation buttons panel
    JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    navPanel.setBackground(new Color(240, 240, 240));
    
    // Action buttons panel
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
    actionPanel.setBackground(new Color(240, 240, 240));

    // Create and style buttons
    JButton[] buttons = {
        createStyledButton("First", "⏮", mainFont, new Color(51, 153, 255)),
        createStyledButton("Previous", "◀", mainFont, new Color(51, 153, 255)),
        createStyledButton("Next", "▶", mainFont, new Color(51, 153, 255)),
        createStyledButton("Last", "⏭", mainFont, new Color(51, 153, 255)),
        createStyledButton("Add", "＋", mainFont, new Color(46, 204, 113)),
        createStyledButton("Edit", "✎", mainFont, new Color(241, 196, 15)),
        createStyledButton("Delete", "╳", mainFont, new Color(231, 76, 60)),
        createStyledButton("Refresh", "↻", mainFont, new Color(52, 152, 219))
    };

    // Add action listeners
    buttons[0].addActionListener(e -> {
        offset = 0;
        loadDataFromDatabase(tableName, table);
    });

    buttons[1].addActionListener(e -> {
        if(offset > 0) {
            offset = Math.max(0, offset - limit);
            loadDataFromDatabase(tableName, table);
        } else {
            showStyledMessage("You are already on the first page", JOptionPane.INFORMATION_MESSAGE);
        }
    });

    buttons[2].addActionListener(e -> {
        int totalRows = getTotalRows(tableName);
        if(offset + limit < totalRows) {
            offset += limit;
            loadDataFromDatabase(tableName, table);
        } else {
            showStyledMessage("You are already on the last page", JOptionPane.INFORMATION_MESSAGE);
        }
    });

    buttons[3].addActionListener(e -> {
        int totalRows = getTotalRows(tableName);
        offset = Math.max(0, totalRows - limit);
        loadDataFromDatabase(tableName, table);
    });

    buttons[4].addActionListener(e -> {
        try {
            handleAddRecord(tableName, table);
        } catch (SQLException ex) {
            Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDataFromDatabase(tableName, table); // Refresh after adding
    });

    buttons[5].addActionListener(e -> {
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) {
            try {
                handleEditRecord(tableName, table);
            } catch (SQLException ex) {
                Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadDataFromDatabase(tableName, table); // Refresh after editing
        } else {
            showStyledMessage("Please select a record to edit", JOptionPane.WARNING_MESSAGE);
        }
    });

    buttons[6].addActionListener(e -> {
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                dataFrame,
                "Are you sure you want to delete this record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if(confirm == JOptionPane.YES_OPTION) {
                try {
                    handleDeleteRecord(tableName, table);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                loadDataFromDatabase(tableName, table); // Refresh after deleting
            }
        } else {
            showStyledMessage("Please select a record to delete", JOptionPane.WARNING_MESSAGE);
        }
    });

    buttons[7].addActionListener(e -> {
        loadDataFromDatabase(tableName, table);
        showStyledMessage("Data refreshed successfully", JOptionPane.INFORMATION_MESSAGE);
    });

    // Add navigation buttons
    for(int i = 0; i < 8; i++) {
        navPanel.add(buttons[i]);
    }

    // Add action buttons
    for(int i = 8; i < buttons.length; i++) {
        actionPanel.add(buttons[i]);
    }

    // Combine button panels
    buttonPanel.add(navPanel);
    buttonPanel.add(actionPanel);

    // Add components to panels
    tablePanel.add(scrollPane, BorderLayout.CENTER);
    mainPanel.add(tablePanel, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    
    // Final frame setup
    dataFrame.add(mainPanel);
    dataFrame.setVisible(true);
}

// Helper method to create styled buttons
private JButton createStyledButton(String text, String icon, java.awt.Font font, Color color) {
    JButton button = new JButton(icon + " " + text);
    button.setFont(font);
    button.setForeground(Color.WHITE);
    button.setBackground(color);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setPreferredSize(new Dimension(120, 35));
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Add hover effect
    button.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            button.setBackground(color.brighter());
        }
        public void mouseExited(MouseEvent e) {
            button.setBackground(color);
        }
    });
    
    return button;
}

// Helper method for styled messages
private void showStyledMessage(String message, int messageType) {
    UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    UIManager.put("OptionPane.buttonFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    JOptionPane.showMessageDialog(null, message, "Message", messageType);
}


private void loadDataFromDatabase(String tableName, JTable table) {
    // JDBC connection and query to load data from the database
     String url = "jdbc:mysql://localhost:3306/my_assignment"; // Change your database name here
        String dbUser = "root"; // Change your DB username
        String dbPassword = "your_password"; // Change your DB password
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");
        String query = "SELECT * FROM " + tableName + " LIMIT ? OFFSET ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, limit);
        stmt.setInt(2, offset);
        rs = stmt.executeQuery();
        
        // Get metadata and column names
        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

        // Load data into table
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(rowData);
        }
        table.setModel(tableModel);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading data from database: " + ex.getMessage());
    }
}
// Method to get the total number of rows in a table
private int getTotalRows(String tableName) {
    int totalRows = 0;
     java.sql.Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");
        String query = "SELECT COUNT(*) FROM " + tableName;
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            totalRows = rs.getInt(1); // Get the total count of rows
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error getting total row count: " + ex.getMessage());
    }
    return totalRows;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame());
    }

    private void handleAddRecord(String tableName, JTable table) throws SQLException {
        java.sql.Connection conn = null;
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");
        // Get column names from table model
    int columnCount = table.getColumnCount();
    String[] columnNames = new String[columnCount];
    for (int i = 0; i < columnCount; i++) {
        columnNames[i] = table.getColumnName(i);
    }
    
    // Create input fields for each column
    JPanel panel = new JPanel(new GridLayout(columnCount, 2, 5, 5));
    JTextField[] fields = new JTextField[columnCount];
    
    for (int i = 0; i < columnCount; i++) {
        panel.add(new JLabel(columnNames[i] + ":"));
        fields[i] = new JTextField(20);
        panel.add(fields[i]);
    }
    
    // Show input dialog
    int result = JOptionPane.showConfirmDialog(null, panel, 
            "Add New Record", JOptionPane.OK_CANCEL_OPTION);
            
    if (result == JOptionPane.OK_OPTION) {
        try {
            // Build SQL insert statement
            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
            sql.append(String.join(", ", columnNames));
            sql.append(") VALUES (");
            
            // Add placeholders for values
            for (int i = 0; i < columnCount; i++) {
                if (i > 0) sql.append(", ");
                sql.append("?");
            }
            sql.append(")");
            
            // Execute insert
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < columnCount; i++) {
                    stmt.setString(i + 1, fields[i].getText());
                }
                stmt.executeUpdate();
                showStyledMessage("Record added successfully", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            showStyledMessage("Error adding record: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    private void handleEditRecord(String tableName, JTable table) throws SQLException {
        java.sql.Connection conn = null;
         int selectedRow = table.getSelectedRow();
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");
    if (selectedRow == -1) return;
    
    // Get column names and current values
    int columnCount = table.getColumnCount();
    String[] columnNames = new String[columnCount];
    String[] currentValues = new String[columnCount];
    
    for (int i = 0; i < columnCount; i++) {
        columnNames[i] = table.getColumnName(i);
        currentValues[i] = table.getValueAt(selectedRow, i).toString();
    }
    
    // Create input fields with current values
    JPanel panel = new JPanel(new GridLayout(columnCount, 2, 5, 5));
    JTextField[] fields = new JTextField[columnCount];
    
    for (int i = 0; i < columnCount; i++) {
        panel.add(new JLabel(columnNames[i] + ":"));
        fields[i] = new JTextField(currentValues[i], 20);
        panel.add(fields[i]);
    }
    
    // Show edit dialog
    int result = JOptionPane.showConfirmDialog(null, panel, 
            "Edit Record", JOptionPane.OK_CANCEL_OPTION);
            
    if (result == JOptionPane.OK_OPTION) {
        try {
            // Build SQL update statement
            StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
            for (int i = 0; i < columnCount; i++) {
                if (i > 0) sql.append(", ");
                sql.append(columnNames[i] + " = ?");
            }
            sql.append(" WHERE " + columnNames[0] + " = ?"); // Assuming first column is ID
            
            // Execute update
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < columnCount; i++) {
                    stmt.setString(i + 1, fields[i].getText());
                }
                stmt.setString(columnCount + 1, currentValues[0]); // ID for WHERE clause
                stmt.executeUpdate();
                showStyledMessage("Record updated successfully", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            showStyledMessage("Error updating record: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    private void handleDeleteRecord(String tableName, JTable table) throws SQLException {
         java.sql.Connection conn = null;
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_assignment", "root", "your_password");
         int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) return;
    
    try {
        // Get ID (assuming first column is ID)
        String id = table.getValueAt(selectedRow, 0).toString();
        
        // Build and execute delete statement
        String sql = "DELETE FROM " + tableName + " WHERE " + table.getColumnName(0) + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            showStyledMessage("Record deleted successfully", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException ex) {
        showStyledMessage("Error deleting record: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
    }
    }
}
