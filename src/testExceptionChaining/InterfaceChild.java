package testExceptionChaining;

import java.io.IOException;

public interface InterfaceChild extends InterfaceParent {
	
	void methodA() throws RuntimeException;
	
}	
