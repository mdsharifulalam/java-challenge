package jp.co.axa.apidemo.model;

/**
 * @author md.shariful
 *
 */
public class Response {

	private int status;
	private String msg;

	public Response() {
		super();
	}

	public Response(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
