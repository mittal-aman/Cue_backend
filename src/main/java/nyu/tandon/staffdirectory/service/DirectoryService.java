package nyu.tandon.staffdirectory.service;


import nyu.tandon.staffdirectory.model.Directory;
import nyu.tandon.staffdirectory.repository.DirectoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {

    private DirectoryRepo directoryRepo;

    @Autowired
    public DirectoryService(DirectoryRepo directoryRepo) {
        this.directoryRepo = directoryRepo;
    }

    public List<Directory> findAllEmployees(String deptCode, String empTypeCode) {
        return directoryRepo.findByEmpTypeCodeIgnoreCaseAndDeptCodeIgnoreCase(empTypeCode, deptCode);
    }

}