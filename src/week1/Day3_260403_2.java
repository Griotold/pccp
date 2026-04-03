package week1;

import java.util.*;

/*
 * [1주차 Day3] 기사 출정식
 * 출처: 코드트리 (60XP, 평균 59분, 정답률 60%)
 *
 * 문제:
 * 원탁에 N명의 기사가 앉아 있다.
 * 왕이 기사 번호를 부르면, 해당 기사는 자리에서 일어나 떠나면서
 * 자신의 왼쪽과 오른쪽에 앉아 있는 기사의 번호를 외친다.
 * 왕이 M번 기사를 호출할 때, 각 호출마다 떠나는 기사의
 * [왼쪽 번호, 오른쪽 번호]를 반환하세요.
 *
 * 메서드 시그니처:
 * public static int[][] solution(int[] knights, int[] calls)
 *   - knights: 원탁에 앉은 기사 번호 배열 (순서대로, 원형 연결)
 *   - calls: 왕이 호출하는 기사 번호 배열
 *   - 반환: 각 호출마다 {오른쪽 번호, 왼쪽 번호} 배열
 *
 * 입출력 예시:
 * knights=[1,2,3,4,5], calls={3,5}
 *   → {{4,2},{1,4}}
 *   (3 떠남 → 왼쪽 2, 오른쪽 4 출력 / 5 떠남 → 왼쪽 4, 오른쪽 1 출력)
 *
 * knights=[1,10,8,6,4,3,9], calls={4,3,6}
 *   → {{3,6},{9,6},{9,8}}
 *
 * 힌트:
 * - 원형 구조 → 이중 연결 리스트(Doubly Linked List)로 구현
 * - 각 기사 번호 → 노드 매핑을 HashMap으로 관리하면 O(1) 탐색 가능
 * - 노드 삭제 시 prev.next = next, next.prev = prev 처리
 * - Java에서 직접 Node 클래스를 만들어서 구현해보자
 */
public class Day3_260403_2 {

    // 이중 연결 리스트 노드
    static class Node {
        int val;
        Node prev, next;
        Node(int val) { this.val = val; }
    }

    public static int[][] solution(int[] knights, int[] calls) {
        int n = knights.length;

        // 1. 노드 생성 + 번호→노드 매핑
        Map<Integer, Node> map = new HashMap<>();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(knights[i]);
            map.put(knights[i], nodes[i]);
        }

        // 2. 원형 이중 연결 리스트 연결
        for (int i = 0; i < n; i++) {
            nodes[i].next = nodes[(i + 1) % n];
            nodes[i].prev = nodes[(i - 1 + n) % n];
        }

        // 3. 호출마다 삭제 + 양 옆 반환
        int[][] result = new int[calls.length][2];
        for (int i = 0; i < calls.length; i++) {
            Node cur = map.get(calls[i]);
            result[i][0] = cur.next.val;  // 오른쪽
            result[i][1] = cur.prev.val;  // 왼쪽

            // 삭제: 양 옆을 서로 연결
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (Arrays.deepEquals(
            new int[][]{{4,2},{1,4}},
            solution(new int[]{1,2,3,4,5}, new int[]{3,5})
        ) ? "PASS" : "FAIL"));

        System.out.println("테스트 2: " + (Arrays.deepEquals(
            new int[][]{{3,6},{9,6},{9,8}},
            solution(new int[]{1,10,8,6,4,3,9}, new int[]{4,3,6})
        ) ? "PASS" : "FAIL"));
    }
}
