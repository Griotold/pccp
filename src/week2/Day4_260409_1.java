package week2;

import java.util.*;

/**
 * 문제: 로봇 청소기 시뮬레이션
 *
 * N x N 격자에서 로봇 청소기가 명령어에 따라 이동하며 청소합니다.
 * 로봇은 (y=0, x=0) 즉 왼쪽 위 모서리에서 출발하며, 처음 방향은 오른쪽(RIGHT)을 향합니다.
 * 명령어를 모두 수행한 뒤, 청소한 칸의 총 개수를 반환하세요.
 * (같은 칸을 여러 번 지나도 1번으로 카운트)
 *
 * [명령어]
 * - "G" : 현재 방향으로 1칸 전진
 * - "L" : 왼쪽으로 90도 회전 (이동 없음)
 * - "R" : 오른쪽으로 90도 회전 (이동 없음)
 *
 * [조건]
 * - 격자 범위: 0 <= y, x < n  (board[y][x] 스타일)
 * - 격자 밖으로 나가는 "G" 명령은 무시 (이동하지 않음)
 * - 출발 칸 (0, 0)도 청소한 것으로 카운트
 *
 * [힌트]
 * - 방향 배열: 상·우·하·좌 순서 (시계 방향)
 *     dy = {-1, 0, 1, 0}
 *     dx = { 0, 1, 0,-1}
 * - 시작 방향 인덱스는 1 (우)
 * - "R" 회전: dir = (dir + 1) % 4
 * - "L" 회전: dir = (dir + 3) % 4   (음수 나머지 회피)
 * - 방문한 좌표는 Set<String>에 "y,x" 형태로 저장하면 중복 제거 가능
 * - 경계 체크: 0 <= ny && ny < n && 0 <= nx && nx < n
 *
 * @param n 격자 크기
 * @param commands 명령어 배열
 * @return 청소한 칸의 수
 */
public class Day4_260409_1 {
    static final int[] dy = {-1, 0, 1, 0};  // 상, 우, 하, 좌
    static final int[] dx = { 0, 1, 0,-1};
    static Set<String> visited;

    public static int solution(int n, String[] commands) {
        visited = new HashSet<>();
        int y = 0, x = 0;
        int dir = 1;  // 시작 방향: 우
        visited.add(y + "," + x);

        for (String cmd : commands) {
            if (cmd.equals("G")) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny >= 0 && ny < n && nx >= 0 && nx < n) {
                    y = ny;
                    x = nx;
                    visited.add(y + "," + x);
                }
            } else if (cmd.equals("R")) {
                dir = (dir + 1) % 4;
            } else if (cmd.equals("L")) {
                dir = (dir + 3) % 4;
            }
        }
        return visited.size();
    }

    public static void main(String[] args) {
        // 테스트 1: 5x5 격자, 오른쪽으로 쭉 가다가 아래로 꺾기
        // 좌표는 (y, x) 기준
        // (0,0)→G(0,1)→G(0,2)→R(하)→G(1,2)→G(2,2) = 5칸
        int r1 = solution(5, new String[]{"G","G","R","G","G"});
        System.out.printf("테스트 1: %s (결과: %d, 기대: 5)%n", r1 == 5 ? "PASS" : "FAIL", r1);

        // 테스트 2: 격자 밖 이동 무시
        // 시작 방향 우에서 L → 상으로 회전, (0,0)에서 위로 가면 밖이라 무시
        // 청소한 칸 = (0,0) 하나
        int r2 = solution(3, new String[]{"L","G"});
        System.out.printf("테스트 2: %s (결과: %d, 기대: 1)%n", r2 == 1 ? "PASS" : "FAIL", r2);

        // 테스트 3: 제자리 회전만 → 시작 칸 1개
        int r3 = solution(3, new String[]{"R","R","R","R"});
        System.out.printf("테스트 3: %s (결과: %d, 기대: 1)%n", r3 == 1 ? "PASS" : "FAIL", r3);

        // 테스트 4: 정사각 루프로 원래 자리 복귀 (같은 칸 재방문)
        // (0,0)→G(0,1)→R(하)→G(1,1)→R(좌)→G(1,0)→R(상)→G(0,0)
        // 방문: (0,0), (0,1), (1,1), (1,0) = 4칸
        int r4 = solution(3, new String[]{"G","R","G","R","G","R","G"});
        System.out.printf("테스트 4: %s (결과: %d, 기대: 4)%n", r4 == 4 ? "PASS" : "FAIL", r4);

        // 테스트 5: 큰 격자에서 한 줄 직진
        // (0,0)에서 오른쪽으로 5번 전진 → (0,0)~(0,5) = 6칸
        int r5 = solution(10, new String[]{"G","G","G","G","G"});
        System.out.printf("테스트 5: %s (결과: %d, 기대: 6)%n", r5 == 6 ? "PASS" : "FAIL", r5);

        // 테스트 6: 명령어 없음 → 시작점만
        int r6 = solution(5, new String[]{});
        System.out.printf("테스트 6: %s (결과: %d, 기대: 1)%n", r6 == 1 ? "PASS" : "FAIL", r6);

        // 테스트 7: 모서리에 갇힘 - 왼쪽 위에서 L과 G 반복해도 밖
        // 시작 우 → L(상) → G(밖) → L(좌) → G(밖) → L(하) → G(1,0) → L(우) → G(1,1) = 3칸
        int r7 = solution(4, new String[]{"L","G","L","G","L","G","L","G"});
        System.out.printf("테스트 7: %s (결과: %d, 기대: 3)%n", r7 == 3 ? "PASS" : "FAIL", r7);
    }
}
