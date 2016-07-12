package romanNumerals;

import java.util.LinkedList;
import java.util.List;

public class RomanModel {
	
	int currentArabNumberValue;
	StringBuilder resultingRomanString; 

	public String convertArabToRoman(int arabNumber) {
//		for(RomanLiterals literal : RomanLiterals.values()){
//			if(arabNumber == literal.getValue()) {
//				return literal.toString();
//			}
//		}
//		return null;
		resultingRomanString = new StringBuilder();
		currentArabNumberValue = arabNumber;
		return convertArabToRoman(currentArabNumberValue, resultingRomanString).toString();
	}
	
	private StringBuilder convertArabToRoman(Integer arabNumber, StringBuilder existingString){
		if(arabNumber == 0) {
			return existingString;
		}
		
		RomanLiterals literal = getNextLitteral(currentArabNumberValue, existingString);
//		existingString.append(literal.toString());
//		arabNumber -= literal.getValue();
		if(currentArabNumberValue > 0) {
			convertArabToRoman(currentArabNumberValue, existingString);
		}
		return existingString;
		
	}
	private void addLitteral(RomanLiterals literal) {
		this.currentArabNumberValue -= literal.getValue();
		this.resultingRomanString.append(literal.toString());
	}
	private RomanLiterals getNextLitteral(Integer arabNumber, StringBuilder existingString) {
		RomanLiterals[] values = RomanLiterals.values();
		for(int i =  values.length - 1; i >= 0; i-- ){
			int itValue = values[i].getValue(); 
			
			if(itValue == arabNumber && checkRepeatRule(values[i], existingString)){
				addLitteral(values[i]);
				return values[i];
			}
			else if(findSubtractionConstruct(values[i])){
				return null;
			
			}else if (itValue < arabNumber) {
					if(checkRepeatRule(values[i], existingString)){
						addLitteral(values[i]);
						return values[i];
					}
			}
			
		}
		throw new RuntimeException("Cannot find a matching number, error in code");
	}
	
//	private RomanLiterals lookFor

	private boolean findSubtractionConstruct(RomanLiterals baseLiterals) {
		try{
			for(RomanLiterals subtractor: baseLiterals.getReducableBy()){
				int differential = baseLiterals.getValue() - subtractor.getValue();
				if(this.currentArabNumberValue >= differential) {
					currentArabNumberValue -= differential;
					resultingRomanString.append(subtractor.toString() + baseLiterals.toString());
					return true;
				}
			}
			
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
			this.isSubtractable = subtractable;
		}
		public int getValue() {
			return value;
		}
		public List<RomanLiterals> getReducableBy() {
			List <RomanLiterals> result = new LinkedList<RomanLiterals>();
			try{
				if(RomanLiterals.values()[this.ordinal() - 1].isSubtractable) {
					result.add(RomanLiterals.values()[this.ordinal() - 1]);
				}
				if(RomanLiterals.values()[this.ordinal() - 2].isSubtractable) {
					result.add(RomanLiterals.values()[this.ordinal() - 2]);
				}
			}catch(IndexOutOfBoundsException e){}
			return result;
			
		}
	}
}
