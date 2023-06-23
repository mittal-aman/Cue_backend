package nyu.tandon.staffdirectory.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(DirectoryId.class)
public class Directory implements Serializable {

    @Id
    @Column(name = "netId")
    private String netId;
    @Column(name = "empTypeCode")
    private String empTypeCode;
    @Column(name = "deptCode")
    private String deptCode;
    @Id
    @Column(name = "specCode")
    private String specCode;


}
