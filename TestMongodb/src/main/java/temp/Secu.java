package temp;

import 基本操作.BaseObject;

/**
 * Created by junmeng.xu on 2017/1/4.
 */
public class Secu extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String cd;

    private String org;

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "Secu{" +
                "cd='" + cd + '\'' +
                ", org='" + org + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Secu secu = (Secu) o;

        if (cd != null ? !cd.equals(secu.cd) : secu.cd != null) return false;
        return !(org != null ? !org.equals(secu.org) : secu.org != null);

    }

    @Override
    public int hashCode() {
        int result = cd != null ? cd.hashCode() : 0;
        result = 31 * result + (org != null ? org.hashCode() : 0);
        return result;
    }
}
