import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomerSearchGUI extends JFrame {
    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionModel;
    private List<String> allCustomers;

    public CustomerSearchGUI() {
        // Initialize data
        allCustomers = loadAllCustomers();

        // Setup GUI
        setLayout(new BorderLayout());

        // Search field
        searchField = new JTextField();
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateSuggestions();
            }
        });
        add(searchField, BorderLayout.NORTH);

        // Suggestion list
        suggestionModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionModel);
        JScrollPane scrollPane = new JScrollPane(suggestionList);
        add(scrollPane, BorderLayout.CENTER);

        // Frame settings
        setTitle("Customer Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    private void updateSuggestions() {
        String query = searchField.getText().toLowerCase();
        suggestionModel.clear();
        for (String customer : allCustomers) {
            if (customer.toLowerCase().contains(query)) {
                suggestionModel.addElement(customer);
            }
        }
    }

    private List<String> loadAllCustomers() {
        // This method should return a list of all customers.
        // Replace this with actual data loading code.
        List<String> customers = new ArrayList<>();
        customers.add("Alice");
        customers.add("Bob");
        customers.add("Charlie");
        // ... load more customers
        return customers;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerSearchGUI().setVisible(true);
        });
    }
}

