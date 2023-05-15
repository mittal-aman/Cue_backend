package nyu.tandon.staffdirectory.repository;

import nyu.tandon.staffdirectory.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelRepo extends JpaRepository<Personnel, String> {

    List<Personnel> findByNetIdIn(List<String> netIds);

}
