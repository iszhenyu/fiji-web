package tech.jianshuo.fiji.core.model.page;

import java.io.Serializable;
import java.util.List;

import tech.jianshuo.fiji.common.util.CollectionUtils;

/**
 * @author zhen.yu
 * @since 2017/8/11
 */
public class Pagination<T> extends SimplePage implements Serializable {
    private static final long serialVersionUID = 3744434893536458346L;
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Pagination() {
    }

    public Pagination(List<T> items) {
        this.items = items;
        if (CollectionUtils.isNotEmpty(items)) {
            this.totalCount = items.size();
        }
    }

    public Pagination(List<T> items, int pageNo, int pageSize) {
        this(items);
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private Pagination<T> pagination;

        Builder() {
            pagination = new Pagination<>();
        }

        public Builder<T> items(List<T> items) {
            pagination.setItems(items);
            return this;
        }

        public Builder<T> totalCount(int count) {
            pagination.setTotalCount(count);
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            pagination.setPageSize(pageSize);
            return this;
        }

        public Builder<T> pageNo(int pageNo) {
            pagination.setPageNo(pageNo);
            return this;
        }

        public Pagination<T> build() {
            return pagination;
        }
    }
}
