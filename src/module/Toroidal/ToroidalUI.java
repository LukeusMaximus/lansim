package module.Toroidal;

import module.DataUI;
import module.ModuleUI;
import framework.Packet;

public class ToroidalUI extends ModuleUI {
    public ToroidalUI(ToroidalMod m) {
    super(m);
    }
    
    protected DataUI getNewDataUI(Packet p) {
    DataUI dui = super.getNewDataUI(p);
    dui.setDisplayString(p.getFromId() + "->" + p.getToId());
    return dui;
    }

}
