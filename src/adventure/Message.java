package adventure;

import java.util.Arrays;

public class Message {
    
    // parser messages
    
    public static String parseEmptyMessage() {
	return "Did you just say something?";
    }
    
    public static String parseUndefinedWordMessage(String word) {
	return "The word '" + word + "' is not defined.";
    }
    
    public static String parseUndefinedWordsMessage(String... words) {
	StringBuilder sb = new StringBuilder();
	sb.append("The follwoing words are not defined: ");
	Arrays.stream(words)
		.forEach( word -> sb.append(word + " ") );
	return sb.toString();
    }
    
    public static String parseUnknownVerbMessage(String verb) {
	return "The word '" + verb + "' is not defined as a verb.";
    }
    
    public static String parseUnknownNounMessage(String noun, String phrase) {
	return "I expected '" + phrase + "' to be a game object, but '" + noun + "' is not defined as a noun.";
    }

    public static String parseUnknownAdjectiveMessage(String adjective, String phrase) {
	return "I expected '" + phrase + "' to be a game object, but '" + adjective + "' is not defined as an adjective.";
    }
        
    public static String parseVerbPatternMessage(String verb, String... patterns) {
	StringBuilder sb = new StringBuilder();
	sb.append("The verb " + verb + " only works with the following patterns: <br>");
	Arrays.stream(patterns)
		.forEach( pattern -> sb.append(pattern + "<br>") );
	return sb.toString();
    }
    
    public static String parseUnknownPhraseMessage(String phrase) {
	return "The phrase '" + phrase + "' is not associated with any object in this game.";
    }
    
    
}
