package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.startsWith("//")){
			return sum(splitNumbersNewDelim(text));
		}
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}



	private static String[] splitNumbers(String numbers){
	    return numbers.split("[,|\\\n,]");
	}

	private static String[] splitNumbersNewDelim(String numbers){
		int index = numbers.indexOf('\n');
		String newDelim = numbers.substring(2, index);
		numbers = numbers.substring(index + 1);
		return numbers.split(newDelim);
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