// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// This program has each oracle answer all of it's given questions in a round robin fashion
public class Executor {

	public static void main(String[] args) {
		Utility.init(); // initializes file readers
		String[] questions = Utility.readQuestions(); //reads question.txt file into questions array
		String[] answers = Utility.readAnswers(); // reads answers.txt file into answers array
		
		int numOracles = answers.length; //finds the number of oracles
		
		// ArrayQueue
		// plays a "game" of oracles answering their questions in round robin fashion
		StringQueue[] oracles = assign(numOracles, new ArrayQueue[numOracles], true);
		arrange(oracles, questions);
		wiseWords(oracles, answers, questions.length);
		
		// separator to distinguish between the ArrayQueue and ListQueue
		System.out.println("\n******************************************************\n");
		
		// ListQueue
		// plays a "game" of oracles answering their questions in round robin fashion
		StringQueue[] oracleList = assign(numOracles, new ListQueue[numOracles], false);
		arrange(oracleList, questions);
		wiseWords(oracleList, answers, questions.length);
	}
	
	/**
	 * @function prepares each oracle to receive it's questions
	 * @param num: number of oracles
	 * @param oracles: the array of oracles
	 * @param isArrayQ: distinguish between types of queue
	 * @return the array of oracles
	 */
	public static StringQueue[] assign(int num, StringQueue[] oracles, boolean isArrayQ) {
		for (int i = 0; i < num; i++) {
			if (isArrayQ) {
				oracles[i] = new ArrayQueue();
			} else {
				oracles[i] = new ListQueue();
			}
		}
		return oracles;
	}
	
	/**
	 * @function gives each oracle it's set of questions, randomly
	 * @param oracles: the list of all oracles
	 * @param questions: the given entire set of questions
	 */
	public static void arrange(StringQueue[] oracles, String[] questions) {
		int num = oracles.length;
		for (int i = 0; i < questions.length; i++) {
			oracles[Utility.random(num)].enqueue(questions[i]); // randomly assign each oracle questions
		}
	}
	
	/**
	 * @function The oracles go around round robin answering each question it has 1 by 1 until there are
	 * 	no more questions to answer
	 * 	the question and answer are printed to the console
	 * @param oracles: list of oracles
	 * @param answers: list of answers that correspond to each oracle
	 * @param numQ: number of total questions
	 */
	public static void wiseWords(StringQueue[] oracles, String[] answers, int numQ) {
		int current = 0; // count of oracles "visited"
		int qAnswered = 0; // number of questions answered
		while (qAnswered < numQ) {
			int index = current % answers.length; // finds the correct oracle to ask
			StringQueue oracle = oracles[index];
			if (!oracle.isEmpty()) {
				String question = oracle.dequeue();
				System.out.println(question + ": " + answers[index]);
				qAnswered++;
			}
			current++;
		}
	}
}