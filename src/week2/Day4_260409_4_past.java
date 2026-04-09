package week2;

import java.util.*;

/**
 * 문제: [PCCP 모의고사 #1] 1번 / 외톨이 알파벳
 *
 * 알파벳 소문자로만 이루어진 문자열에서, 어떤 알파벳이
 * "2회 이상 나타나면서 2개 이상의 부분으로 나뉘어 있는" 경우를
 * "외톨이 알파벳"이라고 정의합니다.
 *
 * 외톨이 알파벳들을 알파벳순으로 이어 붙인 문자열을 반환하세요.
 * 외톨이 알파벳이 없으면 "N"을 반환합니다.
 *
 * [정의 풀이]
 * - "부분(덩어리)" = 같은 문자가 연속으로 이어진 하나의 구간
 *     예: "aaabba"  →  a 덩어리 2개, b 덩어리 1개
 * - 어떤 문자의 덩어리 개수가 2개 이상이면 외톨이
 *
 * [제한 사항]
 * - 1 ≤ input_string.length ≤ 2,600
 * - input_string 은 알파벳 소문자로만 구성
 *
 * [예시로 이해하기]
 * 입력: "edeaaabbccd"
 *
 *  문자 | 등장 위치 그룹             | 덩어리 수 | 외톨이?
 *  -----|----------------------------|-----------|---------
 *    a  | [aaa]                      |     1     |   ❌
 *    b  | [bb]                       |     1     |   ❌
 *    c  | [cc]                       |     1     |   ❌
 *    d  | [d] ... [d]                |     2     |   ✅
 *    e  | [e] ... [e]                |     2     |   ✅
 *
 * 외톨이: d, e → 알파벳순 정렬 → "de"
 *
 * 입력: "eeddee"
 *
 *  문자 | 등장 위치 그룹             | 덩어리 수 | 외톨이?
 *  -----|----------------------------|-----------|---------
 *    d  | [dd]                       |     1     |   ❌
 *    e  | [ee] ... [ee]              |     2     |   ✅
 *
 * 외톨이: e → "e"
 *
 * 포인트:
 * - 총 등장 횟수가 아니라 "덩어리 개수"가 기준. "aaaa" 는 덩어리 1개라 외톨이 아님.
 * - "aba" 는 a 덩어리 2개 → a 외톨이. b 는 덩어리 1개 → 아님.
 *
 * [힌트]
 * - 문자열을 왼쪽에서 오른쪽으로 한 번만 훑으면서 "덩어리의 시작 지점"을 찾으면 됨.
 *     int[] groupCount = new int[26];
 *     for (int i = 0; i < s.length(); i++) {
 *         if (i == 0 || s.charAt(i) != s.charAt(i - 1)) {
 *             groupCount[s.charAt(i) - 'a']++;
 *         }
 *     }
 *   → 매 문자마다 이전 문자와 다르면 "새 덩어리 시작"으로 카운트 +1.
 *
 * - 다 센 뒤 a~z 를 순회하며 groupCount[c] >= 2 인 문자를 StringBuilder 에 append.
 * - 결과가 비어있으면 "N" 반환.
 * - 알파벳순 출력은 a~z 순회하면 자연스럽게 정렬 상태로 나옴 (별도 정렬 불필요).
 * - 문자 인덱싱: char - 'a' 하면 0~25 인덱스가 나옴.
 *
 * @param input_string 입력 문자열
 * @return 외톨이 알파벳을 이어붙인 문자열 또는 "N"
 */
public class Day4_260409_4_past {

    public static String solution(String input_string) {
        int[] groupCount = new int[26];
        for (int i = 0 ; i < input_string.length(); i++) {
            if(i == 0 || input_string.charAt(i) != input_string.charAt(i - 1)) {
                groupCount[input_string.charAt(i) - 'a']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 26; i++) {
            if(groupCount[i] >= 2) sb.append((char) (i + 'a'));
        }
        return sb.isEmpty() ? "N" : sb.toString();
    }

    public static void main(String[] args) {
        // 테스트 1: 예제 #1 — d, e 가 각각 두 덩어리
        String r1 = solution("edeaaabbccd");
        System.out.printf("테스트 1: %s (결과: %s, 기대: de)%n",
                r1.equals("de") ? "PASS" : "FAIL", r1);

        // 테스트 2: 예제 #2 — e 만 두 덩어리
        String r2 = solution("eeddee");
        System.out.printf("테스트 2: %s (결과: %s, 기대: e)%n",
                r2.equals("e") ? "PASS" : "FAIL", r2);

        // 테스트 3: 예제 #3 — 모두 1회 등장, 외톨이 없음
        String r3 = solution("string");
        System.out.printf("테스트 3: %s (결과: %s, 기대: N)%n",
                r3.equals("N") ? "PASS" : "FAIL", r3);

        // 테스트 4: 예제 #4 — z 세 덩어리, b 두 덩어리
        String r4 = solution("zbzbz");
        System.out.printf("테스트 4: %s (결과: %s, 기대: bz)%n",
                r4.equals("bz") ? "PASS" : "FAIL", r4);

        // 테스트 5: 엣지 — 길이 1
        String r5 = solution("a");
        System.out.printf("테스트 5: %s (결과: %s, 기대: N)%n",
                r5.equals("N") ? "PASS" : "FAIL", r5);

        // 테스트 6: 엣지 — 전부 같은 문자 (덩어리 1개, 외톨이 아님)
        String r6 = solution("aaaaaa");
        System.out.printf("테스트 6: %s (결과: %s, 기대: N)%n",
                r6.equals("N") ? "PASS" : "FAIL", r6);

        // 테스트 7: 엣지 — 알파벳 전체가 두 덩어리로 교차
        // "abababab" → a 4덩어리, b 4덩어리 → 둘 다 외톨이
        String r7 = solution("abababab");
        System.out.printf("테스트 7: %s (결과: %s, 기대: ab)%n",
                r7.equals("ab") ? "PASS" : "FAIL", r7);

        // 테스트 8: 엣지 — 맨 끝과 맨 앞에만 같은 문자
        // "xabcx" → x 2덩어리, 나머지 1덩어리
        String r8 = solution("xabcx");
        System.out.printf("테스트 8: %s (결과: %s, 기대: x)%n",
                r8.equals("x") ? "PASS" : "FAIL", r8);

        // 테스트 9: 엣지 — 대량 입력에서도 정상 동작 (길이 근처 확인)
        // "az" 2600번 반복 → a 1300덩어리, z 1300덩어리 → "az"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1300; i++) sb.append("az");
        String r9 = solution(sb.toString());
        System.out.printf("테스트 9: %s (결과: %s, 기대: az)%n",
                r9.equals("az") ? "PASS" : "FAIL", r9);
    }
}
