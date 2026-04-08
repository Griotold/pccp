package week2;

import java.util.*;

/**
 * 문제: 콜라츠 추측 (Collatz Conjecture)
 *
 * 양의 정수 n이 주어질 때, 아래 규칙을 반복 적용하여
 * n이 1이 되기까지의 과정을 배열로 반환하세요. (시작 값 n 포함, 1 포함)
 *
 * [규칙]
 * - n이 짝수 → n / 2
 * - n이 홀수 → n * 3 + 1
 * - n이 1이면 종료
 *
 * [조건]
 * - 1 <= n <= 10000
 * - n이 1이면 [1]을 반환
 *
 * [힌트]
 * - while 반복문으로 n이 1이 될 때까지 반복
 * - ArrayList에 각 단계의 값을 저장
 * - 짝수/홀수 판별: n % 2 == 0
 */
public class Day3_260408_1 {

    public static int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(n);
        while(n != 1) {
            if( n % 2 ==0 ) n /= 2;
            else n = n * 3 + 1;
            list.add(n);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        // 테스트 1: n=6 → 6, 3, 10, 5, 16, 8, 4, 2, 1
        int[] result1 = solution(6);
        int[] expected1 = {6, 3, 10, 5, 16, 8, 4, 2, 1};
        System.out.printf("테스트 1: %s (n=6)%n",
                Arrays.equals(result1, expected1) ? "PASS" : "FAIL");

        // 테스트 2: n=1
        int[] result2 = solution(1);
        int[] expected2 = {1};
        System.out.printf("테스트 2: %s (n=1)%n",
                Arrays.equals(result2, expected2) ? "PASS" : "FAIL");

        // 테스트 3: n=7 → 7,22,11,34,17,52,26,13,40,20,10,5,16,8,4,2,1
        int[] result3 = solution(7);
        int[] expected3 = {7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1};
        System.out.printf("테스트 3: %s (n=7)%n",
                Arrays.equals(result3, expected3) ? "PASS" : "FAIL");

        // 테스트 4: n=2 → 2, 1
        int[] result4 = solution(2);
        int[] expected4 = {2, 1};
        System.out.printf("테스트 4: %s (n=2)%n",
                Arrays.equals(result4, expected4) ? "PASS" : "FAIL");
    }
}
