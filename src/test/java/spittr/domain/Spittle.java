package spittr.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Cache(region="spittleCache", usage=CacheConcurrencyStrategy.READ_WRITE)
public class Spittle implements Serializable {

	private static final long serialVersionUID = 1L;

	public Spittle() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="spitter")
	private Spitter spitter;
	
	@Column
	private String message;
	
	@Column
	private Date postedTime;

	public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
		this.id = id;
		this.spitter = spitter;
		this.message = message;
		this.postedTime = postedTime;
	}

	public Long getId() {
		return this.id;
	}

	public String getMessage() {
		return this.message;
	}

	public Date getPostedTime() {
		return this.postedTime;
	}

	public Spitter getSpitter() {
		return this.spitter;
	}

	@Override
	public String toString() {
		return "Spittle{" +
				"id=" + id +
				", spitter=" + spitter +
				", message='" + message + '\'' +
				", postedTime=" + postedTime +
				'}';
	}
}
