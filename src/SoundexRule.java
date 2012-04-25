import java.util.ArrayList;
import java.util.List;


public class SoundexRule
{
	
	public SoundexRule(List<String> grams, int soundMark)
	{
		this.grams = grams; 
		this.soundMark = soundMark; 

	}

	

	public List<String> grams = new ArrayList<String>();
	
	public int soundMark; 
}
