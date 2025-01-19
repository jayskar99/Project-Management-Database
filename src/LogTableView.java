import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogTableView extends JFrame {
    private final Database database;

    private JTable logTable;
    private JButton refreshButton;

    public LogTableView() {
        database = new Database();

        // Create and set up the frame
        setTitle("Log Table View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Create components
        logTable = new JTable();
        refreshButton = new JButton("Refresh");

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        JScrollPane scrollPane = new JScrollPane(logTable);
        add(scrollPane, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> refreshLogTable());

        // Initial load of the table
        refreshLogTable();
    }

    private void refreshLogTable() {
        try {
            List<LogModel> logs = getAllLogs();
            updateLogTable(logs);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error refreshing log table: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<LogModel> getAllLogs() throws SQLException {
        List<LogModel> logs = new ArrayList<>();
        // Call readLog for each entry in Work_Log
        // You may need to modify readLog to return a list or use a different approach
        // depending on how your readLog function is implemented
        // For the sake of this example, assuming readLog reads logs by entry_number
        for (int entryNumber = 1; entryNumber <= database.getMaxEntryNumber(); entryNumber++) {
            LogModel log = database.readLog(entryNumber);
            if (log != null) {
                logs.add(log);
            }
        }
        return logs;
    }

    private void updateLogTable(List<LogModel> logs) {
        // Create a table model with appropriate column names
        String[] columnNames = {"Entry Number", "Project Hours", "Employee Number", "Project Number"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (LogModel log : logs) {
            Object[] rowData = {log.entry_number, log.project_hours, log.employee_number, log.project_number};
            tableModel.addRow(rowData);
        }

        // Set the table model to the JTable
        logTable.setModel(tableModel);
    }
}
