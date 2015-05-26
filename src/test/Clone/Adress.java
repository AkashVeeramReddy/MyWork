package test.Clone;

public class Adress {
	private String locn;

	/**
	 * @param locn the locn to set
	 */
	public void setLocn(String locn) {
		this.locn = locn;
	}

	/**
	 * @return the locn
	 */
	public String getLocn() {
		return locn;
	}
	
	@Override
	protected Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}
}
