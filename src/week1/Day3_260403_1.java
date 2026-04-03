package week1;

import java.util.*;

/*
 * [1주차 Day3] 배열에서 자리 바꾸기
 * 출처: 코드트리 (60XP, 평균 104분, 정답률 49%)
 *
 * 문제:
 * 1부터 N까지 수가 차례대로 적혀있는 길이 N짜리 배열이 있다.
 * 값이 a인 원소에서 시작해서 값이 b인 원소로 끝나는 부분배열을 ⟨a, b⟩로 표기한다.
 * 겹치지 않는 두 부분배열 ⟨a, b⟩와 ⟨c, d⟩의 위치를 서로 바꾸는 연산을 Q번 수행한 뒤,
 * 최종 배열 상태를 반환하세요.
 *
 * 예: [3, 1, 4, 2, 5, 9, 6, 8, 7]에서 연산 (1, 2, 9, 7)
 *     → 부분배열 [1, 4, 2]와 [9, 6, 8, 7]의 위치를 교환
 *     → [3, 9, 6, 8, 7, 5, 1, 4, 2]
 *
 * 메서드 시그니처:
 * public static int[] solution(int n, int[][] operations)
 *   - n: 배열 길이 (초기 배열은 [1, 2, 3, ..., n])
 *   - operations: Q개의 연산, 각 연산은 {a, b, c, d}
 *
 * 입출력 예시:
 * n=5, ops={{2,2,3,3},{2,2,5,5}}                     → [1,3,5,4,2]
 * n=6, ops={{5,6,2,3},{1,6,4,3},{1,5,2,2},{5,5,3,2}} → [4,1,3,2,5,6]
 *
 * 힌트:
 * - 값 → 인덱스 매핑 배열을 유지하면 값의 위치를 O(1)로 찾을 수 있다
 * - 두 부분배열의 길이가 다를 수 있으므로, 새 배열을 조립하는 방식으로 접근해보자
 *   (앞부분 + 뒷 부분배열 + 중간 + 앞 부분배열 + 뒷부분)
 */
public class Day3_260403_1 {

    public static int[] solution(int n, int[][] operations) {
        // 초기 배열 [1, 2, ..., n]
        int[] arr = new int[n];
        int[] pos = new int[n + 1]; // pos[v] = 값 v의 인덱스
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            pos[i + 1] = i;
        }

        for (int[] op : operations) {
            int a = op[0], b = op[1], c = op[2], d = op[3];

            // 값으로 인덱스 찾기
            int s1 = pos[a], e1 = pos[b];
            int s2 = pos[c], e2 = pos[d];
            if (s1 > e1) { int tmp = s1; s1 = e1; e1 = tmp; }
            if (s2 > e2) { int tmp = s2; s2 = e2; e2 = tmp; }

            // 항상 s1 < s2가 되도록 정렬
            if (s1 > s2) {
                int tmp;
                tmp = s1; s1 = s2; s2 = tmp;
                tmp = e1; e1 = e2; e2 = tmp;
            }

            // 새 배열 조립: 앞 + 뒷구간 + 중간 + 앞구간 + 뒤
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < s1; i++) result.add(arr[i]);           // 앞부분
            for (int i = s2; i <= e2; i++) result.add(arr[i]);         // 뒷 부분배열
            for (int i = e1 + 1; i < s2; i++) result.add(arr[i]);     // 중간
            for (int i = s1; i <= e1; i++) result.add(arr[i]);         // 앞 부분배열
            for (int i = e2 + 1; i < n; i++) result.add(arr[i]);      // 뒷부분

            // 배열 및 위치 갱신
            for (int i = 0; i < n; i++) {
                arr[i] = result.get(i);
                pos[arr[i]] = i;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("테스트 1: " + (Arrays.equals(
            new int[]{1, 3, 5, 4, 2},
            solution(5, new int[][]{{2,2,3,3},{2,2,5,5}})
        ) ? "PASS" : "FAIL"));

        System.out.println("테스트 2: " + (Arrays.equals(
            new int[]{4, 1, 3, 2, 5, 6},
            solution(6, new int[][]{{5,6,2,3},{1,6,4,3},{1,5,2,2},{5,5,3,2}})
        ) ? "PASS" : "FAIL"));
    }
}
