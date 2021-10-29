package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {
    /**
     * createdat
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdat;

    /**
     * updatedAt
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdat;
    }

    public void setCreatedAt(final Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
	 * @param createdat
	 * @param updatedAt
	 */
	public AuditModel(final Date createdat,final Date updatedAt) {
		super();
		this.createdat = createdat;
		this.updatedAt = updatedAt;
	}

	public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

	/**
	 * AuditModel
	 */
	public AuditModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}