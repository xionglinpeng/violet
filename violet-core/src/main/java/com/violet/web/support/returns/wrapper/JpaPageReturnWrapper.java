package com.violet.web.support.returns.wrapper;

import org.springframework.data.domain.Page;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/21 下午5:32
 * @since 1.0.0
 */
public class JpaPageReturnWrapper<E> extends BasePageReturnWrapper<E> {

    public JpaPageReturnWrapper(Object data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void pageOf(Object data) {
        if (!(data instanceof Page)) {
            throw new PageWrapperTypeException(
                    String.format("%s cannot be converted to %s.",
                            data.getClass().getName(), Page.class.getName()));
        }
        Page<E> page = (Page<E>) data;
        setPageIndex(page.getNumber() + 1);
        setPageSize(page.getSize());
        setTotalPage(page.getTotalPages());
        setTotalRecord(page.getTotalElements());
        setData(page.getContent());
    }
}
