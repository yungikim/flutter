/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package chatGPT;

import com.github.plexpt.chatgpt.Chatbot;

public class Library {
	
	public static void main(String[] args) {
		Library ll = new Library();
		ll.chat();
	}
	
	private void chat() {
		Chatbot chatbot = new Chatbot("", "cf_clearance", "user-agent");
	}
	
    public boolean someLibraryMethod() {
        return true;
    }
}
