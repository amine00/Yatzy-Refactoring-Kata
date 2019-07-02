import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class Yatzy {

	protected int[] dice;

	public Yatzy(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		dice = new int[] { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };
	}

	public static int chance(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		return Arrays.stream(rolls).sum();
	}

	public static int yatzy(int... dice) {

		if (dice == null) {
			return 0;
		}

		if (YatzyUtils.countOccurencesOfNumberInDice(dice[0], dice) == 5) {
			return 50;
		}

		return 0;
	}

	public static int ones(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		return YatzyUtils.countOccurencesOfNumberInDice(1, rolls);
	}

	public static int twos(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		return YatzyUtils.countOccurencesOfNumberInDice(2, rolls) * 2;
	}

	public static int threes(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		return YatzyUtils.countOccurencesOfNumberInDice(3, rolls) * 3;
	}

	public int fours() {

		return YatzyUtils.countOccurencesOfNumberInDice(4, dice) * 4;
	}

	public int fives() {

		return YatzyUtils.countOccurencesOfNumberInDice(5, dice) * 5;
	}

	public int sixes() {

		return YatzyUtils.countOccurencesOfNumberInDice(6, dice) * 6;
	}

	public static int score_pair(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		int highestPairNumber = Arrays.stream(rolls)
				.filter(value -> YatzyUtils.countOccurencesOfNumberInDice(value, rolls) >= 2).max().orElse(0);

		return highestPairNumber * 2;
	}

	public static int two_pair(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		IntSummaryStatistics summaryStatistics = Arrays.stream(rolls)
				.filter(value -> YatzyUtils.countOccurencesOfNumberInDice(value, rolls) >= 2).distinct()
				.summaryStatistics();

		if (summaryStatistics.getCount() == 2) {

			return (int) (summaryStatistics.getSum() * 2);
		}

		return 0;
	}

	public static int three_of_a_kind(int first_roll, int second_roll, int thirs_roll, int fourth_roll,
			int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		int threeOfAKind = Arrays.stream(rolls)
				.filter(value -> YatzyUtils.countOccurencesOfNumberInDice(value, rolls) >= 3).findAny().orElse(0);

		return threeOfAKind * 3;
	}

	public static int four_of_a_kind(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		int fourOfAKind = Arrays.stream(rolls)
				.filter(value -> YatzyUtils.countOccurencesOfNumberInDice(value, rolls) >= 4).findAny().orElse(0);

		return fourOfAKind * 4;
	}

	public static int smallStraight(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		return YatzyUtils.checkIfSmallStraightOrLargeStraight(true, rolls) ? 15 : 0;
	}

	public static int largeStraight(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int[] rolls = { first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll };

		return YatzyUtils.checkIfSmallStraightOrLargeStraight(false, rolls) ? 20 : 0;
	}

	public static int fullHouse(int first_roll, int second_roll, int thirs_roll, int fourth_roll, int fifth_roll) {

		int scorePair = score_pair(first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll);
		if (scorePair == 0) {
			return 0;
		}

		int threeOfAkind = three_of_a_kind(first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll);
		if (threeOfAkind == 0) {
			return 0;
		}

		int sumOfAllNumbers = chance(first_roll, second_roll, thirs_roll, fourth_roll, fifth_roll);

		return sumOfAllNumbers == (scorePair + threeOfAkind) ? sumOfAllNumbers : 0;
	}

}
