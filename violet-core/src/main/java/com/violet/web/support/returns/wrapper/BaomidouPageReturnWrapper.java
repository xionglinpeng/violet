package com.violet.web.support.returns.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/7/15 下午2:45
 * @since 1.0.0
 */
public class BaomidouPageReturnWrapper<E> extends BasePageReturnWrapper<E> {

    public BaomidouPageReturnWrapper(Object data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void pageOf(Object data) {
        if (!(data instanceof IPage)) {
            throw new PageWrapperTypeException(
                    String.format("%s cannot be converted to %s.",
                            data.getClass().getName(), IPage.class.getName()));
        }
        IPage<E> page = (IPage<E>) data;
        setPageIndex(page.getCurrent());
        setPageSize(page.getSize());
        setTotalPage(page.getPages());
        setTotalRecord(page.getTotal());
        setData(page.getRecords());
    }
}
