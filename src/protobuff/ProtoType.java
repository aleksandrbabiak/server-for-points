package protobuff;


import com.my_point_game.MyPointGame;

public enum ProtoType {

    SUNKNOWN((byte) 0x00),
    SWONTGAME((byte) 0x01),
    SWONTPLAY((byte) 0x03),
    SSTARTGAME((byte) 0x04),
    SONEMOVE((byte) 0x05),
    SWINGAME((byte) 0x06),
    SNEWGAME((byte) 0x07),
    SFINISHGAME((byte) 0x08),
    SSENDMESSAGE((byte) 0x09),



    CUNKNOWN((byte) 0x00),
    CWONTGAME((byte) 0x01, MyPointGame.CWontGame.class),
    CUPDATEONLINEPLAYER((byte) 0x02, MyPointGame.CUpdateOnlinePlayer.class),
    CWONTPLAY((byte) 0x03, MyPointGame.CWontPlay.class ),
    CSTARTGAME((byte) 0x04, MyPointGame.CStartGame.class),
    CONEMOVE((byte) 0x05, MyPointGame.COneMove.class),
    CWINGAME((byte) 0x06, MyPointGame.CWinGame.class),
    CNEWGAME((byte) 0x07, MyPointGame.CNewGame.class),
    CFINISHGAME((byte) 0x08, MyPointGame.CFinishGame.class),
    CSENDMESSAGE((byte) 0x09, MyPointGame.CSendMessage.class);

    private final byte b;
    private Class protoClass;

    private ProtoType(byte b) {
        this.b = b;
    }

    private ProtoType(byte b, Class protoClass) {
        this.b = b;
        this.protoClass = protoClass;
    }


    public static ProtoType fromByte(byte b) {
        for (ProtoType code : values()) {
            if (code.b == b) {
                return code;
            }
        }
        return CUNKNOWN;
    }

    public static ProtoType fromClass(Class c) {
        for (ProtoType type : values()) {
            if (type.protoClass != null && type.protoClass.equals(c)) {
                return type;
            }
        }
        return CUNKNOWN;
    }

    public byte getByteValue() {
        return b;
    }
}
