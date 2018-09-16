package tech.jianshuo.fiji.core.model.page;

/**
 * @author zhen.yu
 * @since 2017/8/11
 */
public class SimplePage implements Pageable {
    private static final long serialVersionUID = 8520867783922615755L;
    public static final int DEFAULT_PAGE_SIZE = 10;

    protected int totalCount = 0;
    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected int pageNo = 1;

    public SimplePage() {
    }

    public SimplePage(int totalCount, int pageNo) {
        this(totalCount, pageNo, DEFAULT_PAGE_SIZE);
    }

    public SimplePage(int totalCount, int pageNo, int pageSize) {
        if (totalCount < 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
        if (pageNo > 0) {
            this.pageNo = pageNo;
        }
        if ((this.pageNo - 1) * this.pageSize >= totalCount) {
            this.pageNo = totalCount / pageSize;
            if (this.pageNo == 0) {
                this.pageNo = 1;
            }
        }
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void adjustPage() {
        if (totalCount <= 0) {
            totalCount = 0;
        }
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if ((pageNo - 1) * pageSize >= totalCount) {
            pageNo = totalCount / pageSize;
        }
    }

    @Override
    public int getTotalCount() {
        return this.totalCount;
    }

    @Override
    public int getTotalPage() {
        if (totalCount < 0) {
            return -1;
        }
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0) {
            totalPage++;
        }
        return totalPage;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public int getPageNo() {
        return this.pageNo;
    }

    @Override
    public boolean isFirstPage() {
        if (totalCount < 0) {
            return false;
        }
        return pageNo <= 1;
    }

    @Override
    public boolean isLastPage() {
        if (totalCount < 0) {
            return false;
        }
        return pageNo >= getTotalPage();
    }

    @Override
    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    @Override
    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }
}
