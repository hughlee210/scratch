package com.hlee.scratch;

public class RansomNote {

    public static void main(String[] args) {

        String article = "jfjdkgjdfkdfifjdkfdve _fdmgge_ jjkhklolnlej @vm vifllfionv vdfodlddcdlar!";
        String ransomNote = "give me one _million_ dollar!@";

        boolean result = isRansomNotePossible(ransomNote, article);
        System.out.println("[" + ransomNote + "] can be created from [" + article + "]:\n" + result);
    }

    // case sensitive: g is different from G 
    // Time complexity: O(N+M), Space: O(1), O(N)
    static boolean isRansomNotePossible(String ransomNote, String article) {

        if (ransomNote == null || ransomNote.length() == 0 || article == null || article.length() == 0) {
            return false;
        }

        int[] charCount = new int[256]; // assuming ascii character set

        for (int i = 0; i < article.length(); i++) {
            charCount[(int) article.charAt(i)]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (charCount[(int) ransomNote.charAt(i)]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
