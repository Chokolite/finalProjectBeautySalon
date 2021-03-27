package ua.kharkiv.syvolotskyi.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CountPagesTag extends SimpleTagSupport {

    private int catalogSize;
    private int size;
    private String var;

    public void setVar(String var) {
        this.var = var;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCatalogSize(int catalogSize) {
        this.catalogSize = catalogSize;
    }

    @Override
    public void doTag() {
        int count = catalogSize / size + ((catalogSize % size) > 0 ? 1 : 0);
        getJspContext().setAttribute(var, count);
    }
}