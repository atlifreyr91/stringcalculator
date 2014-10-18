package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		// If string is empty
		if(text.equals("")){
			return 0;
		}
		// If string contains 
		else if(text.contains("][")){
			return sum(splitNumbersMultyDelim(text));
		}
		else if(text.startsWith("//")){
			return sum(splitNumbersNewDelim(text));
		}
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		else
			return toInt(text);
	}


	private static String[] splitNumbers(String numbers){
	    return numbers.split("[,|\\\n]");
	}

	private static String[] splitNumbersNewDelim(String numbers){
		int index = numbers.indexOf('\n');
		String newDelim = numbers.substring(2, index);
		numbers = numbers.substring(index + 1);
 		return numbers.split(newDelim);
	}

	private static String[] splitNumbersMultyDelim(String numbers){
		int end = numbers.indexOf('\n');           //newLineIndex
		int begin = numbers.indexOf(']');           //FirstDeliminatorIndex
		String delimnators = numbers.substring(2, begin);   // [*

		for(int i = begin; i < end; i++){
			if(numbers.charAt(i) == '['){
				delimnators += "|\\" + numbers.substring(i + 1, numbers.indexOf(']', i));
			}
		}

		delimnators += "]";
		numbers = numbers.substring(end + 1);
		return numbers.split(delimnators);
	}

	private static int toInt(String number){
			return Integer.parseInt(number);
	}

    private static int sum(String[] numbers){
 	    int total = 0;
 	    int n = 0;
 	    String NegNumbers = "";
        for(String number : numbers){
        	if(number.equals("")){ continue; }
		        n = toInt(number);
		       	if(n <= 1000){
		       		if(n > 0){ total += n; }
		       		else{ NegNumbers += number + ","; }
		    }
		}
		if(!NegNumbers.isEmpty()){
			throw new RuntimeException("Negatives not allowed: " + NegNumbers.substring(0, (NegNumbers.length() - 1)));
		}
		return total;
    }

}