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
	
	private StringBuilder convertArabToRoman(int arabNumber, StringBuilder existingString){
		if(arabNumber == 0) {
			return existingString;
		}
		
		RomanLiterals literal = getNextLitteral(arabNumber, existingString);
		existingString.append(literal.toString());
		int newArabValue = arabNumber - literal.getValue();
		if(newArabValue > 0) {
			convertArabToRoman(newArabValue, existingString);
		}
		return existingString;
		
	}
	
	private RomanLiterals getNextLitteral(int arabNumber, StringBuilder existingString) {
		RomanLiterals[] values = RomanLiterals.values();
		for(int i =  values.length - 1; i >= 0; i-- ){
			int itValue = values[i].getValue(); 
			if(itValue == arabNumber){
				return values[i];
			}
			else {
				if (itValue < arabNumber) {
					if(checkRepeatRule(values[i], existingString)){
						return values[i];
					}
				}
			}
		}
		return null;
	}

	private boolean checkRepeatRule(RomanLiterals romanLiterals, StringBuilder existingString) {
		// TODO Auto-generated method stub
		return true;
	}

	enum RomanLiterals {
		I(1, true),
		V(5, true);
		private int value;
		private boolean isRepeatable;
		RomanLiterals (int value, boolean repeatable) {
			this.value = value;
			this.isRepeatable = repeatable;
		}
		public int getValue() {
			return value;
		}
	}
}
