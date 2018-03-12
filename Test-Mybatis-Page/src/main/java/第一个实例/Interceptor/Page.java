package 第一个实例.Interceptor;

/**
 * Created by james on 2017/7/14.
 */
public class Page {

    private int startLine;
    private int pageSize;

    public Page() {
    }

    public Page(int startLine, int pageSize) {
        this.startLine = startLine;
        this.pageSize = pageSize;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
