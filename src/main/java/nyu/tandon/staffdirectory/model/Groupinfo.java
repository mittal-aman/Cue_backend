package nyu.tandon.staffdirectory.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Groupinfo implements Serializable {

    @Id
    @Column(name = "groupCode")
    private String groupCode;
    @Column(name = "deptCode")
    private String deptCode;
    @Column(name = "flag")
    private String flag;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "groupDescription")
    private String groupDescription;

}
