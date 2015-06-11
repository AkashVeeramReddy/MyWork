package divide_conquer;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
 * TODO
 * @author nithin
 *
 */
public class SkylineBuilding {

	public static class BuildingInfo {
		public int x_Left;
		public int height;
		public int x_Right;
		
		public BuildingInfo(int x_Left,int height,int x_Right) {
			this.x_Left = x_Left;
			this.height = height;
			this.x_Right = x_Right;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + height;
			result = prime * result + x_Left;
			result = prime * result + x_Right;
			return result;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("BuildingInfo [x_Left=");
			builder.append(x_Left);
			builder.append(", height=");
			builder.append(height);
			builder.append(", x_Right=");
			builder.append(x_Right);
			builder.append("]");
			return builder.toString();
		}
		
		
		
	}
	
	public static class SkyLineInfo implements Comparable<SkyLineInfo> {
		
		public int sky_x_Left;
		public int sky_height;
		
		public SkyLineInfo(int sky_x_Left,int sky_height) {
			this.sky_x_Left = sky_x_Left;
			this.sky_height = sky_height;
		}

		@Override
		public int compareTo(SkyLineInfo other) {
			if(sky_x_Left == other.sky_x_Left ) {
				return sky_height - other.sky_height;
			}
			return sky_x_Left - other.sky_x_Left;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof SkyLineInfo) {
				SkyLineInfo other = (SkyLineInfo) obj;
				return (sky_x_Left == other.sky_x_Left) && 
						(sky_height == other.sky_height);
			}
			return false;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("SkyLineInfo [sky_x_Left=");
			builder.append(sky_x_Left);
			builder.append(", sky_height=");
			builder.append(sky_height);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param buildings - should be in sorted order(first by x_left,then by x_right)
	 * @return
	 */
	public static List<SkyLineInfo> getSkyLine(List<BuildingInfo> buildings) {
		return getSkyLine(buildings, 0, buildings.size()-1);
	}
	
	public static List<SkyLineInfo> getSkyLine(List<BuildingInfo> buildings,int start,int end) {
		if(start ==  end) {
			//one building
			BuildingInfo buildingInfo = buildings.get(start);
					
			List<SkyLineInfo> skyLineInfos = new ArrayList<SkyLineInfo>();
			skyLineInfos.add(new SkyLineInfo(buildingInfo.x_Left, buildingInfo.height));
			skyLineInfos.add(new SkyLineInfo(buildingInfo.x_Right, 0));
			return skyLineInfos;
			
		} else {
			int mid = (start + end)/2;
			List<SkyLineInfo> leftSkyLineInfo = getSkyLine(buildings, start, mid);
			List<SkyLineInfo> rightSkyLineInfo = getSkyLine(buildings, mid + 1, end);
			return getMergedSkylines(leftSkyLineInfo,rightSkyLineInfo,buildings,start,mid,end);
		}
		//return null;
	}

	private static List<SkyLineInfo> getMergedSkylines(
			List<SkyLineInfo> leftSkyLines,
			List<SkyLineInfo> rightSkyLines,List<BuildingInfo> buildings,
			int start,int mid,int end) {
		List<SkyLineInfo> merge = new ArrayList<SkyLineInfo>();
		
		int leftItr = 0;
		int rightItr = 0;
		
		SkyLineInfo leftPrevSkyLine = new SkyLineInfo(0, 0);
		SkyLineInfo rightPrevSkyLine = new SkyLineInfo(0,0);
		
		SkyLineInfo leftCurSkyLine;
		SkyLineInfo rightCurSkyLine;
		
		while( (leftItr < leftSkyLines.size()) && (rightItr < rightSkyLines.size())) {
			if(leftItr == leftSkyLines.size() -1) {
				//add all right skylines
				
				return merge;
			}
			if(rightItr == rightSkyLines.size() -1) {
				//add all left skylines
				return merge;
			}
			
			leftCurSkyLine = leftSkyLines.get(leftItr);
			rightCurSkyLine = rightSkyLines.get(rightItr);
			
			int compareTo = leftCurSkyLine.compareTo(rightCurSkyLine);
			if(compareTo == 0) {
				merge.add(leftCurSkyLine);
				leftItr++;
				rightItr++;
			} else if (compareTo < 0) {
				//left skyline smaller than right skyline
			} else {
				//compareTo > 0
			}
		}
		
		for(int i= 0;i< leftSkyLines.size();) {
			for(int j= 0;i< rightSkyLines.size();) {
				SkyLineInfo leftSkyLine = leftSkyLines.get(i);
				SkyLineInfo rightSkyLine = rightSkyLines.get(j);
				
				int compareTo = leftSkyLine.compareTo(rightSkyLine);
				if(compareTo == 0) {
					i++;
				} else if(compareTo < 0) {
					j++;
				} else {
					//compareTo > 0
					i++;
				}
			}
		}
		// TODO Auto-generated method stub
		return merge;
	}
}
