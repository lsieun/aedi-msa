import utils.IdeaUtils;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Usage:" + System.lineSeparator());
        sb.append("1. java -jar Inability-2019.02.jar <path/to/idea.jar>" + System.lineSeparator());
        sb.append("2. jar -uvf idea.jar com/" + System.lineSeparator());
        sb.append("3. Input License Server: http://In.God.We.Trust" + System.lineSeparator());

        System.out.println(sb.toString());

        if (args == null || args.length < 1) {
            return;
        }

        IdeaUtils.process(args[0]);
    }
}
