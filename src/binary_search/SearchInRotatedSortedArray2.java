package binary_search;

/**
 * Description: https://leetcode.com/problems/search-in-rotated-sorted-array-ii
 * Difficulty: Medium
 * Time complexity: O(log n) average, O(n) worst
 * Space complexity: O(1)
 */
public class SearchInRotatedSortedArray2 {

    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // [2, 3, 4, 0, 1]

            // imagine we have [1,0,1,1,1] array
            // nums[left] == nums[mid] == nums[right]
            // there's no way to determine whether [left, mid] and [mid, right] are sorted or not
            while (left < right && nums[left] == nums[left + 1]) left++;
            while (left < right && nums[right] == nums[right - 1]) right--;

            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;

            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
