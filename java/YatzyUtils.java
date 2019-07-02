import java.util.Arrays;

public class YatzyUtils {

	public static int countOccurencesOfNumberInDice(int number, int[] dice) {

		if (dice == null) {
			return 0;
		}

		return (int) Arrays.stream(dice).filter(value -> number == value).count();
	}

	public static boolean checkIfSmallStraightOrLargeStraight(boolean smallStraight, int[] counts) {

		if (counts == null) {
			return false;
		}

		boolean check = YatzyUtils.countOccurencesOfNumberInDice(2, counts) == 1
				&& YatzyUtils.countOccurencesOfNumberInDice(3, counts) == 1
				&& YatzyUtils.countOccurencesOfNumberInDice(4, counts) == 1
				&& YatzyUtils.countOccurencesOfNumberInDice(5, counts) == 1;

		if (smallStraight) {

			return check && YatzyUtils.countOccurencesOfNumberInDice(1, counts) == 1;
		} else {

			return check && YatzyUtils.countOccurencesOfNumberInDice(6, counts) == 1;
		}
	}
}
