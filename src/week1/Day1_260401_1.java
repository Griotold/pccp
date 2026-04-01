package week1;

/*
 * [1주차 Day1] 문자열 뒤집기 판별 (Palindrome)
 *
 * 문제:
 * 문자열 s가 주어졌을 때, s를 뒤집은 문자열이 원래 문자열과 같으면 true,
 * 다르면 false를 반환하세요.
 * - 대소문자 구분 없이 비교합니다.
 * - 알파벳과 숫자만 비교 대상으로 합니다. (특수문자, 공백 무시)
 *
 * 메서드 시그니처:
 * public static boolean isPalindrome(String s)
 *
 * 입출력 예시:
 * "racecar"                         → true
 * "hello"                           → false
 * "A man, a plan, a canal: Panama"  → true
 * ""                                → true
 *
 * 힌트:
 * - Character.isLetterOrDigit() — 알파벳/숫자 판별
 * - Character.toLowerCase() — 소문자 변환
 * - 투 포인터(양쪽 끝에서 좁혀오기) 방식을 생각해보세요
 */
public class Day1_260401_1 {

    public static boolean isPalindrome(String s) {
        // 일단 소문자 변환이 필요해보인다.
        int len = s.length();
        StringBuilder lower = new StringBuilder();
        for(int i = 0 ; i < len; i++) {
            // 그리고 알파벳과/숫자만 남겨야겠지?
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
                lower.append(Character.toLowerCase(ch));
            }
        }
        // hello
        // 투포인터로 검증하면 되겠네
        int lt = 0, rt = lower.length() - 1;
        boolean flag = true;
        while(lt < rt) {
            if(lower.charAt(lt) != lower.charAt(rt)) {
                flag = false;
                break;
            }
            lt++;
            rt--;
        }
        return flag;
    }

    // 최적화: StringBuilder 없이 원본 문자열에서 바로 투 포인터
    // 공간 복잡도 O(n) → O(1)
    public static boolean isPalindromeOptimized(String s) {
        int lt = 0, rt = s.length() - 1;

        while (lt < rt) {
            // 왼쪽: 알파벳/숫자가 아니면 건너뛰기
            while (lt < rt && !Character.isLetterOrDigit(s.charAt(lt))) lt++;
            // 오른쪽: 알파벳/숫자가 아니면 건너뛰기
            while (lt < rt && !Character.isLetterOrDigit(s.charAt(rt))) rt--;

            if (Character.toLowerCase(s.charAt(lt)) != Character.toLowerCase(s.charAt(rt))) {
                return false;
            }
            lt++;
            rt--;
        }
        return true;
    }

    public static void main(String[] args) {
        // 테스트 케이스
        System.out.println("테스트 1: " + (isPalindrome("racecar") ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + (!isPalindrome("hello") ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + (isPalindrome("A man, a plan, a canal: Panama") ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + (isPalindrome("") ? "PASS" : "FAIL"));
        System.out.println("테스트 5: " + (!isPalindrome("0P") ? "PASS" : "FAIL"));
    }
}
