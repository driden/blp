package ort.assi.blp.secure;

import ort.assi.blp.context.ReceiveContext;
import ort.assi.blp.context.TransferContext;
import ort.assi.blp.covertchannel.CovertChannel;
import ort.assi.blp.covertchannel.SequenceHandler;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.*;

import java.util.HashMap;

public class SecureSystem {
    private final ReferenceMonitor referenceMonitor;

    private final HashMap<String, SysSubject> subjects = new HashMap<>();
    private final SequenceHandler sequenceHandler;

    public SecureSystem(SequenceHandler sequenceHandler) {
        referenceMonitor = new ReferenceMonitor();
        this.sequenceHandler = sequenceHandler;
    }

    public SysSubject createSubject(String name, SecurityLevel clearance) {
        SysSubject subj = new SysSubject(name, clearance);
        this.subjects.put(name, subj);
        this.referenceMonitor.addSubject(subj);
        return subj;
    }

    public SysSubject getSubject(String subjectName) {
        return subjects.get(subjectName);
    }

    public void run(ReceiveContext receiveContext, TransferContext transferContext) {
        var hal = getSubject("hal");
        var lyle = getSubject("lyle");

        var covertChannel = new CovertChannel(hal, lyle, sequenceHandler, referenceMonitor);
        covertChannel.syncAndTransmitData(receiveContext, transferContext);
    }

}
