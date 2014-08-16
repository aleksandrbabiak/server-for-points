package protobuff;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import net_handlers.*;


public class ProtoFactory {

    public static IncomingMessageHandler createHandler(byte data[], ProtoType type) throws InvalidProtocolBufferException {

        switch (type) {
            case SWONTGAME:
                return new SWontGameHandler(data);
            case SWONTPLAY:
                return new SWontPlayHandler(data);
            case SSTARTGAME:
                return new SStartGameHandler(data);
            case SONEMOVE:
                return new SOneMoveHandler(data);
            case SWINGAME:
                return new SWinGameHandler(data);
            case SNEWGAME:
                return new SNewGameHandler(data);
            case SFINISHGAME:
                return new SFinishGameHandler(data);
            case SSENDMESSAGE:
                return new SSendMessageHandler(data);

            default:
                System.out.println("GET BED PACKET " + type);
                return null;
        }


    }


}
