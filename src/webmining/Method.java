package webmining;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import webmining.Word;


public class Method {	
	
	public static void main(String args[]){
		Method method = new Method();
	
		ArrayList<String> stopWordList = new ArrayList<String>();
		stopWordList = method.createArrayListFromFile("../webmining/src/webmining/stopwords/german");		
		String text = method.createStringFromFile("../webmining/src/webmining/texts/text1");
		method.finalPrint(text, stopWordList);
		
	}
	
	public ArrayList<String> createArrayListFromFile(String url){
	    ArrayList<String> arr = new ArrayList<String>();
		try{
		    InputStream fis=new FileInputStream(url);
		    BufferedReader br=new BufferedReader(new InputStreamReader(fis));

		    for (String line = br.readLine(); line != null; line = br.readLine()) {
		       arr.add(line);
		    }

		    br.close();
		}
		catch(Exception e){
		    System.err.println("Error: Target File Cannot Be Read");
		}
		
		return arr;
	}
	
	public String createStringFromFile(String url){
	    String temp = "";
		try{
		    InputStream fis=new FileInputStream(url);
		    BufferedReader br=new BufferedReader(new InputStreamReader(fis));

		    for (String line = br.readLine(); line != null; line = br.readLine()) {
		       temp += line;
		    }

		    br.close();
		}
		catch(Exception e){
		    System.err.println("Error: Target File Cannot Be Read");
		}
		
		return temp;
	}
	
	//returns an ArrayList of words from text without stop words
	public ArrayList<String> createListFromText(String text, ArrayList<String> stopWords){	
		String[] a = text.split(" ");
		ArrayList<String> arr = new ArrayList(Arrays.asList(a));
		arr.removeAll(stopWords);
		return arr;
	}
	
	//counts a word in a list
	public int getAbsolut(ArrayList<String> list, String word){
		int temp = 0;
		for(int i=0; i<list.size(); i++){
			
			if(list.get(i).equals(word)){
				temp++;
			}
		}
		return temp;
	}
	
	public double getRelative(ArrayList<String> list, String word){
		double temp = 0.0;
		int absolut = getAbsolut(list, word);
		temp = (double)absolut/list.size();
		return temp;
	}
	
	public ArrayList<Word> sortedList(ArrayList<String> list){
		ArrayList<Word> arr = new ArrayList<Word>();
		for (String l: list){
			if(notInTheList(arr, l)){
				arr.add(new Word(l, getAbsolut(list, l), getRelative(list, l)));	
			}			
		}
		Collections.sort(arr, new CustomComparator());
		return arr;
	}
	
	boolean notInTheList(ArrayList<Word> list, String word){
		boolean temp=true;
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getWord().equalsIgnoreCase(word)){
				temp=false;
			}
		}
		
		return temp;
	}
	
	public void ausgabe(ArrayList<Word> liste){
		for(int i=0; i<liste.size(); i++){
			System.out.println(liste.get(i));	
		}
		
	}

	//final print
	public void finalPrint(String text, ArrayList<String> stop){
		ArrayList<String> list = createListFromText(text, stop);
		ArrayList<Word> arr = new ArrayList<Word>();
		arr = sortedList(list);
		ausgabe(arr);
	}
}


class CustomComparator implements Comparator<Word> {
    @Override
    public int compare(Word a, Word b) {
        return a.getAmount().compareTo(b.getAmount());
    }
}
