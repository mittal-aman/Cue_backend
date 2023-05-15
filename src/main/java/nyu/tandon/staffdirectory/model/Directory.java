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
public class Directory implements Serializable {

    @Id
    @Column(name = "netId")
    private String netId;
    @Column(name = "empTypeCode")
    private String empTypeCode;
    @Column(name = "deptCode")
    private String deptCode;
    @Column(name = "specCode")
    private String specCode;


}
