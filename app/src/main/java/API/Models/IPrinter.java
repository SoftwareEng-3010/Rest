package API.Models;

import API.IOrderListener;

public interface IPrinter extends IOrderListener {
    public void print();
    public void setLocation(int location);
    public int getLocation();
}
