package nyu.tandon.staffdirectory.repository;


import nyu.tandon.staffdirectory.model.Groupinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepo extends JpaRepository<Groupinfo, String> {

    List<Groupinfo> findByFlagIgnoreCaseAndDeptCodeIgnoreCase(String flag,String deptCode);
    Groupinfo findByDeptCodeIgnoreCaseAndGroupNameIgnoreCase(String deptCode,String groupName);

}
