package tech.jianshuo.fiji.admin.util;

import java.util.Collections;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tech.jianshuo.fiji.core.model.page.Pagination;
import tech.jianshuo.fiji.core.model.page.SimplePage;

/**
 * @author zhen.yu
 * Created on 2018-09-16
 */
public class Paginations {

    /**
     * 只有首页需要count查询
     */
    public static <E> Page<E> startPage(int pageNo, int pageSize) {
        int no = validPageNo(pageNo);
        int size = validPageSize(pageSize);
        if (no == 1) {
            return PageHelper.startPage(no, size);
        }
        return PageHelper.startPage(no, size, false);
    }

    private static int validPageNo(int pageNo) {
        return pageNo <= 0 ? 1 : pageNo;
    }

    private static int validPageSize(int pageSize) {
        return pageSize <= 0 ? SimplePage.DEFAULT_PAGE_SIZE : pageSize;
    }

    public static <T> Pagination<T> emptyPagination() {
        return emptyPagination(SimplePage.DEFAULT_PAGE_SIZE);
    }

    public static <T> Pagination<T> emptyPagination(int pageSize) {
        Pagination.Builder<T> builder = Pagination.newBuilder();
        return builder.pageSize(pageSize)
                .items(Collections.emptyList())
                .build();
    }

    public static <T> Pagination<T> fromPage(Page<T> page) {
        Pagination.Builder<T> builder = Pagination.newBuilder();
        return builder.items(page)
                .totalCount((int) page.getTotal())
                .pageSize(page.getPageSize())
                .pageNo(page.getPageNum())
                .build();
    }

}
