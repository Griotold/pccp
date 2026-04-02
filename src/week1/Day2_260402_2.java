package week1;

import java.util.*;

/*
 * [1주차 Day2] 중복 없는 최대 구간 (Longest Substring Without Repeating)
 *
 * 문제:
 * 정수 배열 nums가 주어졌을 때, 중복된 원소가 없는 연속 부분 배열의 최대 길이를 반환하세요.
 *
 * 메서드 시그니처:
 * public static int solution(int[] nums)
 *
 * 입출력 예시:
 * [1,2,3,1,2]       → 3     ([1,2,3])
 * [1,1,1,1]         → 1     ([1])
 * [1,2,3,4,5]       → 5     (전체)
 * [4,2,4,2,1]       → 3     ([4,2,1], 인덱스 2~4)
 * []                → 0
 *
 * 힌트:
 * - HashSet으로 현재 구간에 어떤 원소가 있는지 추적하세요.
 * - 투 포인터(left, right)로 구간을 조절하세요.
 * - 중복이 발견되면 left를 오른쪽으로 이동시키며 중복을 제거하세요.
 */
public class Day2_260402_2 {

    public static int solution(int[] nums) {
        if(nums.length == 0) return 0;

        int lt = 0, rt = 0, result = 0;
        Set<Integer> set = new HashSet<>();
        while(rt < nums.length) {
            if(set.contains(nums[rt])) {
                set.remove(nums[lt]);
                lt++;
            } else {
                set.add(nums[rt]);
                result = Math.max(result, set.size());
                rt++;
            }
        }
        return result;
    }

    // Claude 풀이: HashMap으로 중복 원소의 마지막 인덱스를 저장해서 left를 한번에 점프
    public static int solution2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // 값 → 마지막 등장 인덱스
        int left = 0, result = 0;

        for (int right = 0; right < nums.length; right++) {
            if (map.containsKey(nums[right]) && map.get(nums[right]) >= left) {
                left = map.get(nums[right]) + 1; // 중복 원소 다음으로 바로 점프
            }
            map.put(nums[right], right);
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (3 == solution(new int[]{1,2,3,1,2}) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + (1 == solution(new int[]{1,1,1,1}) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + (5 == solution(new int[]{1,2,3,4,5}) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + (3 == solution(new int[]{4,2,4,2,1}) ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + (0 == solution(new int[]{}) ? "PASS" : "FAIL"));
    }
}
