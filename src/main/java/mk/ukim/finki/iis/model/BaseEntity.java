package mk.ukim.finki.iis.model;

import javax.persistence.*;

/**
 * Created by User on 11/30/2015.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long entityId;

    public BaseEntity() {

    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return !(getEntityId() != null ? !getEntityId().equals(that.getEntityId()) : that.getEntityId() != null);

    }

    @Override
    public int hashCode() {
        return getEntityId() != null ? getEntityId().hashCode() : 0;
    }
}
