import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
    private final Database database;

    private JTextField nameField;
    private JTextField numberField;
    private JTextField roleField;
    private JTextField hoursField;

    private JButton addButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;

    public EmployeeView() {
        database = new Database();

        // Create and set up the frame
        setTitle("Employee View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        roleField = new JTextField();
        JLabel hoursLabel = new JLabel("Hours:");
        hoursField = new JTextField();
        addButton = new JButton("Add Employee");
        readButton = new JButton("Read Employee");
        updateButton = new JButton("Update Employee");
        deleteButton = new JButton("Delete Employee");

        // Set layout
        setLayout(new GridLayout(6, 2));

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(numberLabel);
        add(numberField);
        add(roleLabel);
        add(roleField);
        add(hoursLabel);
        add(hoursField);
        add(addButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readEmployee();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
    }

    private void addEmployee() {
        String name = nameField.getText();
        int number = Integer.parseInt(numberField.getText());
        String role = roleField.getText();
        float hours = Float.parseFloat(hoursField.getText());

        EmployeeModel employee = new EmployeeModel();
        employee.employee_name = name;
        employee.employee_number = number;
        employee.role = role;
        employee.total_hours = hours;

        database.createEmployee(employee);
    }

    private void readEmployee() {
        int employeeNumber = Integer.parseInt(numberField.getText());
        EmployeeModel employee = database.readEmployee(employeeNumber);

        nameField.setText(employee.employee_name);
        roleField.setText(employee.role);
        hoursField.setText(String.valueOf(employee.total_hours));
    }

    private void updateEmployee() {
        String name = nameField.getText();
        int number = Integer.parseInt(numberField.getText());
        String role = roleField.getText();
        float hours = Float.parseFloat(hoursField.getText());

        EmployeeModel employee = new EmployeeModel();
        employee.employee_name = name;
        employee.employee_number = number;
        employee.role = role;
        employee.total_hours = hours;

        database.updateEmployee(employee);
    }

    private void deleteEmployee() {
        int employeeNumber = Integer.parseInt(numberField.getText());
        database.deleteEmployee(employeeNumber);
    }
}
