package tech.jianshuo.fiji.security;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhen.yu
 * Created on 2018-09-02
 */
public class StatelessToken implements AuthenticationToken {
    private static final long serialVersionUID = 7700322840225062716L;

    private long userId;
    private Map<String, Object> params;
    private String clientDigest;

    public static Builder newBuilder() {
        return new Builder();
    }

    private StatelessToken() {
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public static class Builder {
        private StatelessToken token;

        public Builder() {
            this.token = new StatelessToken();
        }

        public void setUserId(long userId) {
            this.token.userId = userId;
        }

        public void setParams(Map<String, Object> params) {
            this.token.params = params;
        }

        public void setClientDigest(String digest) {
            this.token.clientDigest = digest;
        }

        public StatelessToken build() {
            return this.token;
        }
    }
}
