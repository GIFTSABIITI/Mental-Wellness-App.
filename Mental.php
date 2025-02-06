<?php
// PHP Variables
$websiteTitle = "Mental Health Support and Awareness";
$welcomeMessage = "Welcome to Mental Health Support and Awareness Website";
$searchPlaceholder = "Search...";
$footerMessage = "Your Safe Space for Mental Health Awareness and Support";
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo $websiteTitle; ?></title>
</head>
<body style="font-family: Arial, sans-serif; text-align: center;">

    <h1 style="color: #4CAF50;"><?php echo $welcomeMessage; ?></h1>

    <!-- Navigation -->
    <nav style="margin-bottom: 20px;">
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Home</a> |
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">About Us</a> |
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Support Forums</a> |
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Contact Us</a> |
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Tools</a>
    </nav>

    <!-- Search Bar with PHP handling form submission -->
    <p>
        <form method="GET" action="">
            <input type="text" name="search" placeholder="<?php echo $searchPlaceholder; ?>" style="padding: 5px; width: 200px;" />
            <button type="submit" style="padding: 5px 10px; background-color: #4CAF50; color: white; border: none;">Search</button>
        </form>
    </p>

    <?php
    // Handling form submission for search
    if (isset($_GET['search']) && !empty($_GET['search'])) {
        $searchTerm = htmlspecialchars($_GET['search']);
        echo "<p>You searched for: <strong>" . $searchTerm . "</strong></p>";
    }
    ?>

    <!-- Main Content -->
    <section style="margin: 20px;">
        <p style="font-size: 20px; font-weight: bold;">You Are Not Alone</p>
        <table style="margin: 0 auto; border-collapse: collapse;">
            <tr>
                <td style="border: 1px solid #ddd; padding: 10px;">Image 1</td>
                <td style="border: 1px solid #ddd; padding: 10px;">Image 2</td>
                <td style="border: 1px solid #ddd; padding: 10px;">Image 3</td>
            </tr>
            <tr>
                <td style="border: 1px solid #ddd; padding: 10px;">Image 4</td>
                <td style="border: 1px solid #ddd; padding: 10px;">Image 5</td>
                <td style="border: 1px solid #ddd; padding: 10px;">Image 6</td>
            </tr>
        </table>
    </section>

    <!-- Login and Signup Buttons -->
    <section style="margin: 20px;">
        <button style="padding: 10px 20px; margin-right: 10px; background-color: #007BFF; color: white; border: none;">Login</button>
        <button style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none;">Signup</button>
    </section>

    <!-- Footer -->
    <footer style="margin-top: 40px;">
        <p><?php echo $footerMessage; ?></p>
        <a href="#" style="text-decoration: none; color: #007BFF;">Find a therapist</a>
        <p style="margin-top: 20px;">Follow us on:</p>
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Twitter</a> |
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Facebook</a> |
        <a href="#" style="text-decoration: none; color: #007BFF; margin: 0 10px;">Instagram</a>
    </footer>

</body>
</html>
