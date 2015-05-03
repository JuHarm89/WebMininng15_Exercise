package webmining;

public class Word {
	public String word;
	public int amount;
	public double rAmount;
	
	public Word(String word, int amount, double rAmount){	
		this.word = word;
		this.amount = amount;
		this.rAmount = rAmount;
	}
	
	public String getWord(){
		return word;
	}
	
	public Integer getAmount(){
		return amount;
	}
	
	public Double getRAmount(){
		return rAmount;
	}
	
	public void setWord(String value){
		this.word = value;
	}
	
	public void setAmount(Integer value){
		this.amount = value;
	}
	
	public void setRAmount(Double value){
		this.rAmount = value;
	}
	
	@Override
	public String toString(){
		return word + " - " + amount + " - " + rAmount + "%";
	}
}
