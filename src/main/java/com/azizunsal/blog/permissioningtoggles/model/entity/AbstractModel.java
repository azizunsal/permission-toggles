package com.azizunsal.blog.permissioningtoggles.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author azizunsal
 */
@MappedSuperclass
public class AbstractModel implements Serializable {
    private static final long serialVersionUID = 4154755307049091715L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(nullable = false, updatable = false)
    protected Date created = new Date();

    @Column(nullable = false)
    protected Date updated = new Date();

    protected boolean deleted;

    public AbstractModel() {

    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created != null ? (Date) created.clone() : null;
    }

    public void setCreated(final Date created) {
        this.created = created != null ? (Date) created.clone() : null;
    }

    public Date getUpdated() {
        return (Date) updated.clone();
    }

    public void setUpdated(final Date updated) {
        this.updated = updated != null ? (Date) updated.clone() : null;
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", deleted=" + deleted +
                '}';
    }
}
