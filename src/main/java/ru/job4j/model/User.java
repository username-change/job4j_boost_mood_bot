package ru.job4j.model;

import java.util.Objects;

public class User {
	private Long id;
	private long clientId;
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
