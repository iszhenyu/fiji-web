package tech.jianshuo.component.datasource.route;

/**
 * @author zhenyu
 * Created on 2018-10-25
 */
public enum RoutingPolicy {
    POLLING("polling"),         // 轮询
    TAIL_NUMBER("tail-number"); // 取模

    private final String policyName;

    RoutingPolicy(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyName() {
        return policyName;
    }
}
