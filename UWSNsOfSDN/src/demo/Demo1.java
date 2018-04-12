package demo;

import java.util.ArrayList;
import java.util.Arrays;

public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []nums1 = {1,2,3};
		int []nums2 = new int[4];
		for(int i = 0;i < 3;i ++){
			nums2[i] = nums1[i];
		}
		ArrayList<int []> nums_arr = new ArrayList<int[]>();
		nums_arr.add(nums1);
		for(int i = 0;i < 4;i ++){
			nums2[3] = i + 5;
			int []new_nums = nums2;
			nums_arr.add(new_nums);
		}
//		for(int i = 0;i < 4;i ++){
//			System.out.println(Arrays.toString(nums_arr.get(i)));
//		}
		
	}

}
