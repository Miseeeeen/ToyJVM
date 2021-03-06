package instructions.stack;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Slot;

public class DUP extends NoOperandsInstruction {

    public void execute(Frame frame) {
        Slot slot = frame.operandStack().popSlot();
        frame.operandStack().pushSlot(slot.clone());
        frame.operandStack().pushSlot(slot.clone());
    }

}
