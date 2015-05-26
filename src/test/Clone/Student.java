package test.Clone;

public class Student implements Cloneable{
	
	private Adress adress;
	
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	/**
	 * @return the adress
	 */
	public Adress getAdress() {
		return adress;
	}
	
	public static void main(String[] args) {
		int [] array = new int[1];
		Class<? extends int[]> class1 = array.getClass();
		System.out.println(class1);
		Student strudent = new Student();
		Adress adress2 = new Adress();
		adress2.setLocn("jai");
		
		strudent.setAdress(adress2);
		Object clone = strudent.clone();
		System.out.println();
		
	}
}
