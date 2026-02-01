import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static char[] alphabets;
    static final Set<Character> MOEUM = Set.of('a','e','i','o','u');

    // 방문한 인덱스의 알파벳들이 조건에 만족하는지 확인한다(모음1, 자음2개 이상)
    static boolean canBePassword(boolean[] visited, int passwordLen) {
        int moeumCnt = 0; // 모음 개수

        for(int i=0; i<alphabets.length; i++){
            if(!visited[i]) continue;

            char c = alphabets[i];
            if(MOEUM.contains(c)){ // 모음일 경우
                moeumCnt++;
            }
        }

        int jaeumCnt = passwordLen - moeumCnt;
        return 1<=moeumCnt && 2<=jaeumCnt; // 모음 1개이상, 자음 2개이상일 경우 true
    }

    // 방문한 인덱스의 알파벳을 출력한다.
    static void printPassword(boolean[] visited) {
        for(int i=0; i<visited.length; i++){
            if(!visited[i]) continue;

            System.out.print(alphabets[i]); // 알파벳 출력
        }
        System.out.println();
    }

    // 조합 생성
    static void combination(int idx, int cnt, int passwordLen, boolean[] visited) {
        if(cnt == passwordLen) { // 조합 완성
            if(canBePassword(visited, passwordLen)){
                printPassword(visited); // 가능한 조합일 경우 출력
            }
            return;
        }


        // 조합 생성
        for(int i=idx; i<visited.length; i++){
            visited[i] = true;
            combination(i+1, cnt+1, passwordLen, visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int C = sc.nextInt();

        alphabets = new char[C];
        for(int i=0; i<C; i++){
            alphabets[i] = sc.next().charAt(0);
        }

        Arrays.sort(alphabets); // 오름차순 정렬
        boolean[] visited = new boolean[C]; // 사용할 알파벳 인덱스
        combination(0, 0, L, visited); // 조합 구하기
    }
}