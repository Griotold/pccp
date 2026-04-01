package week1;

/*
 * [1주차 Day3] 배열 회전 (Rotate Array)
 *
 * 문제:
 * 정수 배열 nums와 정수 k가 주어졌을 때,
 * 배열을 오른쪽으로 k칸 회전시킨 결과를 반환하세요.
 *
 * 메서드 시그니처:
 * public static int[] solution(int[] nums, int k)
 *
 * 입출력 예시:
 * nums = [1, 2, 3, 4, 5], k = 2  → [4, 5, 1, 2, 3]
 * nums = [1, 2, 3], k = 1        → [3, 1, 2]
 * nums = [1, 2, 3], k = 5        → [2, 3, 1]  (k가 배열 길이보다 클 수 있음!)
 * nums = [1], k = 3              → [1]
 *
 * 힌트:
 * - k가 배열 길이보다 클 때를 먼저 처리하세요. (% 연산)
 * - 새 배열을 만들어서 옮기는 방법이 가장 직관적입니다.
 */
public class Day1_260401_3 {

    public static int[] solution(int[] nums, int k) {
        int len = nums.length;

        int[] result = new int[len];
        for(int i = 0 ; i < len; i++) {
            int next = (i + k) % len;
            result[next] = nums[i];
        }
        return result;
    }

    // 뒤집기(reverse) 방식 — 추가 배열 없이 O(1) 공간
    // [1,2,3,4,5] k=2
    // 1단계: 전체 뒤집기    → [5,4,3,2,1]
    // 2단계: 앞 k개 뒤집기  → [4,5,3,2,1]
    // 3단계: 나머지 뒤집기   → [4,5,1,2,3]
    public static int[] solutionReverse(int[] nums, int k) {
        int[] result = nums.clone();
        int len = result.length;
        k %= len;

        reverse(result, 0, len - 1);
        reverse(result, 0, k - 1);
        reverse(result, k, len - 1);
        return result;
    }

    private static void reverse(int[] arr, int lt, int rt) {
        while (lt < rt) {
            int temp = arr[lt];
            arr[lt] = arr[rt];
            arr[rt] = temp;
            lt++;
            rt--;
        }
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (check(solution(new int[]{1, 2, 3, 4, 5}, 2), new int[]{4, 5, 1, 2, 3}) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + (check(solution(new int[]{1, 2, 3}, 1), new int[]{3, 1, 2}) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + (check(solution(new int[]{1, 2, 3}, 5), new int[]{2, 3, 1}) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + (check(solution(new int[]{1}, 3), new int[]{1}) ? "PASS" : "FAIL"));
    }

    private static boolean check(int[] result, int[] expected) {
        if (result.length != expected.length) return false;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != expected[i]) return false;
        }
        return true;
    }
}
