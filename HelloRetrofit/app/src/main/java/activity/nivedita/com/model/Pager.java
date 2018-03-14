package activity.nivedita.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by NEETU on 10-03-2018.
 */

public class Pager {

    private int page;
    private List<Result> resultList;

    @Inject
    public Pager() {

        this.resultList = new ArrayList<>();
        this.page = 1;

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public int getItemCount() {
        return resultList.size();
    }

    public void updateItemList(List<Result> resultList) {
        if (this.resultList != null && !resultList.isEmpty()) {
            this.resultList.addAll(resultList);
        } else {
            this.resultList = resultList;
        }
        this.page++;
    }



}
