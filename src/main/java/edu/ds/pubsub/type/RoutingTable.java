package edu.ds.pubsub.type;

import java.util.Set;

public class RoutingTable {

	private Integer port;
	private String ip;

	private Set<String> subscribers;

	public RoutingTable(Integer port, String ip, Set<String> subscribers) {
		super();
		this.port = port;
		this.ip = ip;
		this.subscribers = subscribers;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Set<String> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Set<String> subscribers) {
		this.subscribers = subscribers;
	}

}
