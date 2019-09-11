package com.wwq.common.http;

/**
 * 异常返回信息（msg）
 *
 * @author wenzy
 * @since 2019/06/06
 */
public enum RespCode {

    success,
    error_db_conn("db-conn-error: %s"),     //数据库连接失败
    error_input_pkg("input-pkg-error"),     //输入报文格式不合法
    error_input_data("input-data-error: %s"),     //输入参数不合法
    error_db_select("db-select-error: %s"),      //数据库查询失败
    error_db_insert("db-insert-error: %s"),      //数据库insert失败
    error_db_update("db-update-error: %s"),      //数据库update失败
    error_db_delete("db-delete-error: %s"),      //数据库delete失败
    error_file_read("file-read-error: %s"),      //文件读取失败
    error_service("service-error: %s"),      //service异常
    error_system_unknown("system-unknown-error: %s");   //系统未知异常  (设计文档不要求)


    private boolean ok;
    private String msgFormat;

    RespCode() {
        this(true, "");
    }

    RespCode(boolean ok) {
        this(ok, ok ? "" : "failed");
    }

    RespCode(String msgFormat) {
        this(false, msgFormat);
    }

    RespCode(boolean ok, String msgFormat) {
        this.ok = ok;

        if (msgFormat == null) {
            msgFormat = "";
        }
        this.msgFormat = msgFormat;
    }

    public String getMsgFormat() {
        return this.msgFormat;
    }

    public boolean isOk() {
        return ok;
    }

}


