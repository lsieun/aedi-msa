package lsieun.aedi.b;

import java.io.IOException;

import com.jetbrains.ls.responses.ObtainTicketResponse;
import com.jetbrains.ls.responses.ResponseCode;

public class B_ObtainTicket {
    public static ObtainTicketResponse a(String var0, String var1, char var2, int var3, char var4, int var5, int var6, boolean var7) throws IOException {

        System.out.println("Hello");
        new Exception().printStackTrace();

        ObtainTicketResponse response = new ObtainTicketResponse();
        long timestamp = System.currentTimeMillis();
        response.setResponseCode(ResponseCode.OK);

        response.setTicketId("1");
        String username = System.getProperty("user.name");
        response.setTicketProperties("licensee=" + username + "\tlicenseType=0");
        response.setMessage("DIY ObtainTicketResponse Message");

        response.setSalt(timestamp);
        response.setConfirmationStamp(String.valueOf(timestamp));

        response.setServerUid("DIY_server_uid");
        response.setServerLease("DIY_server_lease");
        response.setLeaseSignature("DIY_lease_signature");
        response.setSignature("DIY_signature");

        long period = 3600 * 60 * 24 * 365 * 10;
        response.setProlongationPeriod(period);
        response.setValidationPeriod(period);
        response.setValidationDeadlinePeriod(period);
        return response;
    }
}
