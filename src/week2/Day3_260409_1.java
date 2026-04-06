package week2;

import java.util.*;

/**
 * 문제: 숫자 뒤집기 (음수 포함)
 *
 * 정수 n이 주어질 때, 각 자릿수를 뒤집은 결과를 정수로 반환하세요.
 *
 * [규칙]
 * - 양수: 123 → 321
 * - 음수: -456 → -654 (부호는 유지)
 * - 끝자리 0: 1200 → 21 (앞에 붙는 0은 제거)
 *
 * [힌트]
 * - 반복문에서 % 10으로 마지막 자릿수 추출, / 10으로 자릿수 제거
 * - Math.abs()로 절대값 처리 후 부호를 나중에 복원
 * - 문자열 변환 없이 수학 연산만으로 풀어보세요
 */
public class Day3_260409_1 {

    public static int solution(int n) {
        // 여기에 풀이를 작성하세요
        return 0;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {123, 321},
            {-456, -654},
            {1200, 21},
            {0, 0},
            {7, 7},
            {-100, -1},
            {1000000, 1},
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
