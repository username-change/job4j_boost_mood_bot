package ru.job4j.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mb_achievement")
public class Achievement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private long createAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "award_id")
	private Award award;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCreateAt() {
		return createAt;
	}

	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	@Override
	public int hashCode() {
		return Objects.hash(award, createAt, id, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Achievement other = (Achievement) obj;
		return Objects.equals(award, other.award) && createAt == other.createAt && Objects.equals(id, other.id)
				&& Objects.equals(user, other.user);
	}
	
	
}
