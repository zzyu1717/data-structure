package zzy.array;

/**
 * //TODO
 *
 * @since: 1.0 created by zhaozhenyu on 2020/10/5 10:09
 * @link: zzyu1010@163.com
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1,3,4,8,9,11,34,89,100,101};
        int index = floor(nums, 0);
        int index2 = ceil(nums, 36);
//        System.out.println(index == -1? -1 : nums[index]);
        System.out.println(index2 == -1? -1 : nums[index2]);
    }

    public static int ceil(int[] nums,int key) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int m = (r-l)/2+l;
            if (nums[m] <= key) {
                l = m+1;
            } else {
                r = m-1;
            }
        }
        // 全部小于key
        if (l  == nums.length) {
            return -1;
        }
        // 全部大于key
        if (r < 0) {
            return 0;
        }
        return nums[r] == key ? r : r+1;
    }

    public static int floor(int[] nums,int key) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int m = (r-l)/2+l;
            if (nums[m] >= key) {
                r = m -1;
            } else {
                l = m+1;
            }
        }
        // 全部大于key
        if (r < 0) {
            return -1;
        }
        // 全部小于key
        if (l == nums.length) {
            return nums.length-1;
        }
        return nums[l] == key ? l : l-1;
    }

    public static int find(int[] nums,int key) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int m = (r-l)/2+l;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                r = m -1;
            } else {
                l = m+1;
            }
        }
        return -1;
    }
}
