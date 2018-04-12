package entity;

import java.util.Arrays;

public class Road {
	int[] road = null;//点序列
	int[] dist = null;//路径上的边的距离序列
	private int road_dist = 0;//路径总长度
	private int skip = 0;
	private int start = -1;//开始点
	private int end = -1;//结束点
	
	public Road(){
		
	}
	
	public Road(int[] road, int[] dist, int road_dist, int skip, int start,
			int end) {
		super();
		this.road = road;
		this.dist = dist;
		this.road_dist = road_dist;
		this.skip = skip;
		this.start = start;
		this.end = end;
	}
	public int[] getRoad() {
		return road;
	}
	public void setRoad(int[] road) {
		this.road = road;
	}
	public int[] getDist() {
		return dist;
	}
	public void setDist(int[] dist) {
		this.dist = dist;
	}
	public int getRoad_dist() {
		return road_dist;
	}
	public void setRoad_dist(int road_dist) {
		this.road_dist = road_dist;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Road [road=" + Arrays.toString(road) + ", dist="
				+ Arrays.toString(dist) + ", road_dist=" + road_dist
				+ ", skip=" + skip + ", start=" + start + ", end=" + end + "]";
	}
	
}
