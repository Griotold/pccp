package week2;

import java.util.*;

/**
 * 문제: [PCCP 모의고사 #2] 1번 / 실습용 로봇
 *
 * 2차원 좌표 평면 위의 로봇이 4가지 명령에 따라 이동합니다.
 * - 'R': 오른쪽으로 90도 회전
 * - 'L': 왼쪽으로 90도 회전
 * - 'G': 한 칸 전진
 * - 'B': 한 칸 후진
 *
 * 로봇은 (0, 0) 에서 시작하며 초기 방향은 +y 축(위쪽)입니다.
 * 명령을 모두 수행한 뒤 최종 좌표를 [x, y] 배열로 반환하세요.
 *
 * [주의 — 좌표계]
 * 이 문제는 board[y][x] 인덱싱이 아니라 "수학 좌표계"를 씁니다.
 * - (x, y) 순서로 표기
 * - +y 축이 "위쪽" (화면상 y 가 위로 증가)
 * - 반환값도 [x, y] 순서
 *
 * 그래서 로봇 청소기 시뮬레이션과 달리, 여기서는 "상(+y)"이 y 증가 방향입니다.
 *
 * [제한 사항]
 * - 1 ≤ command.length ≤ 1,000,000
 * - command 는 'R', 'L', 'G', 'B' 로만 구성
 *
 * [예시로 이해하기]
 * 입력: "GRGRGRB"  →  기대: [2, 0]
 *
 *  단계 | 명령 | 방향         | 좌표 (x, y)
 *  -----|------|--------------|------------
 *   시작| -    | +y (위)      | (0, 0)
 *    1  | G    | +y           | (0, 1)     ← 위로 한 칸
 *    2  | R    | +x (오른)    | (0, 1)     ← 회전만
 *    3  | G    | +x           | (1, 1)     ← 오른쪽 한 칸
 *    4  | R    | -y (아래)    | (1, 1)
 *    5  | G    | -y           | (1, 0)     ← 아래 한 칸
 *    6  | R    | -x (왼)      | (1, 0)
 *    7  | B    | -x           | (2, 0)     ← 후진: -x 방향의 반대, 즉 +x
 *
 * 최종 위치: [2, 0]
 *
 * 포인트:
 * - 'B'(후진)는 "현재 방향의 반대로 이동"이라, 굳이 방향을 뒤집을 필요 없이
 *   좌표에 -dx, -dy 를 더하면 끝.
 * - R/L 은 좌표를 건드리지 않음 (방향만 바뀜).
 *
 * [힌트]
 * - 방향 배열 (수학 좌표계, (x, y) 순서):
 *     상(+y) → 우(+x) → 하(-y) → 좌(-x)  순서로 정의
 *     dx = { 0, 1, 0, -1};
 *     dy = { 1, 0, -1, 0};
 *   → 이 배열 기준 상(+y)=0, 우(+x)=1, 하(-y)=2, 좌(-x)=3
 *   → 시작 방향 인덱스 = 0
 *
 * - R (시계 방향 회전): dir = (dir + 1) % 4
 * - L (반시계 방향):    dir = (dir + 3) % 4
 * - G (전진):           x += dx[dir]; y += dy[dir];
 * - B (후진):           x -= dx[dir]; y -= dy[dir];
 *
 * - command.length 가 최대 100만이라 O(n) 루프로 돌면 충분.
 * - char 배열로 꺼내놓고 (toCharArray) switch 로 처리하면 빠르고 가독성도 좋음.
 * - 이 문제는 격자 경계가 없어서 좌표 범위 체크 불필요.
 *
 * @param command 명령어 문자열
 * @return 최종 좌표 [x, y]
 */
public class Day4_260409_5_past {

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    // 입력: "GRGRGRB"  →  기대: [2, 0]
    public static int[] solution(String command) {
        int idx = 0;
        int x = 0, y = 0;
        for(char ch: command.toCharArray()) {
            if (ch == 'G') {
                x += dx[idx];
                y += dy[idx];
            } else if (ch == 'B') {
                x -= dx[idx];
                y -= dy[idx];
            } else if (ch == 'R') {
                idx = (idx + 1) % 4;
            } else {
                idx = (idx + 3 ) % 4;
            }
        }

        // Java 14의 switch 표현식
//        for (char ch : command.toCharArray()) {
//            switch (ch) {
//                case 'G' -> { x += dx[idx]; y += dy[idx]; }
//                case 'B' -> { x -= dx[idx]; y -= dy[idx]; }
//                case 'R' -> idx = (idx + 1) % 4;
//                case 'L' -> idx = (idx + 3) % 4;
//            }
//        }

        return new int[]{x, y};
    }

    public static void main(String[] args) {
        // 테스트 1: 예제 #1
        int[] r1 = solution("GRGLGRG");
        System.out.printf("테스트 1: %s (결과: [%d, %d], 기대: [2, 2])%n",
                (r1[0] == 2 && r1[1] == 2) ? "PASS" : "FAIL", r1[0], r1[1]);

        // 테스트 2: 예제 #2
        int[] r2 = solution("GRGRGRB");
        System.out.printf("테스트 2: %s (결과: [%d, %d], 기대: [2, 0])%n",
                (r2[0] == 2 && r2[1] == 0) ? "PASS" : "FAIL", r2[0], r2[1]);

        // 테스트 3: 엣지 — 한 번 전진만
        int[] r3 = solution("G");
        System.out.printf("테스트 3: %s (결과: [%d, %d], 기대: [0, 1])%n",
                (r3[0] == 0 && r3[1] == 1) ? "PASS" : "FAIL", r3[0], r3[1]);

        // 테스트 4: 엣지 — 후진만 (방향 그대로 -y 방향)
        int[] r4 = solution("B");
        System.out.printf("테스트 4: %s (결과: [%d, %d], 기대: [0, -1])%n",
                (r4[0] == 0 && r4[1] == -1) ? "PASS" : "FAIL", r4[0], r4[1]);

        // 테스트 5: 엣지 — 회전만 (좌표 변화 없음)
        int[] r5 = solution("RRRRLLLL");
        System.out.printf("테스트 5: %s (결과: [%d, %d], 기대: [0, 0])%n",
                (r5[0] == 0 && r5[1] == 0) ? "PASS" : "FAIL", r5[0], r5[1]);

        // 테스트 6: 엣지 — R 4번으로 원위치 후 G
        int[] r6 = solution("RRRRG");
        System.out.printf("테스트 6: %s (결과: [%d, %d], 기대: [0, 1])%n",
                (r6[0] == 0 && r6[1] == 1) ? "PASS" : "FAIL", r6[0], r6[1]);

        // 테스트 7: 엣지 — L 한 번 후 G (왼쪽: -x 방향)
        int[] r7 = solution("LG");
        System.out.printf("테스트 7: %s (결과: [%d, %d], 기대: [-1, 0])%n",
                (r7[0] == -1 && r7[1] == 0) ? "PASS" : "FAIL", r7[0], r7[1]);

        // 테스트 8: 성능 — 1,000,000 개 명령 (전부 G)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) sb.append('G');
        int[] r8 = solution(sb.toString());
        System.out.printf("테스트 8: %s (결과: [%d, %d], 기대: [0, 1000000])%n",
                (r8[0] == 0 && r8[1] == 1_000_000) ? "PASS" : "FAIL", r8[0], r8[1]);

        // 테스트 9: 복합 — G 와 B 가 번갈아 나와 상쇄
        int[] r9 = solution("GBGBGB");
        System.out.printf("테스트 9: %s (결과: [%d, %d], 기대: [0, 0])%n",
                (r9[0] == 0 && r9[1] == 0) ? "PASS" : "FAIL", r9[0], r9[1]);
    }
}
