package nyu.tandon.staffdirectory.service;


import nyu.tandon.staffdirectory.model.Groupinfo;
import nyu.tandon.staffdirectory.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private GroupRepo groupRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Groupinfo> findGroupInfo(String flag, String deptCode) {
        return groupRepo.findByFlagIgnoreCaseAndDeptCodeIgnoreCase(flag,deptCode);
    }

    public Groupinfo findGroupCode(String deptCode, String groupName) {
        return groupRepo.findByDeptCodeIgnoreCaseAndGroupNameIgnoreCase(deptCode,groupName);
    }

}