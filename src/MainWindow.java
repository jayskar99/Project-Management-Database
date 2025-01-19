import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow() {
        // Create and set up the frame
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create components
        JButton jobButton = new JButton("Open Job View");
        JButton employeeButton = new JButton("Open Employee View");
        JButton projectButton = new JButton("Open Project View");
        JButton logButton = new JButton("Open Work Log View");

        // Set layout
        setLayout(new GridLayout(4, 1));

        // Add components to the frame
        add(jobButton);
        add(employeeButton);
        add(projectButton);
        add(logButton);

        jobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openJobView();
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEmployeeView();
            }
        });

        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProjectView();
            }
        });

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLogView();
            }
        });
    }

    private void openJobView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JobView().setVisible(true);
            }
        });
    }

    private void openEmployeeView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeView().setVisible(true);
            }
        });
    }

    private void openProjectView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProjectView().setVisible(true);
            }
        });
    }

    private void openLogView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogView().setVisible(true);
                // new LogTableView().setVisible(true);
            }
        });
    }
}

