package maquinas;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import com.github.britooo.looca.api.group.dispositivos.DispositivosUsbGrupo;

import java.awt.*;
import java.util.List;

public class USB {
    Looca looca = new Looca();
    DispositivosUsbGrupo usbs = new DispositivosUsbGrupo();

    private Integer totalConectados;
    private List<DispositivoUsb> dispositivosUsb;

    public USB() {
        this.totalConectados= usbs.getTotalDispositvosUsbConectados();
        this.dispositivosUsb = usbs.getDispositivosUsb();
    }

    @Override
    public String toString() {
        return "USB{" +
                "total de USBs Conectados=" + totalConectados +
                ", Listas de dispositivos Usb=" + dispositivosUsb +
                '}';
    }
}