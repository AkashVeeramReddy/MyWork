package pattern;

class PatternUtils {
	
	public static final boolean isEqual(char[] array1, int start1Idx, int end1Idx,
										char[] array2, int start2Idx, int end2Idx) {
		try {
			int len1 = end1Idx - start1Idx + 1;
			int len2 = end2Idx - start2Idx + 1;
			if(len1 != len2)
				return false;
			int idx1Itr, idx2Itr;
			for (int i = 0; i < len1; i++) {
				idx1Itr = i + start1Idx;
				idx2Itr = i + start2Idx;
				if(array1[idx1Itr] != array2[idx2Itr]) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
