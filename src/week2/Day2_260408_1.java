package week2;

import java.util.*;

/**
 * 문제: FizzBuzz 변형 - 배수 판별기
 *
 * 정수 n이 주어질 때, 1부터 n까지의 수를 순회하며
 * 아래 규칙에 따라 문자열 배열을 반환하세요.
 *
 * [규칙]
 * - 3과 5의 공배수 → "FizzBuzz"
 * - 3의 배수 → "Fizz"
 * - 5의 배수 → "Buzz"
 * - 그 외 → 해당 숫자를 문자열로 변환
 *
 * [힌트]
 * - 나머지 연산자(%)로 배수 판별
 * - 공배수 조건을 먼저 검사해야 함 (순서 중요!)
 * - String.valueOf() 또는 Integer.toString()으로 숫자→문자열 변환
 */
public class Day2_260408_1 {

    public static String[] solution(int n) {
        // 여기에 풀이를 작성하세요
        return new String[]{};
    }

    public static void main(String[] args) {
        // 테스트 1: n=15
        String[] result1 = solution(15);
        String[] expected1 = {"1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"};
        System.out.printf("테스트 1: %s (n=15)%n", Arrays.equals(result1, expected1) ? "PASS" : "FAIL");

        // 테스트 2: n=1
        String[] result2 = solution(1);
        String[] expected2 = {"1"};
        System.out.printf("테스트 2: %s (n=1)%n", Arrays.equals(result2, expected2) ? "PASS" : "FAIL");

        // 테스트 3: n=5
        String[] result3 = solution(5);
        String[] expected3 = {"1","2","Fizz","4","Buzz"};
        System.out.printf("테스트 3: %s (n=5)%n", Arrays.equals(result3, expected3) ? "PASS" : "FAIL");

        // 테스트 4: n=30 → 마지막이 FizzBuzz인지 확인
        String[] result4 = solution(30);
        System.out.printf("테스트 4: %s (n=30, 마지막=FizzBuzz)%n",
                result4.length == 30 && result4[29].equals("FizzBuzz") ? "PASS" : "FAIL");
    }
}
