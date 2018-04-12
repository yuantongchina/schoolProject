package entity;

public class Sensor {
	private String nodeName;		//节点名称
	private int ID;					//节点ID
	private int ram_capacity = 0;	//ram容量，单位KB
	private int ram_usage = 0;		//ram使用率
	private int cpu_frequency = 0;	//cpu主频,单位hz
	private int cpu_usage = 0;		//cpu使用率
	private float x;				//横坐标
	private float y;				//纵坐标
	private int degree;				//邻接顶点数
	private int[] adjoin_sensor;	//邻接顶点
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int[] getAdjoin_sensor() {
		return adjoin_sensor;
	}
	public void setAdjoin_sensor(int[] adjoin_sensor) {
		this.adjoin_sensor = adjoin_sensor;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getRam_capacity() {
		return ram_capacity;
	}
	public void setRam_capacity(int ram_capacity) {
		this.ram_capacity = ram_capacity;
	}
	public int getRam_usage() {
		return ram_usage;
	}
	public void setRam_usage(int ram_usage) {
		this.ram_usage = ram_usage;
	}
	public int getCpu_frequency() {
		return cpu_frequency;
	}
	public void setCpu_frequency(int cpu_frequency) {
		this.cpu_frequency = cpu_frequency;
	}
	public int getCpu_usage() {
		return cpu_usage;
	}
	public void setCpu_usage(int cpu_usage) {
		this.cpu_usage = cpu_usage;
	}
	
}
