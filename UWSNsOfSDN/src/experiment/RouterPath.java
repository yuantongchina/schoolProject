package experiment;

import java.util.ArrayList;
import java.util.Arrays;

public class RouterPath {
	/*
	 * （6）sdn水下传感器网络初始化最小权值路由系统
	1）随机产生多个节点，多个节点之间计算欧氏距离。
	2）采用欧氏距离作为相邻节点间一条路径的计算权值，写入数据库
	3）可以随机选择一个点a和点b，计算显示出所有点之间的路径。注意路由不能有环路。
	4）在web页面上列出所有a-b的可能路径表。
	5）把a-b之间的最小相邻节点距离作为下一轮次的计算值
	从上面的路径表中的每个链路上
	6）执行减法操作，计算下一个轮次的路径表
	7）重复执行4和5，直到截止。
	 * */
	/**
	 * @param args
	 */
	
	//可以随机选择一个点a和点b，计算显示出所有点之间的路径。注意路由不能有环路。
	//先写死，找ab
	public Road searchAllRoad(NetStatic net){
		/*
		 * 1、从出发点开始，出发点有邻接点，扫描该点，并将该点记入路径
		 * 2、若该点为结束点，则结束算法，并产生一条路径，否则执行3
		 * 3、若该点无未扫描过的邻接点，则回溯到上上一个遍历节点，并转入2
		 * 4、若该点有邻接点，且未扫描过，将邻接点记入路径，并且扫描，转入2
		 * */
		//初始化
		int net_size = net.net[0].length;
		Road road_tree = new Road();
		String []name_list = {"a","b","c","d","e","f","g"};
		boolean []visit_flag = new boolean[net_size];
		Node head = new Node(name_list[0]);
		Node tree_p = head;
		road_tree.setHead(head);
		//算法开始
		//1、从出发点开始，出发点有邻接点，扫描该点，并将该点记入路径
		//扫描矩阵第一行，搜索a的邻接点
		
		//创建一个栈，用于遍历过程中保存节点
		int []stack = new int[net_size];
		int top = -1;//栈顶指针，指向栈顶元素
		stack[0] = 0;
		for(int i = 0;i < net_size;i ++){
			//检查点是否与点a邻接
			if( net.net[0][i] > 0 ){//判断与a相邻接的点
				stack[top++ ] = i;
				tree_p = new Node(name_list[i]);
				if(name_list[i].equals("b")){
					//生成一条路
				}
				while(true){
					for(int j = 0;j < net_size;j ++){
						if(net.net[i][j] > 0){
							stack[top++ ] = i;
							tree_p = new Node(name_list[i]);
							if(name_list[i].equals("b")){
							}
						}//if
						
					}//for
				}//while
			}
		}
		
		
		return null;
	}
	
	public Road searchOneRoad(NetStatic net){
		//找一条路
		int net_size = net.net[0].length;
		String []name_list = {"a","b","c","d","e","f","g"};
		int []travel_stack = new int [net_size];
		boolean []travel_flag = new boolean [net_size];
		
		for(int i = 0;i < net_size;i++){
			travel_flag[i]  = false;//访问标志位，未访问为false
		}
		//开始找路径
		int stack_p = -1;
		int node_now = 0;
		for(int i = 0;i < net_size;i ++){
			stack_p = -1;
			node_now = 0;
			travel_stack[++stack_p] = node_now;
			travel_flag[node_now] = true;
			if(net.net[0][i] > 0 && !travel_flag[i]){
				node_now = i;
				travel_flag[node_now] = true;
				travel_stack[++stack_p] = node_now;
				while(node_now != 1){
					boolean has_next = false;
					for(int j = 0;j < net_size;j ++){
						//i j之间有路，且j未被访问过
						if(net.net[node_now][j] > 0 && !travel_flag[j]){
							has_next = true;
							node_now = j;
							travel_flag[j] = true;
							travel_stack[++stack_p] = node_now;
							break;
						}	
					}//for(int j = 0;j < net_size;j ++)
					if(!has_next){//没有能走下去的路径，退栈
						node_now = travel_stack[stack_p--];
					}
				}//while
				for(int i2 = 0;i2 < stack_p + 1;i2 ++){
					System.out.print(name_list[travel_stack[i2]] + "-->");
				}
				System.out.println();
				
				break;
			}//if(net.net[0][i] > 0 && !travel_flag[i])
		}//for(int i = 0;i < net_size;i ++)
		return null;
	}
	
	public Road searchMutiRoad(NetStatic net,int start,int end,String []name_list){
		/*
		 发现多条路径
		 n个点，最长路长度为n-1跳
		 进行n-1轮计算
		 第i轮找到从起始点开始经过i跳能到达的节点
		 * */
		int net_size = net.net[0].length;
		//二维数组travel_flag,travel_flag[i][j] = true表示i-j路已经存在
		//boolean数组的初始会被自动初始化为false
		
		 
		ArrayList<int []> roads = new ArrayList();
		
		//计算net_size - 1轮
		int []r1 = {start};
		roads.add(r1);
		
		ArrayList<int []> roads_ans = new ArrayList();
		for(int i = 0;i < net_size - 1;i ++){
			int roads_num = roads.size();
			for(int print_i = 0;print_i < roads_num;print_i ++){
				System.out.println(Arrays.toString(roads.get(print_i)));
			}
			for(int j = 0;j < roads_num;j ++){//取出数组中的每条路
				//取出一条路，增加路的下一跳
				int []one_road = roads.get(j);
				boolean []travel_flag = new boolean[net_size];
				//将路上经过的节点标记为已访问
				for (int road_num : one_road) {
					travel_flag[road_num] = true;
				}
				//取路上的最后一个点
				int one_road_last_node = one_road[one_road.length - 1];
				if(one_road_last_node == end){
					roads_ans.add(one_road);
					roads.remove(j);
					j --;
					roads_num --;
					continue;
				}
				//查找与one_road_last_node邻接且未在路中的点
				boolean has_next_node = false;
				for(int search_neighbour_j = 0;search_neighbour_j < net_size;search_neighbour_j ++){
					if(net.net[one_road_last_node][search_neighbour_j] > 0 
					&& travel_flag[search_neighbour_j] == false){
						//找到符合条件的下一跳点
						int []new_one_road = new int[one_road.length + 1];
						for(int copy_j = 0;copy_j < one_road.length;copy_j ++){
							new_one_road[copy_j] = one_road[copy_j];
						}
						new_one_road[one_road.length] = search_neighbour_j;
						roads.add(new_one_road);
						if(!has_next_node){//删除基路
							roads.remove(j);
							j--;
							roads_num --;
							has_next_node = true;
						}
						if(search_neighbour_j == end){
							has_next_node = true;
						}
					}//if
				}//for
				if(!has_next_node){
					roads.remove(j);
					j--;
					roads_num --;
				}
			}//for(int j = 0;j < roads_num;j ++){//取出数组中的每条路
		}
		for(int i = 0;i < roads_ans.size();i ++){
			int []road = roads_ans.get(i);
			for(int j = 0;j < road.length;j ++){
				System.out.print(name_list[road[j]] + "-->");
			}//for
			System.out.println();
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//NetStatic.show();
		RouterPath rp = new RouterPath();
		NetStatic net = new NetStatic();
		rp.searchMutiRoad(net,0,1,net.net02_name_list);
	}

}
class Roads extends ArrayList{
	
}
class Road{
	private String roadName;
	private int roadLength;
	private Node head;
	
	
	public Road() {
		super();
	}
	
	public Road(String roadName) {
		super();
		this.roadName = roadName;
	}

	public Road(String roadName, int roadLength, Node head) {
		super();
		this.roadName = roadName;
		this.roadLength = roadLength;
		this.head = head;
	}

	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public int getRoadLength() {
		return roadLength;
	}
	public void setRoadLength(int roadLength) {
		this.roadLength = roadLength;
	}
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
}

class Node{
	private String nodeName;
	private int lengthToNext;
	private Node next = null;
	
	public Node(){
		super();
	}
	public Node(String nodeName) {
		super();
		this.nodeName = nodeName;
	}
	public Node(String nodeName, int lengthToNext, Node next) {
		super();
		this.nodeName = nodeName;
		this.lengthToNext = lengthToNext;
		this.next = next;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getLengthToNext() {
		return lengthToNext;
	}
	public void setLengthToNext(int lengthToNext) {
		this.lengthToNext = lengthToNext;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}
class NetStatic{
	
	static String []net_name_list= {"a","b","c","d","e","f","g"};
	static int[][] net02 = new int[16][16];
	static String []net02_name_list = {"a","b","c","d","e","f","g"
		,"h","i","j","k","l","m","n","o","p"};
	static int [][] net = net02;
	static{
//		net[0][2] = 1;
//		net[2][0] = 1;
//		
//		net[0][3] = 2;
//		net[3][0] = 2;
//		
//		net[0][4] = 2;
//		net[4][0] = 2;
//		
//		net[1][5] = 5;
//		net[5][1] = 5;
//		
//		net[1][6] = 3;
//		net[6][1] = 3;
//		
//		net[2][6] = 2;
//		net[6][2] = 2;
//		
//		net[3][4] = 4;
//		net[4][3] = 4;
//		
//		net[3][5] = 3;
//		net[5][3] = 3;
//		
//		net[3][6] = 1;
//		net[6][3] = 1;
		
		//
		
		net02[0][2] = 3;
		net02[2][0] = 3;

		net02[0][3] = 4;
		net02[3][0] = 4;

		net02[0][4] = 2;
		net02[4][0] = 2;

		net02[0][5] = 6;
		net02[5][0] = 6;

		net02[0][6] = 3;
		net02[6][0] = 3;

		net02[0][9] = 5;
		net02[9][0] = 5;

		net02[2][8] = 4;
		net02[8][2] = 4;

		net02[2][9] = 3; 
		net02[9][2] = 3;

		net02[6][7] = 5;
		net02[7][6] = 5;

		net02[7][8] = 4;
		net02[8][7] = 4;

		net02[8][9] = 2;
		net02[9][8] = 2;

		net02[2][10] = 10;
		net02[10][2] = 10;

		net02[4][10] = 3;
		net02[10][4] = 3;

		net02[4][5] = 3;
		net02[5][4] = 3;

		net02[3][5] = 4;
		net02[5][3] = 4;

		net02[5][10] = 4;
		net02[10][5] = 4;

		net02[5][11] = 7;
		net02[11][5] = 7;

		net02[5][14] = 13;
		net02[14][5] = 13;

		net02[5][15] = 7;
		net02[15][5] = 7;

		net02[10][12] = 11;
		net02[12][10] = 11;

		net02[10][11] = 6;
		net02[11][10] = 6;

		net02[11][14] = 3;
		net02[14][11] = 3;

		net02[12][13] = 7;
		net02[13][12] = 7;

		net02[12][1] = 3;
		net02[1][12] = 3;

		net02[12][15] = 8;
		net02[15][12] = 8;

		net02[14][1] = 2;
		net02[1][14] = 2;
	}//static
	
	public static void show(){
		System.out.println("\ta\tb\tc\td\te\tf\tg");
		String cloName = "abcdefg";
		for(int i = 0;i < 7;i ++){
			System.out.print(cloName.charAt(i)+"\t");
			for(int j = 0;j < 7;j ++){
				System.out.print(net[i][j]+"\t");
			}
			System.out.println();
		}
	}
}//