package week1;

import java.util.*;

/*
 * [1주차 Day4] 문자열 압축 (String Compression)
 *
 * 문제:
 * 문자열 s가 주어졌을 때, 연속으로 반복되는 문자를 "문자+개수" 형태로 압축하세요.
 * 단, 1개인 문자는 개수를 생략합니다.
 *
 * 메서드 시그니처:
 * public static String solution(String s)
 *
 * 입출력 예시:
 * "aaabbc"    → "a3b2c"
 * "abcde"     → "abcde"       (압축할 게 없으면 그대로)
 * "aaa"       → "a3"
 * "aabbccdd"  → "a2b2c2d2"
 * ""          → ""
 *
 * 힌트:
 * - StringBuilder로 결과를 만들어가세요.
 * - 현재 문자와 다음 문자가 같은지 비교하며 카운트하세요.
 */
public class Day1_260401_4 {

    public static String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char prev = '1';
        // 다르면 문자를 더한다.
        for(int i = 0 ; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 다르면 문자를 더한다
            // 근데 카운트도 붙여줘야하는데? 카운트를 붙일때는 1이면 안 붙이는데?
            if(prev != ch) {
                if(count != 1) {
                    sb.append(count);
                    count = 1; // 카운트 초기화
                }
                sb.append(ch);
                prev = ch;
            } else {
                // 같으면 카운트를 올린다.
                count++;
                if(i == s.length() - 1) {
                    sb.append(count);
                }
            }
        }
        // 마지막 문자 처리!
        return sb.toString();
    }

    // Claude 풀이: 첫 문자를 초기값으로 잡고, 인덱스 1부터 비교
    public static String solution2(String s) {
        if (s.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1));
                if (count > 1) sb.append(count);
                count = 1;
            }
        }

        // 마지막 그룹 처리 (루프 밖에서 한번에)
        sb.append(s.charAt(s.length() - 1));
        if (count > 1) sb.append(count);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + ("a3b2c".equals(solution("aaabbc")) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + ("abcde".equals(solution("abcde")) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + ("a3".equals(solution("aaa")) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + ("a2b2c2d2".equals(solution("aabbccdd")) ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + ("".equals(solution("")) ? "PASS" : "FAIL"));
    }
}
