package week2;

import java.util.*;

/**
 * 문제: 비밀번호 강도 판별기
 *
 * 비밀번호 문자열이 주어질 때, 아래 조건을 만족하는 개수에 따라 강도를 반환하세요.
 *
 * [조건]
 * 1. 길이가 8 이상
 * 2. 대문자(A-Z)를 1개 이상 포함
 * 3. 소문자(a-z)를 1개 이상 포함
 * 4. 숫자(0-9)를 1개 이상 포함
 * 5. 특수문자(!@#$%^&*)를 1개 이상 포함
 *
 * [반환값]
 * - 5개 모두 충족 → "STRONG"
 * - 3~4개 충족   → "MEDIUM"
 * - 0~2개 충족   → "WEAK"
 *
 * [힌트]
 * - Character.isUpperCase(), Character.isLowerCase(), Character.isDigit() 활용
 * - 문자열을 한 글자씩 순회하며 각 조건의 충족 여부를 체크
 * - boolean 플래그 또는 int 카운터로 조건 수를 세는 방식
 */
public class Day1_260407_1 {

    public static String solution(String password) {
        int count = 0;
        boolean upper = false, lower = false, digit = false, special = false;
        String specials = "!@#$%^&*";
        if(password.length() >= 8) count++;
        for(char ch : password.toCharArray()) {
            if(Character.isUpperCase(ch) && !upper) {
                upper = true;
                count++;
            }
            if (Character.isLowerCase(ch) && !lower) {
                lower = true;
                count++;
            }
            if (Character.isDigit(ch) && !digit) {
                digit = true;
                count++;
            }
            if (specials.indexOf(ch) >= 0 && !special) {
                special = true;
                count++;
            }
         }

        if(count == 5) return "STRONG";
        else if (count >= 3 && count <= 4) return "MEDIUM";
        else if (count >= 0 && count <= 2) return "WEAK";
        return "";
    }

    public static void main(String[] args) {
        // 테스트 케이스
        String[][] tests = {
            {"Abcd1234!@", "STRONG"},    // 5개 모두 충족
            {"Abcd1234", "MEDIUM"},      // 4개 충족 (특수문자 없음)
            {"abcd1234", "MEDIUM"},      // 3개 충족 (대문자, 특수문자 없음)
            {"abcd", "WEAK"},            // 2개 충족 (길이 미달, 대문자·숫자·특수문자 없음)
            {"A!", "WEAK"},              // 2개 충족 (길이 미달, 소문자·숫자 없음) -- 대문자, 특수문자만 충족
            {"", "WEAK"},                // 0개 충족
            {"P@ssw0rd", "STRONG"},      // 5개 모두 충족
        };

        for (int i = 0; i < tests.length; i++) {
            String result = solution(tests[i][0]);
            String expected = tests[i][1];
            String status = result.equals(expected) ? "PASS" : "FAIL";
            System.out.printf("테스트 %d: %s (입력: \"%s\" → 결과: %s, 기대: %s)%n",
                    i + 1, status, tests[i][0], result, expected);
        }
    }
}
