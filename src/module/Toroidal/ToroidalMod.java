package module.Toroidal;

import module.ModuleAbstractClass;
import module.ModuleUI;
import framework.Packet;
import framework.Port;

public class ToroidalMod extends ModuleAbstractClass {
    private static int DIMENSIONS = 2;
    private static int MAXPORTS = DIMENSIONS * 2;
    volatile boolean canEmitData = false;
    int noOfPorts = 0;
    double elapsedTime = 0;
    double absoluteTime = 0;
    private boolean newPacket = false;
    private Port ports[];
    private int serialNo = 0;
    private final String name = "ToroidalNode";
    final double LATENCY = 0;
    private ModuleUI modUI = null;

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
        Packet packet;
        absoluteTime++;
        if (canEmitData) {
            for (int i = 0; i < noOfPorts; i++) {
                // System.out.println(name+serialNo+" "+absoluteTime+" "+i+" "+ports[i].isActive()
                // +" "+ ports[i].hasData());
                if (ports[i].isActive() && ports[i].hasData()
                        && !ports[0].isTransmiting()) {
                    packet = ports[i].getPacket(this);
                    if (packet.getToId() == 1) {
                        System.out.println("Packet received at node "
                                + serialNo);
                        if (!packet.isCorrupt() && packet.getData() != null) {
                            System.out.println("from " + packet.getFromId()
                                    + " with data " + packet.getData() + "to "
                                    + packet.getToId());
                        } else {
                            System.out.println("from " + packet.getFromId()
                                    + " but packet is CORRUPTED.");
                        }
                        // else {
                        // for(int j=0;j<MAXPORTS;j++){
                        // if(i==j) continue;
                        // ports[j].putPacket(new Packet(packet), this);
                        // }
                        // }
                    }
                }
            }
        }
        if (absoluteTime % 25 == 0 && canEmitData && noOfPorts != 0
                && !ports[0].isTransmiting()) {
            // System.out.println("pcppcpc ,,,,,,,,,, "+ports.length);
            for (int i = 0; i < noOfPorts; i++) {
                ports[i].putPacket(new Packet((int) (Math.random() * 10),
                        serialNo, 10, name + serialNo + absoluteTime), this);
                ports[i].setActive(true);
                System.out
                        .println("Put packet on port " + i + "   " + serialNo);
                newPacket = true;
            }
        }

        return true;
    }
}
