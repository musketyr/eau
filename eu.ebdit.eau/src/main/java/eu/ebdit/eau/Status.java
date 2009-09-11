package eu.ebdit.eau;

public enum Status {
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
