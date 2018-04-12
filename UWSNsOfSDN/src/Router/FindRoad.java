package Router;

import java.util.ArrayList;
import java.util.Arrays;

import entity.Road;

import experiment.RouterPath;

public class FindRoad {
	/**
	 * @param args
	 */
	public ArrayList<Road> searchMutiRoad(NetStatic net,int start,int end,String []name_list){
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
		}//for(int i = 0;i < net_size - 1;i ++)
		//将找出的每条路的顶点序列转换为完整的路径实体，并将所有路装进ArrayList
		ArrayList<Road> roadArray = new ArrayList<Road>();
		for(int i = 0;i < roads_ans.size();i ++){
			int []road = roads_ans.get(i);
			Road r = new Road();
			r.setRoad(road);
			int[] dist = new int[road.length - 1];
			int road_dist = 0;//路径总长度
			int skip = road.length - 1;
			int rstart = -1;//开始点
			int rend = -1;//结束点 
			for(int j = 0;j < road.length - 1;j ++){
				//System.out.print(name_list[road[j]] + "-->");
				dist[j] = net.net[road[j]][road[j+1]];
				road_dist += dist[j];
			}//for
			rstart = start;
			rend = end;
			r.setDist(dist);
			r.setRoad_dist(road_dist);
			r.setSkip(skip);
			r.setStart(rstart);
			r.setEnd(rend);
			roadArray.add(r);

		}
		
		for(int i = 0;i < roadArray.size();i ++){
			System.out.println("roadArray[" + i +"]");
			System.out.println(roadArray.get(i).toString());
		}
		return roadArray;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindRoad fr = new FindRoad();
		NetStatic net = new NetStatic();
		fr.searchMutiRoad(net,0,1,net.net02_name_list);
	}

}//class FindRoad

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