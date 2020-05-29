package com.violet.collection.updater;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p>集合更新器</p>
 *
 * @author xlp
 * @date 2020/5/4 上午10:07
 * @since 1.0.0
 */
public class CollectionUpdater<E,I> {

    private Collection<E> elements;

    private Function<E, I> identifierCollect;

    /**
     * 通过构造函数传递需要被处理的元素集合。
     * @param elements 被处理的元素集合。
     */
    protected CollectionUpdater(Collection<E> elements) {
        this.elements = elements;
    }

    /**
     * 通过构造函数传递需要被处理的元素集合，以及元素标识符收集器。
     * @param elements 被处理的元素集合。
     * @param identifierCollect 元素标识符收集器。
     */
    protected CollectionUpdater(Collection<E> elements,Function<E, I> identifierCollect) {
        this.elements = elements;
        this.identifierCollect = identifierCollect;
    }

    /**
     * 计算新增元素元素（标识符）。
     * <p>
     * 例如：
     * <li>旧集合元素：1 - 2 - 3 - 4</li>
     * <li>新集合元素：--------3 - 4 - 5</li>
     * <li>新增的元素：5</li>
     * </p>
     * @param elementIdentifiers 集合元素表标识符。
     * @return 新增标识符
     */
    public final Collection<I> calculateNewElementIdentifier(Collection<I> elementIdentifiers) {
        Collection<I> newElementIdentifiers = new ArrayList<>(elementIdentifiers);
        Collection<I> oldElementIdentifiers = collectElementIdentifier();
        newElementIdentifiers.removeIf(oldElementIdentifiers::contains);
        return newElementIdentifiers;
    }

    /**
     * 计算集合中需要被移除的元素。
     * <p>
     * 例如：
     * <li>旧集合元素：1 - 2 - 3 - 4</li>
     * <li>新集合元素：--------3 - 4 - 5</li>
     * <li>被移除元素：1 - 2</li>
     * </p>
     * @param elementIdentifiers 元素标识符集合。
     * @return 被处理的集合中-在删除之后的元素标识符集合。
     */
    public final Collection<I> calculateRemoveElementIdentifier(Collection<I> elementIdentifiers) {
        Collection<I> removeElementIdentifiers = new ArrayList<>(elementIdentifiers);
        Collection<I> oldElementIdentifiers = collectElementIdentifier();
        oldElementIdentifiers.removeIf(removeElementIdentifiers::contains);
        return oldElementIdentifiers;
    }

    /**
     * 移除指定标识符的元素，如果旧集合中元素的标识符包含在指定标识符集合中，则将其移除。
     * <p>
     * 例如：
     * <li>旧集合元素：element(1) - element(2) - element(3) - element(4)</li>
     * <li>标识符集合：1 - 2</li>
     * <li>移除剩余元素：element(3) - element(4)</li>
     * </p>
     * @param elementIdentifier 标识符集合
     */
    public final void removeIfElements(Collection<I> elementIdentifier){
        this.elements.removeIf(element -> elementIdentifier.contains(obtainElementIdentifier(element)));
    }


    /**
     * 更新集合。从旧集合中移除被移除的元素，返回新的元素标识符集合。
     * @param elementIdentifiers 元素标识符集合。
     * @return 新的元素标识符集合。
     */
    public final Collection<I> modifyCollections(Collection<I> elementIdentifiers){
        //计算新增对象
        Collection<I> newElementIdentifier = calculateNewElementIdentifier(elementIdentifiers);
        //计算移除对象
        Collection<I> removeElementIdentifier = calculateRemoveElementIdentifier(elementIdentifiers);
        //移除对象
        if (!removeElementIdentifier.isEmpty()) {
            removeIfElements(removeElementIdentifier);
        }
        //返回新增对象
        return newElementIdentifier;
    }

    /**
     * 向被处理集合中添加新的元素。
     * @param elements 添加的元素。
     */
    public final void addElements(Collection<E> elements){
        this.elements.addAll(elements);
    }

    /**
     * 向被处理集合中添加新的元素。这里被添加的新的元素是计算出的新增的元素的标识符。
     * 将通过Function和新增的元素标识符获取新增元素。
     * @param elementIdentifiers 新增的元素标识符集合。
     * @param elements 提供新增的元素的标识符的元素。
     */
    public final void addElements(Collection<I> elementIdentifiers, Function<Collection<I>,Collection<E>> elements){
        Collection<I> newElementIdentifier = modifyCollections(elementIdentifiers);
        if (!newElementIdentifier.isEmpty()) {
            this.addElements(elements.apply(newElementIdentifier));
        }
    }

    /**
     * 收集元素集合的标识符。
     * @return 元素集合的标识符。
     */
    public final Collection<I> collectElementIdentifier() {
        return elements.stream()
                .map(this::obtainElementIdentifier)
                .collect(()->new ArrayList<>(elements.size()),ArrayList::add,ArrayList::addAll);
    }

    /**
     * 获取指定元素的标识符。
     * @param element 元素。
     * @return 元素的标识符。
     */
    protected I obtainElementIdentifier(E element) {
        if (Objects.isNull(identifierCollect)) {
            throw new IllegalStateException("必须设置标识符收集器，或者重写obtainElementIdentifier(E)函数");
        }
        return identifierCollect.apply(element);
    }

    /**
     * 获取指定类型的集合更新器。
     * @param elements 元素集合。
     * @param collectionUpdaterClass 集合更新器类型。
     * @param <E> 元素类型。
     * @param <I> 标识符类型。
     * @return 集合更新器。
     */
    @SuppressWarnings("all")
    public static <E,I> CollectionUpdater<E,I> getInstance(
            Collection<E> elements,Class<? extends CollectionUpdater<E,I>> collectionUpdaterClass)  {
        Objects.requireNonNull(elements,"The elements must not be null");
        Objects.requireNonNull(collectionUpdaterClass,"The collection updater type must not be null");
        try {
            Constructor<?> constructor = collectionUpdaterClass.getConstructor(Collection.class);
            return (CollectionUpdater<E, I>) constructor.newInstance(elements);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取集合更新器。通过参数传递元素标识符收集器，而非使用子类重写的元素标识符收集方法。
     * @param elements 元素集合。
     * @param identifierCollect 元素标识符收集器。
     * @param <E> 元素类型。
     * @param <I> 标识符类型。
     * @return 集合更新器。
     */
    public static <E,I> CollectionUpdater<E,I> getInstance(
            Collection<E> elements,Function<E, I> identifierCollect)  {
        Objects.requireNonNull(elements,"The elements must not be null");
        Objects.requireNonNull(identifierCollect,"The identifier collect must not be null");
        return new CollectionUpdater<>(elements,identifierCollect);
    }

}
