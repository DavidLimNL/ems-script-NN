package nl.david.converter.ems;

public class EMSScriptException extends Exception {
	private Integer code;
	private String message;
	
	public EMSScriptException(String message) {
		super(message);
	}
	public EMSScriptException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public String printException() {
		return this.code + " - " + this.message;
	}
}
