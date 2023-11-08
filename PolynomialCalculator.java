import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class PolynomialCalculator{
	String input;
	boolean error = false;
	ArrayList<String> addSubTerms = new ArrayList<String>();
	ArrayList<String> multiplicationOutput = new ArrayList<String>();
	ArrayList<String> signs = new ArrayList<String>();
	int signCounter = 0;
	String addSubInput = "";
	ArrayList<String> addAndSub = new ArrayList<String>();
	ArrayList<Integer> descendingVariableOrder = new ArrayList<Integer>();
	ArrayList<String> outputArray = new ArrayList<String>();
	String output = "";

	PolynomialCalculator(){
		// input = "2x^3-4x^2-12x^3+x-8x^2+5x-3-5*3";
		// input = "x-x^2+32x-3*4x-12x^2-1";
		// input = "3x+2x^2";
		// input = "8x-8x+8x^2-2x*4x+3-3";
		// input = "9*3x^2+6x^7-3x^2*6x^4+12x^8-6x^2+3*8-6+5";
		input = "8x^2-3x^2+4x^2-8*3x^2";

		System.out.println(input + "\n");

		checkForErrors();

		if (error){
			System.out.println("Error");
		}else{
			// breaking inp into terms (add/sub) and adding to arraylist
			StringTokenizer st1 = new StringTokenizer(input, "+-");
			while (st1.hasMoreTokens()){
				addSubTerms.add(st1.nextToken());
			}

			multiply();

			// determining signs
			determineSigns();

			// putting back into a string for addition/subtraction
			addSubString();

			// breaking up addition and subtraction
			addSubTerms = new ArrayList<String>();
			StringTokenizer st2 = new StringTokenizer(addSubInput, "+-");
			signCounter = 0;

			while(st2.hasMoreTokens()){
				addSubTerms.add(st2.nextToken());
			}

			addSubTermSigns();

			// addition and subtraction
			additionAndSubtraction();

			descendingOrder();

			for(int i = 0; i < outputArray.size(); i++)
				output += outputArray.get(i);

			if (output.length() == 0)
				output = "0";
			else if (output.charAt(0) == '+')
				output = output.substring(1);

			System.out.println(output);
		}
	}

	public static void main(String[] args){
		new PolynomialCalculator();
	}

	public void multiply(){
		for (int i = 0; i < addSubTerms.size(); i++){
			// breaking up multiplication for each term and adding to arraylist
			ArrayList<String> multiplicationTerms = new ArrayList<String>();
			StringTokenizer st2 = new StringTokenizer(addSubTerms.get(i), "*");
			int constants = 1;
			int variableCounter = 0;

			while(st2.hasMoreTokens()){
				multiplicationTerms.add(st2.nextToken());
			}

			for (int j = 0; j < multiplicationTerms.size(); j++){
				String temp = multiplicationTerms.get(j);

				//multiplying constants together
				if (temp.contains("x")){
					try{
						constants *= Integer.parseInt(temp.substring(0, temp.indexOf("x")));
					}catch (NumberFormatException e){
						constants*=1;
					}
				}else
					constants *= Integer.parseInt(temp);

				//multiplying variables together
				if (temp.contains("^"))
					variableCounter += Integer.parseInt(temp.substring(temp.indexOf("^")+1));
				else if (temp.contains("x"))
					variableCounter++;
			}

			if (variableCounter != 0 && variableCounter != 1)
				multiplicationOutput.add(constants + "x^" + variableCounter);
			else if(variableCounter == 1)
				multiplicationOutput.add(constants + "x");
			else
				multiplicationOutput.add(Integer.toString(constants));
		}
	}

	public void determineSigns(){
		for (int i = 0; i < input.length(); i++){
			if (input.charAt(i) == '+')
				signs.add("+");
			else if (input.charAt(i) == '-')
				signs.add("-");
		}
	}

	public void addSubString(){
		for (int i = 0; i < multiplicationOutput.size(); i++){
			if (input.charAt(0) == '-'){
				if (signCounter < signs.size()){
					addSubInput += signs.get(signCounter);
					addSubInput += multiplicationOutput.get(i);
				}
			}else{
				addSubInput += multiplicationOutput.get(i);
				if (signCounter < signs.size())
					addSubInput += signs.get(signCounter);
			}
			signCounter++;
		}
	}

	public void addSubTermSigns(){
		if (input.charAt(0) == '-'){
			for (int i = 0; i < addSubTerms.size(); i++){
				if (signCounter < signs.size()){
					if (signs.get(signCounter).equals("-")){
						addSubTerms.set(i, "-" + addSubTerms.get(i));
					}
				}
				signCounter++;
			}
		}else{
			for (int i = 1; i < addSubTerms.size(); i++){
				if (signCounter < signs.size()){
					if (signs.get(signCounter).equals("-")){
						addSubTerms.set(i, "-" + addSubTerms.get(i));
					}
				}
				signCounter++;
			}
		}
	}

	public void additionAndSubtraction(){
		for (int i = 0; i < addSubTerms.size(); i++){
			int variable = 0;
			String temp = addSubTerms.get(i);
			if (temp.contains("^"))
				variable = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
			else if (temp.contains("x"))
				variable = 1;
			else
				variable = 0;

			int cons = 0;
			if (variable >= 1)
				cons = Integer.parseInt(temp.substring(0, temp.indexOf("x")));
			else
				cons = Integer.parseInt(temp);

			int tempSize = addSubTerms.size();
			for (int j = addSubTerms.size()-1; j > i; j--){
				int variable2 = 0;
				String temp2 = addSubTerms.get(j);
				if (temp2.contains("^"))
					variable2 = Integer.parseInt(temp2.substring(temp2.indexOf("^")+1));
				else if (temp2.contains("x"))
					variable2 = 1;
				else
					variable2 = 0;

				if (variable == variable2){
					int cons2 = 0;
					if (variable >= 1)
						cons2 = Integer.parseInt(temp2.substring(0, temp2.indexOf("x")));
					else
						cons2 = Integer.parseInt(temp2);

					cons += cons2;

					addSubTerms.remove(j);
				}
			}

			if (tempSize == addSubTerms.size()){
				if (temp.charAt(0) == '-')
					addAndSub.add(addSubTerms.get(i));
				else
					addAndSub.add("+" + addSubTerms.get(i));
			}else if (variable > 1){
				if (cons > 0)
					addAndSub.add("+" + (cons)+"x^"+variable);
				else if (cons < 0)
					addAndSub.add((cons)+"x^"+variable);
			}else if (variable == 1){
				if (cons > 0)
					addAndSub.add("+" + (cons)+"x");
				else if (cons < 0)
					addAndSub.add((cons)+"x");
			}else if (variable == 0){
				if (cons > 0)
					addAndSub.add("+" + (cons));
				else if (cons < 0)
					addAndSub.add(Integer.toString(cons));
			}
		}

		for (int i = 0; i < addAndSub.size(); i++){
			String temp = addAndSub.get(i);
			if (temp.contains("x")){
				if (temp.substring(0, temp.indexOf("x")).equals("+1"))
					addAndSub.set(i, "+" + temp.substring(temp.indexOf("x")));
				if (temp.substring(0, temp.indexOf("x")).equals("-1"))
					addAndSub.set(i, "-" + temp.substring(temp.indexOf("x")));
			}
		}
	}

	public void descendingOrder(){
		for (int i = 0; i < addAndSub.size(); i++){
			String temp = addAndSub.get(i);
			if (temp.contains("^")){
				int tempI = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
				descendingVariableOrder.add(tempI);
			}else if (temp.contains("x")){
				descendingVariableOrder.add(1);
			}else{
				descendingVariableOrder.add(0);
			}
		}
		Collections.sort(descendingVariableOrder, Collections.reverseOrder());

		for(int i = 0; i < descendingVariableOrder.size(); i++){
			int tempD = descendingVariableOrder.get(i);
			for (int j = 0; j < addAndSub.size(); j++){
				String temp = addAndSub.get(j);
				int tempF = -1;
				if(temp.contains("^"))
					tempF = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
				else if (temp.contains("x"))
					tempF = 1;
				else
					tempF = 0;
				if (tempF == tempD)
					outputArray.add(temp);
			}
		}
	}

	public void checkForErrors(){
		char last = input.charAt(input.length()-1);
		if(last == '+' || last == '*' || last == '^' || last == '-')
			error = true;

		for (int i = 0; i < input.length()-1; i++){
			char temp = input.charAt(i);
			if (temp == '+' || temp == '^' || temp == 'x' || temp == '-' || temp == '*'){
				if (temp == input.charAt(i+1))
					error = true;
			}
			if (temp == '^'){
				char temp2 = input.charAt(i+1);
				if (temp2 == 'x' || temp2 == '+' || temp2 == '-' || temp2 == '*')
					error = true;
			}
			if (temp == '-'){
				if (input.charAt(i+1) == '+')
					error = true;
			}
			if (temp == '+'){
				if (input.charAt(i+1) == '-')
					error = true;
			}
			if (i == 0){
				char first = input.charAt(i);
				if(first == '+' || first == '*' || first == '^')
					error = true;
			}
		}
	}
}
