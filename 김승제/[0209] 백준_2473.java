import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] arr = new long[N];
        for(int i=0; i<N; i++) {
            arr[i] = sc.nextLong();
        }

        Arrays.sort(arr);

        long minGap = Long.MAX_VALUE;
        long[] ans = new long[3];
        for(int mid=1; mid<N-1; mid++) {
            long midValue = arr[mid];
            int s = 0;
            int e = N-1;

            while(s < mid && mid < e) {
                long sum = arr[s] + midValue + arr[e];
                long abs = Math.abs(sum);

                if(abs < minGap) {
                    minGap = abs;
                    ans[0] = arr[s];
                    ans[1] = arr[mid];
                    ans[2] = arr[e];
                }

                if(sum < 0) {
                    s++;
                }
                else {
                    e--;
                }
            }
        }

        for(int i=0; i<3; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}