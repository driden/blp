package ort.assi.blp.covertchannel;

import ort.assi.blp.context.ReceiveContext;
import ort.assi.blp.context.TransferContext;
import ort.assi.blp.entities.SysObject;
import ort.assi.blp.entities.SysSubject;
import ort.assi.blp.io.instruction.*;
import ort.assi.blp.secure.ReferenceMonitor;

public class CovertChannel {

    public static final String SYNC_OBJ = "objSync";
    public static final String TRANSFER_OBJ = "obj";

    private final SysSubject higherLevel;
    private final SysSubject lowerLevel;
    private final SequenceHandler sequenceHandler;
    private final ReferenceMonitor referenceMonitor;

    public CovertChannel(
            SysSubject higherLevel,
            SysSubject loweLevel,
            SequenceHandler sequenceHandler,
            ReferenceMonitor referenceMonitor) {

        this.higherLevel = higherLevel;
        this.lowerLevel = loweLevel;
        this.sequenceHandler = sequenceHandler;
        this.referenceMonitor = referenceMonitor;

    }

    public void syncAndTransmitData(ReceiveContext receiveContext, TransferContext transferContext) {
        setUpSubjects(receiveContext, transferContext);
        char c;
        var moe = referenceMonitor.getSubject("moe");
        while ((c = sequenceHandler.getNextSubject()) != ' ') {
            switch (c) {
                case 'H':
                    higherLevel.setCanAct(canHigherLevelSendData());
                    if (higherLevel.execute() == 1) {
                        referenceMonitor.executeInstruction(
                                new DestroyInstruction(higherLevel, new SysObject(SYNC_OBJ)));
                    }
                    break;
                case 'L':
                    lowerLevel.setCanAct(canLowerLevelReceiveData());
                    lowerLevel.execute();
                    break;
                case 'M':
                    referenceMonitor.executeInstruction(
                            new WriteInstruction(moe, new SysObject(TRANSFER_OBJ),9));
                    referenceMonitor.executeInstruction(new RunInstruction(moe));

                default:
            }
        }
    }

    private Boolean canHigherLevelSendData() {
        return 1 == referenceMonitor.executeInstruction(
                new CreateInstruction(higherLevel, new SysObject(SYNC_OBJ)));
    }

    private Boolean canLowerLevelReceiveData() {

        return 1 == referenceMonitor.executeInstruction(
                new DestroyInstruction(lowerLevel, new SysObject(SYNC_OBJ)));
    }

    private void setUpSubjects(ReceiveContext receiveContext, TransferContext transferContext) {

        lowerLevel.setFunction(() -> {
            if (!lowerLevel.getCanAct()) return 0;
            var createResult = referenceMonitor.executeInstruction(
                    new CreateInstruction(lowerLevel, new SysObject(TRANSFER_OBJ)));
            var bit = createResult == 1;
            receiveContext.receive(bit);
            referenceMonitor.executeInstruction(new WriteInstruction(lowerLevel, new SysObject(TRANSFER_OBJ), 1));
            referenceMonitor.executeInstruction(new ReadInstruction(lowerLevel, new SysObject(TRANSFER_OBJ)));
            referenceMonitor.executeInstruction(new DestroyInstruction(lowerLevel, new SysObject(TRANSFER_OBJ)));
            referenceMonitor.executeInstruction(new RunInstruction(lowerLevel));
            return 1;
        });

        higherLevel.setFunction(() -> {
            if (!higherLevel.getCanAct()) return 0;
            if (!transferContext.hasNext()) return 1;
            referenceMonitor.executeInstruction(new RunInstruction(higherLevel));
            if (!transferContext.getNext()) {
                referenceMonitor.executeInstruction(
                        new CreateInstruction(higherLevel, new SysObject(TRANSFER_OBJ)));
            }

            return 0;
        });

    }
}
