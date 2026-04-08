package week2;

import java.util.*;

/**
 * 문제: 자릿수 합 구하기 (재귀 함수)
 *
 * 음이 아닌 정수 n이 주어질 때, 각 자릿수의 합이 한 자릿수가 될 때까지
 * 반복하여 최종 한 자릿수를 반환하세요.
 *
 * 예: 9875 → 9+8+7+5 = 29 → 2+9 = 11 → 1+1 = 2 → 반환: 2
 *
 * [조건]
 * - 0 <= n <= 2,000,000,000
 *
 * [힌트]
 * - 자릿수 합을 구하는 함수를 별도로 만들고, 재귀 호출 또는 while로 반복
 * - 자릿수 합: n % 10으로 마지막 자리 추출, n / 10으로 제거
 * - 한 자릿수 = 0~9 범위
 */
public class Day3_260408_2 {

    static int recur(int num) {
        if(num / 10 == 0) return num;
        int sum = 0;
        while(num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return recur(sum);
    }

    public static int solution(int n) {
        return recur(n);
    }

    /**
     * while 반복문 방식
     * - 재귀 없이 바깥 while로 한 자릿수가 될 때까지 반복
     */
    public static int solution2(int n) {
        while(n / 10 != 0) {
            int sum = 0;
            while(n != 0) {
                sum += n % 10;
                n /= 10;
            }
                n = sum;
        }
        return n;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {9875, 2},      // 29 → 11 → 2
            {0, 0},
            {5, 5},
            {38, 2},        // 3+8=11 → 1+1=2
            {199, 1},       // 19 → 10 → 1
            {999999999, 9}, // 81 → 9
        };

        for (int i = 0; i < tests.length; i++) {
            int result = solution(tests[i][0]);
            int expected = tests[i][1];
            String status = result == expected ? "PASS" : "FAIL";
            System.out.printf("테스트 %d: %s (입력: %d → 결과: %d, 기대: %d)%n",
                    i + 1, status, tests[i][0], result, expected);
        }
    }
}
