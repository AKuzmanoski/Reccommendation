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
}
