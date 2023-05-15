package nyu.tandon.staffdirectory.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response {
    List<Personnel> personnels;
    String deptName;

}
