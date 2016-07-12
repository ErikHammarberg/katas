package romanNumerals;

public class RomanModel {

	public String convertArabToRoman(int arabNumber) {
//		for(RomanLiterals literal : RomanLiterals.values()){
//			if(arabNumber == literal.getValue()) {
//				return literal.toString();
//			}
//		}
//		return null;
		return convertArabToRoman(arabNumber, new StringBuilder()).toString();
	}
	
	private StringBuilder convertArabToRoman(Integer arabNumber, StringBuilder existingString){
		if(arabNumber == 0) {
			return existingString;
		}
		
		RomanLiterals literal = getNextLitteral(arabNumber, existingString);
		existingString.append(literal.toString());
		arabNumber -= literal.getValue();
		if(arabNumber > 0) {
			convertArabToRoman(arabNumber, existingString);
		}
		return existingString;
		
	}
	
	private RomanLiterals getNextLitteral(Integer arabNumber, StringBuilder existingString) {
		RomanLiterals[] values = RomanLiterals.values();
		for(int i =  values.length - 1; i >= 0; i-- ){
			int itValue = values[i].getValue(); 
			
			/*if(itValue == arabNumber){
				return values[i];
			}
			else*/ if(findSubtractionConstruct(arabNumber, existingString, values[i])){
				
			
			}else if (itValue <= arabNumber) {
					if(checkRepeatRule(values[i], existingString)){
						return values[i];
					}
			}
			
		}
		return null;
	}
	
//	private RomanLiterals lookFor

	private boolean findSubtractionConstruct(Integer arabNumber, StringBuilder existingString,
			RomanLiterals romanLiterals) {
		try{
			
		}catch(RuntimeException e){}
		return false;
	}

	private boolean checkRepeatRule(RomanLiterals romanLiteral, StringBuilder existingString) {
		int length = existingString.length();
		if(length == 0) {
			return true;
		}
		if(romanLiteral.isRepeatable){
			
//			int itStop = length - 4;
			
			for(int i = length - 1 ; i > length - 4; i--  ) {
				if(/* i == itStop ||*/ (i < 0) || (!romanLiteral.toString().equals(""+existingString.charAt(i)))){
					return true;
				}
			}
		} else {
			
		}
		return false;
	}

	enum RomanLiterals {
		I(1, true, true),
		V(5, false, false);
		private int value;
		private boolean isRepeatable;
		private boolean isSubtractable;
		RomanLiterals (int value, boolean repeatable, boolean subtractable) {
			this.value = value;
			this.isRepeatable = repeatable;
		}
		public int getValue() {
			return value;
		}
	}
}
