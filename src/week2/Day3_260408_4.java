package week2;

import java.util.*;

/**
 * 문제: 다중 수식 계산기
 *
 * "숫자 연산자 숫자 연산자 숫자 ..." 형태의 문자열이 주어질 때,
 * 왼쪽부터 순서대로 계산한 결과를 반환하세요.
 * (연산자 우선순위 무시, 왼쪽에서 오른쪽으로 순차 계산)
 *
 * 예: "3 + 5 * 2" → (3 + 5) * 2 = 16 (우선순위 무시)
 *
 * [지원 연산자]
 * - +, -, *, /
 *
 * [조건]
 * - 0으로 나누기 시도 시 → Integer.MIN_VALUE 반환
 * - 나눗셈은 정수 나눗셈 (소수점 버림)
 * - 숫자는 최소 2개 이상, 연산자는 숫자보다 1개 적음
 * - 입력은 항상 "숫자 연산자 숫자 ..." 형태가 보장됨
 *
 * [힌트]
 * - split(" ")으로 토큰 분리 후, 짝수 인덱스는 숫자, 홀수 인덱스는 연산자
 * - 첫 번째 숫자를 초기값으로 잡고, 반복문으로 연산자+숫자 쌍을 순차 처리
 * - Day3_260408_3에서 만든 연산 함수를 재활용할 수 있음
 */
public class Day3_260408_4 {

    static int plus(int a, int b) {
        return a + b;
    }
    static int minus(int a, int b) {
        return a - b;
    }
    static int multiply(int a, int b) {
        return a * b;
    }
    static int divide(int a, int b) {
        if(b == 0) return Integer.MIN_VALUE;
        return a / b;
    }

    public static int solution(String expression) {
        String[] tokens = expression.split(" ");
        int result = Integer.parseInt(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int b = Integer.parseInt(tokens[i + 1]);

            if (operator.equals("+")) result = plus(result, b);
            else if (operator.equals("-")) result = minus(result, b);
            else if (operator.equals("*")) result = multiply(result, b);
            else {
                result = divide(result, b);
                if (result == Integer.MIN_VALUE) return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] tests = {
            {"3 + 5 * 2", "16"},                    // (3+5)*2
            {"10 - 3 + 2", "9"},                    // (10-3)+2
            {"100 / 3 * 2 + 1", "67"},              // ((100/3)*2)+1 = (33*2)+1
            {"5 + 0 * 3", "15"},                    // (5+0)*3
            {"10 / 0 + 5", String.valueOf(Integer.MIN_VALUE)}, // 0으로 나누기
            {"1 + 2 + 3 + 4 + 5", "15"},
            {"50 - 10 - 10 - 10 - 10", "10"},
            {"-3 + 10 * 2", "14"},                  // (-3+10)*2
        };

        for (int i = 0; i < tests.length; i++) {
            int result = solution(tests[i][0]);
            int expected = Integer.parseInt(tests[i][1]);
            String status = result == expected ? "PASS" : "FAIL";
            System.out.printf("테스트 %d: %s (입력: \"%s\" → 결과: %d, 기대: %d)%n",
                    i + 1, status, tests[i][0], result, expected);
        }
    }
}
