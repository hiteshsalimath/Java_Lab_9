import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class PublicTransit {
    private String route;
    private String vehicleType;
    private int capacity;
    private double fare;

    public PublicTransit(String route, String vehicleType, int capacity, double fare) {
        this.route = route;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Route: " + route + ", Vehicle Type: " + vehicleType + ", Capacity: " + capacity + ", Fare: " + fare;
    }
}

class PublicTransitGUI extends JFrame {
    private List<PublicTransit> transitList;

    private JTextField routeField;
    private JTextField vehicleTypeField;
    private JTextField capacityField;
    private JTextField fareField;
    private JTextArea displayArea;

    public PublicTransitGUI() {
        super("Public Transit Application");

        transitList = new ArrayList<>();

        setLayout(new FlowLayout());

        routeField = new JTextField(20);
        vehicleTypeField = new JTextField(20);
        capacityField = new JTextField(10);
        fareField = new JTextField(10);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton addButton = new JButton("Add Transit");
        JButton displayButton = new JButton("Display Transit");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTransit();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransit();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(new JLabel("Route: "));
        add(routeField);
        add(new JLabel("Vehicle Type: "));
        add(vehicleTypeField);
        add(new JLabel("Capacity: "));
        add(capacityField);
        add(new JLabel("Fare: "));
        add(fareField);
        add(addButton);
        add(displayButton);
        add(exitButton);
        add(new JScrollPane(displayArea));

        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTransit() {
        String route = routeField.getText();
        String vehicleType = vehicleTypeField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        double fare = Double.parseDouble(fareField.getText());

        PublicTransit newTransit = new PublicTransit(route, vehicleType, capacity, fare);
        transitList.add(newTransit);

        routeField.setText("");
        vehicleTypeField.setText("");
        capacityField.setText("");
        fareField.setText("");

        JOptionPane.showMessageDialog(this, "Transit added successfully!");
    }

    private void displayTransit() {
        displayArea.setText("");
        if (transitList.isEmpty()) {
            displayArea.setText("No transit available.");
        } else {
            for (PublicTransit transit : transitList) {
                displayArea.append(transit.toString() + "\n");
            }
        }
    }
}

public class Lab9 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PublicTransitGUI();
            }
        });
    }
}