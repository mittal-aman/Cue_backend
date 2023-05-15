package nyu.tandon.staffdirectory.repository;

import nyu.tandon.staffdirectory.model.Department;
import nyu.tandon.staffdirectory.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, String> {

    Department findByDeptCode(String deptCode);

}
