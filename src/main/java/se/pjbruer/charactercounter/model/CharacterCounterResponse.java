package se.pjbruer.charactercounter.model;

public class CharacterCounterResponse {

    private int result;

    public CharacterCounterResponse(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
