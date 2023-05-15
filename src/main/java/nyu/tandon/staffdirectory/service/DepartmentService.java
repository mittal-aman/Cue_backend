package nyu.tandon.staffdirectory.service;


import nyu.tandon.staffdirectory.model.Department;
import nyu.tandon.staffdirectory.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public Department findDeptName(String deptCode) {
        return departmentRepo.findByDeptCode(deptCode);
    }

}