package numbers;

public class BigInteger {
	
	private String value = "";
	
	public BigInteger() {
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public BigInteger(String value) {
		this.value = value;
	}
	
	public static BigInteger add(BigInteger integer1,BigInteger integer2) {
		BigInteger add = new BigInteger();
		BigInteger smallerInt = integer1;
		BigInteger longerInt = integer2;
		if(integer1.value.length() > integer2.value.length()) {
			longerInt = integer1;
			smallerInt = integer2;
		}
		int sum = 0;
		int carry = 0;
		int diffInLength = longerInt.value.length() - smallerInt.value.length();
		for(int i = smallerInt.value.length();i >0;i--) {
			char charAt = smallerInt.value.charAt(i-1);
			char charAt2 = longerInt.value.charAt(i -1+ diffInLength);
			sum = charAt + charAt2 + carry - 96;
			if(sum >= 10) {
				carry = sum/10;
				sum = sum%10;
			}
			add.value = sum + add.value;
		}
		String substring = longerInt.value.substring(0, diffInLength);
		if(carry == 0) {
			add.value = substring + add.value;
		} else {
			BigInteger carryAsBigInt = new BigInteger(Integer.toString(carry));
			add.value = add(new BigInteger(substring), carryAsBigInt).value + add.value;
		}
		return add;
	}
	
	public static void main(String[] args) {
		BigInteger int1 = new BigInteger("99999999999999999999999999999999999999999999999999999999");
		BigInteger int2 = new BigInteger("11111111111111111111111111111111111111111111111111111111");
//										  11111111111111111111111111111111111111111111111111111111	
		BigInteger add = add(int1, int2);
		System.out.println(add);
	}
	
}
