package wiev;

import store.WoodDirectory;

public interface IWoodDialog {
    public void setVisible(boolean b);
    public Object getObject();
    public void setWoodDirectory(WoodDirectory wd);
    public String toString();
}
