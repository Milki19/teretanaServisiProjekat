import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Main extends JFrame {

    private String username;
    private String password;
    private String email;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emaillField;
    private JTextField birthdateField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton insertButton;

    public Main() {
        setTitle("H2 GUI App");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emaillField = new JTextField(20);
        birthdateField = new JTextField(20);
        insertButton = new JButton("Insert into H2");

        // Postavljamo default vrednosti za testiranje
        birthdateField.setText("dd/mm/yyyy");
    }

    private void layoutComponents() {
        setLayout(new FlowLayout());
        add(new JLabel("First name: "));
        add(firstNameField);
        add(new JLabel("Last name: "));
        add(lastNameField);
        add(new JLabel("Username: "));
        add(usernameField);
        add(new JLabel("Password: "));
        add(passwordField);
        add(new JLabel("Email: "));
        add(emaillField);
        add(new JLabel("Birth date: "));
        add(birthdateField);
        add(insertButton);
    }

    private void addListeners() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDataIntoH2();
            }
        });
    }

    private void insertDataIntoH2() {
        String jdbcUrl = "jdbc:h2:mem:test";
        String ime = "sa";
        String sifra = "";

        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, ime, sifra)) {
                String insertQuery = "INSERT INTO jdbc:h2:mem:test (name) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    firstName = firstNameField.getText();
                    lastName = lastNameField.getText();
                    username = usernameField.getText();
                    password = passwordField.getText();
                    String datum = birthdateField.getText();
                    birthDate = LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    //                preparedStatement.setString(2, name);

                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Data inserted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to insert data.");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }

        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
