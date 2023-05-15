package nyu.tandon.staffdirectory.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department implements Serializable {

    @Id
    @Column(name = "deptCode")
    private String deptCode;
    @Column(name = "deptName")
    private String deptName;


}
