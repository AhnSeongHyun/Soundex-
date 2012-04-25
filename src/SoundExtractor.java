
import java.util.Collections;
import java.util.List;

public class SoundExtractor
{ 
	
	List<SoundexRule> soundexRules = Collections.emptyList();

	public SoundExtractor(List<SoundexRule> soundexRules)
	{
		this.soundexRules = soundexRules;

	}

	public String extractSound(String term)
	{

		term = term.trim();
		char[] grams = term.toCharArray();

		 
		StringBuilder sb = new StringBuilder();
		sb.append(term.charAt(0));
		 
		for (int i = 1; i < grams.length; i++)
		{ 
			for (SoundexRule soundexRule : this.soundexRules)
			{  
				if(soundexRule.grams.contains(String.valueOf(grams[i]).toUpperCase()))
				{
					sb.append(soundexRule.soundMark);
					break;
					
				}
			}
		}
		
		return refineSound(sb.toString().toUpperCase());
 
	}
	
	private String refineSound(String soundCode)
	{
		
		StringBuilder sb = new StringBuilder();
		
		char[] codeGram = soundCode.toCharArray();
		
		char preGram = ' ';
		for(char gram : codeGram)
		{
			if(preGram != gram)
			{
			 
				sb.append(gram); 
			} 
			
			preGram = gram; 
		}
		
		
		return removeZero(sb);
		
	}
	
	private String removeZero(StringBuilder soundCode)
	{
	  	
		StringBuilder sb = soundCode; 
		
		for (;;)
		{

			int index = sb.indexOf("0");

			if (index == -1)
			{
				break;
			}
			else
			{
				sb.deleteCharAt(index);
			}
		}
		
		return sb.toString(); 
		
	}

}
