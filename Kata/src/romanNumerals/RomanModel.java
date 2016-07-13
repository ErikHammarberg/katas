package romanNumerals;

import java.util.LinkedList;
import java.util.List;

public class RomanModel {
	
	int currentArabNumberValue;
	StringBuilder resultingRomanString; 

	public String convertArabToRoman(int arabNumber) {
		resultingRomanString = new StringBuilder();
		currentArabNumberValue = arabNumber;
		convertArabToRoman();
		System.out.println("Looking for: " + arabNumber + "\nResulting String" + resultingRomanString);
		return resultingRomanString.toString();
	}
	
	private void convertArabToRoman(){
		if(this.currentArabNumberValue == 0) {
			return;
		}
		addNextLitteral();
		convertArabToRoman();
	}
	private void addNextLitteral() {
		RomanLiterals[] values = RomanLiterals.values();
		for(int i =  values.length - 1; i >= 0; i-- ){
			int itValue = values[i].getValue(); 
			
			if(itValue <= currentArabNumberValue && checkRepeatRule(values[i])){
				addLitteral(values[i]);
				return;
			}
			else if(findSubtractionConstruct(values[i])){
				return;
			
			}else if (itValue < currentArabNumberValue) {
					if(checkRepeatRule(values[i])){
						addLitteral(values[i]);
						return;
					}
			}
		}
		throw new RuntimeException("Cannot find a matching number, error in code");
	}
	
	private void addLitteral(RomanLiterals literal) {
		this.currentArabNumberValue -= literal.getValue();
		this.resultingRomanString.append(literal.toString());
	}
	
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

	private boolean checkRepeatRule(RomanLiterals romanLiteral) {
		int length = this.resultingRomanString.length();
		if(length == 0) {
			return true;
		}
		if(romanLiteral.isRepeatable){
			for(int i = length - 1 ; i > length - 4; i--  ) {
				if((i < 0) || (!romanLiteral.toString().equals(""+this.resultingRomanString.charAt(i)))){
					return true;
				}
			}
		}
		return false;
	}

	enum RomanLiterals {
		I(1, true, true),
		V(5, false, false),
		X(10,true, true),
		L(50, false, false),
		C(100, true, true),
		D(500, false, false),
		M(1000, true, true);
		
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
				for (int i = 1; i <= 2; i++) {
					if (RomanLiterals.values()[this.ordinal() - i].isSubtractable) {
						result.add(RomanLiterals.values()[this.ordinal() - i]);
					}
				}
			} catch (IndexOutOfBoundsException e) {
			}
			return result;

		}
	}
}
