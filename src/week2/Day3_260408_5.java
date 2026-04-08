package week2;

import java.util.*;

/**
 * 문제: 로봇 청소기 시뮬레이션
 *
 * N x N 격자에서 로봇 청소기가 명령어에 따라 이동하며 청소합니다.
 * 로봇은 (0, 0)에서 출발하며 방향은 위(UP)를 향합니다.
 * 명령어를 모두 수행한 뒤, 청소한 칸의 총 개수를 반환하세요.
 * (같은 칸을 여러 번 지나도 1번으로 카운트)
 *
 * [명령어]
 * - "G" : 현재 방향으로 1칸 전진
 * - "L" : 왼쪽으로 90도 회전 (이동 없음)
 * - "R" : 오른쪽으로 90도 회전 (이동 없음)
 *
 * [조건]
 * - 격자 범위: 0 <= x, y < n
 * - 격자 밖으로 나가는 "G" 명령은 무시 (이동하지 않음)
 * - 출발 칸 (0, 0)도 청소한 것으로 카운트
 *
 * [힌트]
 * - 방향 배열: 상(0,-1), 우(1,0), 하(0,1), 좌(-1,0) 순으로 관리
 * - "R"은 방향 인덱스 +1, "L"은 -1 (% 4로 순환)
 * - 방문한 좌표를 Set<String>에 "x,y" 형태로 저장하면 중복 제거 가능
 *
 * @param n 격자 크기
 * @param commands 명령어 배열
 * @return 청소한 칸의 수
 */
public class Day3_260408_5 {

    public static int solution(int n, String[] commands) {
        // 여기에 풀이를 작성하세요
        return 0;
    }

    public static void main(String[] args) {
        // 테스트 1: 5x5 격자, 직진 후 우회전 반복
        // (0,0)→(0,1)→(0,2)→우회전→(1,2)→(2,2) = 5칸
        int r1 = solution(5, new String[]{"G","G","R","G","G"});
        System.out.printf("테스트 1: %s (결과: %d, 기대: 5)%n", r1 == 5 ? "PASS" : "FAIL", r1);

        // 테스트 2: 격자 밖 이동 무시
        // (0,0)에서 위로 전진 → 밖이므로 무시, 청소한 칸 1개
        int r2 = solution(3, new String[]{"L","G"});
        System.out.printf("테스트 2: %s (결과: %d, 기대: 1)%n", r2 == 1 ? "PASS" : "FAIL", r2);

        // 테스트 3: 제자리 회전만
        int r3 = solution(3, new String[]{"R","R","R","R"});
        System.out.printf("테스트 3: %s (결과: %d, 기대: 1)%n", r3 == 1 ? "PASS" : "FAIL", r3);

        // 테스트 4: 같은 칸 재방문
        // (0,0)→(1,0)→(1,0 우회전)→(1,1)→(1,1 우회전)→(0,1)→(0,1 우회전)→(0,0) = 4칸
        int r4 = solution(3, new String[]{"R","G","R","G","R","G","R","G"});
        System.out.printf("테스트 4: %s (결과: %d, 기대: 4)%n", r4 == 4 ? "PASS" : "FAIL", r4);

        // 테스트 5: 큰 격자에서 한 줄 직진
        // (0,0)부터 오른쪽으로 5칸 = 6칸
        int r5 = solution(10, new String[]{"R","G","G","G","G","G"});
        System.out.printf("테스트 5: %s (결과: %d, 기대: 6)%n", r5 == 6 ? "PASS" : "FAIL", r5);

        // 테스트 6: 명령어 없음 → 시작점만
        int r6 = solution(5, new String[]{});
        System.out.printf("테스트 6: %s (결과: %d, 기대: 1)%n", r6 == 1 ? "PASS" : "FAIL", r6);
    }
}
