import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class MaleUsersReport {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/my_assignment"; // Update your database connection URL
        String username = "root"; // Update with your DB username
        String password = "your_password"; // Update with your DB password

        String query = "SELECT user_id, username, email, date_of_birth, created_at " +
                "FROM users WHERE gender = 'Male' AND TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) > 18";

        Document document = new Document();

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();
             FileOutputStream fileOut = new FileOutputStream("Males_Above_18_Report.pdf")) {

            PdfWriter.getInstance(document, fileOut);
            document.open();

            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Males Above 18 Years Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Empty line for spacing

            // Create table
            PdfPTable table = new PdfPTable(5); // 5 columns: user_id, username, email, date_of_birth, created_at
            table.setWidthPercentage(100);

            // Add table headers
            addTableHeader(table);

            // Populate table with data
            while (resultSet.next()) {
                table.addCell(String.valueOf(resultSet.getInt("user_id")));
                table.addCell(resultSet.getString("username"));
                table.addCell(resultSet.getString("email"));
                table.addCell(resultSet.getString("date_of_birth"));
                table.addCell(resultSet.getString("created_at"));
            }

            document.add(table);
            document.close();

            System.out.println("PDF Report generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to add table header
    private static void addTableHeader(PdfPTable table) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase("User ID", headerFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Username", headerFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Email", headerFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Date of Birth", headerFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Created At", headerFont));
        table.addCell(cell);
    }
}
