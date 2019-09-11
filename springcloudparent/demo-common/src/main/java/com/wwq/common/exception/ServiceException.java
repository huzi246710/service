package com.wwq.common.exception;

/**
 * sevice异常信息
 * @author wenzy
 * @since 2019/07/15
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -1281585440323438806L;

    private Object[] args;

    /**
     * 构造器
     */
    public ServiceException() {
        super();
    }

    /**
     * 构造器
     *
     * @param message 异常信息
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 构造器
     *
     * @param t 异常种类
     */
    public ServiceException(Throwable t) {
        super(t);
    }

    /**
     * 构造器
     *
     * @param message 异常信息
     * @param args    属性
     */
    public ServiceException(String message, Object[] args) {
        super(message);
        this.setArgs(args);
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

}
