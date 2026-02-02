import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Palindrome {
	// 회문인지 체크해주는 메서드
	public static boolean isOk(List addArr) {
		// 만약 길이가 홀수면 mid -1 부터 0까지 firstfix, mid부터 List길이까지 endfix
		String firfix = "";
		String endfix = "";
		for(int i = (addArr.size() / 2) - 1; i >= 0; i--) {
			firfix += addArr.get(i);
		}
		// 만약 길이가 짝수면 mid -1 부터 0까지 firstfix, mid + 1부터 List길이까지 endfix
		if (addArr.size() % 2 == 0) {
			for(int i = (addArr.size() / 2); i < addArr.size(); i++) {
				endfix += addArr.get(i);
			}	
		} else {
			for(int i = (addArr.size() / 2) + 1; i < addArr.size(); i++) {
				endfix += addArr.get(i);
			}
		}
		// firstfix와 endfix가 같다면 true반환 아니면 false반환
		if(firfix.equals(endfix))
			return true;
		else
			return false;
		
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		// 문자열을 쪼개서 배열에 저장
		char[] arr = str.toCharArray();
		
		// 결과 문자길이 저장 변수
		int result = 0;
		
		// 문자 추가할 수 있게 List로 배열을 다시 재정의
		List<Character> addArr = new ArrayList<>();
		
		// 가장 긴 회문 크기 저장 변수
		int max = 0;
		
		// 배열 마지막 인덱스부터 조회해서 가장 긴 회문 탐색
		for(int i = str.length()-1; i >= 0; i--) {
			addArr.add(arr[i]);
			// 만약 회문이 있다면 true로 max값 변경
			if(isOk(addArr)) {
				max = addArr.size();
			}
		}
		// 회문길이만큼 배열idx빼주고 그 뒤에서부터 List에 추가
		for(int i = addArr.size()-max-1; i >=0; i--) {
			addArr.add(arr[i]);
		}
		
		System.out.println(addArr.size());
	}
}