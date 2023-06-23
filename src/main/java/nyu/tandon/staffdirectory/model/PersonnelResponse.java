package nyu.tandon.staffdirectory.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonnelResponse {
    List<Personnel> personnels;
    String deptName;

}
