package roads.model;

import java.util.List;

public class Way {

    private List<Way> way;

    public Way(List<Way> way) {
        this.way = way;
    }

    public List<Way> getWay() {
        return way;
    }

    public void setWay(List<Way> way) {
        this.way = way;
    }
}
