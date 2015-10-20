package numbers;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * If it is overflow, return MAX_INT.
 * 
 * @author user
 * 
 */
public class DivideUsingSubtraction {

    public static int divide(int dividend, int divisor) {
        if(divisor == 0)
            return Integer.MAX_VALUE;
        if(divisor == 1)
            return dividend;
        if(divisor == -1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
        if(divisor == -1)
            return -dividend;
        long divid = dividend;
        long divis = divisor;
        int absQuot = (int)dividePos(Math.abs(divid),Math.abs(divis));
        if(dividend > 0 && divisor > 0) {
            return absQuot;
        } else if(dividend < 0 && divisor < 0) {
            return absQuot;
        }
        return -absQuot;
    }
    
    public static long dividePos(long dividend, long divisor) {
        if(dividend < divisor)
            return 0;
        long factor = 1;
        long product = divisor;
        long oldProduct = 1;
        while(product < dividend) {
            factor = factor*2;
            oldProduct = product;
            product = product*2;
        }
        if(product == dividend)
            return factor;
        //product between dividend and two dividend
        factor = factor>>1;
        System.out.println(factor);
        long end = product;
        long start = oldProduct;
        long interval = factor;
        while(start <= end) {
            if(start == end) {
                return factor;   
            } else {
                //start < end
                long mid = (start + end)>>1;
                System.out.println("mid "+mid+",start "+start+",end "+end);
                if(mid == dividend) {
                    factor = factor + (interval>>1);
                    break;
                } else if(mid < dividend) {
                    factor = factor + (interval>>1);
                    interval = interval>>1;
                    start = mid;
                    
                } else {
                    //factor = factor + (interval>>1);
                    interval = interval>>1;
                    end = mid;
                }
                System.out.println("factor "+factor);
            }
        }
        return factor;
    }

}
