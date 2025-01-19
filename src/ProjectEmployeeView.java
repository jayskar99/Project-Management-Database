import java.util.List;

import javax.swing.*;

public class ProjectEmployeeView extends JFrame {
    private JTextArea employeeDisplay;
    private String employeeString;

    public ProjectEmployeeView(List<EmployeeModel> employees) {
        employeeDisplay = new JTextArea();
        employeeString = "employee_name, project_hours\n";
        // Create and set up the frame
        setTitle("Project View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Add components to the frame
        add(employeeDisplay);

        for (EmployeeModel employee : employees) {
            employeeString = employeeString + employee.employee_name + ", " + employee.total_hours + "\n";
        }

        employeeDisplay.setText(employeeString);
    }
}
