package instructions.stack;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.Slot;

public class DUP_X1 extends NoOperandsInstruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(slot1.clone());
        stack.pushSlot(slot2.clone());
        stack.pushSlot(slot1.clone());
    }

}
