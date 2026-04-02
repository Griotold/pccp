package week1;

import java.util.*;

/*
 * [1주차 Day2] 두 배열의 공통 원소 (Intersection of Two Arrays)
 *
 * 문제:
 * 두 정수 배열 nums1, nums2가 주어졌을 때, 두 배열에 공통으로 존재하는 원소를
 * 중복 없이 오름차순으로 정렬하여 반환하세요.
 *
 * 메서드 시그니처:
 * public static int[] solution(int[] nums1, int[] nums2)
 *
 * 입출력 예시:
 * [1,2,3,4], [3,4,5,6]       → [3,4]
 * [1,1,2,2], [2,2,3,3]       → [2]
 * [5,3,1], [1,3,5,7]         → [1,3,5]
 * [1,2,3], [4,5,6]           → []
 * [], [1,2,3]                → []
 *
 * 힌트:
 * - HashSet을 활용하면 중복 제거와 포함 여부 확인을 O(1)로 할 수 있습니다.
 * - 결과를 정렬하는 것을 잊지 마세요. (Arrays.sort)
 */
public class Day2_260402_1 {

    public static int[] solution(int[] nums1, int[] nums2) {
        // 첫번째 배열을 해시셋으로 넣는다.
        Set<Integer> set1 = new HashSet<>();
        for(int a : nums1) {
            set1.add(a);
        }
        Set<Integer> set2 = new HashSet<>();
        for(int a : nums2) {
            if(set1.contains(a)) {
                set2.add(a);
            }
        }
        int[] arr = set2.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(arr);
        return arr;
    }

    // Claude 풀이: retainAll로 교집합을 한 줄로 처리
    public static int[] solution2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int a : nums1) set1.add(a);

        Set<Integer> set2 = new HashSet<>();
        for (int a : nums2) set2.add(a);

        set1.retainAll(set2); // set1에 교집합만 남김

        int[] arr = set1.stream().mapToInt(Integer::intValue).toArray();
        // 스트림 안쓰면 for-loop이라면 어떻게 되는거야?
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (Arrays.equals(new int[]{3,4}, solution(new int[]{1,2,3,4}, new int[]{3,4,5,6})) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + (Arrays.equals(new int[]{2}, solution(new int[]{1,1,2,2}, new int[]{2,2,3,3})) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + (Arrays.equals(new int[]{1,3,5}, solution(new int[]{5,3,1}, new int[]{1,3,5,7})) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + (Arrays.equals(new int[]{}, solution(new int[]{1,2,3}, new int[]{4,5,6})) ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + (Arrays.equals(new int[]{}, solution(new int[]{}, new int[]{1,2,3})) ? "PASS" : "FAIL"));
    }
}
