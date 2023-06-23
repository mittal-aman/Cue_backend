package nyu.tandon.staffdirectory.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Personnel implements Serializable {

    @Id
    @NotEmpty(message = "N-Number cannot be empty or null")
    @Column(unique = true, name = "netId")
    private String netId;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "personalUrl")
    private String personalUrl;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "facultyType")
    private String facultyType;
    @Column(name = "brooklyn")
    private String brooklyn;
    @Column(name = "officeNum")
    private String officeNum;
    @Column(name = "title")
    private String title;


}
