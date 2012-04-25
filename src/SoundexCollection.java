import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 


public class SoundexCollection
{

	private Map<String, List<String>> soundexHash = new HashMap<String, List<String>>();
	private static SoundexCollection shared_object = new SoundexCollection();

	
	private SoundexCollection()
	{
		
		
	}
	
	public static SoundexCollection getInstance()
	{
		return shared_object;
	}
	
	
	public void set(String hashKey, String keyword)
	{
		if (soundexHash.containsKey(hashKey))
		{

			List<String> existKeywordList = soundexHash.get(hashKey);
			existKeywordList.add(keyword);
			
			
		}
		else
		{

			List<String> keywordList = new ArrayList<String>();
			keywordList.add(keyword);
			soundexHash.put(hashKey, keywordList);

		}

	}
	
	public int getCountOfKeyword(String hashKey)
	{
		

		List<String> existKeywordList = soundexHash.get(hashKey);
		return existKeywordList.size();

	}

	public List<String> get(String soundHashKey)
	{

		List<String> existkeywordList = Collections.emptyList();

		if (soundexHash.containsKey(soundHashKey))
		{
			existkeywordList = soundexHash.get(soundHashKey);
		}

		return existkeywordList;
	}

	public int count()
	{
		return this.soundexHash.size();
		
	}
	
	public void print()
	{
		for(String key : soundexHash.keySet())
		{
			
			System.out.println("key : "+key);
		}
		
		 
	}

}
