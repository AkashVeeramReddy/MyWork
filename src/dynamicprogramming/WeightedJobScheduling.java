package dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.CollectionUtils;

/**
 * http://www.geeksforgeeks.org/weighted-job-scheduling/
 * 
 * Weighted Job Scheduling
Given N jobs where every job is represented by following three elements of it.
1) Start Time
2) Finish Time.
3) Profit or Value Associated.
Find the maximum profit subset of jobs such that no two jobs in the subset overlap.

Example:

Input: Number of Jobs n = 4
       Job Details {Start Time, Finish Time, Profit}
       Job 1:  {1, 2, 50} 
       Job 2:  {3, 5, 20}
       Job 3:  {6, 19, 100}
       Job 4:  {2, 100, 200}
Output: The maximum profit is 250.
We can get the maximum profit by scheduling jobs 1 and 4.
Note that there is longer schedules possible Jobs 1, 2 and 3 
but the profit with this schedule is 20+50+100 which is less than 250. 
 * @author user
 *
 */
public class WeightedJobScheduling {
	public static void main(String[] args) {
		Job job1 = new Job(1, 2, 50);
		Job job2 =  new Job(3, 5, 20);
		Job job3 =  new Job(6, 19, 100);
		Job job4 =  new Job(2, 100, 200);
		
		List<Job> jobs = new ArrayList<WeightedJobScheduling.Job>();
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);
		jobs.add(job4);
		Collections.sort(jobs);
		System.out.println(getMaxProfit(jobs));
	}
	
	public static int getMaxProfit(List<Job> jobs) {
		int length = jobs.size();
		int profit[] = new int[length];
		profit[0] = jobs.get(0).profit;
		Job itrJob = null;
		Job prevJobToItr = null;
		int profitWithout;
		int profitWith;
		for (int i = 1; i < profit.length; i++) {
			profitWithout = profit[i-1];
			itrJob = jobs.get(i);
			int startTime = itrJob.startTime;
			int lastIndexOf = getLastIndexOf(jobs, startTime, 0, length-1);
			if(lastIndexOf == -1) {
				profitWith = itrJob.profit;
			} else {
				profitWith = itrJob.profit + profit[lastIndexOf];
			}
			profit[i] = Math.max(profitWith, profitWithout);
		}
		return profit[length-1];
	}
	
	public static int getLastIndexOf(List<Job> jobs,int finishing,int startIdx,int endIdx) {
		if(startIdx == endIdx) {
			Job job = jobs.get(startIdx);
			if(job.finishTime <= finishing) {
				return startIdx;
			}
		} else if(startIdx < endIdx) {
			int mid = (startIdx + endIdx) / 2;
			Job midJob = jobs.get(startIdx);
			if(midJob.finishTime <= finishing) {
				Job nextJob = jobs.get(mid+1);
				if(nextJob.finishTime <= finishing) {
					return getLastIndexOf(jobs, finishing, mid+1, endIdx);
				} else {
					return mid;
				}
			} else {
				return getLastIndexOf(jobs, finishing, startIdx, mid);
			}
		}
		return -1;
	}
	
	public static class Job implements Comparable<Job> {
		
		public int startTime;
		public int finishTime;
		public int profit;
		
		public Job(int startTime, int finishTime, int profit) {
			this.startTime = startTime;
			this.finishTime = finishTime;
			this.profit = profit;
		}

		@Override
		public int compareTo(Job o) {
			int cmpFinishtTime = finishTime - o.finishTime;
			if(cmpFinishtTime == 0) {
				
			}
			return cmpFinishtTime;
		}
		
	}
}
