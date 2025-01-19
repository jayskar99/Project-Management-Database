import java.sql.*;

public class Database {
    public Connection conn = connect();

    public Connection connect() {
        Connection newConnection = null;
        try {
            String url = "jdbc:sqlite:project.db";
            newConnection = DriverManager.getConnection(url);
            System.out.print("connected!\n");
        } catch (SQLException sqle) {
            System.out.print("not connected!\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return newConnection;
    }

    public void disconnect() {
        try {
            conn.close();
            System.out.print("disconnected!\n");
        } catch (SQLException sqle) {
            System.out.print("not disconnected!\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    // getters
    public float getEmployeeCharge(int employee_number) {
        try {
            PreparedStatement getRole = conn.prepareStatement(
                "SELECT role FROM Employees WHERE employee_number = ?;");
            getRole.setInt(1,employee_number);
            ResultSet result1 = getRole.executeQuery();
            String role = result1.getString("role");

            PreparedStatement getCharge_Hour = conn.prepareStatement(
                "SELECT charge_hour FROM Roles WHERE role = ?;");
            getCharge_Hour.setString(1,role);
            ResultSet result2 = getCharge_Hour.executeQuery();
            return result2.getFloat("charge_hour");
        } catch (SQLException sqle) {
            System.out.print("failed to get employee charge\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return 0f;
    }

    // CRUD job
    public void createJob(JobModel job) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "INSERT INTO Roles(role,charge_hour) VALUES (?,?);");
            q.setString(1, job.role);
            q.setFloat(2, job.charge_hour);
            q.executeUpdate();
            System.out.print("job created\n");
        } catch (SQLException sqle) {
            System.out.print("job not created\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public JobModel readJob(int role_number) {
        JobModel job = new JobModel();
        try {
            PreparedStatement q = conn.prepareStatement(
                "SELECT * FROM Roles WHERE role_number = ?;");
            q.setInt(1, role_number);
            ResultSet result = q.executeQuery();
            result.next();
            job.role_number = result.getInt("role_number");
            job.role = result.getString("role");
            job.charge_hour = result.getFloat("charge_hour");
            System.out.print("job read\n");
        } catch (SQLException sqle) {
            System.out.print("job not read\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return job;
    }

    public void updateJob(JobModel job) {
        deleteJob(job.role_number);
        createJob(job);
    }

    public void deleteJob(int role_number) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "DELETE FROM Roles WHERE role_number = ?;");
            q.setInt(1, role_number);
            q.executeUpdate();
            System.out.print("job deleted\n");
        } catch (SQLException sqle) {
            System.out.print("job not deleted\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    // CRUD employee
    public void createEmployee(EmployeeModel employee) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "INSERT INTO Employees(employee_name,employee_number,role) VALUES (?,?,?);");
            q.setString(1, employee.employee_name);
            q.setInt(2, employee.employee_number);
            q.setString(3, employee.role);
            q.executeUpdate();
            System.out.print("employee created\n");
        } catch (SQLException sqle) {
            System.out.print("employee not created\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public EmployeeModel readEmployee(int employee_number) {
        EmployeeModel employee = new EmployeeModel();
        try {
            PreparedStatement q = conn.prepareStatement(
                "SELECT * FROM Employees WHERE employee_number = ?;");
            q.setInt(1, employee_number);
            ResultSet result = q.executeQuery();
            result.next();
            employee.employee_name = result.getString("employee_name");
            employee.employee_number = result.getInt("employee_number");
            employee.role = result.getString("role");
            employee.total_hours = result.getFloat("total_hours");

            result.close();
            q.close();
            System.out.print("employee read\n");
        } catch (SQLException sqle) {
            System.out.print("employee not read\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return employee;
    }

    public void updateEmployee(EmployeeModel employee) {
        deleteEmployee(employee.employee_number);
        createEmployee(employee);
    }

    public void deleteEmployee(int employee_number) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "DELETE FROM Employees WHERE employee_number = ?;");
            q.setInt(1, employee_number);
            q.executeUpdate();
            System.out.print("employee deleted\n");
        } catch (SQLException sqle) {
            System.out.print("employee not deleted\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }
    
    public void updateEmployeeHours(int employee_number, float hours) {
        try {
            float total_hours = readEmployee(employee_number).total_hours;

            PreparedStatement q = conn.prepareStatement(
                "UPDATE Employees SET total_hours = ? WHERE employee_number = ?;");
            q.setFloat(1,total_hours+hours);
            q.setInt(2, employee_number);
            q.executeUpdate();
        } catch (SQLException sqle) {
            System.out.print("failed to update employee hours\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    // CRUD project
    public void createProject(ProjectModel project) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "INSERT INTO Projects(project_name,project_number,total_charge,project_lead) VALUES (?,?,?,?);");
            q.setString(1, project.project_name);
            q.setInt(2, project.project_number);
            q.setFloat(3, 0.0f);
            q.setInt(4, project.project_lead);
            q.executeUpdate();
            System.out.print("project created\n");
        } catch (SQLException sqle) {
            System.out.print("project not created\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public ProjectModel readProject(int project_number) {
        ProjectModel project = new ProjectModel();
        try {
            PreparedStatement getProject = conn.prepareStatement(
                "SELECT project_name,total_charge,project_lead FROM Projects WHERE project_number = ?;");
            getProject.setInt(1,project_number);
            ResultSet resultProject = getProject.executeQuery();
            project.project_name = resultProject.getString("project_name");
            project.project_lead = resultProject.getInt("project_lead");
            project.total_charge = resultProject.getFloat("total_charge");

            resultProject.close();
            getProject.close();

            PreparedStatement getEmployees = conn.prepareStatement(
                "SELECT employee_number,project_hours FROM Work_Log WHERE project_number = ?;");
            getEmployees.setInt(1,project_number);
            ResultSet resultEmployees = getEmployees.executeQuery();
            while (resultEmployees.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.employee_number = resultEmployees.getInt("employee_number");
                employee.total_hours = resultEmployees.getFloat("project_hours");
                employee.employee_name = readEmployee(employee.employee_number).employee_name;
                project.employees.add(employee);
            }
            System.out.print("project read\n");
        } catch (SQLException sqle) {
            System.out.print("project not read\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return project;
    }

    public void updateProject(ProjectModel project) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "UPDATE Projects SET project_name = ? WHERE project_number = ?;");
            PreparedStatement q2 = conn.prepareStatement(
                "UPDATE Projects SET project_lead = ? WHERE project_number = ?;");
            q.setString(1,project.project_name);
            q.setInt(2,project.project_number);
            q2.setInt(1, project.project_lead);
            q2.setInt(2,project.project_number);
            q.executeUpdate();
            q2.executeUpdate();
        } catch (SQLException sqle) {
            System.out.print("not updated\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public void deleteProject(int project_number) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "DELETE FROM Projects WHERE project_number = ?;");
            q.setInt(1, project_number);
            q.executeUpdate();
            System.out.print("project deleted\n");
        } catch (SQLException sqle) {
            System.out.print("project not deleted\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public void updateProjectTotal(int project_number, float charge) {
        try {
            float total_charge = readProject(project_number).total_charge;

            PreparedStatement q = conn.prepareStatement(
                "UPDATE Projects SET total_charge = ? WHERE project_number = ?;");
            q.setFloat(1,total_charge+charge);
            q.setInt(2, project_number);
            q.executeUpdate();
        } catch (SQLException sqle) {
            System.out.print("failed to update project total\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    // CRUD log
    public void createLog(LogModel log) {
        try {
            PreparedStatement q = conn.prepareStatement(
                "INSERT INTO Work_Log(project_hours,employee_number,project_number) VALUES (?,?,?);");
            q.setFloat(1, log.project_hours);
            q.setInt(2, log.employee_number);
            q.setInt(3, log.project_number);
            q.executeUpdate();

            float total_charge = getEmployeeCharge(log.employee_number)*log.project_hours;
            updateEmployeeHours(log.employee_number, log.project_hours);
            updateProjectTotal(log.project_number, total_charge);
            System.out.print("log created\n");
        } catch (SQLException sqle) {
            System.out.print("log not created\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public LogModel readLog(int entry_number) {
        LogModel log = new LogModel();
        try {
            PreparedStatement q = conn.prepareStatement(
                "SELECT * FROM Work_Log WHERE entry_number = ?;");
            q.setInt(1, entry_number);
            ResultSet result = q.executeQuery();
            result.next();
            log.entry_number = entry_number;
            log.project_hours = result.getFloat("project_hours");
            log.employee_number = result.getInt("employee_number");
            log.project_number = result.getInt("project_number");
            System.out.print("log read\n");
        } catch (SQLException sqle) {
            System.out.print("log not read\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return log;
    }

    public void updateLog(LogModel log) {
        deleteLog(log.entry_number);
        createLog(log);
    }

    public void deleteLog(int entry_number) {
        try {
            LogModel log = readLog(entry_number);
            PreparedStatement q = conn.prepareStatement(
                "DELETE FROM Work_Log WHERE entry_number = ?;");
            q.setInt(1, entry_number);
            q.executeUpdate();

            float total_charge = getEmployeeCharge(log.employee_number)*log.project_hours;
            updateEmployeeHours(log.employee_number, log.project_hours*-1);
            updateProjectTotal(log.project_number, total_charge*-1);
            System.out.print("log deleted\n");
        } catch (SQLException sqle) {
            System.out.print("log not deleted\n");
            System.err.println(sqle);
            System.exit(1);
        }
    }

    public int getMaxEntryNumber() throws SQLException {
        try {
            // Assuming there is an entry_number field in Work_Log
            PreparedStatement getMaxEntryNumberStatement = conn.prepareStatement(
                "SELECT MAX(entry_number) FROM Work_Log");
            ResultSet result = getMaxEntryNumberStatement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.print("max entry failed\n");
            System.err.println(sqle);
            System.exit(1);
        }
        return 0;
    }
};