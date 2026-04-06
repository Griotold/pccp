package week2;

import java.util.*;

/**
 * 문제: 소수 판별 & 소수 목록
 *
 * 정수 n이 주어질 때, 2부터 n까지의 소수를 오름차순 배열로 반환하세요.
 *
 * [조건]
 * - 2 <= n <= 1000
 * - 소수: 1과 자기 자신만으로 나누어지는 1보다 큰 자연수
 *
 * [힌트]
 * - 소수 판별 로직을 별도 함수(isPrime)로 분리해보세요
 * - 판별 시 2부터 Math.sqrt(n)까지만 검사하면 효율적
 * - ArrayList에 모아서 마지막에 배열로 변환: list.stream().mapToInt(i->i).toArray()
 */
public class Day4_260410_1 {

    public static int[] solution(int n) {
        // 여기에 풀이를 작성하세요
        return new int[]{};
    }

    public static void main(String[] args) {
        // 테스트 1: n=10
        int[] result1 = solution(10);
        int[] expected1 = {2, 3, 5, 7};
        System.out.printf("테스트 1: %s (n=10)%n",
                Arrays.equals(result1, expected1) ? "PASS" : "FAIL");

        // 테스트 2: n=2
        int[] result2 = solution(2);
        int[] expected2 = {2};
        System.out.printf("테스트 2: %s (n=2)%n",
                Arrays.equals(result2, expected2) ? "PASS" : "FAIL");

        // 테스트 3: n=20
        int[] result3 = solution(20);
        int[] expected3 = {2, 3, 5, 7, 11, 13, 17, 19};
        System.out.printf("테스트 3: %s (n=20)%n",
                Arrays.equals(result3, expected3) ? "PASS" : "FAIL");

        // 테스트 4: n=1 → 소수 없음
        int[] result4 = solution(1);
        System.out.printf("테스트 4: %s (n=1, 빈 배열)%n",
                result4.length == 0 ? "PASS" : "FAIL");

        // 테스트 5: n=50 → 개수 확인 (15개)
        int[] result5 = solution(50);
        System.out.printf("테스트 5: %s (n=50, 소수 15개)%n",
                result5.length == 15 ? "PASS" : "FAIL");
    }
}
