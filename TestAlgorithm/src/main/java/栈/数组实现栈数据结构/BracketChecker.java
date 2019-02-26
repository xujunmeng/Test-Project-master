package 栈.数组实现栈数据结构;

/**
 * Created by jgsoft on 2019/2/23.
 */
public class BracketChecker {

    private String input;

    public BracketChecker(String in) {
        input = in;
    }

    /**
     * 检查分隔符是否匹配
     */
    public void check() {
        int strLength = input.length();
        StackX stackX = new StackX(strLength);

        //"Error: " + ch + " at " + " i

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stackX.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (stackX.isNotEmpty()) {
                        char chx = (char)stackX.pop();
                        if ((ch == '}' && chx != '{') ||
                                (ch == ']' && chx != '[') ||
                                (ch == ')' && chx != '(')) {
                            System.out.println("Error: " + ch + " at " + i);
                        }
                    }
            }
        }
    }

}
