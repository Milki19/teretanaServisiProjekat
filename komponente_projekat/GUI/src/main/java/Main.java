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

public class Main extends JFrame {

    private String username;
    private String password;
    private String email;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField birthdateField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton insertButton;
    private JButton loginButton;
    private JButton registerButton;

    private JFrame registerFrame;
    private JFrame loginFrame;

    public Main() {
        setTitle("Teretana");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        layoutComponents();
        addListeners();

        setLocationRelativeTo(null);
    }

    private void initComponents() {
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(20);
        birthdateField = new JTextField(20);
        insertButton = new JButton("Insert into H2");
        loginButton = new JButton("Log in");
        registerButton = new JButton("Register");

        // Postavljamo default vrednosti za testiranje
        birthdateField.setText("dd/mm/yyyy");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(createSpacer(50,30));
        topPanel.add(loginButton);
        topPanel.add(registerButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());



        JLabel poruka = new JLabel("Dobrodosli u aplikaciju za vasu teretanu!");
        poruka.setFont(new Font("SansSerif", Font.PLAIN, 60));
        centerPanel.add(createSpacer(500,0));
        centerPanel.add(poruka);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void addListeners() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //insertDataIntoH2();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeRegisterScreen();
                showLoginScreen();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeLoginScreen();
                showRegisterScreen();
            }
        });
    }

    private void closeRegisterScreen(){
        if(registerFrame != null){
            registerFrame.dispose();
        }
    }

    private void closeLoginScreen(){
        if(loginFrame != null){
            loginFrame.dispose();
        }
    }

    private void showLoginScreen() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 175);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Log in");

        centerPanel.add(new JLabel("Username: "));
        centerPanel.add(usernameField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(new JLabel("Password: "));
        centerPanel.add(passwordField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(loginButton);

        panel.add(centerPanel, BorderLayout.CENTER);

        loginFrame.getContentPane().add(panel);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodajte kod za proveru korisničkih podataka
                JOptionPane.showMessageDialog(loginFrame, "Login logic here");
            }
        });

    }

    private void showRegisterScreen() {
        // Kreiranje novog prozora za registraciju
        registerFrame = new JFrame("Register");
        registerFrame.setSize(400, 300);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JButton registerButton = new JButton("Register");

        centerPanel.add(new JLabel("Username: "));
        centerPanel.add(usernameField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(new JLabel("Password: "));
        centerPanel.add(passwordField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(new JLabel("Email: "));
        centerPanel.add(emailField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(new JLabel("First name: "));
        centerPanel.add(firstNameField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(new JLabel("Last name: "));
        centerPanel.add(lastNameField);
        centerPanel.add(createSpacer(30, 40));
        centerPanel.add(registerButton);

        panel.add(centerPanel, BorderLayout.CENTER);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodajte kod za registrovanje korisnika
                JOptionPane.showMessageDialog(registerFrame, "Registration logic here");
            }
        });

        registerFrame.getContentPane().add(panel);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(true);
    }

    private JLabel createSpacer(int height, int width) {
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(width, height));
        return spacer;
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
