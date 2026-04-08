package week2;

import java.util.*;

/**
 * 문제: 주차 복습 - 수식 계산기 (문자열 파싱 + 조건문 + 함수)
 *
 * "숫자 연산자 숫자" 형태의 문자열이 주어질 때, 계산 결과를 반환하세요.
 * (공백으로 구분됨)
 *
 * [지원 연산자]
 * - +, -, *, /
 *
 * [조건]
 * - 0으로 나누기 시도 시 → Integer.MIN_VALUE 반환
 * - 나눗셈은 정수 나눗셈 (소수점 버림)
 * - 입력은 항상 "숫자 연산자 숫자" 형태가 보장됨
 *
 * [힌트]
 * - String.split(" ")으로 토큰 분리
 * - Integer.parseInt()로 문자열 → 정수 변환
 * - switch문 또는 if-else로 연산자 분기
 * - 각 연산을 별도 함수로 분리하면 가독성이 좋아짐 (선택)
 */
public class Day3_260408_3 {

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
        int result = 0;
        String[] split = expression.split(" ");
        int a = Integer.parseInt(split[0]);
        String operator = split[1];
        int b = Integer.parseInt(split[2]);
        if(operator.equals("+")) result = plus(a, b);
        else if(operator.equals("-")) result = minus(a, b);
        else if(operator.equals("*")) result = multiply(a, b);
        else result = divide(a, b);
        return result;
    }

    public static void main(String[] args) {
        String[][] tests = {
            {"3 + 5", "8"},
            {"10 - 7", "3"},
            {"4 * 6", "24"},
            {"15 / 4", "3"},
            {"10 / 0", String.valueOf(Integer.MIN_VALUE)},
            {"0 + 0", "0"},
            {"-3 + 7", "4"},
            {"100 / 3", "33"},
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
