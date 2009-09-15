package eu.ebdit.eau.util;

import eu.ebdit.eau.Status;

public enum DefaultStatus implements Status {
    OK {
	@Override
	public boolean isOK() {
	    return true;
	}
    },
    FAILED {
	@Override
	public boolean isOK() {
	    return false;
	}
    },
    ERROR {
	@Override
	public boolean isOK() {
	    return false;
	}
    };

    public abstract boolean isOK();
}
