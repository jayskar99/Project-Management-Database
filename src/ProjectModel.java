import java.util.List;
import java.util.ArrayList;

public class ProjectModel {
    public String project_name;
    public int project_number;
    public float total_charge;
    public int project_lead;
    public List<EmployeeModel> employees;

    public ProjectModel(){
        employees = new ArrayList<EmployeeModel>();
    }
}