package apackage.cs407;

public class Scores {

    private int _id;
    private int _score;
    private String _name;

    public Scores(int id, int score, String name) {
        this._id = id;
        this._score = score;
        this._name = name;
    }

    public Scores(int score, String name) {
        this._score = score;
        this._name = name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_id() {
        return _id;
    }

    public int get_score() {
        return _score;
    }

    public String get_name() {
        return _name;
    }
}
