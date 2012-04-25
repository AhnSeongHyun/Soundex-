import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main
{
	public static void main(String[] args) throws IOException
	{

		InputStreamReader br = new InputStreamReader(System.in);
		BufferedReader bReader = new BufferedReader(br);

 
		SoundExtractor se = new SoundExtractor(makeSoundexRules());
		
		String dicPath = null; 
		
		if(args.length == 1)
		{
			dicPath = args[0]; 
		}	
		else
		{
			dicPath = "./resource/verb.pl";
		 
		}
		
 
		RegisterDictionary(dicPath, se);
		 
		SoundexCollection soundCollection = SoundexCollection.getInstance();
		
		for (;;)
		{
			System.out.print(">>");
			String inputKeyword = bReader.readLine();

			if (inputKeyword == null || inputKeyword.length()<1)
			{
				
				continue;

			}
			else
			{

				if (inputKeyword.equals("q"))
				{
					System.out.println("EXIT");
					break;
				}
				else
				{
					String soundHashKey = se.extractSound(inputKeyword);

					List<String> resultKeyword = soundCollection.get(soundHashKey);

					if (resultKeyword.size() != 0)
					{
						for (String keyword : resultKeyword)
						{

							System.out.println("Keyword : " + keyword);
						}
					}
					else
					{
						System.out.println("no keywrod");
					}

				}
			}
		}

	}

	private static List<SoundexRule> makeSoundexRules()
	{

		List<SoundexRule> soundexRules = new ArrayList<SoundexRule>();

		List<String> vowels = makeSoundexVowelRule();
		SoundexRule vowelsRule = new SoundexRule(vowels, 0);
		soundexRules.add(vowelsRule);

		List<String> consonantBFPVs = makeSoundexConsonantBFPVRule();
		SoundexRule consonantBFPVRule = new SoundexRule(consonantBFPVs, 1);
		soundexRules.add(consonantBFPVRule);

		List<String> consonantCZs = makeSoundexConsonantCZRule();
		SoundexRule consonantCZRule = new SoundexRule(consonantCZs, 2);
		soundexRules.add(consonantCZRule);

		List<String> consonantDTs = makeSoundexConsonantDTRule();
		SoundexRule consonantDTRule = new SoundexRule(consonantDTs, 3);
		soundexRules.add(consonantDTRule);

		List<String> consonantLs = makeSoundexConsonantLRule();
		SoundexRule consonantLRule = new SoundexRule(consonantLs, 4);
		soundexRules.add(consonantLRule);

		List<String> consonantMNs = makeSoundexConsonantMNRule();
		SoundexRule consonantMNRule = new SoundexRule(consonantMNs, 5);
		soundexRules.add(consonantMNRule);

		List<String> consonantRs = makeSoundexConsonantRRule();
		SoundexRule consonantRRule = new SoundexRule(consonantRs, 6);
		soundexRules.add(consonantRRule);

		return soundexRules;
	}

	private static List<String> makeSoundexVowelRule()
	{

		List<String> vowels = new ArrayList<String>();

		vowels.add("A");
		vowels.add("E");
		vowels.add("I");
		vowels.add("O");
		vowels.add("U");
		vowels.add("H");
		vowels.add("W");
		vowels.add("Y");

		return vowels;

	}

	private static List<String> makeSoundexConsonantBFPVRule()
	{

		List<String> consonantBFPV = new ArrayList<String>();

		consonantBFPV.add("B");
		consonantBFPV.add("F");
		consonantBFPV.add("P");
		consonantBFPV.add("V");

		return consonantBFPV;

	}

	private static List<String> makeSoundexConsonantCZRule()
	{

		List<String> consonantCZ = new ArrayList<String>();

		consonantCZ.add("C");
		consonantCZ.add("G");
		consonantCZ.add("J");
		consonantCZ.add("K");
		consonantCZ.add("Q");
		consonantCZ.add("S");
		consonantCZ.add("X");
		consonantCZ.add("Z");

		return consonantCZ;

	}

	private static List<String> makeSoundexConsonantDTRule()
	{

		List<String> consonantDT = new ArrayList<String>();

		consonantDT.add("D");
		consonantDT.add("T");

		return consonantDT;

	}

	private static List<String> makeSoundexConsonantLRule()
	{

		List<String> consonantL = new ArrayList<String>();

		consonantL.add("L");

		return consonantL;

	}

	private static List<String> makeSoundexConsonantMNRule()
	{

		List<String> consonantMN = new ArrayList<String>();

		consonantMN.add("M");
		consonantMN.add("N");

		return consonantMN;

	}

	private static List<String> makeSoundexConsonantRRule()
	{

		List<String> consonantR = new ArrayList<String>();

		consonantR.add("R");

		return consonantR;

	}

	private static void RegisterDictionary(String dicPath, SoundExtractor se)
	{
		SoundexCollection soundCollection = SoundexCollection.getInstance();
		KeywordDictionaryReader dicReader = new KeywordDictionaryReader();

		List<String> dicKeyword = dicReader.ReadWordFromDictionary(dicPath);

		for (String keyword : dicKeyword)
		{
			String soundHashKey = se.extractSound(keyword);

			soundCollection.set(soundHashKey, keyword);
		}

		System.out.println(soundCollection.count() + " keywrods loaded...");
	}

}
