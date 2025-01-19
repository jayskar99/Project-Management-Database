import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectView extends JFrame {
    private final Database database;

    private JTextField nameField;
    private JTextField numberField;
    private JTextField chargeField;
    private JTextField leadField;

    private JButton addButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;

    public ProjectView() {
        database = new Database();

        // Create and set up the frame
        setTitle("Project View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField();
        JLabel chargeLabel = new JLabel("Charge:");
        chargeField = new JTextField();
        JLabel leadLabel = new JLabel("Lead:");
        leadField = new JTextField();
        addButton = new JButton("Add Project");
        readButton = new JButton("Read Project");
        updateButton = new JButton("Update Project");
        deleteButton = new JButton("Delete Project");

        // Set layout
        setLayout(new GridLayout(6, 2));

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(numberLabel);
        add(numberField);
        add(chargeLabel);
        add(chargeField);
        add(leadLabel);
        add(leadField);
        add(addButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject();
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readProject();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProject();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProject();
            }
        });
    }

    private void addProject() {
        int projectNumber = Integer.parseInt(numberField.getText());
        String projectName = nameField.getText();
        int projectLead = Integer.parseInt(leadField.getText());

        ProjectModel project = new ProjectModel();
        project.project_number = projectNumber;
        project.project_name = projectName;
        project.project_lead = projectLead;

        database.createProject(project);
    }

    private void readProject() {
        int projectNumber = Integer.parseInt(numberField.getText());
        ProjectModel project = database.readProject(projectNumber);

        nameField.setText(project.project_name);
        chargeField.setText(String.valueOf(project.total_charge));
        leadField.setText(String.valueOf(project.project_lead));
        new ProjectEmployeeView(project.employees).setVisible(true);
    }

    private void updateProject() {
        int projectNumber = Integer.parseInt(numberField.getText());
        String projectName = nameField.getText();
        int projectLead = Integer.parseInt(leadField.getText());

        ProjectModel project = new ProjectModel();
        project.project_number = projectNumber;
        project.project_name = projectName;
        project.project_lead = projectLead;

        database.updateProject(project);
    }

    private void deleteProject() {
        int projectNumber = Integer.parseInt(numberField.getText());
        database.deleteProject(projectNumber);
    }
}
