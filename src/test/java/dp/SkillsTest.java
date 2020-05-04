package dp;

import org.junit.Test;

import java.util.*;

public class SkillsTest {

	@Test
	public void test() {
		String[] req_skills = {"java","nodejs","reactjs"};
		String[][] lst = {{"java"},{"nodejs"},{"nodejs","reactjs"}};
		List<List<String>> llst = new ArrayList<>();

		for (String[] l : lst) {
			llst.add(Arrays.asList(l));
		}

		System.out.println(smallestSufficientTeam(req_skills, llst).length);
	}

	Map<Integer, String> skillsMap;
	Map<String, Integer> reverseSkillsMap;

	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
		skillsMap = new HashMap<>();
		reverseSkillsMap = new HashMap<>();

		int k = 0;
		int reqSkills = 0;
		for (String skill : req_skills) {
			reqSkills |= (1<<k);
			skillsMap.put(k, skill);
			reverseSkillsMap.put(skill, k++);
		}
		int[][] memo = new int[1<<16][people.size()];

		solve(reqSkills, people.size()-1, people, memo);

		int state = reqSkills;
		int n = people.size()-1;

		int[] ret = new int[memo[state][n]];
		int i = 0;
		while(state != 0) {
			int nextStateExcluding = state;
			int nextStateIncluding = state;
			for (String skill : people.get(n)) {
				int skillId = reverseSkillsMap.get(skill);
				int unsetSkill = (1<<16)-1 ^(1<<skillId);
				nextStateIncluding &= unsetSkill;
			}
			if (n == 0) {
				if (nextStateIncluding == 0) {
					ret[i++] = n;
					break;
				}
			}
			int countExcluding = memo[nextStateExcluding][n-1];
			int countIncluding = 1+memo[nextStateIncluding][n-1];

			if (countExcluding == -1 && countIncluding == -1) {
				break;
			} else if (countExcluding == -1) {
				state = nextStateIncluding;
				ret[i++] = n;
			} else {
				if (countExcluding < countIncluding) {
					state = nextStateExcluding;
				} else {
					state = nextStateIncluding;
					ret[i++] = n;
				}
			}
			n--;
		}

		return ret;
	}

	int solve(int reqSkills, int n, List<List<String>> people, int[][] memo) {
		if (n < 0) {
			if (reqSkills == 0)
				return 0;
			else
				return -1;
		}

		if (memo[reqSkills][n] != 0)
			return memo[reqSkills][n];

		int currSkillsReq = reqSkills;

		int skipCount = solve(reqSkills, n-1, people, memo);

		List<String> currPersonSkills = people.get(n);

		for (String skill : currPersonSkills) {
			int skillId = reverseSkillsMap.get(skill);

			int unsetSkill = (1<<16)-1 ^ (1<<skillId);

			reqSkills &= unsetSkill;
		}

		int includeCount = solve(reqSkills, n-1, people, memo);

		if (skipCount == -1 && includeCount == -1) {
			memo[currSkillsReq][n] = -1;
		} else if (skipCount == -1) {
			memo[currSkillsReq][n] = 1 + includeCount;
		} else {
			memo[currSkillsReq][n] = Math.min(skipCount, 1 + includeCount);
		}
		return memo[currSkillsReq][n];
	}
}
