package week1;

import java.util.*;

/*
 * [1주차 Day3] 시저 암호 (Caesar Cipher)
 *
 * 문제:
 * 문자열 s와 정수 n이 주어졌을 때, 각 알파벳을 n만큼 밀어서 암호화한 문자열을 반환하세요.
 * - 알파벳 대문자는 대문자로, 소문자는 소문자로 유지합니다.
 * - z 다음은 a로, Z 다음은 A로 순환합니다.
 * - 알파벳이 아닌 문자(공백, 숫자 등)는 그대로 둡니다.
 *
 * 메서드 시그니처:
 * public static String solution(String s, int n)
 *
 * 입출력 예시:
 * "abc", 3        → "def"
 * "xyz", 2        → "zab"
 * "AB", 1         → "BC"
 * "a B z", 4      → "e F d"       (공백은 그대로, 대소문자 유지)
 * "Hello World", 7 → "Olssv Dvysk"
 *
 * 힌트:
 * - 'a'~'z'는 0~25로 변환해서 생각하면 편합니다. (ch - 'a')
 * - n만큼 더한 후 26으로 나머지 연산하면 순환 처리 가능 ((ch - 'a' + n) % 26 + 'a')
 * - StringBuilder로 결과를 조립하세요.
 */
public class Day3_260403_3 {

    public static String solution(String s, int n) {

        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()) {
            // 대문자 일때
            if(Character.isUpperCase(ch)) {
                char upper = (char) ((ch - 'A' + n) % 26 + 'A');
                sb.append(upper);
            }
            // 소문자 일때
            else if (Character.isLowerCase(ch)) {
                char lower = (char) ((ch - 'a' + n) % 26 + 'a');
                sb.append(lower);
            } else {
                // 공백, 숫자일때
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + ("def".equals(solution("abc", 3)) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + ("zab".equals(solution("xyz", 2)) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + ("BC".equals(solution("AB", 1)) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + ("e F d".equals(solution("a B z", 4)) ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + ("Olssv Dvysk".equals(solution("Hello World", 7)) ? "PASS" : "FAIL"));
    }
}
