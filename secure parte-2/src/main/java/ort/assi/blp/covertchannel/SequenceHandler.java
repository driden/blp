package ort.assi.blp.covertchannel;

public class SequenceHandler {
    private char[] sequence;
    private int index;

    public SequenceHandler(String sequence){
        this.sequence = sequence.toCharArray();
        this.index = -1;
    }

    public char getNextSubject(){
        if (index + 1 < sequence.length)
            return sequence[++index];
        return ' ';
    }
}
