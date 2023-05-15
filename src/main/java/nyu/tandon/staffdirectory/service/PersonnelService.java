package nyu.tandon.staffdirectory.service;


import nyu.tandon.staffdirectory.model.Personnel;
import nyu.tandon.staffdirectory.repository.PersonnelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    private PersonnelRepo personnelRepo;

    @Autowired
    public PersonnelService(PersonnelRepo personnelRepo) {
        this.personnelRepo = personnelRepo;
    }

    public List<Personnel> findEmployeesInfo(List<String> netIds) {
        return personnelRepo.findByNetIdIn(netIds);
    }

}