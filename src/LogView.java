import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogView extends JFrame {
    private final Database database;

    private JTextField entryNumberField;
    private JTextField projectHoursField;
    private JTextField employeeNumberField;
    private JTextField projectNumberField;

    private JButton addButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;

    public LogView() {
        database = new Database();

        // Create and set up the frame
        setTitle("Log View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create components
        JLabel entryNumberLabel = new JLabel("Entry Number:");
        entryNumberField = new JTextField();
        JLabel projectHoursLabel = new JLabel("Project Hours:");
        projectHoursField = new JTextField();
        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        employeeNumberField = new JTextField();
        JLabel projectNumberLabel = new JLabel("Project Number:");
        projectNumberField = new JTextField();
        addButton = new JButton("Add Log");
        readButton = new JButton("Read Log");
        updateButton = new JButton("Update Log");
        deleteButton = new JButton("Delete Log");

        // Set layout
        setLayout(new GridLayout(6, 2));

        // Add components to the frame
        add(entryNumberLabel);
        add(entryNumberField);
        add(projectHoursLabel);
        add(projectHoursField);
        add(employeeNumberLabel);
        add(employeeNumberField);
        add(projectNumberLabel);
        add(projectNumberField);
        add(addButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLog();
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readLog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLog();
            }
        });
    }

    private void addLog() {
        float projectHours = Float.parseFloat(projectHoursField.getText());
        int employeeNumber = Integer.parseInt(employeeNumberField.getText());
        int projectNumber = Integer.parseInt(projectNumberField.getText());

        LogModel log = new LogModel();
        log.project_hours = projectHours;
        log.employee_number = employeeNumber;
        log.project_number = projectNumber;

        database.createLog(log);
    }

    private void readLog() {
        int entryNumber = Integer.parseInt(entryNumberField.getText());
        LogModel log = database.readLog(entryNumber);

        projectHoursField.setText(String.valueOf(log.project_hours));
        employeeNumberField.setText(String.valueOf(log.employee_number));
        projectNumberField.setText(String.valueOf(log.project_number));
    }

    private void updateLog() {
        int entryNumber = Integer.parseInt(entryNumberField.getText());
        float projectHours = Float.parseFloat(projectHoursField.getText());
        int employeeNumber = Integer.parseInt(employeeNumberField.getText());
        int projectNumber = Integer.parseInt(projectNumberField.getText());

        LogModel log = new LogModel();
        log.entry_number = entryNumber;
        log.project_hours = projectHours;
        log.employee_number = employeeNumber;
        log.project_number = projectNumber;

        database.updateLog(log);
    }

    private void deleteLog() {
        int entryNumber = Integer.parseInt(entryNumberField.getText());
        database.deleteLog(entryNumber);
    }
}

