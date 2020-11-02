package DataLayer;

import java.util.List;

// det her giver vel nok mening ikke?
public interface IScoreLogger {
    public void save(List<String> data);
    public List<String> load();
}
