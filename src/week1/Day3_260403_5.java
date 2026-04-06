package week1;

import java.util.*;

/*
 * [1주차 Day3] 요세푸스 문제 (Josephus Problem)
 *
 * 문제:
 * N명의 사람이 원형으로 앉아 있다.
 * 1번부터 시작하여 매번 K번째 사람을 제거한다.
 * 제거된 순서대로 번호를 배열에 담아 반환하세요.
 *
 * 예: N=5, K=2일 때
 *   원형: 1 - 2 - 3 - 4 - 5
 *   1번부터 시작, 2번째 사람 제거 → 2 제거
 *   다음 3에서 시작, 2번째 → 4 제거
 *   다음 5에서 시작, 2번째 → 1 제거
 *   다음 3에서 시작, 2번째 → 5 제거
 *   마지막 3 제거
 *   결과: [2, 4, 1, 5, 3]
 *
 * 메서드 시그니처:
 * public static int[] solution(int n, int k)
 *
 * 입출력 예시:
 * n=5, k=2  → [2, 4, 1, 5, 3]
 * n=7, k=3  → [3, 6, 2, 7, 5, 1, 4]
 * n=4, k=1  → [1, 2, 3, 4]
 * n=1, k=1  → [1]
 *
 * 힌트:
 * - 기사 출정식처럼 원형 이중 연결 리스트로 구현
 * - 현재 위치에서 K칸 이동 → 해당 노드 삭제 → 반복
 * - HashMap 없이 순회로 이동하면 됩니다
 */
public class Day3_260403_5 {

    static class Node {
        int val;
        Node prev;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    public static int[] solution(int n, int k) {
        // 노드 생성
        Node[] nodes = new Node[n];
        for(int i = 0 ; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
        // 원형 이중 연결 리스트 만들기
        for(int i = 0 ; i < n; i++) {
            nodes[i].next = nodes[(i + 1) % n];
            nodes[i].prev = nodes[(i - 1 + n) % n];
        }

        // 현재 위치에서 k칸 이동 -> 삭제


        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (Arrays.equals(
            new int[]{2, 4, 1, 5, 3}, solution(5, 2)
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 2: " + (Arrays.equals(
            new int[]{3, 6, 2, 7, 5, 1, 4}, solution(7, 3)
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 3: " + (Arrays.equals(
            new int[]{1, 2, 3, 4}, solution(4, 1)
        ) ? "PASS" : "FAIL"));
        System.out.println("테스트 4: " + (Arrays.equals(
            new int[]{1}, solution(1, 1)
        ) ? "PASS" : "FAIL"));
    }
}
