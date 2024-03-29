package binary_search;

/**
 * Description: https://leetcode.com/problems/search-in-rotated-sorted-array
 * Difficulty: Medium
 * Time complexity: O(log n)
 * Space complexity: O(1)
 */
public class SearchInRotatedSortedArray {

    public int searchViaSinglePass(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    public int searchViaTwoPasses(int[] nums, int target) {
        int pivot = findPivot(nums);

        return target >= nums[pivot] && target <= nums[nums.length - 1]
                ? findTarget(nums, pivot, nums.length - 1, target)
                : findTarget(nums, 0, pivot - 1, target);
    }

    private int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int findTarget(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
