package exception;

import util.MSG_CONST;

public class SysException extends Exception {

	public SysException(String message){
		super(message);
	}

	public SysException(MSG_CONST msg_const) {
		super(msg_const.toString());
	}	
}
