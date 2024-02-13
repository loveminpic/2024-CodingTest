package src.Class.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomTest {

	static class Meeting implements Comparable<Meeting>{
		int start, end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ? this.end - o.end : this.start - o.start;
			
		}
		@Override
		public String toString() {
			return "Meeting [start=" + start + ", end=" + end + "]";
		}
		
		
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Meeting[] meetings = new Meeting[N];
		
		for(int i = 0; i < N; i ++) {
			meetings[i]  = new Meeting(sc.nextInt(), sc.nextInt());
			
		}
		Arrays.sort(meetings);
		
		List<Meeting> list = new ArrayList<>();
		list.add(meetings[0]);
		
		for(int j= 1; j < N; j++) {
			if(list.get(list.size() -1).end <= meetings[j].start) {
				list.add(meetings[j]);
			}
		}
		
		System.out.println(list.size());
		for(Meeting meeting : list) {
			System.out.println(meeting.toString());
		}
	}

}
