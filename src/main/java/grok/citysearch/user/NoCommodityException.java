package grok.citysearch.user;

public class NoCommodityException extends RuntimeException{
	private String message;
	public NoCommodityException(String message) {
		this.setMessage(message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
