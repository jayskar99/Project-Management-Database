import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobView extends JFrame {
    private final Database database;

    private JTextField numberField;
    private JTextField roleField;
    private JTextField chargeHourField;

    private JButton addButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;

    public JobView() {
        database = new Database();

        // Create and set up the frame
        setTitle("Job View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create components
        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        roleField = new JTextField();
        JLabel chargeHourLabel = new JLabel("Charge Hour:");
        chargeHourField = new JTextField();
        addButton = new JButton("Add Job");
        readButton = new JButton("Read Job");
        updateButton = new JButton("Update Job");
        deleteButton = new JButton("Delete Job");

        // Set layout
        setLayout(new GridLayout(5, 2));

        // Add components to the frame
        add(numberLabel);
        add(numberField);
        add(roleLabel);
        add(roleField);
        add(chargeHourLabel);
        add(chargeHourField);
        add(addButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJob();
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readJob();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateJob();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteJob();
            }
        });
    }

    private void addJob() {
        String role = roleField.getText();
        float chargeHour = Float.parseFloat(chargeHourField.getText());

        JobModel job = new JobModel();
        job.role = role;
        job.charge_hour = chargeHour;

        database.createJob(job);
    }

    private void readJob() {
        int roleNumber = Integer.parseInt(numberField.getText());
        JobModel job = database.readJob(roleNumber);

        roleField.setText(job.role);
        chargeHourField.setText(String.valueOf(job.charge_hour));
    }

    private void updateJob() {
        int roleNumber = Integer.parseInt(numberField.getText());
        String role = roleField.getText();
        float chargeHour = Float.parseFloat(chargeHourField.getText());

        JobModel job = new JobModel();
        job.role_number = roleNumber;
        job.role = role;
        job.charge_hour = chargeHour;

        database.updateJob(job);
    }

    private void deleteJob() {
        int roleNumber = Integer.parseInt(numberField.getText());
        database.deleteJob(roleNumber);
    }
}
