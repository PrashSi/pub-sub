package edu.ds.pubsub.type;

/**
 * PubSub Message.
 * 
 */
public class Message {

	private Integer id;
	private Integer node;
	private String text;

	public Message(Integer id, Integer node, String text) {

		this.id = id;
		this.node = node;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", node=" + node + ", text=" + text + "]";
	}

	public Integer getNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}
}
