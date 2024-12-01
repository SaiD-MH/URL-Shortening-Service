package web.URLShorternService.thirdParty;

import org.springframework.context.annotation.Configuration;

import java.nio.ByteBuffer;
import java.util.UUID;

@Configuration
public class HashValueGenerator {

    private final char[] characterSet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    public String generateHashValue() {


        return convertToBase62(convertUUIDToByteArray(UUID.randomUUID()));

    }


    private byte[] convertUUIDToByteArray(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }


    private String convertToBase62(byte[] bytes) {

        StringBuilder base62 = new StringBuilder();
        for (byte b : bytes) {

            long unsigned = b & 0xFF;

            base62.append(characterSet[(int) (unsigned % 62)]);

        }

        return base62.toString();

    }

}
