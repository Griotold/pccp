package week2;

import java.util.*;

/**
 * 문제: [PCCE 기출문제] 9번 / 지폐 접기
 *
 * 지갑에 지폐를 넣기 위해 지폐를 최소 몇 번 접어야 하는지 구합니다.
 *
 * [접기 규칙]
 * - 항상 "긴 쪽"을 반으로 접음
 * - 홀수를 반으로 접으면 소수점 이하는 버림 (정수 나눗셈)
 * - 접은 지폐를 그대로 또는 90도 돌려서 지갑에 들어가면 그만 접음
 *
 * [지갑에 들어가는 조건]
 * 지폐의 짧은 변이 지갑의 짧은 변보다 작거나 같고,
 * 지폐의 긴 변이 지갑의 긴 변보다 작거나 같으면 들어감.
 * (90도 회전은 "짧은 변/긴 변" 비교로 자동 처리됨)
 *
 * [제한 사항]
 * - wallet.length = bill.length = 2
 * - 10 ≤ wallet[0], wallet[1] ≤ 100
 * - 10 ≤ bill[0], bill[1] ≤ 2,000
 *
 * [예시로 이해하기]
 * 입력: wallet = [50, 50], bill = [100, 241]  →  기대: 4
 *
 *  접은 횟수 | 지폐 크기       | 접는 동작       | 설명
 *  ----------|-----------------|-----------------|---------------------------
 *      0     | [100, 241]      | 241 > 50 안 들어감 | 초기 상태
 *      1     | [100, 120]      | 긴 쪽 241 → 120 | 241 / 2 = 120 (버림)
 *      2     | [100, 60]       | 긴 쪽 120 → 60  | 여전히 100 > 50
 *      3     | [50, 60]        | 긴 쪽 100 → 50  | 50 ≤ 50 이지만 60 > 50
 *      4     | [50, 30]        | 긴 쪽 60 → 30   | 30 ≤ 50 → 들어감! 종료
 *
 * 결과: 4번
 *
 * 포인트:
 * - "90도 회전"은 결국 "짧은 변 vs 짧은 변, 긴 변 vs 긴 변" 비교로 자동 처리됨
 *   → 별도의 회전 로직 필요 없음
 * - 긴 쪽을 접는다 = bill[0] vs bill[1] 비교해서 큰 쪽을 /= 2
 *
 * [힌트]
 * - 반복문 while 로 "지갑에 안 들어가는 동안" 접으면 됨
 * - 매 반복마다:
 *     1) bill 의 짧은/긴 변과 wallet 의 짧은/긴 변 비교
 *     2) 둘 다 지갑보다 작거나 같으면 break
 *     3) bill[0], bill[1] 중 큰 쪽을 /= 2
 *     4) answer++
 * - Math.min, Math.max 쓰면 짧은/긴 변 뽑기 간단
 * - 정수 나눗셈 /2 가 곧 "소수점 이하 버림"
 * - 최대 접는 횟수는 로그 스케일로 매우 작음 (2000 → 1 까지 약 11회)
 *
 * @param wallet 지갑 크기 [가로, 세로]
 * @param bill 지폐 크기 [가로, 세로]
 * @return 지폐를 접어야 하는 최소 횟수
 */
public class Day4_260409_6_past {

    public static int solution(int[] wallet, int[] bill) {
        int walletMin = Math.min(wallet[0], wallet[1]);
        int walletMax = Math.max(wallet[0], wallet[1]);
        int billMin = Math.min(bill[0], bill[1]);
        int billMax = Math.max(bill[0], bill[1]);

        int count = 0;

        while(billMin > walletMin || billMax > walletMax) {
            billMax /= 2;
            if (billMin > billMax) {
                int tmp = billMin;
                billMin = billMax;
                billMax = tmp;
            }
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        // 테스트 1: 예제 #1 — 한 번 접고 90도 돌려서 넣기
        int r1 = solution(new int[]{30, 15}, new int[]{26, 17});
        System.out.printf("테스트 1: %s (결과: %d, 기대: 1)%n",
                r1 == 1 ? "PASS" : "FAIL", r1);

        // 테스트 2: 예제 #2 — 큰 지폐 4번 접기
        int r2 = solution(new int[]{50, 50}, new int[]{100, 241});
        System.out.printf("테스트 2: %s (결과: %d, 기대: 4)%n",
                r2 == 4 ? "PASS" : "FAIL", r2);

        // 테스트 3: 엣지 — 이미 들어감 (접을 필요 없음)
        int r3 = solution(new int[]{100, 100}, new int[]{50, 50});
        System.out.printf("테스트 3: %s (결과: %d, 기대: 0)%n",
                r3 == 0 ? "PASS" : "FAIL", r3);

        // 테스트 4: 엣지 — 90도 돌리면 딱 맞음 (접지 않음)
        // 지갑 [30, 15], 지폐 [15, 30] → 회전하면 바로 들어감
        int r4 = solution(new int[]{30, 15}, new int[]{15, 30});
        System.out.printf("테스트 4: %s (결과: %d, 기대: 0)%n",
                r4 == 0 ? "PASS" : "FAIL", r4);

        // 테스트 5: 엣지 — 같은 크기 (경계, 들어감)
        int r5 = solution(new int[]{20, 20}, new int[]{20, 20});
        System.out.printf("테스트 5: %s (결과: %d, 기대: 0)%n",
                r5 == 0 ? "PASS" : "FAIL", r5);

        // 테스트 6: 엣지 — 홀수 지폐 접기 (버림 확인)
        // 지갑 [10, 10], 지폐 [10, 25]
        // 25/2=12 → [10,12], 12>10 → 12/2=6 → [10,6] 들어감. 2번
        int r6 = solution(new int[]{10, 10}, new int[]{10, 25});
        System.out.printf("테스트 6: %s (결과: %d, 기대: 2)%n",
                r6 == 2 ? "PASS" : "FAIL", r6);

        // 테스트 7: 엣지 — 최대 크기 지폐에서 많이 접기
        // 지갑 [10, 10], 지폐 [2000, 2000]
        // 매번 긴 쪽을 절반으로: 2000→1000→500→250→125→62→31→15→7
        // 실제로 한 변만 계속 접는 게 아니라 긴 쪽을 번갈아 접음
        int r7 = solution(new int[]{10, 10}, new int[]{2000, 2000});
        System.out.printf("테스트 7: %s (결과: %d, 기대: 16)%n",
                r7 == 16 ? "PASS" : "FAIL", r7);

        // 테스트 8: 엣지 — 정확히 한 변만 줄이면 되는 경우
        // 지갑 [50, 100], 지폐 [200, 50]
        // 200>100 → 100, [100,50]. 100≤100, 50≤50. 1번
        int r8 = solution(new int[]{50, 100}, new int[]{200, 50});
        System.out.printf("테스트 8: %s (결과: %d, 기대: 1)%n",
                r8 == 1 ? "PASS" : "FAIL", r8);
    }
}
