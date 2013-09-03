/**
 * 
 */
package tr.com.srdc.mdr.core.model;

/**
 * @author anil
 * 
 */
public class MDRException extends Exception {

	private static final long serialVersionUID = -596274139454781905L;

	public MDRException(String msg) {
		super(msg);
	}

	public MDRException(Throwable cause) {
		super(cause);
	}

	public MDRException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
