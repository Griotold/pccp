package week2;

import java.util.*;

/**
 * 문제: [PCCP 기출문제] 1번 / 동영상 재생기
 *
 * 동영상 재생기는 3가지 기능을 지원합니다.
 *
 * 1) "prev" — 10초 전으로 이동
 *    - 현재 위치가 10초 미만이면 영상 처음(00:00)으로 이동
 *
 * 2) "next" — 10초 후로 이동
 *    - 남은 시간이 10초 미만이면 영상 마지막(video_len)으로 이동
 *
 * 3) 오프닝 건너뛰기 — op_start ≤ 현재 위치 ≤ op_end 이면
 *    자동으로 op_end 로 이동
 *    (시작 시점, 그리고 매 명령 실행 후 매번 체크)
 *
 * 모든 명령이 끝난 후의 재생 위치를 "mm:ss" 형식 문자열로 반환하세요.
 *
 * [제한 사항]
 * - video_len, pos, op_start, op_end 는 모두 "mm:ss" 형식 (길이 5)
 * - 0 ≤ mm ≤ 59, 0 ≤ ss ≤ 59 (한 자리면 0 패딩)
 * - 현재 위치나 오프닝 끝 시각이 동영상 범위 밖인 경우는 없음
 * - op_start 는 op_end 보다 항상 앞
 * - 1 ≤ commands.length ≤ 100
 * - commands[i] 는 "prev" 또는 "next"
 *
 * [예시로 이해하기]
 * 입력: video_len="10:55", pos="00:05", op_start="00:15", op_end="06:55",
 *       commands=["prev","next","next"]
 *
 *  단계 | 위치(초)    | 명령/판정              | 설명
 *  -----|-------------|------------------------|----------------------------------
 *   시작|   5 (00:05) | 오프닝 아님             | 15초 전이라 오프닝 구간 밖
 *    1  |   0 (00:00) | prev → max(0, 5-10)     | 10초 미만이라 처음으로
 *    2  |  10 (00:10) | next → 0+10             | 아직 오프닝(15초) 직전
 *    3  |  20 (00:20) | next → 10+10            | 20초는 [15,415] 구간 안 → 점프
 *       | 415 (06:55) | 오프닝 건너뛰기          | op_end 로 자동 이동
 *
 * 최종 위치: "06:55"
 *
 * 포인트:
 * - mm:ss 를 초 단위 int 로 바꾸면 계산이 훨씬 단순해짐
 * - 오프닝 체크는 "시작 직후"에도 한 번, "매 명령 실행 후"에도 한 번 필요
 * - prev 는 max(0, cur-10), next 는 min(videoTotal, cur+10) 로 양 끝 클램프
 *
 * [힌트]
 * - 문자열 → 초 변환:
 *     String[] mmss = s.split(":");
 *     int sec = Integer.parseInt(mmss[0]) * 60 + Integer.parseInt(mmss[1]);
 * - 초 → 문자열 변환:
 *     String.format("%02d:%02d", sec / 60, sec % 60);
 * - 상태 변수는 "현재 위치(초)" 하나면 충분. 나머지는 상수처럼 들고 있으면 됨.
 * - 오프닝 체크를 함수로 빼두면(예: applyOpeningSkip) 시작 시점과 루프 안에서
 *   같은 로직을 중복 없이 호출할 수 있음.
 * - 클램프 순서:
 *     prev: cur = Math.max(0, cur - 10);
 *     next: cur = Math.min(videoTotal, cur + 10);
 * - 오프닝 조건은 "경계 포함" (op_start ≤ cur ≤ op_end), 부등호 주의.
 *
 * @param video_len 동영상 길이 "mm:ss"
 * @param pos 시작 재생 위치 "mm:ss"
 * @param op_start 오프닝 시작 "mm:ss"
 * @param op_end 오프닝 끝 "mm:ss"
 * @param commands 명령어 배열
 * @return 최종 재생 위치 "mm:ss"
 */
public class Day4_260409_3_past {

    static int toSec(String mmss) {
        String[] p = mmss.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String toMmss(int sec) {
        return String.format("%02d:%02d", sec / 60, sec % 60);
    }

    static int applyOpeningSkip(int cur, int opStart, int opEnd) {
        if(opStart <= cur && cur <= opEnd) {
            return opEnd;
        }
        return cur;
    }
    // 입력: video_len="10:55", pos="00:05", op_start="00:15", op_end="06:55",
    // *       commands=["prev","next","next"]
    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int cur         = toSec(pos);
        int opStartSec  = toSec(op_start);
        int opEndSec    = toSec(op_end);
        int videoLenSec = toSec(video_len);

        cur = applyOpeningSkip(cur, opStartSec, opEndSec);

        for (String com : commands) {
            if (com.equals("prev")) {
                cur = Math.max(0, cur - 10);
            } else {
                cur = Math.min(videoLenSec, cur + 10);
            }
            cur = applyOpeningSkip(cur, opStartSec, opEndSec);
        }
        return toMmss(cur);
    }

    public static void main(String[] args) {
        // 테스트 1: 예제 #1 — 오프닝 건드리지 않음, 전/후 상쇄
        String r1 = solution("34:33", "13:00", "00:55", "02:55",
                new String[]{"next", "prev"});
        System.out.printf("테스트 1: %s (결과: %s, 기대: 13:00)%n",
                r1.equals("13:00") ? "PASS" : "FAIL", r1);

        // 테스트 2: 예제 #2 — prev 클램프 → next 두 번 → 오프닝 점프
        String r2 = solution("10:55", "00:05", "00:15", "06:55",
                new String[]{"prev", "next", "next"});
        System.out.printf("테스트 2: %s (결과: %s, 기대: 06:55)%n",
                r2.equals("06:55") ? "PASS" : "FAIL", r2);

        // 테스트 3: 예제 #3 — 시작 위치가 오프닝 구간 → 시작 직후 점프
        String r3 = solution("07:22", "04:05", "00:15", "04:07",
                new String[]{"next"});
        System.out.printf("테스트 3: %s (결과: %s, 기대: 04:17)%n",
                r3.equals("04:17") ? "PASS" : "FAIL", r3);

        // 테스트 4: 엣지 — next 가 video_len 으로 클램프
        // 10:50 에서 next → 11:00이 아니라 10:55(끝)
        String r4 = solution("10:55", "10:50", "00:00", "00:05",
                new String[]{"next"});
        System.out.printf("테스트 4: %s (결과: %s, 기대: 10:55)%n",
                r4.equals("10:55") ? "PASS" : "FAIL", r4);

        // 테스트 5: 엣지 — prev 가 00:00 으로 클램프
        // 00:09 에서 prev → 00:00 (10초 미만)
        String r5 = solution("10:55", "00:09", "05:00", "06:00",
                new String[]{"prev"});
        System.out.printf("테스트 5: %s (결과: %s, 기대: 00:00)%n",
                r5.equals("00:00") ? "PASS" : "FAIL", r5);

        // 테스트 6: 엣지 — 정확히 op_start 에 있음 (경계 포함)
        // 00:15 == op_start 이므로 오프닝 구간, 00:20 으로 점프 후 next → 00:30
        String r6 = solution("10:00", "00:15", "00:15", "00:20",
                new String[]{"next"});
        System.out.printf("테스트 6: %s (결과: %s, 기대: 00:30)%n",
                r6.equals("00:30") ? "PASS" : "FAIL", r6);

        // 테스트 7: 엣지 — 정확히 op_end 에 있음 (경계 포함)
        // 00:20 == op_end 이므로 오프닝 구간, 여전히 00:20 유지 후 prev → 00:10
        String r7 = solution("10:00", "00:20", "00:15", "00:20",
                new String[]{"prev"});
        System.out.printf("테스트 7: %s (결과: %s, 기대: 00:10)%n",
                r7.equals("00:10") ? "PASS" : "FAIL", r7);

        // 테스트 8: 엣지 — 명령 실행 결과가 정확히 op_end 직전으로
        // next 로 오프닝 구간 밖에서 막 진입해서 점프하는 케이스
        // 00:05 → next → 00:15 (op_start와 같음, 구간 안) → 01:00으로 점프
        String r8 = solution("10:00", "00:05", "00:15", "01:00",
                new String[]{"next"});
        System.out.printf("테스트 8: %s (결과: %s, 기대: 01:00)%n",
                r8.equals("01:00") ? "PASS" : "FAIL", r8);
    }
}
