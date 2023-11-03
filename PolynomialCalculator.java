import java.util.StringTokenizer;
import java.util.ArrayList;

public class PolynomialCalculator{
	String inp;

	PolynomialCalculator(){
		// inp = "-2x^3-4x^2-12x^3+x-8x^2+5x-3-5";
		// inp = "-x^2+32x-3*4x-12x^2+3";
		// inp = "2x^2-x^2";

		System.out.println(inp + "\n");

		boolean startNeg = false;

		// breaking inp into terms (add/sub) and adding to arraylist
		ArrayList<String> a = new ArrayList<String>();
		StringTokenizer st1 = new StringTokenizer(inp, "+-");
		while (st1.hasMoreTokens()){
			a.add(st1.nextToken());
		}

		ArrayList<String> ans = new ArrayList<String>();
		for (int i = 0; i < a.size(); i++){
			// breaking up multiplication for each term and adding to arraylist
			ArrayList<String> m = new ArrayList<String>();
			StringTokenizer st2 = new StringTokenizer(a.get(i), "*");
			int cons = 1;
			int varCount = 0;

			while(st2.hasMoreTokens()){
				m.add(st2.nextToken());
			}

			for (int j = 0; j < m.size(); j++){
				String temp = m.get(j);

				//multiplying constants together
				if (temp.contains("x")){
					try{
						cons *= Integer.parseInt(temp.substring(0, temp.indexOf("x")));
					}catch (NumberFormatException e){
						cons*=1;
					}
				}else
					cons *= Integer.parseInt(temp);

				//multiplying variables together
				if (temp.contains("^"))
					varCount += Integer.parseInt(temp.substring(temp.indexOf("^")+1));
				else if (temp.contains("x"))
					varCount++;
			}

			if (varCount != 0 && varCount != 1)
				ans.add(cons + "x^" + varCount);
			else if(varCount == 1)
				ans.add(cons + "x");
			else
				ans.add(Integer.toString(cons));
		}

		// determining signs
		ArrayList<String> sign = new ArrayList<String>();
		for (int i = 0; i < inp.length(); i++){
			if (inp.substring(i, i+1).equals("+"))
				sign.add("+");
			else if (inp.substring(i, i+1).equals("-"))
				sign.add("-");
		}
		if (inp.indexOf("-") == 0){
			startNeg = true;
		}

		// putting back into a string for addition/subtraction
		int signCount = 0;
		String add = "";
		for (int i = 0; i < ans.size(); i++){
			if (signCount < sign.size())
				add += sign.get(signCount);
			add += ans.get(i);
			signCount++;
		}


		// breaking up addition and subtraction
		ArrayList<String> b = new ArrayList<String>();
		StringTokenizer st2 = new StringTokenizer(add, "+-");
		while (st2.hasMoreTokens()){
			String temp = "";
			for (int i = 0; i < sign.size(); i++){
				if (sign.get(i).equals("-"))
					temp = "-" + st2.nextToken();
				else
					temp = st2.nextToken();
				b.add(temp);
			}
		}

		// addition and subtraction
		ArrayList<String> addFin = new ArrayList<String>();
		for (int i = 0; i < b.size(); i++){
			int highVar = 0;
			String temp = b.get(i);
			if (temp.contains("^"))
				highVar = Integer.parseInt(temp.substring(temp.indexOf("^")+1));
			else if (temp.contains("x"))
				highVar = 1;
			else
				highVar = 0;

			int tempSize = b.size();
			for (int j = b.size()-1; j > i; j--){
				int highVar2 = 0;
				String temp2 = b.get(j);
				if (temp2.contains("^"))
					highVar2 = Integer.parseInt(temp2.substring(temp2.indexOf("^")+1));
				else if (temp2.contains("x"))
					highVar2 = 1;
				else
					highVar2 = 0;

				if (highVar == highVar2){
					int cons = 0;
					int cons2 = 0;
					if (highVar >= 1){
						cons = Integer.parseInt(temp.substring(0, temp.indexOf("x")));
						cons2 = Integer.parseInt(temp2.substring(0, temp2.indexOf("x")));
					}else{
						cons = Integer.parseInt(temp);
						cons2 = Integer.parseInt(temp2);
					}

					if (highVar > 1){
						if (cons+cons2 > 0)
							addFin.add((cons+cons2)+"x^"+highVar);
						else if (cons + cons2 < 0)
							addFin.add((cons+cons2)+"x^"+highVar);
					}
					else if (highVar == 1){
						if (cons+cons2 > 0)
							addFin.add("+" + (cons+cons2)+"x");
						else if (cons + cons2 < 0)
							addFin.add((cons+cons2)+"x");
					}
					else if (highVar == 0){
						if (cons+cons2 > 0)
							addFin.add("+" + (cons+cons2));
						else if (cons + cons2 < 0)
							addFin.add(Integer.toString(cons+cons2));
					}
					b.remove(j);
				}
			}
			if (tempSize == b.size()){
				int num = 0;
				if (b.get(i).contains("x"))
					num = Integer.parseInt(b.get(i).substring(0, b.get(i).indexOf("x")));
				else
					num = Integer.parseInt(b.get(i));

				if (num < 0)
					addFin.add(b.get(i));
				else if (num > 0)
					addFin.add("+" + num);
			}
		}
		System.out.println(addFin + "\n");

		String output = "";
		for(int i = 0; i < addFin.size(); i++)
			output += addFin.get(i);

		System.out.println(output);
	}

	public static void main(String[] args){
		new PolynomialCalculator();
	}
}