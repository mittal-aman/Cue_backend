package nyu.tandon.staffdirectory.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DirectoryId implements Serializable {
    private String netId;
    private String specCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryId that = (DirectoryId) o;
        return Objects.equals(netId, that.netId) &&
                Objects.equals(specCode, that.specCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netId, specCode);
    }
}
