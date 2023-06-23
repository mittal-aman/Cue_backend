package nyu.tandon.staffdirectory.repository;

import nyu.tandon.staffdirectory.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepo extends JpaRepository<Directory, String> {
    List<Directory> findByEmpTypeCodeIgnoreCaseAndDeptCodeIgnoreCase(String empTypeCode, String deptCode);
    List<Directory> findByDeptCodeIgnoreCaseAndSpecCodeIgnoreCase(String deptCode,String specCode);
}
