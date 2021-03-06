package classfile;

import classfile.constant.ConstantDoubleInfo;
import classfile.constant.ConstantFactory;
import classfile.constant.ConstantInfo;
import classfile.constant.ConstantLongInfo;
import classfile.constant.ConstantUtf8Info;

public class RawConstantPool {
    ConstantInfo[] constantInfos;

    public RawConstantPool(ClassReader reader) {
        int n = (int) reader.readU2();

        constantInfos = new ConstantInfo[n];

        /*
         * according to the spec, 0 is an invalid index long_info, double_info will cost two indexes
         */
        for (int i = 1; i < n; i++) {
            constantInfos[i] = ConstantFactory.newConstantInfo(reader, this);
            if (isDouble(constantInfos[i]) || isLong(constantInfos[i])) {
                i++;
            }
        }
    }

    public ConstantInfo getConstantInfo(long index) {
        return constantInfos[(int) index];
    }

    /**
     * 
     * @param index
     * @return the str form of constantUtf8
     */
    public String getUtf8(long index) {
        ConstantUtf8Info utf8 = (ConstantUtf8Info) getConstantInfo(index);
        return utf8.string();
    }

    public ConstantInfo[] constantInfos() {
        return constantInfos;
    }

    /*
     * public String getName(long index){
     * 
     * }
     * 
     * public String getType(long index){
     * 
     * }
     */

    private boolean isDouble(ConstantInfo c) {
        return c instanceof ConstantDoubleInfo;
    }

    private boolean isLong(ConstantInfo c) {
        return c instanceof ConstantLongInfo;
    }
}
