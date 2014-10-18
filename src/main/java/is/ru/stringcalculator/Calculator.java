package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		// If string is empty
		if(text.equals("")){
			return 0;
		}
		// If string contains many custom deliminators of any length
		else if(text.contains("][")){
			return sum(splitNumbersMultyDelim(text));
		}
		// If string contains one custom deliminator of any length
		else if(text.startsWith("//")){
			return sum(splitNumbersNewDelim(text));
		}
		// If string dosen´t contain any custom deliminators; standard (, and \n)
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		// If string only contains one number
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
		int end = numbers.indexOf('\n');                    //newLineIndex
		int begin = numbers.indexOf(']');                   //FirstDeliminatorIndex
		String delimnators = numbers.substring(2, begin);   //The deliminator now contains = [*

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
    	String NegNumbers = "";
 	    int total = 0;
 	    int n = 0;
        for(String number : numbers){
        	if(number.equals("")){ continue; }  // When you split your string with deliminator that is more than one char you also get an empty string ? This fixes that.
		        n = toInt(number);
		       	if(n <= 1000){
		       		if(n > 0) total += n; 
		       		else NegNumbers += number + ",";
		    }
		}
		if(!NegNumbers.isEmpty()){
			throw new RuntimeException("Negatives not allowed: " + NegNumbers.substring(0, (NegNumbers.length() - 1)));
		}
		return total;
    }

}