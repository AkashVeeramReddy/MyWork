package permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

https://leetcode.com/problems/subsets-ii/
 * @author user
 *
 */
public class SubsetsWithDuplicates {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Integer lastSeen = null;
        int count = 0;
        List<Info> infos = new ArrayList<Info>();
        for(int i=0;i<nums.length;i++) {
            
            if(lastSeen != null) {
                if(lastSeen == nums[i]) {
                    count++;
                } else {
                    Info info = new Info();
                    info.num = lastSeen;
                    info.count = count;
                    infos.add(info);
                    
                    lastSeen = nums[i];
                    count = 1;
                }
                
            } else {
                lastSeen = nums[i];
                count = 1;
            }
        }
        Info info = new Info();
        info.num = lastSeen;
        info.count = count;
        infos.add(info);
        System.out.println(infos);
        //List<In>
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        list.add(new ArrayList<Integer>());
        int[]array = new int[nums.length];
        populate(list,array,infos,0,0,0);
        return list;
    }
    
    public void populate(List<List<Integer>> list,int[]array,List<Info> infos, int insertIdx, int idxInfos, int infoCount) {
        if(idxInfos == infos.size()) {
            /*
            List<Integer> eles = new ArrayList<Integer>();
            for(int i=0;i<insertIdx;i++) {
                eles.add(array[i]);
            }
            list.add(eles);
            */
            return;
        }
        Info info = infos.get(idxInfos);
        if(infoCount <info.count) {
            array[insertIdx] = info.num;
            List<Integer> eles = new ArrayList<Integer>();
            for(int i=0;i<=insertIdx;i++) {
                eles.add(array[i]);
            }
            list.add(eles);
            //add more eles
            populate(list,array,infos,insertIdx+1,idxInfos,infoCount+1);
        }
        for(int i=idxInfos+1;i<infos.size();i++) {
            array[insertIdx] = infos.get(i).num;
            List<Integer> eles = new ArrayList<Integer>();
            for(int j=0;j<=insertIdx;j++) {
                eles.add(array[j]);
            }
            list.add(eles);
            populate(list,array,infos,insertIdx+1,i,1);
        }
        
    }
    
    public static class Info {
        public int count;
        public int num;
        
        public String toString() {
            return "{c"+count + ",n" + num + "}";
        }
    }
}
