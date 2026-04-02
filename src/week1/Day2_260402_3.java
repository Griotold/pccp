package week1;

import java.util.*;

/*
 * [1주차 Day2] 가장 긴 팰린드롬 부분 문자열 (Longest Palindrome Substring)
 *
 * 문제:
 * 문자열 s가 주어졌을 때, s에 포함된 가장 긴 팰린드롬(앞뒤가 같은) 부분 문자열을 반환하세요.
 * 길이가 같은 게 여러 개면 아무거나 반환해도 됩니다.
 *
 * 메서드 시그니처:
 * public static String solution(String s)
 *
 * 입출력 예시:
 * "babad"   → "bab" 또는 "aba"
 * "cbbd"    → "bb"
 * "a"       → "a"
 * "abcba"   → "abcba"
 * ""        → ""
 *
 * 힌트:
 * - 팰린드롬은 중심에서 양쪽으로 확장하며 확인할 수 있습니다.
 * - 중심이 한 글자인 경우(홀수 길이)와 두 글자인 경우(짝수 길이) 둘 다 확인하세요.
 * - substring(start, end)를 활용하세요.
 */
public class Day2_260402_3 {

    public static String solution(String s) {
        if(s.isEmpty()) return "";
        String result = "";
        for(int i = 0; i < s.length(); i++) {
            int lt = i, rt = i;
            while(lt >= 0 && rt < s.length() && s.charAt(lt) == s.charAt(rt)) {
                lt--;
                rt++;
            }
            String str = s.substring(lt+1, rt);
            if(result.length() < str.length()) result = str;
        }

        // 짝수
        for(int i = 0 ; i < s.length(); i++) {
            int lt = i, rt = i + 1;
            while(lt >= 0 && rt <s.length() && s.charAt(lt) == s.charAt(rt)) {
                lt--;
                rt++;
            }
            String str = s.substring(lt+1, rt);
            if (result.length() < str.length()) result = str;
        }
        return result;
    }

    // Claude 풀이: 확장 로직을 메서드로 분리해서 홀수/짝수를 한 루프에서 처리
    public static String solution2(String s) {
        if (s.isEmpty()) return "";
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = expand(s, i, i);       // 홀수
            String even = expand(s, i, i + 1);  // 짝수
            if (odd.length() > result.length()) result = odd;
            if (even.length() > result.length()) result = even;
        }
        return result;
    }

    private static String expand(String s, int lt, int rt) {
        while (lt >= 0 && rt < s.length() && s.charAt(lt) == s.charAt(rt)) {
            lt--;
            rt++;
        }
        return s.substring(lt + 1, rt);
    }

    public static void main(String[] args) {
        String r1 = solution("babad");
        System.out.println("테스트 1: " + ("bab".equals(r1) || "aba".equals(r1) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + ("bb".equals(solution("cbbd")) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + ("a".equals(solution("a")) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + ("abcba".equals(solution("abcba")) ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + ("".equals(solution("")) ? "PASS" : "FAIL"));
    }
}
