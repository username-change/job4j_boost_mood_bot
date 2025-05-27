package ru.job4j.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mb_user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "client_id", unique = true)
	private long clientId;
    
    @Column(name = "chat_id")
	private long chatId;

	public User(Long id, long clientId, long chatId) {
		this.id = id;
		this.clientId = clientId;
		this.chatId = chatId;
	}
	
	public User() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatId, clientId, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		return chatId == other.chatId && clientId == other.clientId && Objects.equals(id, other.id);
	}
	
}
