package module.Toroidal;

import module.ModuleAbstractClass;
import module.ModuleUI;
import framework.Port;

public class ToroidalMod extends ModuleAbstractClass {
    private static int DIMENSIONS = 2;
    private static int MAXPORTS = DIMENSIONS * 2;
    volatile boolean     canEmitData  = false;
    int       noOfPorts    = 0;
    double         elapsedTime  = 0;
    double         absoluteTime = 0;
    private boolean      newPacket    = false;
    private Port     ports[];
    private int   serialNo     = 0;
    private final String name    = "ToroidalNode";
    final double     LATENCY      = 0;
    private ModuleUI     modUI  = null;
            
    public int addPort(Port port, int wireType) {
    if (noOfPorts < MAXPORTS) {
        port.setActive(true);
        ports[noOfPorts++] = port;
        System.out.println("ADDED port " + noOfPorts);
        return 1;
    } else
        return ERROR_ALREADY_MAX_PORTS;
    }

    public ModuleUI getModuleUI() {
        return modUI;
    }

    public String getName() {
        return name;
    }

    public int getNoOfPorts() {
    return noOfPorts;
    }

    public Port[] getPorts() {
    return ports;
    }

    public int getSno() {
    return serialNo;
    }
    
    public void setModuleUI(ModuleUI m) {
        modUI = m;
    }

    public void setSno(int n) {
        serialNo = n;
    }

    public boolean reset() {
        elapsedTime = 0;
        absoluteTime = 0;
        return true;
    }
    
    public boolean isNewPacket() {
        if (newPacket == true) {
            newPacket = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean step(double TimeStep) {
    // TODO Auto-generated method stub
    return false;
    }
}
