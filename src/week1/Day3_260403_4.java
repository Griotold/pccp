package week1;

import java.util.*;

/*
 * [1주차 Day3] 두 배열 합치고 정렬하기 (Merge and Sort Two Arrays)
 *
 * 문제:
 * 두 정수 배열 arr1, arr2가 주어졌을 때,
 * 두 배열을 합친 뒤 중복을 제거하고 오름차순으로 정렬한 배열을 반환하세요.
 *
 * 메서드 시그니처:
 * public static int[] solution(int[] arr1, int[] arr2)
 *
 * 입출력 예시:
 * [1,3,5], [2,4,6]         → [1,2,3,4,5,6]
 * [1,2,3], [2,3,4]         → [1,2,3,4]       (중복 제거)
 * [5,5,5], [5,5,5]         → [5]
 * [3,1,2], [6,4,5]         → [1,2,3,4,5,6]   (정렬 안 된 채로 들어올 수 있음)
 * [], [1,2,3]              → [1,2,3]
 *
 * 힌트:
 * - HashSet 또는 TreeSet을 활용하면 중복 제거와 정렬을 한번에 할 수 있습니다.
 * - 또는 합친 후 Arrays.sort → 중복 제거 순서로도 풀 수 있습니다.
 */
public class Day3_260403_4 {

    public static int[] solution(int[] arr1, int[] arr2) {
        Set<Integer> set = new TreeSet<>();
        for(int a: arr1) {
            set.add(a);
        }
        for(int a: arr2) {
            set.add(a);
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (Arrays.equals(
            new int[]{1,2,3,4,5,6}, solution(new int[]{1,3,5}, new int[]{2,4,6})
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + (Arrays.equals(
            new int[]{1,2,3,4}, solution(new int[]{1,2,3}, new int[]{2,3,4})
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + (Arrays.equals(
            new int[]{5}, solution(new int[]{5,5,5}, new int[]{5,5,5})
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + (Arrays.equals(
            new int[]{1,2,3,4,5,6}, solution(new int[]{3,1,2}, new int[]{6,4,5})
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + (Arrays.equals(
            new int[]{1,2,3}, solution(new int[]{}, new int[]{1,2,3})
        ) ? "PASS" : "FAIL"));
    }
}
