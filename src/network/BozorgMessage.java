package network;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BozorgMessage implements Serializable {
	private String type;
	private Object[] args;

	public BozorgMessage(String type, Object... args) {
		this.type = type;
		this.args = args;
	}

	public String getType() {
		return type;
	}

	public Object[] getArgs() {
		return args;
	}
}
