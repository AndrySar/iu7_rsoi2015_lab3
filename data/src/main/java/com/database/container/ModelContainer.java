package com.database.container;

import com.database.logic.Mark;

import java.util.List;

/**
 * Created by user on 26.12.15.
 */
public class ModelContainer {

    private List<Mark> mark;
    private int page;
    private int pages;
    private int per_page;
    private int total;

    public ModelContainer(List<Mark> _mark,
                   int _page,
                   int _pages,
                   int _per_page,
                   int _total)
    {
        mark = _mark;
        page = _page;
        pages = _pages;
        per_page = _per_page;
        total = _total;
    }

    public void setMarks(List<Mark> marks) { mark = marks;}

    public void setPage(int _page){ page = _page; }

    public void setPages(int _pages){ pages = _pages; }

    public void setPerPage(int _per_page){ per_page = _per_page; }

    public void setTotal(int _total){ total = _total; }

    public List<Mark> getMarks(){ return mark; }

    public int getPage(){ return page; }

    public int getPages(){ return pages; }

    public int getPer_page(){ return per_page; }

    public int getTotal(){ return total; }

}
