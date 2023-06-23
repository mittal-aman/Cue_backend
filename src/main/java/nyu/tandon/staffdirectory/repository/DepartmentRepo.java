package nyu.tandon.staffdirectory.repository;

import nyu.tandon.staffdirectory.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, String> {

    Department findByDeptCode(String deptCode);

}
