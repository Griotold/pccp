package week1;

import java.util.HashMap;
import java.util.Map;

/*
 * [1주차 Day2] 두 수의 합 (Two Sum)
 *
 * 문제:
 * 정수 배열 nums와 정수 target이 주어졌을 때,
 * 배열에서 두 수를 더해 target이 되는 두 수의 인덱스를 반환하세요.
 * - 같은 요소를 두 번 사용할 수 없습니다.
 * - 정답은 반드시 하나 존재합니다.
 * - 반환하는 배열의 순서는 상관없습니다.
 *
 * 메서드 시그니처:
 * public static int[] solution(int[] nums, int target)
 *
 * 입출력 예시:
 * nums = [2, 7, 11, 15], target = 9   → [0, 1]  (2 + 7 = 9)
 * nums = [3, 2, 4],      target = 6   → [1, 2]  (2 + 4 = 6)
 * nums = [3, 3],          target = 6   → [0, 1]  (3 + 3 = 6)
 *
 * 힌트:
 * - 이중 for문(브루트포스)으로 먼저 풀어보세요.
 * - 여유가 되면 HashMap을 활용해 O(n)으로 도전해보세요.
 */
public class Day1_260401_2 {

    public static int[] solution(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if(nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[]{};
    }

    // HashMap을 활용한 O(n) 풀이
    public static int[] solutionWithMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++) {
            int temp = target - nums[i];
            if(map.containsKey(temp)) {
                return new int[] {map.get(temp), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        // 테스트 케이스
        int[] r1 = solution(new int[]{2, 7, 11, 15}, 9);
        System.out.println("테스트 1: " + (check(r1, 0, 1) ? "PASS" : "FAIL"));

        int[] r2 = solution(new int[]{3, 2, 4}, 6);
        System.out.println("테스트 2: " + (check(r2, 1, 2) ? "PASS" : "FAIL"));

        int[] r3 = solution(new int[]{3, 3}, 6);
        System.out.println("테스트 3: " + (check(r3, 0, 1) ? "PASS" : "FAIL"));

        // HashMap 풀이 테스트
        int[] r4 = solutionWithMap(new int[]{2, 7, 11, 15}, 9);
        System.out.println("테스트 4 (Map): " + (check(r4, 0, 1) ? "PASS" : "FAIL"));

        int[] r5 = solutionWithMap(new int[]{3, 2, 4}, 6);
        System.out.println("테스트 5 (Map): " + (check(r5, 1, 2) ? "PASS" : "FAIL"));

        int[] r6 = solutionWithMap(new int[]{3, 3}, 6);
        System.out.println("테스트 6 (Map): " + (check(r6, 0, 1) ? "PASS" : "FAIL"));
    }

    // 순서 상관없이 두 인덱스가 맞는지 검증
    private static boolean check(int[] result, int a, int b) {
        if (result.length != 2) return false;
        return (result[0] == a && result[1] == b) || (result[0] == b && result[1] == a);
    }
}
