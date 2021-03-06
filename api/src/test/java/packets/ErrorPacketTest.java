package packets;

import org.junit.Test;
import utils.Constants;

import static org.junit.Assert.*;

public class ErrorPacketTest {

    @Test
    public void testParseWithNoMessage() throws InvalidPacketFormatException {
        byte[] data = new byte[]{
                Constants.ERROR_CODE.UNKNOWNPUBLICKEY, -1, -1, -1, -1
        };

        ErrorPacket packet = ErrorPacket.parse(data);
        assertFalse(packet.hasMessage());
        assertEquals(Constants.ERROR_CODE.UNKNOWNPUBLICKEY, packet.getErrorCode());
    }

    @Test
    public void testParseWithSmallMessage() throws InvalidPacketFormatException {
        byte[] data = new byte[]{
                Constants.ERROR_CODE.OTHER, 0, 0, 0, 6, 'o', 's', 'w', 'e', 'g', 'o'
        };

        ErrorPacket packet = ErrorPacket.parse(data);
        assertTrue(packet.hasMessage());
        assertEquals(Constants.ERROR_CODE.OTHER, packet.getErrorCode());
        assertEquals("oswego", packet.getMessage());
    }

    @Test
    public void testSerializeWithNoMessage() {
        ErrorPacket packet = new ErrorPacket(Constants.ERROR_CODE.JOINERROR);
        assertArrayEquals(new byte[]{
                Constants.ERROR_CODE.JOINERROR, -1, -1, -1, -1
        }, packet.serialize());
    }

    @Test
    public void testSerializeWithSmallMessage() {
        ErrorPacket packet = new ErrorPacket(Constants.ERROR_CODE.CREATEERROR, "oswego");
        assertArrayEquals(new byte[]{
                Constants.ERROR_CODE.CREATEERROR, 0, 0, 0, 6, 'o', 's', 'w', 'e', 'g', 'o'
        }, packet.serialize());
    }
}
