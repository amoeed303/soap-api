

import javax.security.auth.callback.Callback
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.callback.UnsupportedCallbackException
import org.apache.wss4j.common.ext.WSPasswordCallback

class UsernameTokenCallBackHandler implements CallbackHandler {

    @Override
    void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        if (pc.getIdentifier() == "admin") {
            pc.setPassword("pwd123");
        }
    }
}
